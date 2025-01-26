package com.cms.service;

import com.cms.entity.Employee;
import com.cms.exception.ResourceNotFound;
import com.cms.payload.EmployeeDto;
import com.cms.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    public EmployeeDto addEmployee(EmployeeDto dto) {
        Employee employee = mapToEntity(dto);
        Employee emp = employeeRepository.save(employee);
        EmployeeDto employeeDto = mapToDto(emp);
       // employeeDto.setDate(new Date());
        return employeeDto;
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto dto) {
        Employee employee = mapToEntity(dto);
        employee.setId(id);
        Employee updateEmployee = employeeRepository.save(employee);
        EmployeeDto employeeDto = mapToDto(updateEmployee);
        return employeeDto;


//        Optional<Employee> opEmp = employeeRepository.findById(id);
//            Employee employee = opEmp.get();
//            employee.setName(dto.getName());
//            employee.setEmailId(dto.getEmailId());
//            employee.setMobile(dto.getMobile());
//            employeeRepository.save(employee);

    }

    public void getAllEmployees() {
    }

    public List<EmployeeDto> getEmployees(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable page = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Employee> all = employeeRepository.findAll(page);
        List<Employee> employees = all.getContent();
        List<EmployeeDto> dto =  employees.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return dto;
    }

    EmployeeDto mapToDto(Employee employee) {
       EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);
//        EmployeeDto dto = new EmployeeDto();
//        dto.setId(employee.getId());
//        dto.setName(employee.getName());
//        dto.setEmailId(employee.getEmailId());
//        dto.setMobile(employee.getMobile());
        return dto;
    }
    Employee mapToEntity(EmployeeDto dto){
        Employee emp = modelMapper.map(dto, Employee.class);
//        Employee emp = new Employee();
//        emp.setId(dto.getId());
//        emp.setName(dto.getName());
//        emp.setEmailId(dto.getEmailId());
//        emp.setMobile(dto.getMobile());
        return emp;
    }

    public EmployeeDto getEmployeeById(long empId) {
//        Optional<Employee> opEmp = employeeRepository.findById(empId);
//        Employee employee = opEmp.get();
//        return  mapToDto(employee);
        Employee employee = employeeRepository.findById(empId).orElseThrow(
                () ->new ResourceNotFound("Record not found with id: " + empId)
        );
        EmployeeDto dto = mapToDto(employee);
        return dto;
    }
}


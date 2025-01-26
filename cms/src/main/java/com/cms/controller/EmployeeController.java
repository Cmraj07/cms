package com.cms.controller;

// ctrl+shift+F ----> to search url in the project
// ctrl+F ----> search keyword

import com.cms.payload.EmployeeDto;
import com.cms.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee") // this part matches with class
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")  // rest half matches with this method
    public ResponseEntity<?> addEmployee(
          @Valid @RequestBody EmployeeDto dto,
          BindingResult result
    ) {
//        System.out.println(employee.getName()  );
//        System.out.println(employee.getEmailId()   );
//        System.out.println(employee.getMobile()  );

        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        EmployeeDto employeeDto = employeeService.addEmployee(dto);
        return new ResponseEntity<>( employeeDto, HttpStatus.CREATED);    }

    @DeleteMapping
    public ResponseEntity<String> deleteEmployee(
            @RequestParam Long id
    ) {
        // implement delete logic
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployee(
            @RequestParam Long id,
            @RequestBody EmployeeDto dto
    ) {
        // implement delete logic
        EmployeeDto employeeDto = employeeService.updateEmployee(id, dto);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }
// http://localhost:8080/api/v1/employee/employeeId/{empId}
    @GetMapping("/employeeId/{empId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(
    @PathVariable long empId
    ) {
//        List<EmployeeDto> employeesDto = employeeService.getEmployees();
//        return new ResponseEntity<>(employeesDto, HttpStatus.OK);
      EmployeeDto dto = employeeService.getEmployeeById(empId)  ;
      return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    // http://localhost:8080/api/v1/employee?pageSize=3&pageNo=1&sortBy=emailId&sortDir=desc
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees(
            @RequestParam(name="pageSize",required = false, defaultValue = "5") int pageSize,
            @RequestParam(name="pageNo",required = false, defaultValue = "0") int pageNo,
            @RequestParam(name="sortBy",required = false, defaultValue = "name") String sortBy,
            @RequestParam(name="sortDir",required = false, defaultValue = "asc") String sortDir
    ) {

        List<EmployeeDto> employeesDto = employeeService.getEmployees(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(employeesDto, HttpStatus.OK);


    }
}
package com.cms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "bus_stop")
public class BusStop {  // many
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne  //here many belongs to BusStop(class) and one belong to bus
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @ManyToOne //here many belongs to BusStop(class) and one belong to stop
    @JoinColumn(name = "stop_id")
    private Stop stop;

    @Column(name = "order_number", nullable = false)
    private Integer orderNumber;

    @Column(name = "departure_time", nullable = false)
    private LocalTime departureTime;

}
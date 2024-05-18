package com.HotelsBianca.hotels.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "Hotel")
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LATITUDE")
    private Float latitude;

    @Column(name = "LONGITUDE")
    private Float longitude;

    @Column(name = "FEEDBACK")
    private String feedback;

    //private List<Feedback> feedback;

}

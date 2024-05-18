package com.HotelsBianca.hotels.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Room")
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @Column(name = "type")
    private Integer type;

    @Column(name = "price")
    private Float price;

    @Column(name = "isAvailable")
    private Boolean isAvailable;

    @ManyToOne
    @JoinColumn (name = "ht_fk", nullable = false)
    private Hotel hotel;
}

package com.HotelsBianca.hotels.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "Reservation")
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @Column(name = "hotelId")
    private Integer hotelId;

    @Column(name = "roomId")
    private Integer roomId;

    @Column(name = "checkInDate")

    private LocalDateTime checkInDate;

    @ManyToOne
    @JoinColumn (name = "us_fk", nullable = false)
    private User user;
}

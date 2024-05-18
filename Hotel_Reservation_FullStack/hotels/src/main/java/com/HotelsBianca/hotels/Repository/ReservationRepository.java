package com.HotelsBianca.hotels.Repository;

import com.HotelsBianca.hotels.Entity.Hotel;
import com.HotelsBianca.hotels.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Repository;

@EnableJpaAuditing
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}

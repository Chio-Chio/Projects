package com.HotelsBianca.hotels.Repository;

import com.HotelsBianca.hotels.Entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Repository;

@EnableJpaAuditing
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}

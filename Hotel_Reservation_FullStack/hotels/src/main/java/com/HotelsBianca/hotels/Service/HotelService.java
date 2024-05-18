package com.HotelsBianca.hotels.Service;

import com.HotelsBianca.hotels.Entity.Hotel;
import com.HotelsBianca.hotels.Exception.ResourceNotFoundException;
import com.HotelsBianca.hotels.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public Hotel saveDetails(Hotel hotel){
        return hotelRepository.save(hotel);
    }

    public Hotel updateHotel(Integer id, Hotel hotel){
        Hotel updateHotel = hotelRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel does not exist: " + id));
        updateHotel.setName(hotel.getName());
        updateHotel.setLongitude(hotel.getLongitude());
        updateHotel.setLatitude(hotel.getLatitude());

        Hotel savedHotel = hotelRepository.save(updateHotel);
        return savedHotel;
    }

    public void deleteHotel(int id){
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel does not exits with id: "+ id));

        hotelRepository.delete(hotel);
    }

    public List<Hotel> getAllHotels(){
        return hotelRepository.findAll()
                .stream()
                .collect(Collectors.toList());
    }

    public Hotel findById(int id){
        Hotel hotel= hotelRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Hotel does not exits with id: "+ id));
        return hotel;
    }
}

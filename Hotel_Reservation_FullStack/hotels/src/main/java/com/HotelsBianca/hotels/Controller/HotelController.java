package com.HotelsBianca.hotels.Controller;

import com.HotelsBianca.hotels.Entity.Hotel;
import com.HotelsBianca.hotels.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @PostMapping("")
    public Hotel postHotel(@RequestBody Hotel hotel){
        return hotelService.saveDetails(hotel);
    }

    @GetMapping("")
    public List<Hotel> getAllHotels(){
        return hotelService.getAllHotels();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Hotel> getHotelByID(@PathVariable int id) throws Exception{
        Hotel hotel = hotelService.findById(id);
        return ResponseEntity.ok(hotel);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable int id, @RequestBody Hotel hotel) throws Exception {
        Hotel hotel1 = hotelService.updateHotel(id, hotel);
        return ResponseEntity.ok(hotel1);

    }
}

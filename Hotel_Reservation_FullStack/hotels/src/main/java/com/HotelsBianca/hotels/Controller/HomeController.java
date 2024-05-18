//package com.HotelsBianca.hotels.Controller;
//
//import com.HotelsBianca.hotels.Entity.Hotel;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.List;
//
//@Controller
//public class HomeController {
//    private List<Hotel> hotels;
//    private HotelController hotelController = new HotelController();
//
//    public HomeController(){
//        hotels = hotelController.getAllHotels();
//    }
//
//    @GetMapping("/dynamic")
//    public String dynamic(Model model){
//        model.addAttribute("hotels", this.hotels);
//        return "dynamic";
//    }
//
//    @GetMapping("/")
//    public String home(){
//        return "home";
//    }
//}

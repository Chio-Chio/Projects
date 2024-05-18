//package com.HotelsBianca.hotels.Client;
//
//import com.HotelsBianca.hotels.Entity.Hotel;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestClient;
//
//import java.util.List;
//
//@Component
//public class UserClient {
//    private final RestClient restClient;
//
//    public UserClient(RestClient.Builder builder){
//        this.restClient = builder
//                .baseUrl("https://hotels.com/")
//                .build();
//    }
//
//    public List<Hotel> findAll(){
//        return restClient.get()
//                .uri("/hotel/get")
//                .retrieve()
//                .body(new ParameterizedTypeReference<List<Hotel>>() {
//                });
//    }
//
//
//    public Hotel findById(Integer id){
//        return restClient.get()
//                .uri("/hotel/getById/{id}", id)
//                .retrieve()
//                .body(Hotel.class);
//    }
//}

package com.HotelsBianca.hotels;


import com.HotelsBianca.hotels.Controller.UserController;
import com.HotelsBianca.hotels.Entity.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class HotelsApplication {


	public static void main(String[] args) {
		SpringApplication.run(HotelsApplication.class, args);
	}


//	@Bean
//	CommandLineRunner runner(UserClient client){
//		return args -> {
//			List<Hotel> hotels =
//			client.findAll();
//			System.out.println(" aaaaa "+hotels);
//		};
//	}
}

package model;


import java.time.LocalDateTime;

public record Bill(int orderId, String clientName, String productName, int productPrice, int quantity,
                   LocalDateTime timestamp) {

}

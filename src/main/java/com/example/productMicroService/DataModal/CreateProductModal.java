package com.example.productMicroService.DataModal;


import lombok.Data;

@Data
public class CreateProductModal {

    String title;
    double price;
    Integer quantity;
}

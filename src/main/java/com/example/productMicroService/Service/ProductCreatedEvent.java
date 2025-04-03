package com.example.productMicroService.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ProductCreatedEvent {
    String  productId;
    String title;
    double price;
    Integer quantity;
}

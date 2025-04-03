package com.example.productMicroService.Service;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorMessage {

    private Date timeStamp;
    private String message;
    private  String details;
}

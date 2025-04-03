package com.example.productMicroService.Service;


import com.example.productMicroService.DataModal.CreateProductModal;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;


public interface ProductService {
    public String createProduct(CreateProductModal productModal) throws ExecutionException, InterruptedException;
}

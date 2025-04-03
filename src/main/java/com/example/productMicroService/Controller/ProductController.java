package com.example.productMicroService.Controller;

import com.example.productMicroService.DataModal.CreateProductModal;
import com.example.productMicroService.Service.ErrorMessage;
import com.example.productMicroService.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;
    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody CreateProductModal productModal)  {
        String id="";
        try{
            id=productService.createProduct(productModal);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorMessage(new Date(), e.getMessage(),"error in sending message to kafka"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }
}

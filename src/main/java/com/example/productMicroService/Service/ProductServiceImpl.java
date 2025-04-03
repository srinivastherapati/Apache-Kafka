package com.example.productMicroService.Service;

import com.example.productMicroService.DataModal.CreateProductModal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    KafkaTemplate<String,ProductCreatedEvent> kafkaTemplate;
    private final Logger LOGGER= LoggerFactory.getLogger(this.getClass());
    @Override
    public String createProduct(CreateProductModal productModal) throws ExecutionException, InterruptedException {
        String productId= UUID.randomUUID().toString();
        // Async communication
        ProductCreatedEvent productCreatedEvent= new ProductCreatedEvent(productId,productModal.getTitle(),productModal.getPrice(),productModal.getQuantity());
//       CompletableFuture<SendResult<String ,ProductCreatedEvent>> future= kafkaTemplate.send("product-created-events-topic",productId,productCreatedEvent);
//
//       future.whenComplete((result,exception)->{
//           if(exception!=null){
//               LOGGER.error("exception  when sending "+exception.getMessage());
//           }
//           else{
//               LOGGER.info("Message sent successfully "+result.getRecordMetadata());
//           }
//       });

       //to make this async communication sync we can use below to make wait until the response
        //future.join();

        //Sync communication
SendResult<String,ProductCreatedEvent> result=kafkaTemplate.send("product-created-events-topic",productId,productCreatedEvent).get();
LOGGER.info("Partition "+ result.getRecordMetadata().partition());
        LOGGER.info("Topic "+ result.getRecordMetadata().topic());
        LOGGER.info("OffSet "+ result.getRecordMetadata().offset());
        LOGGER.info("****** return product Id");
        return productId;
    }
}

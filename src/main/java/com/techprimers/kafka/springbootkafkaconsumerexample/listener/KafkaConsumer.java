package com.techprimers.kafka.springbootkafkaconsumerexample.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techprimers.kafka.springbootkafkaconsumerexample.Repository.ProductRepository;
import com.techprimers.kafka.springbootkafkaconsumerexample.model.Product;
import com.techprimers.kafka.springbootkafkaconsumerexample.model.es.ProductEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class KafkaConsumer {

    @Autowired
    private ProductRepository repository;

    @KafkaListener(topics = "Kafka_Example", groupId = "group_id",containerFactory = "kafkaListenerContainerFactory")
    public void consume(String message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("******  meassage received *******" + message);
        Product product =  objectMapper.readValue(message, Product.class);
        ProductEs productEs = objectMapper.readValue(message,ProductEs.class);
        System.out.println("******  saving in es *******" + productEs);
        repository.save(productEs);
        System.out.println("Consumed message: " + message);

        System.out.println("Consumed product: " + product);
    }


    @KafkaListener(topics = "Kafka_Example", groupId = "group_json",
            containerFactory = "userKafkaListenerFactory")
    public void consume1(Product product) {
        //System.out.println("***");
        System.out.println("Consumed JSON Message: " + product);

    }


}

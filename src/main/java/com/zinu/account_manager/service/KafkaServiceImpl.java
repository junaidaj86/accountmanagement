package com.zinu.account_manager.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.zinu.account_manager.DTO.UpdateDriverLocationRequest;

@Service
public class KafkaServiceImpl implements KafkaService{

    
    KafkaTemplate<String, UpdateDriverLocationRequest> kafkaTemplate;

    public KafkaServiceImpl( KafkaTemplate<String, UpdateDriverLocationRequest> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String topic, UpdateDriverLocationRequest paylaod) {
        kafkaTemplate.send(topic, paylaod);
    }
    
}

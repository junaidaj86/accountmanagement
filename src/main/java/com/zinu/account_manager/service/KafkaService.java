package com.zinu.account_manager.service;

import com.zinu.account_manager.DTO.UpdateDriverLocationRequest;

public interface KafkaService {
    public void send(String topic, UpdateDriverLocationRequest paylaod);
}

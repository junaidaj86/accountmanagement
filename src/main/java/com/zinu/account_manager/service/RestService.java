package com.zinu.account_manager.service;

import org.springframework.http.ResponseEntity;

public interface RestService {
    public <T> ResponseEntity<T> callSyncRestCall(String uri, Object paylaod, Class<T> responseType);
}

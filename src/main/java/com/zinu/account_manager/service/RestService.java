package com.zinu.account_manager.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.zinu.account_manager.model.Driver;

public interface RestService {
    public <T> ResponseEntity<T> callSyncRestCall(String uri, Object payload, Class<T> responseType);
    public  ResponseEntity<List<Driver>> callSyncRestGet(String uri) ;
}

package com.zinu.account_manager.service;

import org.springframework.http.ResponseEntity;

public interface RestService {
    public <T> ResponseEntity<T> callSyncRestCall(String serviceName, String uri, Object payload, Class<T> responseType);
}

package com.zinu.account_manager.service;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class RestServiceImpl implements RestService {

    @Override
    public <T> ResponseEntity<T> callSyncRestCall(String uri, Object paylaod, Class<T> responseType) {
        RestClient restClient = RestClient.create();
        ResponseEntity<T> response = restClient
                .post()
                .uri(uri)
                .body(paylaod)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(responseType);
        return response;
    }

}

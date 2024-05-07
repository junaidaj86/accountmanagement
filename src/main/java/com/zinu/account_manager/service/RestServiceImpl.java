package com.zinu.account_manager.service;


import java.util.List;

import org.apache.kafka.common.metrics.stats.Rate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.zinu.account_manager.model.Driver;

@Service
public class RestServiceImpl implements RestService {

    
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

    public  ResponseEntity<List<Driver>> callSyncRestGet(String uri) {
        RestClient restClient = RestClient.create();
        ResponseEntity<List<Driver>> response = restClient
                .get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<Driver>>() {});
        return response;
    }

   

  

}

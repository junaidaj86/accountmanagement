package com.zinu.account_manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Service
public class RestServiceImpl implements RestService {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    
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

    @Override
     public <T> ResponseEntity<T> callSyncRestCall(String serviceName, String uri, Object payload, Class<T> responseType) {
        // Get an instance of the specified service using LoadBalancerClient
        ServiceInstance serviceInstance = loadBalancerClient.choose(serviceName);

        // Build the URL using the service instance details
        String url = serviceInstance.getUri().toString() + uri;

        // Create RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Make the REST call
        ResponseEntity<T> response = restTemplate
                .postForEntity(url, payload, responseType);

        return response;
    }

}

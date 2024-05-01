package com.zinu.account_manager.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.zinu.account_manager.DTO.GeoHashRequest;
import com.zinu.account_manager.DTO.GeoHashResponse;

import jakarta.ws.rs.core.MediaType;

@FeignClient(name="geohash")
public interface DriverClient {
    @PostMapping(value="/geohash")
    public GeoHashResponse getGeoHash(GeoHashRequest request);
}

package com.zinu.account_manager.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.zinu.account_manager.DTO.GeoHashRequest;
import com.zinu.account_manager.DTO.GeoHashResponse;

@FeignClient(name = "geohash")
public interface DriverClient {
    @PostMapping(value = "/geohash")
    public GeoHashResponse getGeoHash(GeoHashRequest request);
}

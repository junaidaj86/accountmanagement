package com.zinu.account_manager.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.servlet.function.EntityResponse;

import com.zinu.account_manager.DTO.GeoHashRequest;
import com.zinu.account_manager.DTO.GeoHashResponse;
import com.zinu.account_manager.DTO.UpdateDriverLocationRequest;
import com.zinu.account_manager.configurations.ShardStrategy;
import com.zinu.account_manager.configurations.TenantContext;
import com.zinu.account_manager.model.Driver;
import com.zinu.account_manager.service.DriverService;
import com.zinu.account_manager.service.KafkaService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private DriverService driverService;

    public DriverController(DriverService driverService, KafkaService kafkaService) {
        this.driverService = driverService;
    }

    @PostMapping
    public ResponseEntity<Driver> createDriver(@RequestBody Driver driver) {
        UUID uuid = UUID.randomUUID();
        driver.setId(uuid.toString());
        int shardId = ShardStrategy.calculateShard(driver.getId());
        TenantContext.setCurrentTenant(shardId);
        driver.setShardId(shardId);
        Driver savedDriver = driverService.saveDriver(driver);
        return new ResponseEntity<>(savedDriver, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable String id) {
        Driver driver = driverService.getDriverById(id);
        if (driver != null) {
            return new ResponseEntity<>(driver, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Driver>> getAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriverById(@PathVariable String id) {
        driverService.deleteDriverById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Driver> updateDriver(@RequestBody Driver driverRequest) {
        Driver driver = driverService.updateDriver(driverRequest);
        if (driver != null) {
            return new ResponseEntity<>(driver, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/location")
    public ResponseEntity<Driver> updateDriverLocation(@RequestBody UpdateDriverLocationRequest request) {
        Driver driver = driverService.updateDriverLocation(request);
        if (driver != null) {
            return new ResponseEntity<>(driver, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

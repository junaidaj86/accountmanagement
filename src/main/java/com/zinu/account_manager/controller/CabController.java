package com.zinu.account_manager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zinu.account_manager.DTO.CabCreateRequest;
import com.zinu.account_manager.configurations.TenantContext;
import com.zinu.account_manager.model.Cab;
import com.zinu.account_manager.model.Driver;
import com.zinu.account_manager.service.CabService;
import com.zinu.account_manager.service.DriverService;

@RestController
@RequestMapping("/cab")
public class CabController {

    DriverService driverService;
    CabService cabService;

    public CabController(DriverService driverService, CabService cabService) {
        this.driverService = driverService;
        this.cabService = cabService;
    }

    @PostMapping
    public ResponseEntity<String> registerCab(@RequestBody CabCreateRequest cabCreateRequest){
        cabService.saveCab(cabCreateRequest);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/driver/{driverId}")
    public List<Cab> getCabsByDriverId(@PathVariable String driverId) {
        Driver driver = driverService.getDriverById(driverId);
        TenantContext.setCurrentTenant(driver.getShardId());
        return cabService.getCabsByDriverId(driverId);
    }
}

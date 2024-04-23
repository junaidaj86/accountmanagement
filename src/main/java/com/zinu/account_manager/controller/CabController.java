package com.zinu.account_manager.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zinu.account_manager.DTO.CabCreateRequest;
import com.zinu.account_manager.exception.DriverNotFoundException;
import com.zinu.account_manager.model.Cab;
import com.zinu.account_manager.model.CarType;
import com.zinu.account_manager.model.Driver;
import com.zinu.account_manager.service.CabService;
import com.zinu.account_manager.service.DriverService;

@RestController
@RequestMapping("/Cab")
public class CabController {

    DriverService driverService;
    CabService cabService;

    public CabController(DriverService driverService, CabService cabService) {
        this.driverService = driverService;
        this.cabService = cabService;
    }

    @PostMapping
    public ResponseEntity<String> registerCab(@RequestBody CabCreateRequest cabCreateRequest){
        Driver driver = driverService.getDriverById(cabCreateRequest.driverId());
        if(driver == null){
            String errMessage = "Driver with id " + cabCreateRequest.driverId() + " not found";
            throw new DriverNotFoundException(errMessage);
        }
        Cab cab = new Cab();


    switch (cabCreateRequest.carType().toUpperCase()) {
        case "SEDAN":
            cab.setCarType(CarType.SEDAN);
            break;
        case "SUV":
            cab.setCarType(CarType.SUV);
            break;
        case "TRUCK":
            cab.setCarType(CarType.TRUCK);
            break;
        case "CONVERTIBLE":
            cab.setCarType(CarType.CONVERTIBLE);
            break;
        case "VAN":
            cab.setCarType(CarType.VAN);
            break;
        default:
            break;
    }
        cab.setDriver(driver);
        cab.setRegistrationNumber(cabCreateRequest.carRegistrationNumber());
        cabService.saveCab(cab);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}

package com.zinu.account_manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zinu.account_manager.DTO.UpdateDriverLocationRequest;
import com.zinu.account_manager.model.Driver;
import com.zinu.account_manager.service.DriverService;
import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    public ResponseEntity<Driver> createDriver(@RequestBody Driver driver) {
        Driver savedDriver = driverService.saveDriver(driver);
        return new ResponseEntity<>(savedDriver, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Long id) {
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
    public ResponseEntity<Void> deleteDriverById(@PathVariable Long id) {
        driverService.deleteDriverById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Driver> updateDriverLocation(@RequestBody UpdateDriverLocationRequest request) {
        Driver driver = driverService.getDriverById(request.driverId());

        if (driver != null) {
            driver.setCurrentLatitude(request.latitude());
            driver.setCurrentLongitude(request.longitude());
            driverService.saveDriver(driver);
            return new ResponseEntity<>(driver, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

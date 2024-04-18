package com.zinu.account_manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zinu.account_manager.DTO.UpdateDriverLocationRequest;
import com.zinu.account_manager.model.Driver;
import com.zinu.account_manager.repository.DriverRepository;
import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public Driver saveDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public Driver getDriverById(Long id) {
        Optional<Driver> optionalDriver = driverRepository.findById(id);
        return optionalDriver.orElse(null);
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public void deleteDriverById(Long id) {
        driverRepository.deleteById(id);
    }

    @Override
    public Driver updateDriverCurrentLocation(UpdateDriverLocationRequest driverInfo) {
        Optional<Driver> optionalDriver = driverRepository.findById(driverInfo.driverId());
    if (optionalDriver.isPresent()) {
        Driver driver = optionalDriver.get();
        driver.setCurrentLatitude(driverInfo.latitude());
        driver.setCurrentLongitude(driverInfo.longitude());
        return driverRepository.save(driver);
    } else {
        return null; // Or throw an exception or handle the case as appropriate
    }
    }
    
}


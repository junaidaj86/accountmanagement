package com.zinu.account_manager.service;

import java.util.List;

import com.zinu.account_manager.DTO.UpdateDriverLocationRequest;
import com.zinu.account_manager.model.Driver;

public interface DriverService {
    Driver saveDriver(Driver driver);
    Driver getDriverById(Long id);
    List<Driver> getAllDrivers();
    void deleteDriverById(Long id);
    Driver updateDriver(Driver driver);
    public Driver updateDriverLocation(UpdateDriverLocationRequest request);
}

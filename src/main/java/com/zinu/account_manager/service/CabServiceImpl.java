package com.zinu.account_manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zinu.account_manager.DTO.CabCreateRequest;
import com.zinu.account_manager.configurations.TenantContext;
import com.zinu.account_manager.exception.DriverNotFoundException;
import com.zinu.account_manager.model.Cab;
import com.zinu.account_manager.model.CarType;
import com.zinu.account_manager.model.Driver;
import com.zinu.account_manager.repository.CabRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CabServiceImpl implements CabService {

    @Autowired
    private CabRepository cabRepository;

    @Autowired
    private DriverService driverService;

    @Override
    public Cab saveCab(CabCreateRequest cabCreateRequest) {
         //int cont = TenantContext.getCurrentTenant();
        Driver driver = driverService.getDriverById(cabCreateRequest.driverId());
        if(driver == null){
            String errMessage = "Driver with id " + cabCreateRequest.driverId() + " not found";
            throw new DriverNotFoundException(errMessage);
        }
        int shardId = driver.getShardId();
        TenantContext.setCurrentTenant(shardId);
        
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
       
        return cabRepository.save(cab);
    }

    @Override
    public Cab getCabById(Long id) {
        Optional<Cab> optionalCab = cabRepository.findById(id);
        return optionalCab.orElse(null);
    }

    @Override
    public List<Cab> getAllCabs() {
        return cabRepository.findAll();
    }

    @Override
    public void deleteCabById(Long id) {
        cabRepository.deleteById(id);
    }

    @Override
    public List<Cab> getCabsByDriverId(String driverId) {
        return cabRepository.findByDriverId(driverId);
    }
}


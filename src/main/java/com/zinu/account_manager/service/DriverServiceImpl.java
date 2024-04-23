package com.zinu.account_manager.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.zinu.account_manager.DTO.GeoHashRequest;
import com.zinu.account_manager.DTO.GeoHashResponse;
import com.zinu.account_manager.DTO.UpdateDriverLocationRequest;
import com.zinu.account_manager.exception.DriverUpdateException;
import com.zinu.account_manager.exception.EmptyResponseException;
import com.zinu.account_manager.model.Driver;
import com.zinu.account_manager.repository.DriverRepository;
import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    private DriverRepository driverRepository;

    private KafkaService kafkaService;

    private RestService restService;

    public DriverServiceImpl(DriverRepository driverRepository, KafkaService kafkaService, RestService restService) {
        this.driverRepository = driverRepository;
        this.kafkaService = kafkaService;
        this.restService = restService;
    }

    @Value("${geohash.service.uri}")
    private String geoHashServiceUri;

    @Value("${kafka.geohash.topic.name}")
    private String heoHashTopic;

    @Value("${geohash.precision}")
    private int precision;

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
    public Driver updateDriverLocation(UpdateDriverLocationRequest request) {
        Driver driver = getDriverById(request.driverId());
        if (driver == null) {
            throw new DriverUpdateException("Driver not found with ID: " + request.driverId());
        }
        GeoHashRequest geoHashRequest = new GeoHashRequest(request.latitude(), request.longitude(), precision);
        ResponseEntity<GeoHashResponse> response = restService.callSyncRestCall(geoHashServiceUri, geoHashRequest,
                GeoHashResponse.class);
        UpdateDriverLocationRequest kafkaRequest = new UpdateDriverLocationRequest(request.driverId(),
                request.latitude(), request.longitude(), response.getBody().segment());
        kafkaService.send(heoHashTopic, kafkaRequest);
        if (response != null && response.getBody() != null) {
            driver.setCurrentLatitude(request.latitude());
            driver.setCurrentLongitude(request.longitude());
            driver.setSegmentId(response.getBody().segment());
            driverRepository.save(driver);
            return driver;
        } else {
            throw new EmptyResponseException("No data present in response");
        }
    }

    public Driver updateDriver(Driver driverRequest) {
        Driver driver = getDriverById(driverRequest.getId());
        if (driver == null) {
            throw new DriverUpdateException("Driver not found with ID: " + driverRequest.getId());
        }
        driver.setEmail(driverRequest.getEmail());
        driver.setPhoneNumber(driverRequest.getPhoneNumber());
        driverRepository.save(driver);
        return driver;

    }
}

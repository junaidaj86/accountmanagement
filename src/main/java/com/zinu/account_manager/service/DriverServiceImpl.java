package com.zinu.account_manager.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
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

    private RedisTemplate<String, Object> redisTemplate;

    public DriverServiceImpl(DriverRepository driverRepository, KafkaService kafkaService, RestService restService, RedisTemplate<String, Object> redisTemplate) {
        this.driverRepository = driverRepository;
        this.kafkaService = kafkaService;
        this.restService = restService;
        this.redisTemplate = redisTemplate;
    }

    @Value("${geohash.service.uri}")
    private String geoHashServiceUri;

    @Value("${kafka.geohash.topic.name}")
    private String heoHashTopic;

    @Value("${geohash.precision}")
    private int precision;

    @Override
    public Driver saveDriver(Driver driver) {
        String key = "driver:" + driver.getId();
        redisTemplate.opsForValue().set(key, driver);
        return driverRepository.save(driver);
    }

    @Override
    public Driver getDriverById(Long id) {
        String key = "driver:" + id;
        Driver cachedDriver = (Driver) redisTemplate.opsForValue().get(key);
        if (cachedDriver != null) {
            return cachedDriver; // Return from cache
        }else{
            Optional<Driver> optionalDriver = driverRepository.findById(id);
            if(optionalDriver.isPresent()){
                redisTemplate.opsForValue().set(key, optionalDriver.get());
            }
            return optionalDriver.orElse(null);
        }
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
        String key = "driver:" + driverRequest.getId();
        Driver driver = (Driver) redisTemplate.opsForValue().get(key);
        if (driver != null) {
            return driver; // Return from cache
        }{
             driver = getDriverById(driverRequest.getId());
        }
        if (driver == null) {
            throw new DriverUpdateException("Driver not found with ID: " + driverRequest.getId());
        }
        driver.setEmail(driverRequest.getEmail());
        driver.setPhoneNumber(driverRequest.getPhoneNumber());
        driverRepository.save(driver);
        return driver;

    }
}

package com.zinu.account_manager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="cab")
public class Cab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private CarType carType;
    private String registrationNumber;
    @ManyToOne
    @JoinColumn(name="driver_id", referencedColumnName = "id")
    private Driver driver;
    public Cab() {
    }
    public Cab(Long id, CarType carType, String registrationNumber, Driver driver) {
        this.id = id;
        this.carType = carType;
        this.registrationNumber = registrationNumber;
        this.driver = driver;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public CarType getCarType() {
        return carType;
    }
    public void setCarType(CarType carType) {
        this.carType = carType;
    }
    public String getRegistrationNumber() {
        return registrationNumber;
    }
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
    public Driver getDriver() {
        return driver;
    }
    public void setDriver(Driver driver) {
        this.driver = driver;
    }
    @Override
    public String toString() {
        return "Cab [id=" + id + ", carType=" + carType + ", registrationNumber=" + registrationNumber + ", driver="
                + driver + "]";
    }

    
}

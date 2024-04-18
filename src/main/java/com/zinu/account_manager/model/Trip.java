package com.zinu.account_manager.model;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;
    private Double sourcelatitude;
    private Double sourcelongitude;
    private Double destinationlatitude;
    private Double destinationlongitude;
    private Date createdAt;
    @OneToOne(mappedBy = "trip", cascade = CascadeType.ALL)
    private Payment payment;
    private Status status;

    public Trip() {
    }

    public Trip(long id, Customer customer, Driver driver, Double sourcelatitude, Double sourcelongitude,
            Double destinationlatitude, Double destinationlongitude, Date createdAt, Payment payment, Status status) {
        this.id = id;
        this.customer = customer;
        this.driver = driver;
        this.sourcelatitude = sourcelatitude;
        this.sourcelongitude = sourcelongitude;
        this.destinationlatitude = destinationlatitude;
        this.destinationlongitude = destinationlongitude;
        this.createdAt = createdAt;
        this.payment = payment;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Double getSourcelatitude() {
        return sourcelatitude;
    }

    public void setSourcelatitude(Double sourcelatitude) {
        this.sourcelatitude = sourcelatitude;
    }

    public Double getSourcelongitude() {
        return sourcelongitude;
    }

    public void setSourcelongitude(Double sourcelongitude) {
        this.sourcelongitude = sourcelongitude;
    }

    public Double getDestinationlatitude() {
        return destinationlatitude;
    }

    public void setDestinationlatitude(Double destinationlatitude) {
        this.destinationlatitude = destinationlatitude;
    }

    public Double getDestinationlongitude() {
        return destinationlongitude;
    }

    public void setDestinationlongitude(Double destinationlongitude) {
        this.destinationlongitude = destinationlongitude;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Trip [id=" + id + ", customer=" + customer + ", driver=" + driver + ", sourcelatitude=" + sourcelatitude
                + ", sourcelongitude=" + sourcelongitude + ", destinationlatitude=" + destinationlatitude
                + ", destinationlongitude=" + destinationlongitude + ", createdAt=" + createdAt + ", payment=" + payment
                + ", status=" + status + ", getId()=" + getId() + ", getCustomer()=" + getCustomer() + ", getDriver()="
                + getDriver() + ", getSourcelatitude()=" + getSourcelatitude() + ", getSourcelongitude()="
                + getSourcelongitude() + ", getDestinationlatitude()=" + getDestinationlatitude()
                + ", getDestinationlongitude()=" + getDestinationlongitude() + ", getCreatedAt()=" + getCreatedAt()
                + ", getStatus()=" + getStatus() + ", getPayment()=" + getPayment() + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }

   

}

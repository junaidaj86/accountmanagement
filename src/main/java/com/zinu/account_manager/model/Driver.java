package com.zinu.account_manager.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String phoneNumber;
    private Date createdAt;
    @OneToMany(mappedBy = "driver", cascade = CascadeType.PERSIST)
    private List<Cab> cabs = new ArrayList<>();
    private Double currentLatitude;
    private Double currentLongitude;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new Date(System.currentTimeMillis());
    }

    public List<Cab> getCabs() {
        return cabs;
    }

    public void setCabs(List<Cab> cabs) {
        this.cabs = cabs;
    }

    public Double getCurrentLatitude() {
        return currentLatitude;
    }

    public void setCurrentLatitude(Double currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public Double getCurrentLongitude() {
        return currentLongitude;
    }

    public void setCurrentLongitude(Double currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    @Override
    public String toString() {
        return "Driver [id=" + id + ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber
                + ", createdAt=" + createdAt + ", cabs=" + cabs + ", currentLatitude=" + currentLatitude
                + ", currentLongitude=" + currentLongitude + ", getId()=" + getId() + ", getName()=" + getName()
                + ", getEmail()=" + getEmail() + ", getPhoneNumber()=" + getPhoneNumber() + ", getCreatedAt()="
                + getCreatedAt() + ", getCabs()=" + getCabs() + ", getCurrentLatitude()=" + getCurrentLatitude()
                + ", getCurrentLongitude()=" + getCurrentLongitude() + ", getClass()=" + getClass() + ", hashCode()="
                + hashCode() + ", toString()=" + super.toString() + "]";
    }

    

}

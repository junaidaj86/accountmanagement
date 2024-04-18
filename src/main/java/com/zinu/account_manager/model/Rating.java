package com.zinu.account_manager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;
    @OneToOne
    @JoinColumn(name = "trip_id", unique = true)
    private Trip trip;
    private int rating;
    private String feedback;

    public Rating() {
    }

    public Rating(Long id, Customer customer, Driver driver, Trip trip, int rating, String feedback) {
        this.id = id;
        this.customer = customer;
        this.driver = driver;
        this.trip = trip;
        this.rating = rating;
        this.feedback = feedback;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "Rating [id=" + id + ", customer=" + customer + ", driver=" + driver + ", trip=" + trip + ", rating="
                + rating + ", feedback=" + feedback + ", getId()=" + getId() + ", getCustomer()=" + getCustomer()
                + ", getDriver()=" + getDriver() + ", getTrip()=" + getTrip() + ", getRating()=" + getRating()
                + ", getFeedback()=" + getFeedback() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";
    }

}

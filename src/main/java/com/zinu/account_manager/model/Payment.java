package com.zinu.account_manager.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;
    private PaymentType method;
    private double amount;
    private Date createdAt;

    public Payment(Long id, Trip trip, PaymentType method, double amount, Date createdAt) {
        this.id = id;
        this.trip = trip;
        this.method = method;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public Payment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public PaymentType getMethod() {
        return method;
    }

    public void setMethod(PaymentType method) {
        this.method = method;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Payment [id=" + id + ", trip=" + trip + ", method=" + method + ", amount=" + amount + ", createdAt="
                + createdAt + ", getId()=" + getId() + ", getTrip()=" + getTrip() + ", getMethod()=" + getMethod()
                + ", getAmount()=" + getAmount() + ", getCreatedAt()=" + getCreatedAt() + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }

}

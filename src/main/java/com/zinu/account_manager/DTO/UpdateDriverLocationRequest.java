package com.zinu.account_manager.DTO;

public record UpdateDriverLocationRequest(String driverId, Double latitude, Double longitude, String segmentId) {}

package com.zinu.account_manager.DTO;

public record UpdateDriverLocationRequest(Long driverId, Double latitude, Double longitude, String segmentId) {}

package com.zinu.account_manager.DTO;

public record GeoHashRequest(
     Double latitude, 
     Double longitude, 
     int precision){}

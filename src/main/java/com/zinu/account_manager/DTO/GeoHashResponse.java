package com.zinu.account_manager.DTO;

import java.util.HashMap;

public record GeoHashResponse(String segment, HashMap<String, String> neighbours) {}

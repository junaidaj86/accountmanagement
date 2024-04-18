package com.zinu.account_manager.service;

import java.util.List;

import com.zinu.account_manager.model.Cab;

public interface CabService {
    Cab saveCab(Cab cab);
    Cab getCabById(Long id);
    List<Cab> getAllCabs();
    void deleteCabById(Long id);
}

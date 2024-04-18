package com.zinu.account_manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zinu.account_manager.model.Cab;
import com.zinu.account_manager.repository.CabRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CabServiceImpl implements CabService {

    @Autowired
    private CabRepository cabRepository;

    @Override
    public Cab saveCab(Cab cab) {
        return cabRepository.save(cab);
    }

    @Override
    public Cab getCabById(Long id) {
        Optional<Cab> optionalCab = cabRepository.findById(id);
        return optionalCab.orElse(null);
    }

    @Override
    public List<Cab> getAllCabs() {
        return cabRepository.findAll();
    }

    @Override
    public void deleteCabById(Long id) {
        cabRepository.deleteById(id);
    }
}


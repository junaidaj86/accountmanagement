package com.zinu.account_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.zinu.account_manager.model.Cab;

@Repository
public interface CabRepository extends JpaRepository<Cab, Long> {
    List<Cab> findByDriverId(String driverId);
}


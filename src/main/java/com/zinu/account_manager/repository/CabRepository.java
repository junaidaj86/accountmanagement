package com.zinu.account_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.zinu.account_manager.model.Cab;

@Repository
public interface CabRepository extends JpaRepository<Cab, Long> {
}


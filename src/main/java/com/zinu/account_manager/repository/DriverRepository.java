package com.zinu.account_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.zinu.account_manager.model.Driver;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface DriverRepository extends JpaRepository<Driver, String> {
    List<Driver> findAllByShardId(int shardId);
}


package com.chiran.powerplant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chiran.powerplant.entity.Battery;

@Repository
public interface BatteryRepository extends JpaRepository<Battery, Long> {

}

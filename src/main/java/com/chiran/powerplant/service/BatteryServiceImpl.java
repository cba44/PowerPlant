package com.chiran.powerplant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chiran.powerplant.dto.BatteryDTO;
import com.chiran.powerplant.repository.BatteryRepository;

@Service
public class BatteryServiceImpl implements BatteryService{

	private final BatteryRepository repository;

	public BatteryServiceImpl(BatteryRepository batteryRepository) {
		repository = batteryRepository;
	}

	@Override
	public void saveBatteries(List<BatteryDTO> batteriesDTOList) {

	}
}

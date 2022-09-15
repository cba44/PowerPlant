package com.chiran.powerplant.service;

import java.util.List;

import com.chiran.powerplant.dto.BatteryDTO;
import com.chiran.powerplant.dto.BatteryResponseDTO;

public interface BatteryService {

	List<BatteryDTO> saveBatteries(List<BatteryDTO> batteries);
	BatteryResponseDTO retrieveBatteries(long start, long end);

}

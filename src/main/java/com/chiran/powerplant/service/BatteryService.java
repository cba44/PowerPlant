package com.chiran.powerplant.service;

import java.util.List;

import com.chiran.powerplant.dto.BatteryDTO;

public interface BatteryService {

	void saveBatteries(List<BatteryDTO> batteries);

}

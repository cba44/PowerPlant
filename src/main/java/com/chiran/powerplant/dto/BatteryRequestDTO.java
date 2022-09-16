package com.chiran.powerplant.dto;

import java.util.List;

public class BatteryRequestDTO {

	private List<BatteryDTO> batteries;

	public BatteryRequestDTO() {

	}

	public BatteryRequestDTO(List<BatteryDTO> batteries) {
		this.batteries = batteries;
	}

	public List<BatteryDTO> getBatteries() {
		return batteries;
	}

	public void setBatteries(List<BatteryDTO> batteries) {
		this.batteries = batteries;
	}

}

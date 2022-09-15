package com.chiran.powerplant.dto;

import java.util.List;

public class BatteryDetailsDTO {

	private List<BatteryDTO> batteries;
	private long totalWatts;
	private double averageWatts;

	public List<BatteryDTO> getBatteries() {
		return batteries;
	}

	public void setBatteries(List<BatteryDTO> batteries) {
		this.batteries = batteries;
	}

	public long getTotalWatts() {
		return totalWatts;
	}

	public void setTotalWatts(long totalWatts) {
		this.totalWatts = totalWatts;
	}

	public double getAverageWatts() {
		return averageWatts;
	}

	public void setAverageWatts(double averageWatts) {
		this.averageWatts = averageWatts;
	}

}

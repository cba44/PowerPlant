package com.chiran.powerplant.dto;

import java.util.List;

public class BatteryResponseDTO {

	private List<String> batteries;
	private long totalWatts;
	private double averageWatts;

	public BatteryResponseDTO(List<String> batteries, long totalWatts, double averageWatts) {
		this.batteries = batteries;
		this.totalWatts = totalWatts;
		this.averageWatts = averageWatts;
	}

	public List<String> getBatteries() {
		return batteries;
	}

	public void setBatteries(List<String> batteries) {
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

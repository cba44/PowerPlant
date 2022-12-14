package com.chiran.powerplant.dto;

public class BatteryDTO {

	private String name;
	private long postcode;
	private long capacity;


	public BatteryDTO() {
		super();
	}

	public BatteryDTO(String name, long postcode, long capacity) {
		this.name = name;
		this.postcode = postcode;
		this.capacity = capacity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPostcode() {
		return postcode;
	}

	public void setPostcode(long postcode) {
		this.postcode = postcode;
	}

	public long getCapacity() {
		return capacity;
	}

	public void setCapacity(long capacity) {
		this.capacity = capacity;
	}



}

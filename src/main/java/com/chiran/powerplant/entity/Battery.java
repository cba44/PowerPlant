package com.chiran.powerplant.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Battery {

	@Id
	private long id;
	private String name;
	private long postcode;
	private long capacity;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

package com.chiran.powerplant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.chiran.powerplant.entity.Battery;
import com.chiran.powerplant.repository.BatteryRepository;

@DataJpaTest
class BatteryRepositoryTest {

	@Autowired
	private BatteryRepository batteryRepository;
	private Battery battery;

	@BeforeEach
	public void setUp() {
		battery = new Battery("test battery 1", 2987, 738);
	}

	@AfterEach
	public void tearDown() {
		batteryRepository.deleteAll();
		battery = null;
	}

	@Test
	void saveBattery() {
		Battery savedBattery = batteryRepository.save(battery);
		Battery fetchedBattery = batteryRepository.findById(savedBattery.getId()).get();
		assertEquals("test battery 1", fetchedBattery.getName());
		assertEquals(2987, fetchedBattery.getPostcode());
		assertEquals(738, fetchedBattery.getCapacity());
	}

	@Test
	void retrieveBatteryInRange() {
		Battery battery1 = new Battery("test battery 2", 100, 500);
		Battery battery2 = new Battery("test battery 7", 200, 600);
		Battery battery3 = new Battery("test battery 3", 300, 700);
		Battery battery4 = new Battery("test battery 1", 400, 800);
		Battery battery5 = new Battery("test battery 5", 500, 900);
		Battery battery6 = new Battery("test battery 9", 600, 300);

		List<Battery> batteryList = Arrays.asList(battery1, battery2, battery3, battery4, battery5, battery6);

		batteryRepository.saveAll(batteryList);

		List<Battery> fetched = batteryRepository.findByPostcodeBetween(300, 500);
		assertEquals(3, fetched.size());
		assertEquals("test battery 3", fetched.get(0).getName());
		assertEquals("test battery 1", fetched.get(1).getName());
		assertEquals("test battery 5", fetched.get(2).getName());
	}

}

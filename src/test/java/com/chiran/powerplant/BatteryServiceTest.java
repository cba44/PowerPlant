package com.chiran.powerplant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.chiran.powerplant.dto.BatteryDTO;
import com.chiran.powerplant.dto.BatteryResponseDTO;
import com.chiran.powerplant.entity.Battery;
import com.chiran.powerplant.exception.BatteriesNotFoundException;
import com.chiran.powerplant.exception.IllegalInputException;
import com.chiran.powerplant.repository.BatteryRepository;
import com.chiran.powerplant.service.BatteryService;
import com.chiran.powerplant.service.BatteryServiceImpl;

@ExtendWith(MockitoExtension.class)
class BatteryServiceTest {

	@Mock
	private BatteryRepository batteryRepository;
	private BatteryService batteryService;

	@BeforeEach
	public void setUp() {
		batteryService = new BatteryServiceImpl(batteryRepository, new ModelMapper());
	}

	@AfterEach
	public void tearDown() {
		batteryService = null;
	}

	@Test
	public void batteriesAddedToDBViaService() {
		Battery battery1 = new Battery("test battery 1", 2987, 738);
		Battery battery2 = new Battery("test battery 2", 24987, 7538);
		BatteryDTO batteryDTO1 = new BatteryDTO("test battery 1", 2987, 738);
		BatteryDTO batteryDTO2 = new BatteryDTO("test battery 2", 24987, 7538);

		List<Battery> batteryList = Arrays.asList(battery1, battery2);
		List<BatteryDTO> batteryDTOList = Arrays.asList(batteryDTO1, batteryDTO2);

		Mockito.when(batteryRepository.saveAll(any())).thenReturn(batteryList);
		List<BatteryDTO> savedBatteries = batteryService.saveBatteries(batteryDTOList);
		verify(batteryRepository, times(1)).saveAll(any());
		assertEquals(batteryDTO1.getName(), savedBatteries.get(0).getName());
		assertEquals(batteryDTO2.getName(), savedBatteries.get(1).getName());
	}

	@Test
	public void batteriesInRangeViaService() {
		Battery battery1 = new Battery("test battery 7", 200, 600);
		Battery battery2 = new Battery("test battery 3", 300, 700);
		Battery battery3 = new Battery("test battery 1", 400, 800);
		Battery battery4 = new Battery("test battery 5", 500, 900);

		List<Battery> batteryList = Arrays.asList(battery1, battery2, battery3, battery4);

		Mockito.when(batteryRepository.findByPostcodeBetween(200, 500)).thenReturn(batteryList);
		BatteryResponseDTO retrieveBatteries = batteryService.retrieveBatteries(200, 500);

		verify(batteryRepository, times(1)).findByPostcodeBetween(anyLong(), anyLong());
		assertNotNull(retrieveBatteries);

		List<String> batteries = retrieveBatteries.getBatteries();
		assertEquals("test battery 1", batteries.get(0));
		assertEquals("test battery 3", batteries.get(1));
		assertEquals("test battery 5", batteries.get(2));
		assertEquals("test battery 7", batteries.get(3));
		assertEquals(3000, retrieveBatteries.getTotalWatts());
		assertEquals(750.0, retrieveBatteries.getAverageWatts());
	}

	@Test
	public void checkSaveWithEmptyList() {
		Exception exception = assertThrows(IllegalInputException.class, () -> {
			batteryService.saveBatteries(null);
	    });

	    String expectedMessage = "Error in request body";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void checkRetrieveWhenNoPostcodesReturned() {
		Mockito.when(batteryRepository.findByPostcodeBetween(anyLong(), anyLong())).thenReturn(List.of());
		Exception exception = assertThrows(BatteriesNotFoundException.class, () -> {
			batteryService.retrieveBatteries(anyLong(), anyLong());
	    });

	    String expectedMessage = "Batteries not found for given range";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}

}

package com.chiran.powerplant;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.chiran.powerplant.controller.BatteryController;
import com.chiran.powerplant.dto.BatteryDTO;
import com.chiran.powerplant.dto.BatteryRequestDTO;
import com.chiran.powerplant.dto.BatteryResponseDTO;
import com.chiran.powerplant.service.BatteryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class BatteryControllerTest {

	@Mock
	private BatteryService batteryService;
	@InjectMocks
	private BatteryController batteryController;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(batteryController).build();
	}

	@AfterEach
	void tearDown() {
		mockMvc = null;
	}

	@Test
	public void checkAddingBatteries() throws Exception {
		BatteryRequestDTO reqDtoList = reqDtoList();
		ObjectMapper mapper = new ObjectMapper();

		Mockito.when(batteryService.saveBatteries(any())).thenReturn(reqDtoList.getBatteries());

		mockMvc.perform(post("/api/v1/batteries").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(reqDtoList))).andExpect(status().isCreated())
		.andExpect(MockMvcResultMatchers.jsonPath("$.batteries").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$.batteries[0].name").value("test battery 2"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.batteries[1].name").value("test battery 7"));
		verify(batteryService, times(1)).saveBatteries(any());

	}

	@Test
	public void checkRetreivingBatteries() throws Exception {
		Mockito.when(batteryService.retrieveBatteries(anyLong(), anyLong())).thenReturn(respDto());

		mockMvc.perform(get("/api/v1/batteries").contentType(MediaType.APPLICATION_JSON)
				.param("rangeStart", String.valueOf(200))
				.param("rangeEnd", String.valueOf(500))).andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.batteries").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$.batteries[0]").value("test battery 1"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.batteries[1]").value("test battery 3"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.batteries[2]").value("test battery 7"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.batteries[3]").value("test battery 9"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.totalWatts").value(3000))
		.andExpect(MockMvcResultMatchers.jsonPath("$.averageWatts").value(750.0));

		verify(batteryService, times(1)).retrieveBatteries(anyLong(), anyLong());
	}

	private static BatteryRequestDTO reqDtoList() {
		BatteryDTO battery1 = new BatteryDTO("test battery 2", 100, 500);
		BatteryDTO battery2 = new BatteryDTO("test battery 7", 200, 600);
		BatteryDTO battery3 = new BatteryDTO("test battery 3", 300, 700);
		BatteryDTO battery4 = new BatteryDTO("test battery 1", 400, 800);
		BatteryDTO battery5 = new BatteryDTO("test battery 9", 500, 900);
		BatteryDTO battery6 = new BatteryDTO("test battery 5", 600, 300);

		return new BatteryRequestDTO(List.of(battery1, battery2, battery3, battery4, battery5, battery6));
	}

	private static BatteryResponseDTO respDto() {
		return new BatteryResponseDTO(
				List.of("test battery 1", "test battery 3", "test battery 7", "test battery 9"), 3000, 750.0);
	}

}

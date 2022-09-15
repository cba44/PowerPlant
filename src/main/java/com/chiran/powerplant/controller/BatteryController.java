package com.chiran.powerplant.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chiran.powerplant.dto.BatteryDTO;
import com.chiran.powerplant.dto.BatteryRequestDTO;
import com.chiran.powerplant.dto.BatteryResponseDTO;
import com.chiran.powerplant.service.BatteryService;

@RestController
@RequestMapping("/api/v1")
public class BatteryController {

	private final BatteryService service;

	public BatteryController(BatteryService service) {
		this.service = service;
	}

	@PostMapping("batteries")
	public ResponseEntity<BatteryRequestDTO> addBatteries(@RequestBody BatteryRequestDTO request) {
		List<BatteryDTO> dtos = request.getBatteries();
		return new ResponseEntity<BatteryRequestDTO>(new BatteryRequestDTO(service.saveBatteries(dtos)), HttpStatus.CREATED);
	}

	@GetMapping("batteries")
	public ResponseEntity<BatteryResponseDTO> getBatteriesForRange(@RequestParam long start, @RequestParam long end) {
		return ResponseEntity.ok(service.retrieveBatteries(start, end));
	}
}

package com.chiran.powerplant.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.chiran.powerplant.dto.BatteryDTO;
import com.chiran.powerplant.dto.BatteryResponseDTO;
import com.chiran.powerplant.entity.Battery;
import com.chiran.powerplant.exception.BatteriesNotFoundException;
import com.chiran.powerplant.exception.IllegalInputException;
import com.chiran.powerplant.repository.BatteryRepository;

@Service
public class BatteryServiceImpl implements BatteryService{

	private final BatteryRepository repository;
	private final ModelMapper mapper;

	public BatteryServiceImpl(BatteryRepository repository, ModelMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public List<BatteryDTO> saveBatteries(List<BatteryDTO> batteriesDTOList) {

		if(batteriesDTOList == null) {
			throw new IllegalInputException("Error in request body");
		}

		List<Battery> batteries = batteriesDTOList.stream()
				.filter(dto -> {
					return dto.getName() != null && dto.getCapacity() >= 0 && dto.getPostcode() > 0;
				})
				.map(dto -> mapper.map(dto, Battery.class))
				.collect(Collectors.toList());

		List<Battery> savedBatteries = repository.saveAll(batteries);
		List<BatteryDTO> batteryDTOList = savedBatteries.stream()
				.map(bat -> mapper.map(bat, BatteryDTO.class))
				.collect(Collectors.toList());
		return batteryDTOList;
	}

	@Override
	public BatteryResponseDTO retrieveBatteries(long start, long end) {
		List<Battery> batteries = repository.findByPostcodeBetween(start, end);

		if (batteries.size() == 0) {
			throw new BatteriesNotFoundException("Batteries not found for given range");
		}

		List<String> batteryDTOs = batteries.stream().map(battery -> battery.getName())
				.sorted().collect(Collectors.toList());
		long sum = batteries.stream().map(battery -> battery.getCapacity())
				.mapToLong(Long::longValue).sum();
		double size = batteries.size();
		return new BatteryResponseDTO(batteryDTOs, sum, sum/size);
	}

}

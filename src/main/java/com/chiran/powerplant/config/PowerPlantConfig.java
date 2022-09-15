package com.chiran.powerplant.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PowerPlantConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}

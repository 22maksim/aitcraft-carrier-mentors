package com.home.aircraft_carrier_mentors;

import org.springframework.boot.SpringApplication;

public class TestAircraftCarrierMentorsApplication {

	public static void main(String[] args) {
		SpringApplication.from(AircraftCarrierMentorsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

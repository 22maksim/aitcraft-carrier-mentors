package com.home.aircraft_carrier_mentors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@ConfigurationPropertiesScan
@SpringBootApplication
public class AircraftCarrierMentorsApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(AircraftCarrierMentorsApplication.class, args);
		System.out.println(context);
	}
}

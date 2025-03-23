package com.drivelab.autocenter;

import org.springframework.boot.SpringApplication;

public class TestAutocenterApplication {

	public static void main(String[] args) {
		SpringApplication.from(AutocenterApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

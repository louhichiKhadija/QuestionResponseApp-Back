package com.example.medTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.medTest.utils.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({ FileStorageProperties.class })
public class MedTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedTestApplication.class, args);
	}

}

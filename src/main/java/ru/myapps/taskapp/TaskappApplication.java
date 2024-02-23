package ru.myapps.taskapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class TaskappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskappApplication.class, args);
	}

}

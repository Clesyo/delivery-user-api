package br.com.delivery.user;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import br.com.delivery.user.seeders.DefaultSeeder;

@EnableEurekaClient
@SpringBootApplication
public class DeliveryUserApplication {
	
	@Autowired
	private DefaultSeeder seeder;

	public static void main(String[] args) {
		SpringApplication.run(DeliveryUserApplication.class, args);
	}

	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		seeder.seedProfiles();
		seeder.seedUser();
	}
}

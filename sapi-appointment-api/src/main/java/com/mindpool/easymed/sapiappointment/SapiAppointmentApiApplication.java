package com.mindpool.easymed.sapiappointment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SapiAppointmentApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SapiAppointmentApiApplication.class, args);
	}
}

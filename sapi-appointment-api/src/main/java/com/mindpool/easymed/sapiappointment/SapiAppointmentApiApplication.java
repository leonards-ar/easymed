package com.mindpool.easymed.sapiappointment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.mindpool.easymed.sapiappointment.domain","com.mindpool.easymed.sapiappointment.service"})
public class SapiAppointmentApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SapiAppointmentApiApplication.class, args);
	}
}

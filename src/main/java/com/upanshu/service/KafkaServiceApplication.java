package com.upanshu.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upanshu.service.event.Protagonist;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaServiceApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KafkaServiceApplication.class, args);


	}


	@Override
	public void run(String... args) throws Exception {

         Protagonist p= Protagonist.builder()
				 .characterName("Walker")
				 .power("samplePower")
				 .showName("fallout")
				 .isStillOnAir(true)
				 .build();
		ObjectMapper mapper = new ObjectMapper();
		String xx= mapper.writeValueAsString(p);
		System.out.println(xx);
	}
}

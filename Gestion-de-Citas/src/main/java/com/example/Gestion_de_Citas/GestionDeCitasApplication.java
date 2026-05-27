package com.example.Gestion_de_Citas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GestionDeCitasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionDeCitasApplication.class, args);
	}

}

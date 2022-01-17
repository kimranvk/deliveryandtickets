package org.abudhabi.tickets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition (info=@Info(title="Delivery",version="1.0", description = "Assignment", termsOfService = "Please adhere to policies"))
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}

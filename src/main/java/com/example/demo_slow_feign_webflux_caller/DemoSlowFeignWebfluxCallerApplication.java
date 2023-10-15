package com.example.demo_slow_feign_webflux_caller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DemoSlowFeignWebfluxCallerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSlowFeignWebfluxCallerApplication.class, args);
	}

}

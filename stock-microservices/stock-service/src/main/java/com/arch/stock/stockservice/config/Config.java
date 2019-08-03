package com.arch.stock.stockservice.config;

//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;


@Configuration
public class Config {
	
	
	@Bean
	public RestTemplate rt()
	{
		return new RestTemplate();
	}

}

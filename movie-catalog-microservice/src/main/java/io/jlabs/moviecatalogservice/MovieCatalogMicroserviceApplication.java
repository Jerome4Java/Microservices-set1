package io.jlabs.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
public class MovieCatalogMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogMicroserviceApplication.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	/*
	 * @LoadBalanced
	 * 
	 * @Bean public WebClient.Builder getWebClient(){ return WebClient.builder(); }
	 */

}

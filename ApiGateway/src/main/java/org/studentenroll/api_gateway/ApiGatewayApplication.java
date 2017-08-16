package org.studentenroll.api_gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.web.client.RestTemplate;
import org.studentenroll.api_gateway.messaging.MessageChannels;

/**
 * @author mukund chavali
 * Place for all clients, this acts like a gateway to all internal services.
 */


@EnableFeignClients //Way to do declarative rest clients
@EnableZuulProxy//Zuul API GATEway
@EnableBinding(MessageChannels.class)//For RabbitMQ
@EnableCircuitBreaker//Hystrix Circuit Breaker
@EnableDiscoveryClient//For Eureka Discovery
@IntegrationComponentScan//neccesary for RabbitMQ
@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	@LoadBalanced
	RestTemplate getrestTemplate(){
		return new RestTemplate();
	}
}


//TODO Add COurseService and UserService client representation
//TODO refactor code after adding in more clients


package org.studentenroll.cartservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.studentenroll.cartservice.entity.Cart;
import org.studentenroll.cartservice.messaging.HttpMessaging;
import org.studentenroll.cartservice.messaging.queue.CartChannels;

import org.studentenroll.cartservice.messaging.queue.Writer;
import org.studentenroll.cartservice.repository.CartRepository;

import java.util.*;

/**
 * @author mukund chavali
 * Watch Spring boot  getting started tutorial by Josh Long : https://www.youtube.com/watch?v=sbPSjI4tt10
 */


//@EnableBinding({CartChannels.class,Writer.class})

@EnableDiscoveryClient //for Eureka
@SpringBootApplication
@IntegrationComponentScan
@ComponentScan
public class CartServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(CartServiceApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(CartRepository cr){
		return (args)->{
			Arrays.asList("123","35","456").forEach(n-> cr.save(new Cart(n)));
			cr.findAll().forEach(s->{System.out.println(s);

			                      //  s.setItems(new ArrayList<String>());
                    });

            System.out.println(cr.findCartByUserId("123"));
		}; //Implements CommandLineRunner(Functional Interface)
	}
	@Bean
	@LoadBalanced
	RestTemplate getRestTemplate(){
		return new RestTemplate();
	}


}
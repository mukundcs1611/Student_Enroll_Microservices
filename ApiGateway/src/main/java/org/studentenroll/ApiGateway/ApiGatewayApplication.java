package org.studentenroll.ApiGateway;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mukund chavali
 * Place for all clients, this acts like a gateway to all internal services.
 */


@EnableZuulProxy
@EnableBinding(ApiGatewayApplication.CartSource.class)
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	@Bean
    @LoadBalanced
    RestTemplate restTemplate(){
	    return new RestTemplate();
    }
    @MessagingGateway
    interface CartWriter {

        @Gateway(requestChannel = "output")
        void write(String rn);
    }
    interface CartSource {

        @Output
        MessageChannel output();
    }

}
@RestController
@RequestMapping("/userids")
class CartApiGatewayController{

    @Autowired
    private RestTemplate restTemplate;

//    private final CartWriter cartWriter;
//
//    public CartApiGatewayController(CartWriter cartWriter) {
//        this.cartWriter = cartWriter;
//    }

    public Collection<String> getCartsFallback(){

        return  new ArrayList<String>();
    }

    @HystrixCommand(fallbackMethod = "getCartsFallback")
    @RequestMapping(method= RequestMethod.GET)
    public Collection<String> getCartUserIds(){
        List<Cart> c=new ArrayList<Cart>(){};

        ParameterizedTypeReference<Resources<Cart>> ptr=new ParameterizedTypeReference<Resources<Cart>>() {};
        ResponseEntity<Resources<Cart>> entity= this.restTemplate.exchange("http://cart-service/carts", HttpMethod.GET,null,ptr);

        return entity
                .getBody()
                .getContent()
                .stream()
                .map(Cart::getUserId)
                .collect(Collectors.toList());
    }
//    @RequestMapping(method=RequestMethod.POST)
//    public void addCart(@RequestBody Cart c){
//        Message<String> msg= MessageBuilder.withPayload(c.getUserId()).build();
//        this.source.output().send(msg);
//    }


}

//TODO Add COurseService and UserService client representation
//TODO refactor code after adding in more clients

class Cart{
    private String userId;

    public String getUserId() {
        return userId;
    }
}

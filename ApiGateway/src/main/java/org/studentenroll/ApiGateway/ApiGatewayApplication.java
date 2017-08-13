package org.studentenroll.ApiGateway;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author mukund chavali
 * Place for all clients, this acts like a gateway to all internal services.
 */

interface  MessageChannels{
    @Output
    MessageChannel output(); //one for user, course
}

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

}


@FeignClient("cart-service")
interface CartReader{
    @RequestMapping(method=RequestMethod.GET,value="/carts")
    Resources<Cart> read();
}

@MessagingGateway
interface CartWriter{

    @Gateway(requestChannel="output")
    void write(Map<String,String> param);
}


@RestController
@RequestMapping("/cart")
class CartApiGatewayController{


    private final CartReader cartReader;

    private final CartWriter cartWriter;

    @Autowired
    CartApiGatewayController(CartReader cartReader, CartWriter cartWriter) {
        this.cartReader = cartReader;
        this.cartWriter = cartWriter;
    }


    public Collection<String> getCartsFallback(){
        String st="hold tight";
        List<String> a=new ArrayList<String>();
        a.add(st);
        return  a;
    }

    @HystrixCommand(fallbackMethod = "getCartsFallback")
    @RequestMapping(method= RequestMethod.GET,value="/userids")
    public Collection<String> getCartUserIds(){

        return this.cartReader
                .read()
                .getContent()
                .stream()
                .map(Cart::getUserId)
                .collect(Collectors.toList());
    }
    @RequestMapping(method=RequestMethod.POST,value="/addItem")
    public void addItem(@RequestBody Map<String,String> c){
//        String userId=c.get("userId");
//        String courseId=c.get("courseId");
        this.cartWriter.write(c);
    }


}

//TODO Add COurseService and UserService client representation
//TODO refactor code after adding in more clients

class Cart{
    private String userId;

    public String getUserId() {
        return userId;
    }
}

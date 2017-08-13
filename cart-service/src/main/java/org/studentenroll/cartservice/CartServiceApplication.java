package org.studentenroll.cartservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Payloads;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import java.util.*;

/**
 * @author mukund chavali
 * Watch Spring boot  getting started tutorial by Josh Long : https://www.youtube.com/watch?v=sbPSjI4tt10
 */

interface CartChannels {
    @Input
    SubscribableChannel input();
}


@EnableBinding(CartChannels.class)
@EnableDiscoveryClient //for Eureka
@SpringBootApplication
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

			cr.findCartByUserId("123").forEach(System.out::println);//Method references in Lambda
		}; //Implements CommandLineRunner(Functional Interface)
	}
}

@MessageEndpoint
class CartProcessor{
	@Autowired
	CartProcessor(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	@ServiceActivator(inputChannel="input")
	public void onNewCart(Map<String,String> param){
		Long id=null;
		for(Cart c2:this.cartRepository.findAll()){
		    if(c2.getUserId()==param.get("userId")){//Cart Exist
		       id=c2.getCartid();
               List<String> items=c2.getItems();
               items.add(param.get("courseId"));

                this.cartRepository.save(c2);
            }

        }
        if(id==null){//Cart Not Exist
            Cart c=new Cart(param.get("userId"));
            List<String> newList=new ArrayList<String>();
            newList.add(param.get("courseId"));
            c.setItems(newList);
            this.cartRepository.save(c) ;
		}


	}
	private final CartRepository cartRepository;


}


@RefreshScope
@RestController
class MessageServiceController{
    @Value("${message}")
    private String msg;
    @RequestMapping("/message")
    String message(){
        return this.msg;
    }

    @Autowired
    CartRepository cr;
}
//JpaRepository already has some finder methods
@RepositoryRestResource
interface CartRepository extends JpaRepository<Cart,Long>{
	@RestResource(path="by-id")
	Collection<Cart> findCartByUserId(@Param("userId") String userId);

}
//@Component
//class CartResourceProcessor implements ResourceProcessor<Resource<Cart>> {
//	// this method is for adding hateoas links
//    @Override
//    public Resource<Cart> process(Resource<Cart> resource) {
//        resource.add(new Link("http://something.com/" + resource.getContent().getUserId()));
//        return resource;
//    }
//}
//@Controller
//class CartMVCController{
//    @RequestMapping("/cart.php")
//    String cart(Model model){
//        model.addAttribute("carts",this.cartRepository.findAll());
//        return "carts";//src/main/resources/templatees/+$X+.html
//    }
//
//    @Autowired
//    private CartRepository cartRepository;
//}
@Entity
class Cart{
	@Id
	@GeneratedValue
	private Long cartId;

	@Column(nullable=false)
	private String userId;

    @ElementCollection
	private List<String> items;

	Cart() {
	}
	Cart(String userId){
		this.userId=userId;
	}

	public Long getCartid(){
		return cartId;
	}

	public String getUserId() {
		return userId;
	}


	public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items){
	    this.items=items;
    }


    @Override
	public String toString() {
		return "Cart{" +
				"cartId=" + cartId +
				", userId='" + userId + '\'' +
				'}';
	}

}
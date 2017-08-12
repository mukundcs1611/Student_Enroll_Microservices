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
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author mukund chavali
 * Watch Spring boot  getting started tutorial by Josh Long : https://www.youtube.com/watch?v=sbPSjI4tt10
 */
interface ReservationChannels {
    @Input
    MessageChannel input();
}
@EnableBinding(ReservationChannels.class)
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
                                    s.setNoOfItems((int)Math.rint(Math.random()));
                    });

			cr.findCartByUserId("123").forEach(System.out::println);//Method references in Lambda
		}; //Implements CommandLineRunner(Functional Interface)
	}
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
//    @RequestMapping("/cart")
//    Collection<Cart> allcarts(){
//        return this.cr.findAll();
//    }
    @Autowired
    CartRepository cr;
}
//JpaRepository already has some finder methods
@RepositoryRestResource
interface CartRepository extends JpaRepository<Cart,Long>{
	@RestResource(path="by-id")
	Collection<Cart> findCartByUserId(@Param("userId") String userId);
}
@Component
class CartResourceProcessor implements ResourceProcessor<Resource<Cart>> {
	// this method is for adding hateoas links
    @Override
    public Resource<Cart> process(Resource<Cart> resource) {
        resource.add(new Link("http://something.com/" + resource.getContent().getUserId()));
        return resource;
    }
}
@Controller
class CartMVCController{
    @RequestMapping("/cart.php")
    String cart(Model model){
        model.addAttribute("carts",this.cartRepository.findAll());
        return "carts";//src/main/resources/templatees/+$X+.html
    }

    @Autowired
    private CartRepository cartRepository;
}
@Entity
class Cart{
	@Id
	@GeneratedValue
	private Long cartId;

	@Column(nullable=false)
	private String userId;

	private int noOfItems;

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

//    public List<String> getItems() {
//        return items;
//    }


    public int getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(int noOfItems) {
        this.noOfItems = noOfItems;
    }

    @Override
	public String toString() {
		return "Cart{" +
				"cartId=" + cartId +
				", userId='" + userId + '\'' +
				'}';
	}

}
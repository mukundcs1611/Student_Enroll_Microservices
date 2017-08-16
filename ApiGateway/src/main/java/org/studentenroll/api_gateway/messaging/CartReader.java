package org.studentenroll.api_gateway.messaging;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.studentenroll.api_gateway.entity.Cart;

@FeignClient("cart-service")
public interface CartReader{
    @RequestMapping(method= RequestMethod.GET,value="/carts")
    Resources<Cart> read();
}

package org.studentenroll.cartservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.SubscribableChannel;
import org.studentenroll.cartservice.entity.Cart;
import org.studentenroll.cartservice.repository.CartRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Deprecated
@MessageEndpoint
public class CartProcessor{
    @Autowired
    CartProcessor(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @ServiceActivator(inputChannel="input")
    public boolean addToCart(Map<String,String> param){

        try{
            String userId=param.get("userId");
            String courseId=param.get("courseId");
            Cart c=this.cartRepository.findCartByUserId(userId);

            if(c==null){
                c=new Cart(userId);
                List<String> list=new ArrayList<String>();
                list.add(courseId);
                c.setItems(list);
            }
            else{
                List<String> list=c.getItems();
                list.add(courseId);

            }
            this.cartRepository.save(c);
        }
        catch(Exception ex){//Add more specific exceptions
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private final CartRepository cartRepository;


}

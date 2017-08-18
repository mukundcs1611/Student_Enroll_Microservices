package org.studentenroll.cartservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.studentenroll.cartservice.entity.Cart;
import org.studentenroll.cartservice.exception.BadRequestException;
import org.studentenroll.cartservice.messaging.HttpMessaging;
import org.studentenroll.cartservice.repository.CartRepository;
import org.studentenroll.cartservice.service.CartProcessor;

import java.util.*;

/**
 * @author chavali
 **/
@RefreshScope
@RestController
@ComponentScan("org.studentenroll.cartservice")
public class CartController{

    final CartRepository cr;


     HttpMessaging httpMessaging;
    final RestTemplate restTemplate;


    @Autowired
    CartProcessor cartProcessor;

    @Autowired
     public CartController(CartRepository cr,RestTemplate restTemplate) {
        this.cr = cr;
        this.restTemplate = restTemplate;

    }

    @PutMapping(value="checkout/{cartId}")
    @Transactional
    boolean checkout(@PathVariable Long cartId,@RequestBody List<String> courses){
        this.httpMessaging=new HttpMessaging(restTemplate);
        Cart c=this.cr.getOne(cartId);
        c.setItems(courses);
        Map<String,List<String>> m=new HashMap<String,List<String>>();

        m.put("userId", Arrays.asList(c.getUserId()));//Unnecessary List
        m.put("cartItems",c.getItems());

        //On Payment Success
        //send  output to course service queue
        if(httpMessaging.sendHttpMessage("http://course-service/internal/enrollCart", HttpMethod.POST,m) && httpMessaging.sendHttpMessage("http://user-service/internal/addCourse",HttpMethod.PUT ,m)){
            try{
                this.cr.delete(c);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
            return true;
        }

        return false;
    }

    Cart getCartByUserId(Map<String,String> param){
        String userId=param.get("userId");
        Cart c=this.cr.findCartByUserId(userId);
        if(c==null){
            return null;
        }
        return c;
    }
    @PostMapping(value="/addItem")
    public boolean addToCart(@RequestBody Map<String,String> param){
        return this.cartProcessor.addToCart(param);

    }





}

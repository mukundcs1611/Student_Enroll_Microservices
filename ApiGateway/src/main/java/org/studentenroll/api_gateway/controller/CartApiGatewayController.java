package org.studentenroll.api_gateway.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.studentenroll.api_gateway.entity.Cart;
import org.studentenroll.api_gateway.exception.BadRequestException;
import org.studentenroll.api_gateway.messaging.CartReader;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/cart")
public class CartApiGatewayController{


    private final CartReader cartReader;



    @Autowired
    CartApiGatewayController(CartReader cartReader) {
        this.cartReader = cartReader;

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
    public boolean addItem(@RequestBody Map<String,String> c){
        String userId=c.get("userId");
        String courseId=c.get("courseId");

        try(CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault()) {
            // Start the client
            httpclient.start();

            // Execute the PUT request to the other micro-service(s)?
            // TODO: You should use the "completed" callback handler below to do the next operation
            final HttpPost request = new HttpPost("http://cart-service/addItem");
            String json = "{\"userId\":"+userId+",\"courseId\":"+courseId+"}";
            StringEntity entity = new StringEntity(json);
            request.setEntity(entity);
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");
            httpclient.execute(request, new FutureCallback<HttpResponse>() {

                public void completed(final HttpResponse response2) {

                    System.out.println(request.getRequestLine() + "->" + response2.getStatusLine());

                }

                public void failed(final Exception ex) {
                    System.out.println(request.getRequestLine() + "->" + ex);
                    throw new BadRequestException(" Request Failed");


                }

                public void cancelled() {

                    System.out.println(request.getRequestLine() + " cancelled");
                    throw new BadRequestException("Request Cancelled");
                }

            });

        }

        catch (UnsupportedEncodingException e) {

            e.printStackTrace();
            return false;
        }
        catch(BadRequestException e){
            e.printStackTrace();
            return false;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}

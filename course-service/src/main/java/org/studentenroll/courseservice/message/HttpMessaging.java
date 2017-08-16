package org.studentenroll.courseservice.message;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.studentenroll.courseservice.Exception.BadRequestException;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author chavali
 **/
@RestResource
public class HttpMessaging  {
    HttpHeaders header;

    @Autowired
    public HttpMessaging(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON_UTF8);


    }


    @Bean
    @LoadBalanced
    RestTemplate getrestTemplate(){
        return new RestTemplate();
    }
    final
    RestTemplate restTemplate;

    @Async
    public void post(String url, Map<String,String> param){
//
//        HttpEntity h=new HttpEntity(param,header);
//        restTemplate.exchange(url, HttpMethod.POST, h,Map<String,String>.class);
//        restTemplate.
    }

    /**
     * @author praveen
     * @param param1
     * @param param2
     * @param url
     * param1,param2 can be any String. For sending a request to cart-service for adding an item
     * param1-> userId
     * param2 -> courseId
     * url :http://cart-service/addItem
     * For Sending a post request to mentioned url
     * TODO Make it more generic
     */
    public boolean post(String param1,String param2,String url) {
        boolean returnVal;
        try (CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault()) {
            // Start the client
            httpclient.start();
            // Execute the POST request to the other micro-service(s)?
            // TODO: You should use the "completed" callback handler below to do the next operation
            final HttpPost request = new HttpPost(url);
            String json = "{\"userId\":" + param1 + ",\"courseId\":" + param2 + "}";
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

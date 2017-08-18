package org.studentenroll.cartservice.messaging;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.studentenroll.cartservice.exception.BadRequestException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * @author mukund chavali
 * This class is used for asynchronous rest calls  between services
 *
 */

@Service
public class HttpMessaging  {
    final RestTemplate restTemplate;

    @Autowired
    public HttpMessaging(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Async
    public boolean sendHttpMessage(String url,HttpMethod httpMethod, Map<String,List<String>> param){
        HttpHeaders header=new HttpHeaders();

        HttpEntity h=new HttpEntity(param);
        if(httpMethod==HttpMethod.GET){
            return restTemplate.exchange(url,httpMethod,null,Boolean.class).getBody();
        }
        return restTemplate.exchange(url, httpMethod, h, Boolean.class).getBody();
    }
//
//    /**
//     * @author praveen
//     * @param param1
//     * @param param2
//     * @param url
//     * param1,param2 can be any String. For sending a request to cart-service for adding an item
//     * param1-> userId
//     * param2 -> courseId
//     * url :http://cart-service/addItem
//     * For Sending a post request to mentioned url
//     * TODO Make it more generic
//     */
//    public boolean post(Map<String,List<String>> param, String url) {
//
//        try (CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault()) {
//            // Start the client
//            httpclient.start();
//            // Execute the POST request to the other micro-service(s)?
//            // TODO: You should use the "completed" callback handler below to do the next operation
//            final HttpPost request = new HttpPost(url);
//            HttpEntity httpEntity=new BasicHttpEntity();
//
//            request.setHeader("Accept", "application/json");
//            request.setHeader("Content-type", "application/json");
//            request.setEntity(httpEntity);
//            httpclient.execute(request, new FutureCallback<HttpResponse>() {
//
//                public void completed(final HttpResponse response2) {
//                    try{
//                        if(response2.getEntity().getContent()==null){
//                            throw new BadRequestException("Enrollment Failed");
//                        }
//                    }
//                    catch(IOException e){
//                        e.printStackTrace();
//                    }
//                    System.out.println(request.getRequestLine() + "->" + response2.getStatusLine());
//
//                }
//
//                public void failed(final Exception ex) {
//                    System.out.println(request.getRequestLine() + "->" + ex);
//                    throw new BadRequestException(" Request Failed");
//
//
//                }
//
//                public void cancelled() {
//
//                    System.out.println(request.getRequestLine() + " cancelled");
//                    throw new BadRequestException("Request Cancelled");
//                }
//
//            });
//
//        }
//
//        catch (UnsupportedEncodingException e) {
//
//            e.printStackTrace();
//            return false;
//        }
//        catch(BadRequestException e){
//            e.printStackTrace();
//            return false;
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//

}

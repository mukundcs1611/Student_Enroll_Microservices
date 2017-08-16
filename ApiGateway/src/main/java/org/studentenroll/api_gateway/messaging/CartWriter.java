package org.studentenroll.api_gateway.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import java.util.Map;

@MessagingGateway
public interface CartWriter{

    @Gateway(requestChannel="output")
    void write(Map<String, String> param);
}

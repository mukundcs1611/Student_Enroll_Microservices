package org.studentenroll.cartservice.messaging.queue;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import java.util.List;
import java.util.Map;

/**
 * @author chavali
 **/
@Deprecated
@MessagingGateway
public interface Writer {

    @Gateway(requestChannel = "cartOutput")
    void write(Map<String, List<String>> param);

}

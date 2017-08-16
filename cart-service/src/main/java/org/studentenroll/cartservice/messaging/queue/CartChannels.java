package org.studentenroll.cartservice.messaging.queue;

import org.jboss.logging.Message;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

//For Message Queue
@Deprecated
public interface CartChannels {
    @Input
    SubscribableChannel cartInput();
    @Output
    MessageChannel courseOutput(); //to Course
    @Output
    MessageChannel userOutput(); //to User
}


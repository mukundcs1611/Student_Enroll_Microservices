package org.studentenroll.courseservice.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author chavali
 **/
public interface CourseChannels {
    @Input
    SubscribableChannel enrollInput(); //On Checkout From Cart
}




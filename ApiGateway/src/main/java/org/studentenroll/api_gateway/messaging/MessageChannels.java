package org.studentenroll.api_gateway.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;


public interface  MessageChannels{
    String cart_Channel_name="cartOutput";
    String course_channel_name="courseOutput";
    String user_channel_name="userCourse";
    @Output
    MessageChannel cartOutput();
    @Output
    MessageChannel courseOutput();
    @Output
    MessageChannel userOutput();

}

package org.ljl.look.activity.messaging;

import org.ljl.look.activity.configuration.ConstConfig;
import org.ljl.look.activity.entity.TopicFocus;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicFocusMessaging {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(TopicFocus topicFocus) {
        rabbitTemplate.convertAndSend(ConstConfig.QUEUE_TOPIC_FOCUS, topicFocus);
    }

}

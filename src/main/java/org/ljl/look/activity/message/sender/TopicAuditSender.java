package org.ljl.look.activity.message.sender;

import org.ljl.look.activity.configuration.ConstConfig;
import org.ljl.look.activity.entity.TopicAudit;
import org.ljl.look.activity.util.JsonTool;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicAuditSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(TopicAudit topicAudit) {
        rabbitTemplate.convertAndSend(
                ConstConfig.QUEUE_TOPIC_AUDIT,
                JsonTool.toJson(topicAudit)
        );
    }

}

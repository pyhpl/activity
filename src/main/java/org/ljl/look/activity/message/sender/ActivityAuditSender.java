package org.ljl.look.activity.message.sender;

import org.ljl.look.activity.configuration.ConstConfig;
import org.ljl.look.activity.entity.ActivityAudit;
import org.ljl.look.activity.message.wrapper.MessageWrapper;
import org.ljl.look.activity.util.JsonTool;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivityAuditSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendToAdd(ActivityAudit activityAudit) {
        rabbitTemplate.convertAndSend(
                ConstConfig.QUEUE_ACTIVITY_AUDIT,
                JsonTool.toJson(
                        MessageWrapper.builder().method(MessageWrapper.MessageMethod.POST).body(activityAudit).build()
                )
        );
    }

}

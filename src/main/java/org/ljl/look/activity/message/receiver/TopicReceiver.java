package org.ljl.look.activity.message.receiver;

import com.fasterxml.jackson.core.type.TypeReference;
import org.ljl.look.activity.configuration.ConstConfig;
import org.ljl.look.activity.entity.Topic;
import org.ljl.look.activity.message.wrapper.Message;
import org.ljl.look.activity.service.TopicService;
import org.ljl.look.activity.util.JsonTool;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = ConstConfig.QUEUE_TOPIC)
public class TopicReceiver {

    @Autowired
    private TopicService topicService;

    @RabbitHandler
    public void process(String topicMessageJson) {
        Message<Topic> topicMessage = JsonTool.fromJson(topicMessageJson, new TypeReference<Message<Topic>>() {});
        switch (topicMessage.getMethod()) {
            case PUT:
                topicService.update(topicMessage.getBody());
                break;
        }
    }

}

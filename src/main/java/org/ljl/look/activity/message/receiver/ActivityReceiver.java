package org.ljl.look.activity.message.receiver;

import com.fasterxml.jackson.core.type.TypeReference;
import org.ljl.look.activity.configuration.ConstConfig;
import org.ljl.look.activity.entity.Activity;
import org.ljl.look.activity.message.wrapper.MessageWrapper;
import org.ljl.look.activity.service.ActivityService;
import org.ljl.look.activity.util.JsonTool;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = ConstConfig.QUEUE_ACTIVITY)
public class ActivityReceiver {

    @Autowired
    private ActivityService activityService;

    @RabbitHandler
    public void process(String activityMessageJson) {
        MessageWrapper<Activity> activityMessageWrapper = JsonTool.fromJson(activityMessageJson, new TypeReference<MessageWrapper<Activity>>() {});
        switch (activityMessageWrapper.getMethod()) {
            case PUT:
                activityService.update(activityMessageWrapper.getBody());
                break;
        }
    }

}

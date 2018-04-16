package org.ljl.look.activity.aspect.service;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.ljl.look.activity.configuration.ConstConfig;
import org.ljl.look.activity.entity.Activity;
import org.ljl.look.activity.entity.ActivityImage;
import org.ljl.look.activity.entity.ParentTopic;
import org.ljl.look.activity.entity.Topic;
import org.ljl.look.activity.util.UuidTool;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
@Slf4j
public class CommonAttributeAspect {

    @Pointcut("execution(public * org.ljl.look.activity.service.TopicService.add(..))")
    public void addTopic(){}

    @Pointcut("execution(public * org.ljl.look.activity.service.ActivityService.add(..))")
    public void addActivity(){}

    @Pointcut("execution(public * org.ljl.look.activity.service.ParentTopicService.add(..))")
    public void addParentTopic(){}

    @Pointcut("execution(public * org.ljl.look.activity.service.ActivityImageService.add(..))")
    public void addActivityImage(){}

    @Before("addTopic()||addActivity()||addParentTopic()||addActivityImage()")
    public void doBeforeAdd(JoinPoint joinPoint) throws Exception {
        Object arg = joinPoint.getArgs()[0];
        if (arg instanceof Topic) { // topic
            Topic topic = (Topic) arg;
            topic.setUuid(UuidTool.getValue());
            topic.setCreateDate(new Date());
            topic.setValid(ConstConfig.VALID);
        } else if (arg instanceof Activity) { // activity
            Activity activity = (Activity) arg;
            activity.setUuid(UuidTool.getValue());
            activity.setPublishDate(new Date());
            activity.setValid(ConstConfig.VALID);
            // activity images的处理
            activity.getActivityImages().forEach(activityImage ->
                activityImage.setActivityUuid(activity.getUuid())
            );
        } else if (arg instanceof ParentTopic) { // parent topic
            ParentTopic parentTopic = (ParentTopic) arg;
            parentTopic.setUuid(UuidTool.getValue());
        } else if (arg instanceof ActivityImage) { // activity image
            ActivityImage activityImage = (ActivityImage) arg;
            activityImage.setUuid(UuidTool.getValue());
            activityImage.setValid(ConstConfig.VALID);
        }
    }
}















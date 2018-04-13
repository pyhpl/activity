package org.ljl.look.activity.aspect.controller;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.ljl.look.activity.entity.Activity;
import org.ljl.look.activity.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class OpenIdAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Pointcut("execution(public * org.ljl.look.activity.controller.TopicController.post(..))")
    public void postTopic(){}

    @Pointcut("execution(public * org.ljl.look.activity.controller.ActivityController.post(..))")
    public void postActivity(){}

    @Before("postTopic()||postActivity()")
    public void doBeforeWeavingOpenId(JoinPoint joinPoint) throws Exception {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String openId = stringRedisTemplate.opsForValue().get(request.getHeader("token"));
        Arrays.stream(joinPoint.getArgs()).forEach(arg -> {
            if (arg instanceof Topic) {
                ((Topic) arg).setCreateUser(openId);
            } else if (arg instanceof Activity) {
                ((Activity) arg).setPublishUser(openId);
            }
        });
    }

}

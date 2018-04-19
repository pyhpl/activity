package org.ljl.look.activity.controller;

import org.ljl.look.activity.entity.Topic;
import org.ljl.look.activity.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/api/topic/s/hot")
    @ResponseStatus(HttpStatus.OK)
    public List<Topic> getHot(@RequestParam String pageInfoJsonStr) {
        return topicService.getHotTopic(pageInfoJsonStr);
    }

    @GetMapping("/api/topic")
    @ResponseStatus(HttpStatus.OK)
    public Topic get(@RequestParam String uuid) {
        return topicService.getByUuid(uuid);
    }

    @GetMapping("/api/topic/s")
    @ResponseStatus(HttpStatus.OK)
    public List<Topic> gets(@RequestParam(required = false) String parentTopicUuid,
                            @RequestParam(required = false) String uuidList,
                            @RequestParam(required = false) String key) {
        if (parentTopicUuid != null) { // 根据父主题获取
            return topicService.getByParentTopicUuid(parentTopicUuid);
        } else if (uuidList != null) { // 根据uuid列表获取
            return topicService.getByUuidList(uuidList);
        } else if(key != null) {
            return topicService.getByKey(key);
        } else {
            return null;
        }
    }

    @PostMapping("/api/topic")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpHeaders post(@Validated @RequestBody Topic topic) {
        topicService.add(topic);
        return new HttpHeaders() {{
            set("uuid", topic.getUuid());
        }};
    }

    @GetMapping("/api/user/topic/s")
    @ResponseStatus(HttpStatus.OK)
    public List<Topic> getsByCreateUser(@RequestHeader("token") String token) {
        return topicService.getsByCreateUser(stringRedisTemplate.opsForValue().get(token));
    }

}

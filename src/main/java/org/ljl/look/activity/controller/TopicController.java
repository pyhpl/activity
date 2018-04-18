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
                            @RequestParam(required = false) String uuidList) {
        if (parentTopicUuid != null) {
            return topicService.getByParentTopicUuid(parentTopicUuid);
        } else if (uuidList != null) {
            return topicService.getByUuidList(uuidList);
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
    public List<Topic> getsByFromUser(@RequestHeader("token") String token) {
        return topicService.getsByFromUser(stringRedisTemplate.opsForValue().get(token));
    }

}

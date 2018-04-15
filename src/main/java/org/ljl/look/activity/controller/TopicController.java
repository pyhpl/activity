package org.ljl.look.activity.controller;

import org.ljl.look.activity.entity.Topic;
import org.ljl.look.activity.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping("s")
    @ResponseStatus(HttpStatus.OK)
    public List<Topic> gets(@RequestParam String parentTopicUuid) {
        return topicService.getByParentTopicUuid(parentTopicUuid);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Topic get(@RequestParam String uuid) {
        return topicService.getByUuid(uuid);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpHeaders post(@Validated @RequestBody Topic topic) {
        topicService.add(topic);
        return new HttpHeaders() {{
            set("uuid", topic.getUuid());
        }};
    }
}

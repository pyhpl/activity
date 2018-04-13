package org.ljl.look.activity.controller;

import org.ljl.look.activity.entity.ParentTopic;
import org.ljl.look.activity.service.ParentTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parentTopic")
public class ParentTopicController {

    @Autowired
    private ParentTopicService parentTopicService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpHeaders post(@Validated @RequestBody ParentTopic parentTopic) {
        parentTopicService.add(parentTopic);
        return new HttpHeaders() {{
            set("uuid", parentTopic.getUuid());
        }};
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void put(@Validated @RequestBody ParentTopic parentTopic) {
        parentTopicService.change(parentTopic);
    }

    @GetMapping("s")
    @ResponseStatus(HttpStatus.OK)
    public List<ParentTopic> gets() {
        return parentTopicService.getAll();
    }

}

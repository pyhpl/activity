package org.ljl.look.activity.controller;

import org.ljl.look.activity.entity.Activity;
import org.ljl.look.activity.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ActivityController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/api/activity")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpHeaders post(@Validated @RequestBody Activity activity) {
        activityService.add(activity);
        return new HttpHeaders() {{
            set("uuid", activity.getUuid());
        }};
    }

    @GetMapping("/api/activity")
    @ResponseStatus(HttpStatus.OK)
    public List<Activity> getsByTag(@RequestParam String tag) {
        return activityService.getByFuzzyMatching(tag);
    }

    @GetMapping("/api/activity/s")
    @ResponseStatus(HttpStatus.OK)
    public List<Activity> gets(@RequestParam(required = false) String uuidList) {
        if (uuidList != null) {
           return activityService.getByUuidList(uuidList);
        }
        return null;
    }

    @GetMapping("/api/user/activity/s")
    @ResponseStatus(HttpStatus.OK)
    public List<Activity> getsByFromUser(@RequestHeader("token") String token) {
        return activityService.getsByFromUser(stringRedisTemplate.opsForValue().get(token));
    }
}

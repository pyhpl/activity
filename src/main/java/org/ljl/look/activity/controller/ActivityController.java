package org.ljl.look.activity.controller;

import org.ljl.look.activity.entity.Activity;
import org.ljl.look.activity.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpHeaders post(@Validated @RequestBody Activity activity) {
        activityService.add(activity);
        return new HttpHeaders() {{
            set("uuid", activity.getUuid());
        }};
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Activity> getsByTag(@RequestParam String tag) {
        return activityService.getByFuzzyMatching(tag);
    }
}

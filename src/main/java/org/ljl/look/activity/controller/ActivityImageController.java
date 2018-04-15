package org.ljl.look.activity.controller;

import org.ljl.look.activity.entity.ActivityImage;
import org.ljl.look.activity.service.ActivityImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/activity-image")
public class ActivityImageController {

    @Autowired
    private ActivityImageService activityImageService;

    @GetMapping("")
    public List<ActivityImage> getsByActivityUuid(@RequestParam String activityUuid) {
        return activityImageService.getsByActivityUuid(activityUuid);
    }
}

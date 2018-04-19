package org.ljl.look.activity.service;

import org.ljl.look.activity.entity.Activity;
import org.ljl.look.activity.mapper.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ActivityService {

    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ActivityImageService activityImageService;

    public void add(Activity activity) {
        activity.getActivityImages().forEach(activityImageService::add);
        activityMapper.insert(activity);
    }

    public List<Activity> getByFuzzyMatching(String key) {
        return activityMapper.selectsByLike(key);
    }

    public List<Activity> getByUuidList(String uuidList) {
        return Arrays.stream(uuidList.split(","))
                .map(uuid -> activityMapper.selectByUuid(uuid))
                .collect(Collectors.toList());
    }

    public List<Activity> getsByPublishUser(String fromUser) {
        return activityMapper.selectByPublishUser(fromUser);
    }

    public List<Activity> getsByKey(String key) {
        return null;
    }
}

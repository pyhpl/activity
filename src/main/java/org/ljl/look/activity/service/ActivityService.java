package org.ljl.look.activity.service;

import org.ljl.look.activity.entity.Activity;
import org.ljl.look.activity.mapper.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

}

package org.ljl.look.activity.service;

import org.ljl.look.activity.entity.Activity;
import org.ljl.look.activity.mapper.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    public void add(Activity activity) {
        activityMapper.insert(activity);
    }

}

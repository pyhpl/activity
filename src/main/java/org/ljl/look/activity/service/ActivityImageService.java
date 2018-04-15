package org.ljl.look.activity.service;

import org.ljl.look.activity.entity.ActivityImage;
import org.ljl.look.activity.mapper.ActivityImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActivityImageService {

    @Autowired
    private ActivityImageMapper activityImageMapper;

    public void add(ActivityImage activityImage) {
        activityImageMapper.insert(activityImage);
    }

    public List<ActivityImage> getsByActivityUuid(String activityUuid) {
        return activityImageMapper.selectByActivityUuid(activityUuid);
    }
}

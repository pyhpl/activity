package org.ljl.look.activity.service;

import org.ljl.look.activity.entity.Topic;
import org.ljl.look.activity.feign.UserServiceFeign;
import org.ljl.look.activity.mapper.TopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TopicService {

    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private UserServiceFeign userServiceFeign;

    public void add(Topic topic) {
        topicMapper.insert(topic);
    }

    public List<Topic> getByParentTopicUuid(String parentTopicUuid) {
        return topicMapper.selectByParentTopicUuid(parentTopicUuid);
    }

    public Topic getByUuid(String uuid) {
        return topicMapper.selectByUuid(uuid);
    }

    public List<Topic> getHotTopic(String number) {
//        return userServiceFeign.get("")
        return null;
    }
}

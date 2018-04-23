package org.ljl.look.activity.service;

import org.ljl.look.activity.configuration.ConstConfig;
import org.ljl.look.activity.entity.Topic;
import org.ljl.look.activity.entity.TopicAudit;
import org.ljl.look.activity.feign.UserServiceFeign;
import org.ljl.look.activity.mapper.TopicMapper;
import org.ljl.look.activity.message.sender.TopicAuditSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TopicService {

    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private UserServiceFeign userServiceFeign;
    @Autowired
    private TopicAuditSender topicAuditSender;

    public void add(Topic topic) {
        topicMapper.insert(topic);
        topicAuditSender.sendToAdd(
                TopicAudit.builder().topicUuid(topic.getUuid()).state(ConstConfig.WAITING_AUDIT_STATE).build()
        );
    }

    public List<Topic> getByParentTopicUuid(String parentTopicUuid) {
        return topicMapper.selectByParentTopicUuid(parentTopicUuid);
    }

    public Topic getByUuid(String uuid) {
        return topicMapper.selectByUuid(uuid);
    }

    public List<Topic> getHotTopic(String pageInfoJsonStr) {
        return userServiceFeign.getHotTopicFocuses(pageInfoJsonStr).stream()
                .map(topicFocus -> topicMapper.selectByUuidAndValid(topicFocus.getTopicUuid()))
                .collect(Collectors.toList());
    }

    public List<Topic> getsByCreateUser(String fromUser) {
        return topicMapper.selectByCreateUser(fromUser);
    }

    public List<Topic> getByUuidList(String uuidList) {
        return Arrays.stream(uuidList.split(","))
                .map(uuid -> topicMapper.selectByUuidAndValid(uuid))
                .collect(Collectors.toList());
    }

    public List<Topic> getByKey(String key) {
        return topicMapper.selectByLike(key);
    }

    public void update(Topic topic) {
        topicMapper.update(topic);
    }
}

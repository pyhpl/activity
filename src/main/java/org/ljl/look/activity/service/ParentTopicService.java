package org.ljl.look.activity.service;

import com.github.pagehelper.PageHelper;
import org.ljl.look.activity.entity.ParentTopic;
import org.ljl.look.activity.mapper.ParentTopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ParentTopicService {

    @Autowired
    private ParentTopicMapper parentTopicMapper;

    public void add(ParentTopic parentTopic) {
        parentTopicMapper.insert(parentTopic);
    }

    public void change(ParentTopic parentTopic) {
        parentTopicMapper.update(parentTopic);
    }

    public List<ParentTopic> getAll() {
        return parentTopicMapper.selectAll();
    }
}

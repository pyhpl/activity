package org.ljl.look.activity.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ljl.look.activity.entity.Topic;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TopicMapper {
    @Insert("INSERT INTO topic VALUES(#{uuid}::uuid, #{name}, #{description}, #{createUser}, #{createDate}, #{valid})")
    void insert(Topic topic);

    @Select("Select * FROM topic WHERE parent_topic_uuid=#{parentTopicUuid}")
    List<Topic> selectByParentTopicUuid(@Param("parentTopicUuid") String parentTopicUuid);
}

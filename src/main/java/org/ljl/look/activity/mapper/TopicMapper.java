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
    @Insert("INSERT INTO topic (uuid, name, description, image, create_user, create_date, valid) " +
            "VALUES(#{uuid}::uuid, #{name}, #{description}, #{image}, #{createUser}, #{createDate}, #{valid})")
    void insert(Topic topic);

    @Select("Select uuid, name, description, image FROM topic WHERE parent_topic_uuid=#{parentTopicUuid}::uuid")
    List<Topic> selectByParentTopicUuid(@Param("parentTopicUuid") String parentTopicUuid);
}

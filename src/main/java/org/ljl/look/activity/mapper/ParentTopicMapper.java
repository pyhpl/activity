package org.ljl.look.activity.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.ljl.look.activity.entity.ParentTopic;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ParentTopicMapper {

    @Insert("INSERT INTO parent_topic VALUES(#{uuid}::uuid, #{name})")
    void insert(ParentTopic parentTopic);

    @Update("UPDATE parent_topic SET name=#{name} WHERE uuid=#{uuid}::uuid")
    void update(ParentTopic parentTopic);

    @Select("SELECT * FROM parent_topic")
    List<ParentTopic> selectAll();
}

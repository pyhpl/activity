package org.ljl.look.activity.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ljl.look.activity.entity.Activity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ActivityMapper {

    @Insert("INSERT INTO activity VALUES(#{uuid}::uuid, #{title}, #{topicUuid}::uuid, #{detail}, #{school}, #{place}, #{deadline}, #{limitUserCount}, #{contactType}, #{contactRepresent}, #{publishUser}, #{publishDate}, #{valid}), #{likeCount}")
    void insert(Activity activity);

    @Select("SELECT * FROM activity WHERE " +
            "title LIKE '%'||#{key}||'%' OR " +
            "school LIKE '%'||#{key}||'%' OR " +
            "topic_uuid in (" +
            "   SELECT uuid FROM topic WHERE name LIKE '%'||#{key}||'%' OR parent_topic_uuid in (" +
            "       SELECT uuid FROM parent_topic WHERE name LIKE '%'||#{key}||'%'))")
    List<Activity> selectsByLike(@Param("key") String key);
}

package org.ljl.look.activity.mapper;

import org.apache.ibatis.annotations.*;
import org.ljl.look.activity.entity.Activity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ActivityMapper {

    @Insert("INSERT INTO activity VALUES(#{uuid}::uuid, #{title}, #{topicUuid}::uuid, #{detail}, #{school}, #{place}, #{deadline}, #{limitUserCount}, #{contactType}, #{contactRepresent}, #{publishUser}, #{publishDate}, #{valid})")
    void insert(Activity activity);

    @Select("SELECT * FROM activity WHERE uuid=#{uuid}::uuid AND valid=${@org.ljl.look.activity.configuration.ConstConfig@VALID}")
    Activity selectByUuid(@Param("uuid") String uuid);

    @Select("SELECT * FROM activity WHERE publish_user=#{publishUser} AND valid=${@org.ljl.look.activity.configuration.ConstConfig@VALID}")
    List<Activity> selectByPublishUser(@Param("publishUser") String publishUser);

    @Select("SELECT * FROM activity WHERE " +
            "title LIKE '%'||#{key}||'%' OR " +
            "school LIKE '%'||#{key}||'%' OR " +
            "topic_uuid in (" +
            "   SELECT uuid FROM topic WHERE name LIKE '%'||#{key}||'%' OR parent_topic_uuid in (" +
            "       SELECT uuid FROM parent_topic WHERE name LIKE '%'||#{key}||'%'))")
    List<Activity> selectsByLike(@Param("key") String key);
}

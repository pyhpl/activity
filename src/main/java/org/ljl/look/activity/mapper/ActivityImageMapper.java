package org.ljl.look.activity.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ljl.look.activity.entity.ActivityImage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ActivityImageMapper {
    @Insert("INSERT INTO activity_image VALUES(#{uuid}::uuid, #{image}, #{activityUuid}::uuid, #{valid})")
    void insert(ActivityImage activityImage);

    @Select("SELECT * FROM activity_image WHERE activity_uuid = #{activityUuid}::uuid")
    List<ActivityImage> selectByActivityUuid(@Param("activityUuid") String activityUuid);
}

package org.ljl.look.activity.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.ljl.look.activity.entity.ActivityImage;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ActivityImageMapper {
    @Insert("INSERT INTO activity_image VALUES(#{uuid}::uuid, #{image}, #{activityUuid}::uuid, #{valid})")
    void insert(ActivityImage activityImage);
}

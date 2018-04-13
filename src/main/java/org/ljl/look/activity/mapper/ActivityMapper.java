package org.ljl.look.activity.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.ljl.look.activity.entity.Activity;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ActivityMapper {

    @Insert("INSERT INTO activity VALUES(#{uuid}::uuid, #{title}, #{topicUuid}::uuid, #{detail}, #{school}, #{place}, #{deadline}, #{limitUserCount}, #{contactType}, #{contactRepresent}, #{publishUser}, #{publishDate}, #{valid})")
    void insert(Activity activity);
}

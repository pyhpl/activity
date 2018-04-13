package org.ljl.look.activity.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.ljl.look.activity.entity.Activity;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ActivityMapper {

    @Insert("INSERT INTO activity VALUES(#{uuid}::uuid, #{title}, #{topic_uuid}::uuid, #{detail}, #{school}, #{place}, #{deadline}, #{limit_user_count}, #{contact_type}, #{contact_represent}, #{publish_user}, #{publish_date}, #{valid})")
    void insert(Activity activity);
}

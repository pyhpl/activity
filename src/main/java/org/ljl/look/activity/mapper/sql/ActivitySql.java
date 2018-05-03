package org.ljl.look.activity.mapper.sql;

import org.apache.ibatis.jdbc.SQL;
import org.ljl.look.activity.entity.Activity;
import org.springframework.stereotype.Component;

@Component
public class ActivitySql {
    public String update(Activity activity) {
        return new SQL() {{
            UPDATE("activity");
            SET("valid=#{valid}");
            WHERE("uuid=#{uuid}::uuid");
        }}.toString();
    }
}

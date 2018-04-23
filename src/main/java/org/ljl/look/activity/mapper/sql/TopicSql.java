package org.ljl.look.activity.mapper.sql;

import org.apache.ibatis.jdbc.SQL;
import org.ljl.look.activity.entity.Topic;
import org.springframework.stereotype.Component;

@Component
public class TopicSql {
    public String update(Topic topic) {
        return new SQL() {{
            UPDATE("topic");
            if (topic.getParentTopicUuid() != null) {
                SET("parent_topic_uuid=#{parentTopicUuid}::uuid");
            }
            SET("valid=#{valid}");
            WHERE("uuid=#{uuid}::uuid");
        }}.toString();
    }
}

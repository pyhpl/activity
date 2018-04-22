package org.ljl.look.activity.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ConstConfig {
    public static final String DEFAULT_VISITOR_OPEN_ID = "os0pV4xLjlAf20xsQkycdWk-vVe0";
    public static final String DEFAULT_VISITOR_TOKEN = "c13eb7a0-21ab-4e74-b121-970c030f7071";
    public static final short VALID = (short) 1;
    public static final short UNVALID = (short) 0;

    /** audit state */
    public static final short WAITING_AUDIT_STATE = (short) 0;
    public static final short PASS_AUDIT_STATE = (short) 1;
    public static final short FAIL_AUDIT_STATE = (short) -1;

    /** page info */
    public static final String PAGE_INFO_JSON_STR = "pageInfoJsonStr";
    public static final String PAGE_NUM = "pageNum";
    public static final String PAGE_SIZE = "pageSize";
    public static final String DEFAULT_PAGE_NUM = "1";
    public static final String DEFAULT_PAGE_SIZE = "10";

    /** rabbit mq */
    public static final String QUEUE_TOPIC_AUDIT = "queueTopicAudit";
    public static final String QUEUE_ACTIVITY_AUDIT = "queueActivityAudit";
}

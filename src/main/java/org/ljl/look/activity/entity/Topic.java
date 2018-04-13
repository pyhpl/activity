package org.ljl.look.activity.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    private String uuid;
    private String name;
    private String description;
    private String createUser;
    private Date createDate;
    private short valid;
    private String parentTopicUuid;
}

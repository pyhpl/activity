package org.ljl.look.activity.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    private String uuid;
    private String title;
    private String topicUuid;
    private String detail;
    private String school;
    private String place;
    private Date deadline;
    private int limitUserCount;
    private short contactType;
    private String contactRepresent;
    private String publishUser;
    private Date publishDate;
    private short valid;
}

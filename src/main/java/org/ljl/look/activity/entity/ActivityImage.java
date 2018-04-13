package org.ljl.look.activity.entity;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityImage {
    private String uuid;
    private String image;
    private String activityUuid;
    private short valid;
}

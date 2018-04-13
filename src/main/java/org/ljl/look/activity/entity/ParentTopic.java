package org.ljl.look.activity.entity;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParentTopic {
    @NotNull
    private String uuid;
    @NotNull
    private String name;
}

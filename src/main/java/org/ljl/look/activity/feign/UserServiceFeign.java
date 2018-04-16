package org.ljl.look.activity.feign;

import org.ljl.look.activity.entity.TopicFocus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
@FeignClient("user")
public interface UserServiceFeign {

    // ********************** /api/topic-focus ******************** //
    @GetMapping("/api/topic-focus")
    List<TopicFocus> get(@RequestParam String orderBy, @RequestParam String order, @RequestParam int limit);
}

package com.sfs.learning.Client;

import com.sfs.framework.client.SfsServiceList;
import com.sfs.framework.domain.course.TeachplanMediaPub;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 远程调用搜索服务的接口
 * @author yjw
 * @date 2020/4/25 18:43
 */
@FeignClient(value = SfsServiceList.SFS_SERVICE_SEARCH)
public interface CourseSearchClient {

    /**
     * 根据课程计划id获取课程计划媒资发布信息
     * @param teachplanId
     * @return
     */
    @GetMapping("/search/course/getmedia/{teachplanId}")
    public TeachplanMediaPub getMedia(@PathVariable("teachplanId") String teachplanId);


}

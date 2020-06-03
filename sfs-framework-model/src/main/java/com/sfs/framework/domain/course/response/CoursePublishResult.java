package com.sfs.framework.domain.course.response;

import com.sfs.framework.model.response.ResponseResult;
import com.sfs.framework.model.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 课程详情页面预览的实体类
 * 包含预览url
 * @NoArgsConstructor 无参构造注解
 * @author yjw
 * @date 2019/12/17 9:56
 */
@Data
@ToString
@NoArgsConstructor
public class CoursePublishResult extends ResponseResult {

    String previewUrl;

    public CoursePublishResult(ResultCode resultCode,String previewUrl){
        super(resultCode);
        this.previewUrl = previewUrl;
    }

}

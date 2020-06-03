package com.sfs.framework.domain.course.request;

import com.sfs.framework.model.request.RequestData;
import lombok.Data;
import lombok.ToString;

/**
 * 课程管理扩展实体类
 * 扩展的字段为查询条件
 * @author mrt on 2018/4/13.
 */
@Data
@ToString
public class CourseListRequest extends RequestData {

    /**
     * 公司Id
     */
    private String companyId;

}

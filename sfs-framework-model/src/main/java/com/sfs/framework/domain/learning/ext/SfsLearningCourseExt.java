package com.sfs.framework.domain.learning.ext;

import com.sfs.framework.domain.learning.SfsLearningCourse;
import lombok.Data;
import lombok.ToString;

/**
 * @author yjw
 * @date 2020/5/18 1:17
 */
@Data
@ToString
public class SfsLearningCourseExt extends SfsLearningCourse {

    private String courseName;

    private String teachplanName;


}

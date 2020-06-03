package com.sfs.framework.domain.learning.ext;

import com.sfs.framework.domain.learning.SfsLearningList;
import lombok.Data;
import lombok.ToString;

/**
 * 收藏课程扩展实体类
 * @author yjw
 * @date 2020/5/18 0:43
 */
@Data
@ToString
public class SfsLearningListExt extends SfsLearningList {

    private String courseName;

    private String pic;


}

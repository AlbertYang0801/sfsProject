package com.sfs.framework.domain.course.ext;

import com.sfs.framework.domain.course.Teachplan;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 课程计划查询信息对应实体类
 * @author admin on 2018/2/7.
 */
@Data
@ToString
public class TeachplanNode extends Teachplan {

    /**
     * 课程计划子节点
     */
    List<TeachplanNode> children;

    /**
     * 媒资文件id
     */
    private String mediaId;

    /**
     * 媒资文件原始名
     */
    private String mediaFileOriginalName;


}

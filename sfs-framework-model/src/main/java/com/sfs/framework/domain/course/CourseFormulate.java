package com.sfs.framework.domain.course;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 定制课程路线
 * @author yjw
 * @date 2020/5/11 22:48
 */
@Data
@ToString
@Entity
@Table(name="course_formulate")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class CourseFormulate {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;
    /**
     * 定制名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 建议学习时长
     */
    private String studytime;
    /**
     * 定制路线详情
     */
    private String details;

    @Column(name="create_time")
    private Date createTime;

    private String pic;

}

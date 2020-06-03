package com.sfs.framework.domain.course;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 课程基本信息实体类
 * @author  admin on 2018/2/10.
 */
@Data
@ToString
@Entity
@Table(name="course_base")
//@GenericGenerator(name = "jpa-assigned", strategy = "assigned")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class CourseBase implements Serializable {

    private static final long serialVersionUID = -916357110051689486L;
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 适用人群
     */
    private String users;
    /**
     * 课程大分类
     */
    private String mt;
    /**
     * 课程小分类
     */
    private String st;
    /**
     * 课程等级
     */
    private String grade;
    /**
     * 学习模式
     */
    private String studymodel;
    /**
     * 授课模式
     */
    private String teachmode;
    /**
     * 课程介绍
     */
    private String description;
    /**
     * 课程状态(202001:未发布;202002：已发布)
     */
    private String status;
    /**
     * 教育机构
     */
    @Column(name="company_id")
    private String companyId;
    /**
     * 创建用户
     */
    @Column(name="user_id")
    private String userId;

}

package com.sfs.framework.domain.course;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 课程发布索引实体类
 * @author  admin on 2018/2/10.
 */
@Data
@ToString
@Entity
@Table(name="course_pub")
@GenericGenerator(name = "jpa-assigned", strategy = "assigned")
public class CoursePub implements Serializable {
    private static final long serialVersionUID = -916357110051689487L;
    @Id
    @GeneratedValue(generator = "jpa-assigned")
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
     * 大分类
     */
    private String mt;
    /**
     * 小分类
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
     * 教育模式
     */
    private String teachmode;
    /**
     * 课程介绍
     */
    private String description;
    /**
     * 图片地址
     */
    private String pic;
    /**
     * 时间戳Logstash同步使用
     */
    private Date timestamp;
    /**
     * 收费规则，对应数据字典
     */
    private String charge;
    /**
     * 有效性，对应数据字典
     */
    private String valid;
    /**
     * 咨询qq
     */
    private String qq;
    /**
     * 价格
     */
    private Float price;
    /**
     * 原始价格
     */
    private Float price_old;
    /**
     * 过期时间
     */
    private String expires;
    /**
     * 课程计划
     */
    private String teachplan;
    /**
     * 课程发布时间
     */
    @Column(name="pub_time")
    private String pubTime;
}

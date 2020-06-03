package com.sfs.framework.domain.course;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 课程营销
 * Created by admin on 2018/2/10.
 */
@Data
@ToString
@Entity
@Table(name="course_market")
@GenericGenerator(name = "jpa-assigned", strategy = "assigned")
public class CourseMarket implements Serializable {
    private static final long serialVersionUID = -916357110051689486L;
    @Id
    @GeneratedValue(generator = "jpa-assigned")
    @Column(length = 32)
    private String id;
    /**
     * 课程收费规则，对应数据字典
     * 免费:203001;收费:203002
     */
    private String charge;
    /**
     * 课程有效性，对应数据字典
     * 永久有效:204001;指定时间段有效:204002
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
     * 原价
     */
    private Float price_old;

    /**
     * 过期时间
     */
    private Date expires;

    /**
     * 课程有效期-开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 课程有效期-结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

}

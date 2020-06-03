package com.sfs.framework.domain.course;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author admin on 2018/2/10.
 */
@Data
@ToString
@Entity
@Table(name="course_pic")
@GenericGenerator(name = "jpa-assigned", strategy = "assigned")
public class CoursePic implements Serializable {
    private static final long serialVersionUID = -916357110051689486L;

    /**
     * 课程id
     */
    @Id
    @GeneratedValue(generator = "jpa-assigned")
    private String courseid;

    /**
     * 图片地址
     */
    private String pic;

}

package com.sfs.framework.domain.course;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 课程计划和媒资绑定
 * @author  admin on 2018/2/7.
 */
@Data
@ToString
@Entity
@Table(name="teachplan_media")
@GenericGenerator(name = "jpa-assigned", strategy = "assigned")
public class TeachplanMedia implements Serializable {
    private static final long serialVersionUID = -916357110051689485L;

    /**
     * 课程计划id
     */
    @Id
    @GeneratedValue(generator = "jpa-assigned")
    @Column(name="teachplan_id")
    private String teachplanId;

    /**
     * 媒资id
     */
    @Column(name="media_id")
    private String mediaId;

    /**
     * 媒资文件原名
     */
    @Column(name="media_fileoriginalname")
    private String mediaFileOriginalName;

    /**
     * 媒资文件url
     */
    @Column(name="media_url")
    private String mediaUrl;
    /**
     * 课程id
     */
    @Column(name="courseid")
    private String courseId;

}

package com.sfs.framework.domain.course;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 课程分类信息实体类
 * @author  admin on 2018/2/7.
 */
@Data
@ToString
@Entity
@Table(name="category")
@GenericGenerator(name = "jpa-assigned", strategy = "assigned")
//@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Category implements Serializable {
    private static final long serialVersionUID = -906357110051689484L;
    @Id
    @GeneratedValue(generator = "jpa-assigned")
    @Column(length = 32)
    private String id;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 分类标签（默认和名称一样）
     */
    private String label;
    /**
     * 父节点id
     */
    private String parentid;
    /**
     * 是否显示
     */
    private String isshow;
    /**
     * 排序字段
     */
    private Integer orderby;
    /**
     * 是否叶子
     */
    private String isleaf;

}

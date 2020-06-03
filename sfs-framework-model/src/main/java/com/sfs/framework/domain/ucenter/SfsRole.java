package com.sfs.framework.domain.ucenter;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by admin on 2018/3/19.
 */
@Data
@ToString
@Entity
@Table(name="sfs_role")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class SfsRole {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;
    @Column(name="role_name")
    private String roleName;
    @Column(name="role_code")
    private String roleCode;
    private String description;
    private String status;
    @Column(name="create_time")
    private Date createTime;
    @Column(name="update_time")
    private Date updateTime;


}

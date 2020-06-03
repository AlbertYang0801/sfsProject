package com.sfs.framework.domain.ucenter;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户企业中间表实体类
 *@author admin on 2018/2/10.
 */
@Data
@ToString
@Entity
@Table(name="sfs_company_user")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class SfsCompanyUser implements Serializable {
    private static final long serialVersionUID = -916357110051689786L;
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;
    @Column(name="company_id")
    private String companyId;
    @Column(name="user_id")
    private String userId;


}

package com.sfs.framework.domain.ucenter.ext;

import com.sfs.framework.domain.ucenter.SfsMenu;
import com.sfs.framework.domain.ucenter.SfsUser;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 用户信息的扩展实体类
 * @author admin on 2018/3/20.
 */
@Data
@ToString
public class SfsUserExt extends SfsUser {

    /**
     * 权限信息
     */
    private List<SfsMenu> permissions;

    /**
     * 企业信息
     */
    private String companyId;

}

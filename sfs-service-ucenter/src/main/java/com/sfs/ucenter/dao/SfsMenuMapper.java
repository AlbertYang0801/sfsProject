package com.sfs.ucenter.dao;

import com.sfs.framework.domain.ucenter.SfsMenu;
import com.sfs.framework.domain.ucenter.ext.SfsMenuExt;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 * 用户菜单权限表操作
 * @author yjw
 * @date 2020/5/10 16:47
 */
@Mapper
public interface SfsMenuMapper  {


    /**
     * 根据用户id查找用户角色，根据用户角色查找用户权限
     * @param userid
     * @return
     */
    List<SfsMenu> selectPermissionByUserId(String userid);


    SfsMenuExt selectMenuList();


}

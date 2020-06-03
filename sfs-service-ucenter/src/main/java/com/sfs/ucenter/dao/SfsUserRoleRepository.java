package com.sfs.ucenter.dao;

import com.sfs.framework.domain.ucenter.SfsUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author yjw
 * @date 2020/5/13 2:49
 */
public interface SfsUserRoleRepository extends JpaRepository<SfsUserRole,String> {

    /**
     * 根据用户id获取用户角色列表
     * @param userId
     * @return
     */
    List<SfsUserRole> findByUserId(String userId);

    int deleteByUserId(String userId);

    int deleteByRoleId(String roleId);


}

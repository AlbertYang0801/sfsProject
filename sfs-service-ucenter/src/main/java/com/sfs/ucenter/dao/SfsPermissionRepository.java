package com.sfs.ucenter.dao;

import com.sfs.framework.domain.ucenter.SfsPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author yjw
 * @date 2020/5/13 22:19
 */
public interface SfsPermissionRepository extends JpaRepository<SfsPermission,String> {

    List<SfsPermission> findByRoleId(String roleId);

    int deleteByRoleId(String roleId);

}

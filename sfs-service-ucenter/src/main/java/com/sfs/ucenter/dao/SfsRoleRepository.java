package com.sfs.ucenter.dao;

import com.sfs.framework.domain.ucenter.SfsRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author yjw
 * @date 2020/5/13 2:19
 */
public interface SfsRoleRepository extends JpaRepository<SfsRole,String> {

    Page<SfsRole> findByRoleNameContainingOrderByCreateTimeDesc(String roleName, Pageable pageable);

    List<SfsRole> findByRoleName(String roleName);

    SfsRole findByRoleCode(String roleCode);

}

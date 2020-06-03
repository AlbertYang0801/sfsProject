package com.sfs.ucenter.dao;

import com.sfs.framework.domain.ucenter.SfsCompanyUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yjw
 * @date 2020/5/6 1:04
 */
public interface SfsCompanyUserRepository extends JpaRepository<SfsCompanyUser, String> {

    /**
     * 根据用户id查询所属企业id
     * @param userId
     * @return
     */
    SfsCompanyUser findByUserId(String userId);

}

package com.sfs.ucenter.dao;

import com.sfs.framework.domain.ucenter.SfsUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户表dao层接口
 * @author yjw
 * @date 2020/5/6 0:53
 */
public interface SfsUserRepository extends JpaRepository<SfsUser,String> {

    /**
     * 根据用户名查找信息
     * @param username
     * @return
     */
    SfsUser findByUsername(String username);

}

package com.sfs.auth.service;

import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Spring Security 进行用户认证返回的用户信息扩展实体类
 * @author yjw
 */
@Data
@ToString
public class UserJwt extends User {

    private String userid;
    private String name;
    private String userpic;
    private String utype;
    private String companyId;

    public UserJwt(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

}

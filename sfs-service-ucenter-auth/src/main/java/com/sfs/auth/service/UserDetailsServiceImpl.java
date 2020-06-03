package com.sfs.auth.service;

import com.sfs.auth.client.UserClient;
import com.sfs.framework.domain.ucenter.SfsMenu;
import com.sfs.framework.domain.ucenter.ext.SfsUserExt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Spring Security认证服务
 * @author yjw
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ClientDetailsService clientDetailsService;

    @Autowired
    UserClient userClient;

    /**
     * 根据用户名进行用户认证
     * 校验用户是否合法
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
        if(authentication==null){
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
            if(clientDetails!=null){
                //客户端密码
                String clientSecret = clientDetails.getClientSecret();
                return new User(username,clientSecret,AuthorityUtils.commaSeparatedStringToAuthorityList(""));
            }
        }
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        //请求用户中心服务查询用户信息
        SfsUserExt userext= userClient.getUserext(username);
        if(userext==null){
            //返回null表示用户不存在,Spring Security会抛出异常
            return null;
        }
        //取出正确密码（hash值）
        String password = userext.getPassword();
        //在jwt令牌中包含用户权限
        List<String> user_permission = new ArrayList<>();
        //获取用户查询返回的权限列表
        List<SfsMenu> permissions = userext.getPermissions();
        for (SfsMenu permission : permissions) {
            //添加权限代码值
            user_permission.add(permission.getCode());
        }
//        user_permission.add("course_find_pic");
//        user_permission.add("course_find_list");
        //将用户权限列表转换为逗号连接的字符串
        String user_permission_string = StringUtils.join(user_permission.toArray(), ",");
        //请求Spring Security进行用户校验并授权(校验用户名和密码是否正确)
        UserJwt userDetails = new UserJwt(username, password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(user_permission_string));
        userDetails.setUserid(userext.getId());
        //用户类型
        userDetails.setUtype(userext.getUtype());
        //所属企业id
        userDetails.setCompanyId(userext.getCompanyId());
        //用户名称
        userDetails.setName(userext.getName());
        //用户头像
        userDetails.setUserpic(userext.getUserpic());
       /* UserDetails userDetails = new org.springframework.security.core.userdetails.User(username,
                password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(""));*/
//                AuthorityUtils.createAuthorityList("course_get_baseinfo","course_get_list"));
        return userDetails;
    }


}

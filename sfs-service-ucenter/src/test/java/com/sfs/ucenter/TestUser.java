package com.sfs.ucenter;

import com.sfs.framework.domain.ucenter.SfsMenu;
import com.sfs.framework.domain.ucenter.ext.SfsMenuExt;
import com.sfs.framework.domain.ucenter.request.QueryUserRequest;
import com.sfs.framework.model.response.QueryResponseResult;
import com.sfs.ucenter.dao.SfsMenuMapper;
import com.sfs.ucenter.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author yjw
 * @date 2020/5/10 17:13
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestUser {

    @Autowired
    private SfsMenuMapper sfsMenuMapper;

    @Autowired
    private UserService userService;

    /**
     * 测试根据用户id获取用户权限
     */
    @Test
    public void getPermissionByUserId(){
        List<SfsMenu> sfsMenus = sfsMenuMapper.selectPermissionByUserId("46");
        System.out.println(sfsMenus);
    }

    @Test
    public void testPwd(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("111111");
        System.out.println(encode);
    }


    @Test
    public void testMenu(){
        SfsMenuExt sfsMenuExt = sfsMenuMapper.selectMenuList();
        System.out.println(sfsMenuExt);
    }

    @Test
    public void testGetUser(){
        QueryUserRequest queryUserRequest = new QueryUserRequest();

        QueryResponseResult userList = userService.getUserList(1, 10, queryUserRequest);
        System.out.println(userList);
    }



}

package com.sfs.api.ucenter;

import com.sfs.framework.domain.ucenter.SfsRole;
import com.sfs.framework.domain.ucenter.SfsUser;
import com.sfs.framework.domain.ucenter.ext.SfsMenuExt;
import com.sfs.framework.domain.ucenter.ext.SfsUserExt;
import com.sfs.framework.domain.ucenter.request.LoginRequest;
import com.sfs.framework.domain.ucenter.request.QueryUserRequest;
import com.sfs.framework.model.response.QueryResponseResult;
import com.sfs.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * 用户中心接口
 * @author yjw
 * @date 2020/5/6 0:43
 */
@Api(value = "用户中心",description = "用户中心管理")
public interface UcenterControllerApi {


    @ApiOperation(value = "获取用户信息")
    public SfsUserExt getUserExt(String usernaem);

    @ApiOperation("用户注册")
    public ResponseResult register(LoginRequest loginRequest);

    @ApiOperation("查询用户列表")
    QueryResponseResult getUserList(int page, int size, QueryUserRequest queryUserRequest);

    @ApiOperation("查询用户")
    SfsUser getUserById(String userId);

    @ApiOperation("修改用户")
    ResponseResult editUser(String userId, SfsUser sfsUser);

    @ApiOperation("删除用户")
    ResponseResult deleteUser(String userId);

    @ApiOperation("用户添加角色")
    ResponseResult addUserRole(String userId,String roleIds);

    @ApiOperation("根据用户id获取拥有的角色")
    List<String> getUserRole(String userId);

    @ApiOperation("修改用户密码")
    ResponseResult editPassword(String userId,String password);

    @ApiOperation("分页查询角色列表")
    QueryResponseResult getRoleList(int page, int size, String roleName);

    @ApiOperation("查询所有角色")
    QueryResponseResult findRoleList();

    @ApiOperation("获取权限列表")
    SfsMenuExt getMenuList();

    @ApiOperation("获取角色的权限")
    List<String> getMenuByRoleId(String roleId);

    @ApiOperation("为角色设置权限")
    ResponseResult setRoleMenu(String roleId,String  menuIds);

    @ApiOperation("编辑角色")
    ResponseResult editRole(String roleId, SfsRole sfsRole);

    @ApiOperation("删除角色")
    ResponseResult deleteRole(String roleId);

    @ApiOperation("添加角色")
    ResponseResult addRole(SfsRole sfsRole);

    @ApiOperation("获得角色信息")
    SfsRole getRole(String id);



}



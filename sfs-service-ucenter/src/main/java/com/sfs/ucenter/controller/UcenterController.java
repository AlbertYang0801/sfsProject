package com.sfs.ucenter.controller;

import com.sfs.api.ucenter.UcenterControllerApi;
import com.sfs.framework.domain.ucenter.SfsRole;
import com.sfs.framework.domain.ucenter.SfsUser;
import com.sfs.framework.domain.ucenter.ext.SfsMenuExt;
import com.sfs.framework.domain.ucenter.ext.SfsUserExt;
import com.sfs.framework.domain.ucenter.request.LoginRequest;
import com.sfs.framework.domain.ucenter.request.QueryUserRequest;
import com.sfs.framework.model.response.QueryResponseResult;
import com.sfs.framework.model.response.ResponseResult;
import com.sfs.ucenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户中心
 *
 * @author yjw
 * @date 2020/5/6 1:25
 */
@RestController
@RequestMapping("/ucenter")
public class UcenterController implements UcenterControllerApi {

    @Autowired
    private UserService userService;

    /**
     * 根据用户名获取用户信息包括权限和企业信息
     *
     * @param username
     * @return
     */
    @GetMapping("/getuserext/{username}")
    @Override
    public SfsUserExt getUserExt(@PathVariable("username") String username) {
        return userService.getUserExt(username);
    }

    /**
     * 注册
     *
     * @param loginRequest
     * @return
     */
    @PostMapping("/register")
    @Override
    public ResponseResult register(@RequestBody LoginRequest loginRequest) {
        return userService.register(loginRequest.getUsername(), loginRequest.getPassword());
    }

    //*******************************************用户相关******************************************************

    /**
     * 分页获取用户信息列表
     * @param page
     * @param size
     * @param queryUserRequest
     * @return
     */
    @PreAuthorize("hasAnyAuthority('sfs_sysmanager_user_view')")
    @GetMapping("/user/list/{page}/{size}")
    @Override
    public QueryResponseResult getUserList(@PathVariable("page") int page, @PathVariable("size") int size,
                                           QueryUserRequest queryUserRequest) {
        return userService.getUserList(page, size, queryUserRequest);
    }

    /**
     * 根据用户id获取用户信息
     * @param userId
     * @return
     */
    @GetMapping("/user/get/{userId}")
    @Override
    public SfsUser getUserById(@PathVariable("userId") String userId) {
        return userService.getUserById(userId);
    }

    /**
     * 更新用户信息
     * @param userId
     * @param sfsUser
     * @return
     */
    @PreAuthorize("hasAnyAuthority('sfs_sysmanager_user_edit')")
    @PutMapping("/user/edit/{userId}")
    @Override
    public ResponseResult editUser(@PathVariable("userId") String userId, @RequestBody SfsUser sfsUser) {
        return userService.editUser(userId, sfsUser);
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @PreAuthorize("hasAnyAuthority('sfs_sysmanager_user_delete')")
    @DeleteMapping("/user/delete/{userId}")
    @Override
    public ResponseResult deleteUser(@PathVariable("userId") String userId) {
        return userService.deleteUser(userId);
    }

    /**
     * 为用户设置角色
     * @param userId
     * @param roleIds
     * @return
     */
    @PreAuthorize("hasAnyAuthority('sfs_sysmanager_user_role')")
    @PostMapping("/user/setrole/{userId}")
    @Override
    public ResponseResult addUserRole(@PathVariable("userId") String userId,@RequestParam("roleIds") String roleIds) {
        return userService.setUserRole(userId,roleIds);
    }

    /**
     * 获取用户拥有的角色
     * @param userId
     * @return
     */
    @GetMapping("/role/getUserRole/{userId}")
    @Override
    public List<String> getUserRole(@PathVariable("userId") String userId) {
        return userService.getUserRole(userId);
    }

    /**
     * 修改密码
     * @param userId
     * @param password
     * @return
     */
    @PutMapping("/user/editPwd/{userId}")
    @Override
    public ResponseResult editPassword(@PathVariable("userId")String userId,@RequestParam("password") String password){
        return userService.editPassword(userId,password);
    }


 //*******************************************角色相关******************************************************

    /**
     * 分页获取角色列表
     * @param page
     * @param size
     * @param roleName
     * @return
     */
    @GetMapping("/role/list/{page}/{size}")
    @Override
    public QueryResponseResult getRoleList(@PathVariable("page") int page, @PathVariable("size") int size, @RequestParam(value = "roleName", required = false) String roleName) {
        return userService.getRoleList(page, size, roleName);
    }

    /**
     * 获取所有角色
     * @return
     */
    @GetMapping("/role/findlist/")
    @Override
    public QueryResponseResult findRoleList() {
        return userService.findRole();
    }

    /**
     * 获取所有权限
     * @return
     */
    @GetMapping("/menu/list")
    @Override
    public SfsMenuExt getMenuList(){
        SfsMenuExt sfsMenuExt = userService.getMenuList();
        return sfsMenuExt;
    }

    /**
     * 根据角色id获取拥有的资源
     * @param roleId
     * @return
     */
    @GetMapping("/menu/getRoleMenu/{roleId}")
    @Override
    public List<String> getMenuByRoleId(@PathVariable("roleId")String roleId) {
        return userService.getMenuByRoleId(roleId);
    }

    @PostMapping("/role/setMenu/{roleId}")
    @Override
    public ResponseResult setRoleMenu(@PathVariable("roleId") String roleId, @RequestParam("menuIds") String menuIds) {
        return userService.setRoleMenu(roleId,menuIds);
    }

    @PutMapping("/role/edit/{roleId}")
    @Override
    public ResponseResult editRole(@PathVariable("roleId") String roleId, @RequestBody SfsRole sfsRole) {
        return userService.editRole(roleId,sfsRole);
    }

    @DeleteMapping("/role/delete/{roleId}")
    @Override
    public ResponseResult deleteRole(@PathVariable("roleId") String roleId) {
        return userService.deleteRole(roleId);
    }

    @PostMapping("/role/add")
    @Override
    public ResponseResult addRole(@RequestBody SfsRole sfsRole) {
        return userService.addRole(sfsRole);
    }

    @GetMapping("/role/get/{roleId}")
    @Override
    public SfsRole getRole(@PathVariable("roleId") String id) {
        return userService.getRoleById(id);
    }


}

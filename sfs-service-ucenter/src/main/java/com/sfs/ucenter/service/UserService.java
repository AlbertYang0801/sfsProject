package com.sfs.ucenter.service;

import com.sfs.framework.domain.ucenter.*;
import com.sfs.framework.domain.ucenter.ext.SfsMenuExt;
import com.sfs.framework.domain.ucenter.ext.SfsUserExt;
import com.sfs.framework.domain.ucenter.request.QueryUserRequest;
import com.sfs.framework.domain.ucenter.response.AuthCode;
import com.sfs.framework.domain.ucenter.response.UcenterCode;
import com.sfs.framework.model.response.CommonCode;
import com.sfs.framework.model.response.QueryResponseResult;
import com.sfs.framework.model.response.QueryResult;
import com.sfs.framework.model.response.ResponseResult;
import com.sfs.ucenter.dao.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author yjw
 * @date 2020/5/6 1:08
 */
@Service
public class UserService {

    @Autowired
    private SfsUserRepository sfsUserRepository;

    @Autowired
    private SfsCompanyUserRepository sfsCompanyUserRepository;

    @Autowired
    private SfsMenuMapper sfsMenuMapper;

    @Autowired
    private SfsRoleRepository sfsRoleRepository;

    @Autowired
    private SfsUserRoleRepository sfsUserRoleRepository;

    @Autowired
    private SfsPermissionRepository sfsPermissionRepository;

    @Autowired
    private SfsMenuRepository sfsMenuRepository;

    /**
     * 根据用户名查出用户信息和企业信息
     *
     * @param username
     * @return
     */
    public SfsUserExt getUserExt(String username) {
        //获取用户信息
        SfsUser sfsUser = this.findSfsUserByUsername(username);
        if (sfsUser == null) {
            return null;
        }
        SfsUserExt sfsUserExt = new SfsUserExt();
        BeanUtils.copyProperties(sfsUser, sfsUserExt);
        //获取用户企业信息
        SfsCompanyUser sfsCompanyUser = sfsCompanyUserRepository.findByUserId(sfsUserExt.getId());
        if (sfsCompanyUser != null) {
            sfsUserExt.setCompanyId(sfsCompanyUser.getCompanyId());
        }
        //获取用户权限信息
        List<SfsMenu> permissions = sfsMenuMapper.selectPermissionByUserId(sfsUserExt.getId());
        sfsUserExt.setPermissions(permissions);
        return sfsUserExt;
    }

    public ResponseResult register(String username, String password) {
        if (StringUtils.isEmpty(username)) {
            //提示输入账号
            return new ResponseResult(AuthCode.AUTH_USERNAME_NONE);
        }
        if (StringUtils.isEmpty(password)) {
            //提示请输入密码
            return new ResponseResult(AuthCode.AUTH_PASSWORD_NONE);
        }
        //查找用户是否已存在
        SfsUser data = sfsUserRepository.findByUsername(username);
        if (data != null) {
            //提示用户已存在
            return new ResponseResult(AuthCode.AUTH_USERNAME_EXISTS);
        }
        SfsUser sfsUser = new SfsUser();
        sfsUser.setUsername(username);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //对密码进行加密(使用Spring Security的加密方式加密)
        String encode = bCryptPasswordEncoder.encode(password);
        sfsUser.setPassword(encode);
        sfsUser.setCreateTime(new Date());
        //设置初始身份为学生
        sfsUser.setName("学生");
        sfsUser.setUtype("101001");
        sfsUser.setStatus("1");
        sfsUser.setSex("1");
        sfsUser.setUserpic("group1/M00/00/00/wKh-bl65lhaAS_0FAAegjh4-P08285.jpg");
        SfsUser save = sfsUserRepository.save(sfsUser);
        //为用户添加学生角色
        SfsRole student = sfsRoleRepository.findByRoleCode("student");
        SfsUserRole sfsUserRole = new SfsUserRole();
        sfsUserRole.setUserId(save.getId());
        sfsUserRole.setRoleId(student.getId());
        sfsUserRole.setCreateTime(new Date());
        sfsUserRoleRepository.save(sfsUserRole);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    public QueryResponseResult getUserList(int page, int size, QueryUserRequest queryUserRequest) {
        //设置分页参数
        if (page <= 0) {
            page = 1;
        }
        page = page - 1;
        if (size <= 0) {
            size = 10;
        }
        Pageable pageable = PageRequest.of(page, size);
        SfsUser sfsUser = new SfsUser();
        if (StringUtils.isNotEmpty(queryUserRequest.getUsername())) {
            sfsUser.setUsername(queryUserRequest.getUsername());
        }
        if (StringUtils.isNotEmpty(queryUserRequest.getName())) {
            sfsUser.setName(queryUserRequest.getName());
        }
        if (StringUtils.isNotEmpty(queryUserRequest.getUtype())) {
            sfsUser.setUtype(queryUserRequest.getUtype());
        }
        //创建条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        //设置模糊匹配
        exampleMatcher.withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains());
        exampleMatcher.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<SfsUser> example = Example.of(sfsUser, exampleMatcher);
        Page<SfsUser> all = sfsUserRepository.findAll(example, pageable);
        QueryResult queryResult = new QueryResult<>();
        queryResult.setList(all.getContent());
        queryResult.setTotal(all.getTotalElements());
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }

    public SfsUser getUserById(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return null;
        }
        Optional<SfsUser> optional = sfsUserRepository.findById(userId);
        if (optional.isPresent()) {
            SfsUser sfsUser = optional.get();
            return sfsUser;
        }
        return null;
    }

    @Transactional
    public ResponseResult editUser(String userId, SfsUser sfsUser) {
        //判断修改数据是否为空
        SfsUser backData = this.getUserById(userId);
        if (backData == null) {
            return new ResponseResult(CommonCode.FAIL);
        }
        sfsUser.setCreateTime(backData.getCreateTime());
        sfsUser.setUpdateTime(new Date());
        //修改用户信息
        sfsUserRepository.save(sfsUser);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 删除用户，并删除用户拥有的角色
     *
     * @param userId
     * @return
     */
    @Transactional
    public ResponseResult deleteUser(String userId) {
        SfsUser sfsUser = this.getUserById(userId);
        if (sfsUser == null) {
            return new ResponseResult(CommonCode.FAIL);
        }
        //删除用户拥有的角色信息
        sfsUserRoleRepository.deleteByUserId(userId);
        //删除用户信息
        sfsUserRepository.deleteById(userId);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 修改密码
     *
     * @param userId
     * @param password
     * @return
     */
    @Transactional
    public ResponseResult editPassword(String userId, String password) {
        if (StringUtils.isEmpty(password)) {
            //提示密码不存在
            return new ResponseResult(AuthCode.AUTH_PASSWORD_NONE);
        }
        SfsUser sfsUser = this.getUserById(userId);
        if (sfsUser == null) {
            //提示账户不存在
            return new ResponseResult(AuthCode.AUTH_ACCOUNT_NOTEXISTS);
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //对密码进行加密
        String encode = bCryptPasswordEncoder.encode(password);
        sfsUser.setPassword(encode);
        sfsUser.setUpdateTime(new Date());
        sfsUserRepository.save(sfsUser);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 为用户设置角色
     *
     * @param userId
     * @param roleIds
     * @return
     */
    @Transactional
    public ResponseResult setUserRole(String userId, String roleIds) {
        //判断用户是否存在
        SfsUser sfsUser = this.getUserById(userId);
        if (sfsUser == null) {
            return new ResponseResult(CommonCode.FAIL);
        }
        //删除用户之前的角色信息
        sfsUserRoleRepository.deleteByUserId(userId);
        //获取更新的角色id
        String[] roles = roleIds.split(",");
        for (String roleId : roles) {
            SfsUserRole sfsUserRole = new SfsUserRole();
            sfsUserRole.setUserId(userId);
            sfsUserRole.setRoleId(roleId);
            sfsUserRole.setCreateTime(new Date());
            //保存用户角色
            sfsUserRoleRepository.save(sfsUserRole);
        }
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 获取用户拥有的角色
     *
     * @param userId
     * @return
     */
    public List<String> getUserRole(String userId) {
        SfsUser sfsUser = this.getUserById(userId);
        if (sfsUser == null) {
            return null;
        }
        List<String> roleIds = new ArrayList<>();
        List<SfsUserRole> list = sfsUserRoleRepository.findByUserId(userId);
        for (SfsUserRole sfsUserRole : list) {
            roleIds.add(sfsUserRole.getRoleId());
        }
        return roleIds;
    }

    /**
     * 分页获取角色列表
     *
     * @param page
     * @param size
     * @param roleName
     * @return
     */
    public QueryResponseResult getRoleList(int page, int size, String roleName) {
        if (page <= 0) {
            page = 1;
        }
        page = page - 1;
        if (size <= 0) {
            size = 10;
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<SfsRole> all = sfsRoleRepository.findByRoleNameContainingOrderByCreateTimeDesc(roleName, pageable);
        QueryResult queryResult = new QueryResult<>();
        queryResult.setList(all.getContent());
        queryResult.setTotal(all.getTotalElements());
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }

    /**
     * 获取所有角色的id和名称
     *
     * @return
     */
    public QueryResponseResult findRole() {
        List<SfsRole> all = sfsRoleRepository.findAll();
        QueryResult queryResult = new QueryResult<>();
        queryResult.setList(all);
        queryResult.setTotal(all.size());
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }

    public SfsRole getRoleById(String roleId) {
        Optional<SfsRole> optional = sfsRoleRepository.findById(roleId);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }


    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    private SfsUser findSfsUserByUsername(String username) {
        return sfsUserRepository.findByUsername(username);
    }


    /**
     * 获取所有的权限列表
     *
     * @return
     */
    public SfsMenuExt getMenuList() {
        SfsMenuExt sfsMenuExt = sfsMenuMapper.selectMenuList();
        return sfsMenuExt;
    }

    /**
     * 根据角色id获取角色拥有的权限
     *
     * @param roleId
     * @return
     */
    public List<String> getMenuByRoleId(String roleId) {
        //判断角色是否存在
        SfsRole sfsRole = this.getRoleById(roleId);
        if (sfsRole == null) {
            return null;
        }
        List<String> list = new ArrayList<>();
        List<SfsPermission> permissionList = sfsPermissionRepository.findByRoleId(roleId);
        for (SfsPermission sfsPermission : permissionList) {
            list.add(sfsPermission.getMenuId());
        }
        return list;
    }

    /**
     * 根据角色id添加权限
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    @Transactional
    public ResponseResult setRoleMenu(String roleId, String menuIds) {
        //判断角色是否存在
        SfsRole sfsRole = this.getRoleById(roleId);
        if (sfsRole == null || menuIds == null) {
            return new ResponseResult(CommonCode.FAIL);
        }
        //添加权限之前先清空角色拥有的权限
        sfsPermissionRepository.deleteByRoleId(roleId);
        //删除之后查询是否还存在数据
        List<SfsPermission> list = sfsPermissionRepository.findByRoleId(roleId);
        if (list.size() != 0) {
            return new ResponseResult(CommonCode.FAIL);
        }
        for (String menuId : menuIds.split(",")) {
            SfsMenu sfsMenu = this.getMenuById(menuId);
            if (sfsMenu != null ) {
                SfsPermission sfsPermission = new SfsPermission();
                sfsPermission.setMenuId(sfsMenu.getId());
                sfsPermission.setRoleId(roleId);
                sfsPermission.setCreateTime(new Date());
                sfsPermissionRepository.save(sfsPermission);
            }
        }
        return new ResponseResult(CommonCode.SUCCESS);
    }

    public SfsMenu getMenuById(String menuId) {
        Optional<SfsMenu> optional = sfsMenuRepository.findById(menuId);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Transactional
    public ResponseResult editRole(String roleId, SfsRole sfsRole) {
        SfsRole role = this.getRoleById(roleId);
        if (role == null) {
            return new ResponseResult(CommonCode.FAIL);
        }
        sfsRole.setUpdateTime(new Date());
        sfsRoleRepository.save(sfsRole);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Transactional
    public ResponseResult deleteRole(String roleId) {
        if (StringUtils.isEmpty(roleId)) {
            return new ResponseResult(CommonCode.FAIL);
        }
        SfsRole sfsRole = this.getRoleById(roleId);
        if (sfsRole == null) {
            return new ResponseResult(CommonCode.FAIL);
        }
        //删除角色权限信息
        sfsPermissionRepository.deleteByRoleId(roleId);
        //删除用户拥有该角色的信息
        sfsUserRoleRepository.deleteByRoleId(roleId);
        //删除角色信息
        sfsRoleRepository.deleteById(roleId);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    public ResponseResult addRole(SfsRole sfsRole) {
        List<SfsRole> roleList = sfsRoleRepository.findByRoleName(sfsRole.getRoleName());
        if (roleList.size() > 0) {
            //提示角色名已存在
            return new ResponseResult(UcenterCode.UCENTER_ROLENAME_ISEXIST);
        }
        sfsRole.setStatus("1");
        sfsRoleRepository.save(sfsRole);
        return new ResponseResult(CommonCode.SUCCESS);
    }


}

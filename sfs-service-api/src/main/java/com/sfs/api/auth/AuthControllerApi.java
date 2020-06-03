package com.sfs.api.auth;

import com.sfs.framework.domain.ucenter.request.LoginRequest;
import com.sfs.framework.domain.ucenter.response.JwtResult;
import com.sfs.framework.domain.ucenter.response.LoginResult;
import com.sfs.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 认证接口
 * @author yjw
 * @date 2020/5/5 18:25
 */
@Api(value = "用户认证",description = "用户认证接口")
public interface AuthControllerApi {

    /**
     * 用户密码登录
     * @param loginRequest
     * @return
     */
    @ApiOperation("登录")
    LoginResult login(LoginRequest loginRequest);

    /**
     * 注销
     * @return
     */
    @ApiOperation("退出")
    ResponseResult logout();

    /**
     * 根据cookie中的令牌标识查询redis里的jwt令牌内容
     * @return
     */
    @ApiOperation("查询用户的jwt令牌")
    JwtResult userJwt();


    /**
     * 用户密码登录
     * @param loginRequest
     * @return
     */
    @ApiOperation("注册")
    ResponseResult register(LoginRequest loginRequest);

}

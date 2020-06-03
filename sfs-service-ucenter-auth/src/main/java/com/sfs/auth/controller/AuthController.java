package com.sfs.auth.controller;

import com.sfs.api.auth.AuthControllerApi;
import com.sfs.auth.client.UserClient;
import com.sfs.auth.service.AuthService;
import com.sfs.framework.domain.ucenter.ext.AuthToken;
import com.sfs.framework.domain.ucenter.request.LoginRequest;
import com.sfs.framework.domain.ucenter.response.AuthCode;
import com.sfs.framework.domain.ucenter.response.JwtResult;
import com.sfs.framework.domain.ucenter.response.LoginResult;
import com.sfs.framework.exception.ExceptionCast;
import com.sfs.framework.model.response.CommonCode;
import com.sfs.framework.model.response.ResponseResult;
import com.sfs.framework.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 用户认证
 *
 * @author yjw
 * @date 2020/5/5 22:02
 */
@RestController
public class AuthController implements AuthControllerApi {

    @Value("${auth.clientId}")
    String clientId;

    @Value("${auth.clientSecret}")
    String clientSecret;

    @Value("${auth.cookieDomain}")
    String cookieDomain;

    @Value("${auth.cookieMaxAge}")
    int cookieMaxAge;

    /**
     * token存储到redis的过期时间
     */
    @Value("${auth.tokenValiditySeconds}")
    int tokenValiditySeconds;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserClient userClient;

    /**
     * 用户登录，认证
     *
     * @param loginRequest
     * @return
     */
    @PostMapping("/userlogin")
    @Override
    public LoginResult login(LoginRequest loginRequest) {
        if (loginRequest.getUsername() == null || StringUtils.isEmpty(loginRequest.getUsername())) {
            //提示请输入账号
            ExceptionCast.cast(AuthCode.AUTH_USERNAME_NONE);
        }
        if (loginRequest.getPassword() == null || StringUtils.isEmpty(loginRequest.getPassword())) {
            //提示输入密码
            ExceptionCast.cast(AuthCode.AUTH_PASSWORD_NONE);
        }
        //进行密码模式认证，获取认证令牌，并将令牌保存到redis
        AuthToken authToken = authService.login(loginRequest.getUsername(), loginRequest.getPassword(), clientId, clientSecret);
        //jwt令牌身份标识信息
        String jwt_token = authToken.getJwt_token();
        //将身份令牌写入cookie
        saveCookie(jwt_token);
        return new LoginResult(CommonCode.SUCCESS, jwt_token);
    }

    /**
     * 用户注销
     *
     * @return
     */
    @PostMapping("/userlogout")
    @Override
    public ResponseResult logout() {
        String jti = getJtiByCookie();
        //删除redis中的令牌
        boolean b = authService.deleteToken(jti);
        //删除cookie中的令牌
        clearCookie(jti);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 根据cookie中的令牌标识获取jwt身份令牌内容
     *
     * @return
     */
    @GetMapping("/userjwt")
    @Override
    public JwtResult userJwt() {
        //获取cookie中的令牌标识内容
        String jti = getJtiByCookie();
        //根据令牌标识内容获得jwt令牌全部内容
        AuthToken userToken = authService.getUserToken(jti);
        if (userToken == null) {
            return new JwtResult(CommonCode.FAIL, null);
        }
        return new JwtResult(CommonCode.SUCCESS, userToken.getAccess_token());
    }

    /**
     * 注册,调用用户中心注册接口
     * @param loginRequest
     * @return
     */
    @PostMapping("/userregister")
    @Override
    public ResponseResult register(LoginRequest loginRequest){
        return userClient.register(loginRequest);
    }

//************************************************************************************************************

    /**
     * 将令牌保存到cookie
     *
     * @param token
     */
    private void saveCookie(String token) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //设置cookie
        CookieUtil.addCookie(response, cookieDomain, "/", "uid", token, cookieMaxAge, false);
    }

    /**
     * 从cookie中获取令牌标识内容
     *
     * @return
     */
    private String getJtiByCookie() {
        //获取request请求
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String> map = CookieUtil.readCookie(request, "uid");
        if (map != null && map.get("uid") != null) {
            String uid = map.get("uid");
            return uid;
        }
        return null;
    }

    /**
     * 清除cookie
     * @param token
     */
    private void clearCookie(String token) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //设置指定cookie的有效时间为0，即失效该cookie
        CookieUtil.addCookie(response,cookieDomain,"/","uid",token,0,false);
    }


}


/**
 * 认证流程；
 * 1.用户的登录请求被Spring Security拦截到，首先访问UserDetailsServiceImpl的loadUserByUsername(String username)方法
 * 该方法根据用户名调用用户中心服务获取用户信息和用户密码，然后Spring Security根据用户密码进行校验，成功则可以请求资源。
 * 2.用户请求认证接口，首先根据用户名、密码、客户端id、客户端密码采用密码模式申请jwt令牌
 * 3.令牌申请成功主要包含三部分:身份令牌、刷新令牌、标识令牌
 * 4.将标识令牌作为key，jwt令牌所有内容作为value存进redis
 * 5.将标识令牌存入cookie
 * <p>
 * <p>
 * 前端用户名回显：
 * 1.登录认证结束返回前端，前端请求后端获取jwt身份令牌内容
 * 2.后端从cookie中获取jwt令牌标识内容，根据标识内容从redis中获取jwt令牌
 * 3.将jwt令牌中的身份令牌返回到前端
 * 4.前端解析jwt的身份令牌，获取用户信息
 * 5.根据解析后的用户信息，进行用户名回显
 *
 * 用户退出登录:
 * 1.获取cookie中的标识令牌
 * 2.根据标识令牌内容删除redis中的令牌
 * 3.设置保存标识内容的cookie有效时间为0，删除该cookie
 *
 */
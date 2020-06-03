package com.sfs.govern.gateway.service;

import com.sfs.framework.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author yjw
 * @date 2020/5/7 21:40
 */
@Service
public class AuthService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 从cookie中获取标识令牌
     */
    public String getTokenFromCookie(HttpServletRequest request) {
        Map<String, String> map = CookieUtil.readCookie(request, "uid");
        //获取cookie中的标识令牌
        String jwtToken = map.get("uid");
        if (StringUtils.isEmpty(jwtToken)) {
            return null;
        }
        return jwtToken;
    }

    /**
     * 从header中获得客户端令牌
     *
     * @param request
     * @return
     */
    public String getTokenFromHeader(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isEmpty(authorization)) {
            //请求头令牌为空，拒绝访问
            return null;
        }
        if (!authorization.startsWith("Bearer ")) {
            //请求头令牌内容格式错误，拒绝访问
            return null;
        }
        return authorization.substring(7);
    }

    /**
     * 根据jwt的标识令牌查询redis中的令牌有效期
     * @param jtiToken
     * @return
     */
    public Long getToeknFromRedis(String jtiToken) {
        //拼接标识令牌在redis中的key
        jtiToken = "user_token:" + jtiToken;
        Long expire = stringRedisTemplate.getExpire(jtiToken, TimeUnit.SECONDS);
        return expire;
    }


}

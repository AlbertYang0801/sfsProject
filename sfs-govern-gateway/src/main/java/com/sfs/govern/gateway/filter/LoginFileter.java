package com.sfs.govern.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.sfs.framework.model.response.CommonCode;
import com.sfs.framework.model.response.ResponseResult;
import com.sfs.govern.gateway.service.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 网关过滤器
 *
 * @author yjw
 * @date 2020/5/7 21:21
 */
@Component
public class LoginFileter extends ZuulFilter {

    @Autowired
    private AuthService authService;

    /**
     * 设置过滤器的类型
     * 1.pre：请求在被路由之前执行
     * 2.routing：在路由请求时调用
     * 3.post：在routing和error过滤器之后调用
     * 4.error：处理请求时发生错误调用
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 定义过滤器的优先级，数值越小优先级越高
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 过滤器设置是否执行，true标识该过滤器生效
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的执行逻辑
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        //从cookie中获取jwt的标识令牌
        String jtl_token = authService.getTokenFromCookie(request);
        if (StringUtils.isEmpty(jtl_token)) {
            //拒绝访问
            access_denied();
            return null;
        }
        //查询redis中的jwt令牌生效时间
        Long expire = authService.getToeknFromRedis(jtl_token);
        if (expire <= 0) {
            //redis中存放的令牌已失效，拒绝访问
            access_denied();
            return null;
        }
        //从请求头中获取客户端认证信息
        String jwt = authService.getTokenFromHeader(request);
        if (StringUtils.isEmpty(jwt)) {
            //拒绝访问
            access_denied();
            return null;
        }
        return null;
    }

    /**
     * 拒绝访问
     * 返回提示信息
     */
    private void access_denied() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletResponse response = requestContext.getResponse();
        //设置拒绝访问
        requestContext.setSendZuulResponse(false);
        //设置响应状态码
        requestContext.setResponseStatusCode(200);
        //提示此操作需要登陆系统
        ResponseResult responseResult = new ResponseResult(CommonCode.UNAUTHENTICATED);
        String jsonString = JSON.toJSONString(responseResult);
        requestContext.setResponseBody(jsonString);
        response.setContentType("application/json;charset=utf-8");
    }


}


/**
 * 微服务网关，可以进行代理转发，在配置文件中设置微服务工程的代理转发
 *
 * 微服务网关的过滤器
 * 1.校验cookie中的标识令牌
 * 2.根据标识令牌校验redis中的jwt令牌是否过期
 * 3.校验请求头中携带的jwt的身份令牌
 *
 *
 *
 *
 */

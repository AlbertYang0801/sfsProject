package com.sfs.govern.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

/**
 * 网关过滤器测试
 *
 * @author yjw
 * @date 2020/5/7 21:21
 */
//@Component
public class LoginFileterTest extends ZuulFilter {
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
     * 过滤器设置是否执行
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
//        RequestContext requestContext = RequestContext.getCurrentContext();
//        HttpServletResponse response = requestContext.getResponse();
//        HttpServletRequest request = requestContext.getRequest();
//        //从请求头中获取客户端认证信息
//        String authorization = request.getHeader("Authorization");
//        if (StringUtils.isEmpty(authorization)) {
//            //拒绝访问
//            requestContext.setSendZuulResponse(false);
//            //设置响应状态码
//            requestContext.setResponseStatusCode(200);
//            //提示此操作需要登陆系统
//            ResponseResult responseResult = new ResponseResult(CommonCode.UNAUTHENTICATED);
//            String jsonString = JSON.toJSONString(responseResult);
//            //将反馈信息设置到响应内容里
//            requestContext.setResponseBody(jsonString);
//            response.setContentType("application/json;charset=UTF-8");
//            return null;
//        }
        return null;
    }


}

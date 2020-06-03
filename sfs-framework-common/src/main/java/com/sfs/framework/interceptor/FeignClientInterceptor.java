package com.sfs.framework.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Feign远程服务调用的拦截器
 * 作用:添加jwt令牌到远程请求的请求头中
 * @author yjw
 * @date 2020/5/11 17:06
 */
public class FeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes =(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        if(requestAttributes!=null){
            //取出request
            HttpServletRequest request = requestAttributes.getRequest();
            //取出请求头
            Enumeration<String> headerNames = request.getHeaderNames();
            if(headerNames!=null){
                //遍历请求头
                while (headerNames.hasMoreElements()){
                    //获取请求头名称
                    String name = headerNames.nextElement();
                    //根据请求头获取内容
                    String values = request.getHeader(name);
                    //如果包含携带jwt令牌的请求头
                    if(name.equals("authorization")){
                        //添加jwt令牌到restTemplate的请求头中
                        requestTemplate.header(name,values);
                    }
                }
            }

        }
    }


}

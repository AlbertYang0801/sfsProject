package com.sfs.framework.exception;

import com.google.common.collect.ImmutableBiMap;
import com.sfs.framework.model.response.CommonCode;
import com.sfs.framework.model.response.ResponseResult;
import com.sfs.framework.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 统一捕获异常的类
 * ControllerAdvice 控制器增强的注解
 *
 * @author yjw
 * on 2019/9/16
 */
@ControllerAdvice
public class ExceptionCatch {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionCatch.class);

    //设置一个Map，存放异常类型和错误代码的映射,ImmutableBiMap的特点是一旦创建不可改变，并且线程安全
    private static ImmutableBiMap<Class<? extends Throwable>, ResultCode> EXCEPTIONS;
    //使用builder来构建一个异常类型和错误代码的Map集合
    protected static ImmutableBiMap.Builder<Class<? extends Throwable>, ResultCode> builder = ImmutableBiMap.builder();

    //捕获指定类型的异常,该方法能捕获到所有的CustomException异常
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult customException(CustomException c) {
        logger.error("catch exception:{}:", c.getMessage());
        ResultCode resultCode = c.getResultCode();
        //将异常信息转换为json数据返回前台展示
        return new ResponseResult(resultCode);
    }

    /**
     * 捕获系统异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult exception(Exception e) {
        e.printStackTrace();
        logger.error("catch exception:{}",e.getMessage());
        if (EXCEPTIONS == null) {
            //如果ImmutableBiMap为空，将build存放的Map数据加入到ImmutableBiMap中，并且不可修改
            EXCEPTIONS = builder.build();
        }
        //从ImmutableBiMap中获取异常类，如果有指定异常类，返回指定异常信息，如果没有，返回99999异常信息
        ResultCode resultCode = EXCEPTIONS.get(e.getClass());
        if (resultCode != null) {
            return new ResponseResult(resultCode);
        } else {
            return new ResponseResult(CommonCode.SERVER_ERROR);
        }
    }

    /**
     * 静态变量设置基础的异常类型判断(非法参数异常）
     */
    static {
        //设置参数异常的提示
        builder.put(HttpMessageNotReadableException.class, CommonCode.INVALID_PARAM);
        //设置权限不足的异常提示代码（提示权限不足）
        builder.put(AccessDeniedException.class,CommonCode.UNAUTHORISE);
    }

}


//捕获系统异常的步骤:
//在ExceptionCatch类中定义捕获Exception异常的方法
//创建一个Map(ImmutableBiMap不可修改,且线程安全)，存放一些已知异常类和其对应的异常信息
//捕获异常先判断Map集合是否包含指定异常,若包含，则返回对应的异常信息
//若不包含，则统一返回99999异常
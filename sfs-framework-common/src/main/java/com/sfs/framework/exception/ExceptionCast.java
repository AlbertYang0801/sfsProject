package com.sfs.framework.exception;

import com.sfs.framework.model.response.ResultCode;

/**
 * 自定义异常CustomException的抛出类
 * 封装抛出异常的过程
 * @author yjw
 * on 2019/9/16
 */
public class ExceptionCast {

    /**
     * 使用此静态方法抛出异常
     * @param resultCode
     */
    public static void cast(ResultCode resultCode){
        throw new CustomException(resultCode);
    }

}

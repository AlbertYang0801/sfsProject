package com.sfs.framework.exception;

import com.sfs.framework.model.response.ResultCode;

/**
 * 自定义异常类
 * 抛出运行时异常
 * @author  yjw
 * on 2019/9/16
 */
public class CustomException extends RuntimeException{

    /**
     * 错误代码
     */
    private ResultCode resultCode;

    public CustomException(ResultCode resultCode){
        super("错误代码:"+resultCode.code()+"错误信息:"+resultCode.message());
        this.resultCode=resultCode;
    }

    public ResultCode getResultCode(){
        return this.resultCode;
    }

}

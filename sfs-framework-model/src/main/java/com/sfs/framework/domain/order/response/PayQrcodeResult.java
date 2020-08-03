package com.sfs.framework.domain.order.response;

import com.sfs.framework.model.response.ResponseResult;
import com.sfs.framework.model.response.ResultCode;
import lombok.Data;
import lombok.ToString;

/**
 * @author yjw on 2018/3/27.
 */
@Data
@ToString
public class PayQrcodeResult extends ResponseResult {
    public PayQrcodeResult(ResultCode resultCode){
        super(resultCode);
    }
    private String codeUrl;
    private Float money;
    private String orderNumber;

}

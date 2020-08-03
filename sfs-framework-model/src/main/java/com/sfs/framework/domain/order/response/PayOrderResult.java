package com.sfs.framework.domain.order.response;

import com.sfs.framework.domain.order.SfsOrdersPay;
import com.sfs.framework.model.response.ResponseResult;
import com.sfs.framework.model.response.ResultCode;
import lombok.Data;
import lombok.ToString;

/**
 * Created by mrt on 2018/3/27.
 */
@Data
@ToString
public class PayOrderResult extends ResponseResult {
    public PayOrderResult(ResultCode resultCode) {
        super(resultCode);
    }
    public PayOrderResult(ResultCode resultCode,SfsOrdersPay sfsOrdersPay) {
        super(resultCode);
        this.sfsOrdersPay = sfsOrdersPay;
    }
    private SfsOrdersPay sfsOrdersPay;
    private String orderNumber;

    //当tradeState为NOTPAY（未支付）时显示支付二维码
    private String codeUrl;
    private Float money;


}

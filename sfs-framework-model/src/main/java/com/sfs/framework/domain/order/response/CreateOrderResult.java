package com.sfs.framework.domain.order.response;

import com.sfs.framework.domain.order.SfsOrders;
import com.sfs.framework.model.response.ResponseResult;
import com.sfs.framework.model.response.ResultCode;
import lombok.Data;
import lombok.ToString;

/**
 * @author yjw on 2018/3/26.
 */
@Data
@ToString
public class CreateOrderResult extends ResponseResult {
    private SfsOrders sfsOrders;

    public CreateOrderResult(ResultCode resultCode, SfsOrders sfsOrders) {
        super(resultCode);
        this.sfsOrders = sfsOrders;
    }


}

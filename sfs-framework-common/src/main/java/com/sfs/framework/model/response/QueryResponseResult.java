package com.sfs.framework.model.response;

import lombok.Data;
import lombok.ToString;

/**
 * ajax响应返回list的数据格式
 * 继承ResponseResult，包含返回的数据格式和list数据
 * @author yjw
 */
@Data
@ToString
public class QueryResponseResult<T> extends ResponseResult {

    QueryResult<T> queryResult;

    public QueryResponseResult(ResultCode resultCode,QueryResult queryResult){
        super(resultCode);
       this.queryResult = queryResult;
    }

}

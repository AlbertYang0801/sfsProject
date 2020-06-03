package com.sfs.framework.model.response;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 查询数据的实体类
 * 包含list数据集合和total数据条数
 * @Author: mrt.
 * @Description:
 * @Date:Created in 2018/1/24 18:33.
 * @Modified By:
 */
@Data
@ToString
public class QueryResult<T> {

    /**
     * 数据列表
     */
    private List<T> list;

    /**
     * 数据总数
     */
    private long total;

}

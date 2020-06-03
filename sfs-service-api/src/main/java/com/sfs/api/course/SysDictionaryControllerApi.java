package com.sfs.api.course;

import com.sfs.framework.domain.system.SysDictionary;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 数据字典接口
 * @author yjw
 * @date 2019/11/12
 */
@Api(value = "数据字典接口",tags = {"数据字典管理"})
public interface SysDictionaryControllerApi {

    /**
     * 数据字典查询接口
     * @param type
     * @return
     */
    @ApiOperation(value = "数据字典查询接口")
    public SysDictionary findBydType(String type);

}

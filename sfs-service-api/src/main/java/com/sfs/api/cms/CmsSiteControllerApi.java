package com.sfs.api.cms;

import com.sfs.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * cms站点管理
 * @author  yjw
 * on 2019/9/14
 */
@Api(value = "cms站点管理接口")
public interface CmsSiteControllerApi {

    /**
     * 查询站点列表，实现下拉框的加载
     * @return
     */
    @ApiOperation("站点列表查询")
    QueryResponseResult findSiteList();

}

package com.sfs.api.cms;

import com.sfs.framework.domain.cms.CmsConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * cms系统配置数据接口
 *
 * @author yjw
 * on 2019/9/19
 */
@Api(value = "cms配置管理接口")
public interface CmsConfigControllerApi {

    /**
     * 根据id查询cms系统配置信息
     *
     * @param id
     * @return
     */
    @ApiOperation("根据id查询Cms系统配置信息")
    CmsConfig getModel(String id);


}




package com.sfs.api.cms;

import com.sfs.framework.domain.cms.CmsTemplate;
import com.sfs.framework.model.response.QueryResponseResult;
import com.sfs.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * cms模板管理
 *
 * @author yjw
 * on 2019/9/14
 */
@Api(value = "cms模板管理接口")
public interface CmsTemplateControllerApi {

    /**
     * 模板集合查询，用于下拉选模板选择
     * @return
     */
    @ApiOperation("模板集合查询,用于下拉选模板选择")
    QueryResponseResult findTemplate();

    /**
     * 模板列表查询
     * @param page
     * @param size
     * @param templateName
     * @return
     */
    @ApiOperation("模板列表查询")
    @ApiImplicitParams({                    //描述一个类的参数列表
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, paramType = "path", dataType = "int"),
    })
    QueryResponseResult findTemplateList(int page, int size, String templateName);

    /**
     * 添加模板
     * @param cmsTemplate
     * @return
     */
    @ApiOperation("添加模板")
    ResponseResult addTemplate(CmsTemplate cmsTemplate);

    /**
     * 删除模板
     * @param templateId
     * @return
     */
    @ApiOperation(("删除模板"))
    ResponseResult deleteTemplate(String templateId);

    /**
     * 编辑模板
     * @param templateId
     * @param cmsTemplate
     * @return
     */
    @ApiOperation("编辑模板")
    ResponseResult editTemplate(String templateId, CmsTemplate cmsTemplate);

    /**
     * 获取模板信息
     * @param templateId
     * @return
     */
    @ApiOperation("获取模板信息")
    CmsTemplate getTemplate(String templateId);

}

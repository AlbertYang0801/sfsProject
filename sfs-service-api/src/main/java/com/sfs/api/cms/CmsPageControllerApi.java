package com.sfs.api.cms;

import com.sfs.framework.domain.cms.CmsPage;
import com.sfs.framework.domain.cms.request.QueryPageRequest;
import com.sfs.framework.domain.cms.response.CmsPageResult;
import com.sfs.framework.domain.cms.response.CmsPostPageResult;
import com.sfs.framework.model.response.QueryResponseResult;
import com.sfs.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * CMS系统：页面展示的增删改查接口
 * 注解@Api：描述Api接口类的作用
 * 注解@ApiOperation：描述单个接口的作用
 * 注解@ApiImplicitParams：描述一个接口的参数列表
 *
 * @author yjw
 * @create 2019/8/15 17:42
 */
@Api(value = "cms页面管理接口")
public interface CmsPageControllerApi {

    /**
     * 页面查询，查询cms_page表数据，根据参数查询
     *
     * @param page:页码
     * @param size:每页数据条数
     * @param queryPageRequest:数据请求格式,自定义的QueryPageRequest
     * @return : 自定义响应集合QueryResponseResult
     */
    @ApiOperation("分页查询页面列表")       //描述一个类的一个方法
    @ApiImplicitParams({                    //描述一个类的参数列表
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, paramType = "path", dataType = "int")
    })
    QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

    /**
     * 添加页面的接口
     *
     * @param cmsPage
     * @return
     */
    @ApiOperation("添加页面")
    CmsPageResult add(CmsPage cmsPage);

    /**
     * 根据页面id查询页面数据的接口
     *
     * @param id
     * @return
     */
    @ApiOperation("根据id查询页面数据")
    CmsPage findById(String id);

    /**
     * 根据页面id修改页面
     *
     * @param id
     * @param cmsPage
     * @return
     */
    @ApiOperation("修改页面")
    CmsPageResult edit(String id, CmsPage cmsPage);

    /**
     * 根据页面id删除页面
     *
     * @param id
     * @return
     */
    @ApiOperation("删除页面")
    ResponseResult deleteById(String id);

    /**
     * 发布页面接口
     *
     * @param pageId
     * @return
     */
    @ApiOperation("发布页面")
    ResponseResult postPage(String pageId);

    /**
     * 保存页面信息，如已存在，则更新，用于课程详情界面
     *
     * @param cmsPage
     * @return
     */
    @ApiOperation("保存页面")
    CmsPageResult save(CmsPage cmsPage);

    /**
     * 课程详情一键发布页面，根据课程id发布课程详情页面
     *
     * @param cmsPage
     * @return
     */
    @ApiOperation("一键发布界面")
    CmsPostPageResult postPageQuick(CmsPage cmsPage);


}

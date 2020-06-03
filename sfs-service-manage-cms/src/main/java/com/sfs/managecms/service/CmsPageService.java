package com.sfs.managecms.service;

import com.sfs.framework.domain.cms.CmsPage;
import com.sfs.framework.domain.cms.request.QueryPageRequest;
import com.sfs.framework.domain.cms.response.CmsPageResult;
import com.sfs.framework.domain.cms.response.CmsPostPageResult;
import com.sfs.framework.model.response.QueryResponseResult;
import com.sfs.framework.model.response.ResponseResult;

/**
 * cmsPageService的接口
 * 方法名与参数及返回值与Controller层保持一致
 *
 * @author yjw
 * @create 2019/8/19 9:54
 */
public interface CmsPageService {

    /**
     * 页面列表数据列表分页查询
     *
     * @param page
     * @param size
     * @param queryPageRequest
     * @return
     */
    QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

    /**
     * 页面列表新增功能
     *
     * @param cmsPage
     * @return
     */
    CmsPageResult add(CmsPage cmsPage);

    /**
     * 根据页面id获取页面数据
     *
     * @param id
     * @return
     */
    CmsPage findById(String id);

    /**
     * 更新页面
     *
     * @param id
     * @param cmsPage
     * @return
     */
    CmsPageResult update(String id, CmsPage cmsPage);

    /**
     * 删除页面
     *
     * @param id
     * @return
     */
    ResponseResult deleteById(String id);

    /**
     * 根据页面id执行页面静态化获取页面字符流
     *
     * @param pageId
     * @return
     */
    String getPageHtml(String pageId);

    /**
     * 页面发布
     *
     * @param pageId
     * @return
     */
    ResponseResult postPage(String pageId);

    /**
     * 保存页面，页面已存在，更新页面信息，若不存在，则新增
     * 用于课程详情界面的添加
     *
     * @param cmsPage
     * @return
     */
    CmsPageResult savePage(CmsPage cmsPage);

    /**
     * 一键发布页面
     * 根据页面信息，发布页面到GridFS，向MQ发送消息
     *
     * @param cmsPage
     * @return
     */
    CmsPostPageResult postPageQuick(CmsPage cmsPage);

}

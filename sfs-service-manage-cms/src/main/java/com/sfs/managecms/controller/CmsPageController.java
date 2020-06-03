package com.sfs.managecms.controller;

import com.sfs.api.cms.CmsPageControllerApi;
import com.sfs.framework.domain.cms.CmsPage;
import com.sfs.framework.domain.cms.request.QueryPageRequest;
import com.sfs.framework.domain.cms.response.CmsPageResult;
import com.sfs.framework.domain.cms.response.CmsPostPageResult;
import com.sfs.framework.model.response.QueryResponseResult;
import com.sfs.framework.model.response.ResponseResult;
import com.sfs.managecms.service.CmsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 页面列表管理
 * @author yjw
 * @create 2019/8/16 15:06
 */
@RestController
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi {

    @Autowired
    private CmsPageService cmsPageService;

    /**
     * 测试Controller
     * 测试实现api工程接口
     * cms_page查询，页面列表展示
     * @param page:页码
     * @param size:每页数据条数
     * @param queryPageRequest:数据请求格式,自定义的QueryPageRequest
     * @return
     */
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size,
                                        QueryPageRequest queryPageRequest) {
        return cmsPageService.findList(page, size, queryPageRequest);
    }

    /**
     * 新增页面
     * @RequestBody 将请求的json数据转换为对象
     * @param cmsPage
     * @return
     */
    @Override
    @PostMapping("/add")
    public CmsPageResult add(@RequestBody CmsPage cmsPage) {
        return cmsPageService.add(cmsPage);
    }

    /**
     * 根据id获取页面数据
     * @param id
     * @return
     */
    @Override
    @GetMapping("/get/{id}")
    public CmsPage findById(@PathVariable("id") String id) {
        return cmsPageService.findById(id);
    }

    /**
     * 根据id和页面数据实现更新
     * 提交方式为put，在http请求中代表更新
     * @param id
     * @param cmsPage
     * @return
     */
    @Override
    @PutMapping("/edit/{id}")
    public CmsPageResult edit(@PathVariable("id") String id,@RequestBody CmsPage cmsPage) {
        return cmsPageService.update(id,cmsPage);
    }

    /**
     * 根据id删除页面数据
     * @param id
     * @return
     */
    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteById(@PathVariable("id") String id) {
        return cmsPageService.deleteById(id);
    }

    /**
     * 根据页面id发布页面
     * mq生产者设置站点id为路由key发布消息到交换机
     * @param pageId
     * @return
     */
    @Override
    @PostMapping("/postPage/{pageId}")
    public ResponseResult postPage(@PathVariable("pageId") String pageId) {
        return cmsPageService.postPage(pageId);
    }


 //**********************************************************课程详情*************************************************************

    /**
     * 保存页面，若存在页面，则更新，若不存在，则新增
     * 用于课程详情页面
     * @param cmsPage
     * @return
     */
    @Override
    @PostMapping("/save")
    public CmsPageResult save(@RequestBody CmsPage cmsPage) {
        return cmsPageService.savePage(cmsPage);
    }

    /**
     * 一键发布界面
     * 用于课程详情界面发布
     * @param cmsPage
     * @return
     */
    @PostMapping("/postPageQuick")
    @Override
    public CmsPostPageResult postPageQuick(@RequestBody CmsPage cmsPage) {
        return cmsPageService.postPageQuick(cmsPage);
    }


}

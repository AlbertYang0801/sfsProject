package com.sfs.managecourse.client;

import com.sfs.framework.domain.cms.CmsPage;
import com.sfs.framework.domain.cms.response.CmsPageResult;
import com.sfs.framework.domain.cms.response.CmsPostPageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Feign远程调用接口
 * 设置FeignClient标注该接口为远程调用的接口
 * 设置value值为具体调用提供服务的工程
 *
 * @author yjw
 * @date 2019/11/22 9:55
 */
@FeignClient("sfs-service-manage-cms")
public interface CmsPageClient {

    /**
     * 远程调用cms服务：获取页面信息
     *
     * @param id
     * @return
     */
    @GetMapping("/cms/page/get/{id}")
    CmsPage findById(@PathVariable("id") String id);

    /**
     * 远程调用cms服务：保存页面接口
     *
     * @param cmsPage
     * @return
     */
    @PostMapping("/cms/page/save")
    CmsPageResult save(@RequestBody CmsPage cmsPage);

    /**
     * 一键发布接口，返回页面url
     *
     * @param cmsPage
     * @return
     */
    @PostMapping("/cms/page/postPageQuick")
    CmsPostPageResult postPageQuick(@RequestBody CmsPage cmsPage);

}

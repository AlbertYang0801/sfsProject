package com.sfs.managecms.controller;

import com.sfs.api.cms.CmsSiteControllerApi;
import com.sfs.framework.model.response.QueryResponseResult;
import com.sfs.managecms.service.CmsSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 站点管理
 * @author  yjw
 * on 2019/9/14
 */
@RestController
public class CmsSiteController implements CmsSiteControllerApi {

    @Autowired
    private CmsSiteService cmsSiteService;

    @Override
    @GetMapping("/cms/site/list")
    public QueryResponseResult findSiteList() {
        return cmsSiteService.findSiteList();
    }

}

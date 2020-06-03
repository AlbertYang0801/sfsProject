package com.sfs.managecms.controller;

import com.sfs.api.cms.CmsTemplateControllerApi;
import com.sfs.framework.domain.cms.CmsTemplate;
import com.sfs.framework.model.response.QueryResponseResult;
import com.sfs.framework.model.response.ResponseResult;
import com.sfs.managecms.service.CmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 模板管理
 *
 * @author yjw
 * on 2019/9/14
 */
@RestController
@RequestMapping("/cms/template")
public class CmsTemplateController implements CmsTemplateControllerApi {

    @Autowired
    private CmsTemplateService cmsTemplateService;

    /**
     * 获取模板列表数据，用于下拉框的加载
     * @return
     */
    @GetMapping("/getList")
    @Override
    public QueryResponseResult findTemplate() {
        return cmsTemplateService.findTemplate();
    }

    /**
     * 模板管理列表查询
     * 分页，模板名称查询
     * @param page
     * @param size
     * @param templateName
     * @return
     */
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findTemplateList(@PathVariable("page") int page, @PathVariable("size") int size,
                                                @RequestParam(value = "templateName", required = false) String templateName) {
        return cmsTemplateService.findTemplateList(page, size, templateName);
    }

    /**
     * 根据模板id获取模板信息
     *
     * @param templateId
     * @return
     */
    @GetMapping("/get/{templateId}")
    @Override
    public CmsTemplate getTemplate(@PathVariable("templateId") String templateId) {
        return cmsTemplateService.getTemplateById(templateId);
    }

    /**
     * 添加模板
     *
     * @param cmsTemplate
     * @return
     */
    @Override
    @PostMapping("/add")
    public ResponseResult addTemplate(@RequestBody CmsTemplate cmsTemplate) {
        return cmsTemplateService.addTemplate(cmsTemplate);
    }

    /**
     * 根据模板id删除模板
     *
     * @param templateId
     * @return
     */
    @DeleteMapping("/delete/{templateId}")
    @Override
    public ResponseResult deleteTemplate(@PathVariable("templateId") String templateId) {
        return cmsTemplateService.deleteTemplateById(templateId);
    }

    /**
     * 根据模板id更新模板数据
     *
     * @param templateId
     * @param cmsTemplate
     * @return
     */
    @PutMapping("/edit/{templateId}")
    @Override
    public ResponseResult editTemplate(@PathVariable("templateId") String templateId, @RequestBody CmsTemplate cmsTemplate) {
        return cmsTemplateService.updateTemplate(templateId, cmsTemplate);
    }


}

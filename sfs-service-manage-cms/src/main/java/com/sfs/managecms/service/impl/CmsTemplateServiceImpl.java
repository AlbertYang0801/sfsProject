package com.sfs.managecms.service.impl;

import com.sfs.framework.domain.cms.CmsSite;
import com.sfs.framework.domain.cms.CmsTemplate;
import com.sfs.framework.domain.cms.ext.CmsTemplateInfo;
import com.sfs.framework.domain.cms.response.CmsCode;
import com.sfs.framework.exception.ExceptionCast;
import com.sfs.framework.model.response.QueryResponseResult;
import com.sfs.framework.model.response.QueryResult;
import com.sfs.framework.model.response.ResponseResult;
import com.sfs.managecms.dao.CmsSiteRepository;
import com.sfs.managecms.dao.CmsTemplateRepository;
import com.sfs.managecms.service.CmsTemplateService;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.sfs.framework.model.response.CommonCode.FAIL;
import static com.sfs.framework.model.response.CommonCode.SUCCESS;

/**
 * @author by yjw
 * on 2019/9/14
 */
@Service
public class CmsTemplateServiceImpl implements CmsTemplateService {

    @Autowired
    private CmsTemplateRepository cmsTemplateRepository;

    @Autowired
    private CmsSiteRepository cmsSiteRepository;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Override
    public QueryResponseResult findTemplate() {
        List<CmsTemplate> all = cmsTemplateRepository.findAll();
        QueryResult queryResult = new QueryResult();
        queryResult.setTotal(all.size());
        queryResult.setList(all);
        return new QueryResponseResult(SUCCESS,queryResult);
    }

    @Override
    public QueryResponseResult findTemplateList(int page, int size, String templateName) {
        //设置分页参数默认值
        if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            size = 10;
        }
        page = page - 1;
        //设置分页参数
        Pageable pageable = PageRequest.of(page, size);
        CmsTemplate cmsTemplate = new CmsTemplate();
        if (StringUtils.isNotEmpty(templateName)) {
            cmsTemplate.setTemplateName(templateName);
        }
        //创建条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        //设置templateName作为模糊查询条件
        exampleMatcher.withMatcher("templateName", ExampleMatcher.GenericPropertyMatchers.contains());
        //创建example自定义查询条件
        Example example = Example.of(cmsTemplate, exampleMatcher);
        //设置根据自定义查询条件和分页参数进行查询
        Page<CmsTemplate> all = cmsTemplateRepository.findAll(example, pageable);
        //获取查询到的数据列表
        List<CmsTemplate> content = all.getContent();
        //创建返回包含站点名称的集合
        List<CmsTemplateInfo> list = new ArrayList<>();
        for (CmsTemplate template : content) {
            CmsTemplateInfo cmsTemplateInfo = new CmsTemplateInfo(template);
            if(StringUtils.isNotEmpty(template.getSiteId())){
                //获取站点名称
                Optional<CmsSite> optional = cmsSiteRepository.findById(template.getSiteId());
                if(optional.isPresent()){
                    cmsTemplateInfo.setSiteName(optional.get().getSiteName());
                }
            }
            list.add(cmsTemplateInfo);
        }
        QueryResult queryResult = new QueryResult();
        queryResult.setTotal(all.getTotalElements());
        queryResult.setList(list);
        return new QueryResponseResult(SUCCESS, queryResult);
    }

    @Override
    public ResponseResult addTemplate(CmsTemplate cmsTemplate) {
        if (cmsTemplate == null) {
            //返回异常信息，添加的模板数据不存在
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_CMSTEMPLATEISNULL);
        }
        //判断模板文件路径和模板名称不能为空
        if (StringUtils.isNotEmpty(cmsTemplate.getTemplateFilePath())
                && StringUtils.isNotEmpty(cmsTemplate.getTemplateName())) {
            //模板文件路径不为空，上传模板文件到GridFS，返回模板文件Id
            String fileId = uploadTemplate(cmsTemplate);
            //设置上传后的模板文件id
            cmsTemplate.setTemplateFileId(fileId);
        }
        cmsTemplate.setTemplateId(null);

        cmsTemplateRepository.save(cmsTemplate);
        return new ResponseResult(SUCCESS);
    }

    @Override
    public ResponseResult deleteTemplateById(String templateId) {
        CmsTemplate cmsTemplate = this.getTemplateById(templateId);
        //根据模板id查询到模板信息，执行删除
        if (cmsTemplate != null) {
            cmsTemplateRepository.deleteById(templateId);
            return new ResponseResult(SUCCESS);
        }
        return new ResponseResult(FAIL);
    }

    @Override
    public CmsTemplate getTemplateById(String templateId) {
        Optional<CmsTemplate> optional = cmsTemplateRepository.findById(templateId);
        if (optional.isPresent()) {
            CmsTemplate cmsTemplate = optional.get();
            return cmsTemplate;
        }
        return null;
    }

    @Override
    public ResponseResult updateTemplate(String templateId, CmsTemplate cmsTemplate) {
        CmsTemplate cmsTemplate1 = this.getTemplateById(templateId);
        if (StringUtils.isEmpty(templateId)) {
            //返回异常信息，模板Id为空
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_TEMPLATEIDISNULL);
        }
        if (cmsTemplate1 == null) {
            //返回异常信息，模板为空
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }
        //如果模板文件路径改变，上传新的模板文件，并设置模板文件Id
        if (!cmsTemplate.getTemplateFilePath().equals(cmsTemplate1.getTemplateFilePath())) {
            //上传文件到GridFS，返回文件id
            String fileId = uploadTemplate(cmsTemplate);
            cmsTemplate.setTemplateFileId(fileId);
        }
        CmsTemplate template = cmsTemplateRepository.save(cmsTemplate);
        if (template != null) {
            return new ResponseResult(SUCCESS);
        }
        return new ResponseResult(FAIL);
    }

    /**
     * 上传模板文件到GridFS，返回文件id
     *
     * @param cmsTemplate
     * @return
     */
    private String uploadTemplate(CmsTemplate cmsTemplate) {
        String fileId = null;
        try {
            File file = new File(cmsTemplate.getTemplateFilePath());
            //如果模板文件不存在，设置模板文件Id为空
            if (!file.exists()) {
                fileId = null;
            } else {
                //将要存储的文件写入输入流
                FileInputStream fileInputStream = new FileInputStream(file);
                //文件开始存储
                ObjectId objectId = gridFsTemplate.store(fileInputStream, cmsTemplate.getTemplateName(), "");
                //获取存储的文件id
                fileId = objectId.toString();
            }
        } catch (FileNotFoundException e) {
            //返回异常信息，上传模板文件出错
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_UPLOADTEMPLATEISERROR);
        }
        return fileId;
    }

}

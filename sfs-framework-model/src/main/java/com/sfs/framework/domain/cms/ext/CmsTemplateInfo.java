package com.sfs.framework.domain.cms.ext;

import com.sfs.framework.domain.cms.CmsTemplate;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 模板信息包含站点名称的实体类
 * @author yjw
 * @date 2019/12/15 19:25
 */
@Data
@ToString
public class CmsTemplateInfo {

    //模版内容
    private String siteName;

    //模版ID
    private String templateId;

    //站点ID
    private String siteId;

    //模版名称
    private String templateName;

    //模版参数
    private String templateParameter;

    //模版文件Id
    private String templateFileId;

    //创建时间
    private Date templateCreateTime;

    //模板文件路径
    private String templateFilePath;

    public CmsTemplateInfo(CmsTemplate cmsTemplate) {
        this.siteId  = cmsTemplate.getTemplateId();
        this.templateId = cmsTemplate.getTemplateId();
        this.templateName = cmsTemplate.getTemplateName();
        this.templateParameter = cmsTemplate.getTemplateParameter();
        this.templateFileId = cmsTemplate.getTemplateFileId();
        this.templateCreateTime = cmsTemplate.getTemplateCreateTime();
        this.templateFilePath = cmsTemplate.getTemplateFilePath();
    }


}

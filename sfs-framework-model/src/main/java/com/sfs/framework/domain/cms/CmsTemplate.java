package com.sfs.framework.domain.cms;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @Author: mrt.
 * @Description:
 * @Date:Created in 2018/1/24 10:04.
 * @Modified By:
 */
@Data
@ToString
@Document(collection = "cms_template")
public class CmsTemplate {

    @Id
    /**
     * 模版ID
     */
    private String templateId;

    /**
     * 站点ID
     */
    private String siteId;

    /**
     * 模版名称
     */
    private String templateName;

    /**
     * 模版参数
     */
    private String templateParameter;

    /**
     * 模板文件路径
     */
    private String templateFilePath;

    /**
     * 模版文件Id
     * 根据模板文件路径上传模板到GridFS后生成的文件Id
     */
    private String templateFileId;

    /**
     * 创建时间
     */
    private Date templateCreateTime;


}

package com.sfs.framework.domain.cms.request;

import com.sfs.framework.model.request.RequestData;
import lombok.Data;

/**
 * 定义请求模型
 * 初步定义为cms系统的分页请求数据模型
 * 后期可扩展，extends RequestData
 * @author yjw
 * @create 2019/8/15 17:42
 */
@Data
public class QueryPageRequest extends RequestData {

    //站点id
    private String siteId;
    //页面id
    private String pageId;
    //页面名称
    private String pageName;
    //页面别名
    private String pageAliase;
    //模板id
    private String templateId;
    //页面类型
    private String pageType;

}

/**
 *  接口需求:
 *  1. 分页查询CmsPage集合下的数据
 *  2. 根据站点id，模板id，页面别名查询页面信息
 *  3. 接口基于Http Get请求，响应Json数据
 */
package com.sfs.managecms.dao;

import com.sfs.framework.domain.cms.CmsPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 定义CmsPage的dao层操作
 * 操作Mongodb数据库继承MongoRepository<实体类，主键类型>
 *
 * @author yjw
 * @create 2019/8/16 16:46
 */
public interface CmsPageRepository extends MongoRepository<CmsPage, String> {

    /**
     * 根据pageName查询一条数据
     * @param pageName
     * @return
     */
    CmsPage findByPageName(String pageName);

    /**
     * 根据pageName和siteId查询一条数据
     * @param pageName
     * @param siteId
     * @return
     */
    CmsPage findByPageNameAndSiteId(String pageName, String siteId);

    /**
     * 根据siteId和pageType进行分页查询数据
     * @param siteId
     * @param pageType
     * @param pageable
     * @return
     */
    Page<CmsPage> findBySiteIdAndPageType(String siteId, String pageType, Pageable pageable);

    /**
     * 根据站点id、页面名称、页面访问路径查询
     * 原因:cms_page表设置了相关字段的索引，来保证数据的唯一性
     * @param siteId
     * @param pageName
     * @param PageWebpath
     * @return
     */
    CmsPage findBySiteIdAndPageNameAndPageWebPath(String siteId, String pageName, String PageWebpath);

}


//SpringData mongodb 提供自定义方法的规则
//按照findByXXX,findByXXXAndYYY,countXXXAndYYY等规则定义方法,实现查询操作
package com.sfs.cmsclient.service;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.sfs.cmsclient.dao.CmsPageRepository;
import com.sfs.cmsclient.dao.CmsSiteRepository;
import com.sfs.framework.domain.cms.CmsPage;
import com.sfs.framework.domain.cms.CmsSite;
import com.sfs.framework.domain.cms.response.CmsCode;
import com.sfs.framework.exception.ExceptionCast;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Optional;

/**
 * 页面发布服务层
 * 根据页面id，从GridFS下载文件，并保存到文件需要发布的物理路径
 * @author  yjw
 * on 2019/11/1
 */
@Service
public class PageService {

    @Autowired
    private CmsPageRepository cmsPageRepository;

    @Autowired
    private CmsSiteRepository cmsSiteRepository;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFSBucket gridFSBucket;

    /**
     * 生成html文件发布到物理路径
     * @param pageId
     */
    public void savePageToServerPath(String pageId){
        //获取页面信息
        CmsPage cmsPage = getCmsPageById(pageId);
        //获取站点属性
        CmsSite cmsSite = getCmsSiteById(cmsPage.getSiteId());
        //生成页面发布后的物理路径=站点路径+页面物理路径+页面名称
        String pagePath = cmsSite.getSitePhysicalPath() + cmsPage.getPagePhysicalPath() + cmsPage.getPageName();
        System.out.println("页面物理路径："+pagePath);
        //获取文件id
        String htmlFileId = cmsPage.getHtmlFileId();
        //根据文件id从GridFS下载文件流
        InputStream inputStream = getFileById(htmlFileId);
        if(inputStream==null){
            //提示生成的html为空
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_HTMLISNULL);
        }
        FileOutputStream fileOutputStream = null;
        try {
            //根据文件路径生成文件输出流
            fileOutputStream = new FileOutputStream(new File(pagePath));
            //写入文件内容到生成的输出流
            IOUtils.copy(inputStream,fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //关闭输入流
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                //关闭输出流
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据站点id获取站点
     * @param siteId
     * @return
     */
    public CmsSite getCmsSiteById(String siteId) {
        Optional<CmsSite> optional = cmsSiteRepository.findById(siteId);
        if (!optional.isPresent()) {
            //提示站点不存在
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_CMSSITEISNULL);
        }
        return optional.get();
    }

    /**
     * 根据页面id获取页面
     * @param pageId
     * @return
     */
    public CmsPage getCmsPageById(String pageId) {
        Optional<CmsPage> optional = cmsPageRepository.findById(pageId);
        if (!optional.isPresent()) {
            //提示页面不存在
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_CMSPAGEISNULL);
        }
        return optional.get();
    }

    /**
     * 根据文件id到GridFS下载文件
     * @param fileId
     * @return
     */
    public InputStream getFileById(String fileId) {
        try {
            //根据文件id查询文件
            GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(fileId)));
            //根据GridFSBucket打开一个下载流
            GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
            //创建对象，获取流
            GridFsResource gridFsResource = new GridFsResource(gridFSFile, gridFSDownloadStream);
            return gridFsResource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}


//文件下载，首先从GridFS获取文件流，然后根据页面物理路径生成输出流，再将文件流拷贝进输出流，输出到指定路径。
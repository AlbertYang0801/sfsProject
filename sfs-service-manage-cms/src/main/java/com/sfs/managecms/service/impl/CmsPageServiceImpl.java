package com.sfs.managecms.service.impl;

import com.alibaba.fastjson.JSON;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.sfs.framework.domain.cms.CmsPage;
import com.sfs.framework.domain.cms.CmsSite;
import com.sfs.framework.domain.cms.CmsTemplate;
import com.sfs.framework.domain.cms.request.QueryPageRequest;
import com.sfs.framework.domain.cms.response.CmsCode;
import com.sfs.framework.domain.cms.response.CmsPageResult;
import com.sfs.framework.domain.cms.response.CmsPostPageResult;
import com.sfs.framework.exception.ExceptionCast;
import com.sfs.framework.model.response.QueryResponseResult;
import com.sfs.framework.model.response.QueryResult;
import com.sfs.framework.model.response.ResponseResult;
import com.sfs.managecms.config.RabbitConfig;
import com.sfs.managecms.dao.CmsPageRepository;
import com.sfs.managecms.dao.CmsSiteRepository;
import com.sfs.managecms.dao.CmsTemplateRepository;
import com.sfs.managecms.service.CmsPageService;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.sfs.framework.model.response.CommonCode.FAIL;
import static com.sfs.framework.model.response.CommonCode.SUCCESS;

/**
 * 页面操作
 *
 * @author yjw
 * @create 2019/8/19 9:56
 */
@Service
public class CmsPageServiceImpl implements CmsPageService {

    @Autowired
    private CmsPageRepository cmsPageRepository;

    @Autowired
    private CmsTemplateRepository cmsTemplateRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFSBucket gridFSBucket;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private CmsSiteRepository cmsSiteRepository;

    /**
     * 分页查询页面所有数据
     *
     * @param page             页码:指定当前页码为1,在dao层进行操作时需-1
     * @param size
     * @param queryPageRequest
     * @return
     */
    @Override
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest) {
        //若查询条件为空，将对象置为空，保证queryPageRequest对象不为空
        if (queryPageRequest == null) {
            queryPageRequest = new QueryPageRequest();
        }
        //设置分页默认参数
        if (page <= 0) {        //分页查询从第0页开始
            page = 1;
        }
        page = page - 1;
        if (size <= 0) {
            size = 10;
        }
        Pageable pageable = PageRequest.of(page, size);
        //创建CmsPage对象，进行自定义查询条件设置
        CmsPage cmsPage = new CmsPage();
        //设置站点id为查询条件
        if (StringUtils.isNotEmpty(queryPageRequest.getSiteId())) {
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
//        //设置模板id为查询条件
//        if (StringUtils.isNotEmpty(queryPageRequest.getTemplateId())) {
//            cmsPage.setTemplateId(queryPageRequest.getTemplateId());
//        }
        //设置页面别名为查询条件
        if (StringUtils.isNotEmpty(queryPageRequest.getPageAliase())) {
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }
        //设置页面名称为查询条件(模糊查询)
        if (StringUtils.isNotEmpty(queryPageRequest.getPageName())) {
            cmsPage.setPageName(queryPageRequest.getPageName());
        }
        //设置页面类型为查询条件
        if (StringUtils.isNotEmpty(queryPageRequest.getPageType())) {
            cmsPage.setPageType(queryPageRequest.getPageType());
        }
        //创建条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        //设置根据pageAliase页面别名进行模糊查询 => ExampleMatcher.GenericPropertyMatchers.contains()判断当前字段是否包含指定值
        exampleMatcher = exampleMatcher.withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        exampleMatcher = exampleMatcher.withMatcher("pageName", ExampleMatcher.GenericPropertyMatchers.contains());
        //创建example自定义查询条件
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);
        //根据example查询条件和pageable分页参数进行查询
        Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);
        //添加成功后的数   据返回
        QueryResult cmsPageQueryResult = new QueryResult();
        //设置查询出的数据列表
        cmsPageQueryResult.setList(all.getContent());
        //设置查询出的记录条数
        cmsPageQueryResult.setTotal(all.getTotalElements());
        QueryResponseResult queryResponseResult = new QueryResponseResult(SUCCESS, cmsPageQueryResult);
        return queryResponseResult;
    }

    /**
     * 添加数据到表cms_page
     *
     * @param cmsPage
     * @return
     */
    @Override
    public CmsPageResult add(CmsPage cmsPage) {
        if (cmsPage == null) {
            //调用自定义异常抛出类 提示新增页面不存在
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_CMSPAGEISNULL);
        }
        //新增页面之前检查唯一性(根据站点id，页面名称，页面访问url验证数据唯一性)
        CmsPage data = cmsPageRepository.findBySiteIdAndPageNameAndPageWebPath(cmsPage.getSiteId(),
                cmsPage.getPageName(), cmsPage.getPageWebPath());
        //如果表中存在相同数据(满足数据唯一性的索引)
        if (data != null) {
            //页面已存在，抛出异常
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
        }
        //设置pageId为空，添加时会自动生成
        cmsPage.setPageId(null);
        //调用save()方法，保存数据; save()方法:(1)当添加数据id为空时，添加一条数据到数据库。(2)当添加数据id不为空时，更新数据库数据
        cmsPageRepository.save(cmsPage);
        //保存成功，返回成功标识
        CmsPageResult cmsPageResult = new CmsPageResult(SUCCESS, cmsPage);
        return cmsPageResult;
    }

    /**
     * 根据id获取页面数据
     *
     * @param id
     * @return
     */
    @Override
    public CmsPage findById(String id) {
        Optional<CmsPage> optional = cmsPageRepository.findById(id);
        if (optional.isPresent()) {
            CmsPage cmsPage = optional.get();
            return cmsPage;
        }
        return null;
    }

    /**
     * 根据id和对象数据更新
     *
     * @param id
     * @param cmsPage
     * @return
     */
    @Override
    public CmsPageResult update(String id, CmsPage cmsPage) {
        //根据id查找数据，若为空，则更新失败
        CmsPage one = findById(id);
        if (one != null) {
            one.setSiteId(cmsPage.getSiteId());
            one.setTemplateId(cmsPage.getTemplateId());
            one.setPageName(cmsPage.getPageName());
            one.setPageAliase(cmsPage.getPageAliase());
            one.setPageWebPath(cmsPage.getPageWebPath());
            one.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            //更新dataUrl
            one.setDataUrl(cmsPage.getDataUrl());
            one.setPageType(cmsPage.getPageType());
            one.setPageCreateTime(cmsPage.getPageCreateTime());
            //调用JPA的save()方法更新
            CmsPage save = cmsPageRepository.save(one);
            //保存成功
            if (save != null) {
                return new CmsPageResult(SUCCESS, save);
            }
        }
        //更新失败
        return new CmsPageResult(FAIL, null);
    }

    /**
     * 根据id删除数据
     *
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteById(String id) {
        //根据id查询数据
        CmsPage one = findById(id);
        //判断当前数据不为空
        if (one != null) {
            //删除数据
            cmsPageRepository.deleteById(id);
            return new ResponseResult(SUCCESS);
        }
        return new ResponseResult(FAIL);
    }

    /**
     * 页面静态化方法
     * 步骤:
     * 1.获取页面的dataUrl
     * 2.根据dataUrl获取数据
     * 3.获取页面模板信息
     * 4.根据数据和模板信息进行页面静态化
     *
     * @param pageId
     * @return
     */
    @Override
    public String  getPageHtml(String pageId) {
        //根据页面id查询数据
        CmsPage cmsPage = this.findById(pageId);
        if (cmsPage == null) {
            //页面不存在
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_CMSPAGEISNULL);
        }
        //获取页面模型数据
        Map data = getModel(cmsPage);
        if (data == null) {
            //根据页面的数据url获取不到数据
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_DATAISNULL);
        }
        //获取页面模板
        String template = getTemplate(cmsPage);
        if (StringUtils.isEmpty(template)) {
            //页面模板为空
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }
        //执行静态化
        String html = generateHtml(data, template);
        if (StringUtils.isEmpty(html)) {
            //生成的静态html为空
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_HTMLISNULL);
        }
        return html;
    }

    /**
     * 页面发布
     * @param pageId
     * @return
     */
    @Override
    public ResponseResult postPage(String pageId) {
        //根据页面id执行页面静态化，获取页面静态化后的页面
        String pageHtml = this.getPageHtml(pageId);
        if (StringUtils.isEmpty(pageHtml)) {
            //生成的html页面为空
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_HTMLISNULL);
        }
        try {
            //保存页面到GridFS
            saveHtml(pageId, pageHtml);
        } catch (IOException e) {
            //保存页面出错
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_SAVEHTMLERROR);
        }
        //生产者向消费者发消息
        sendPostPage(pageId);
        return new ResponseResult(SUCCESS);
    }

    @Override
    public CmsPageResult savePage(CmsPage cmsPage) {
        if (cmsPage == null) {
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_CMSPAGEISNULL);
        }
        //根据索引查找页面数据，判断页面唯一性
        CmsPage one = cmsPageRepository.findBySiteIdAndPageNameAndPageWebPath(cmsPage.getSiteId(),
                cmsPage.getPageName(), cmsPage.getPageWebPath());
        if (one != null) {
            //若页面已存在，则更新页面
            return this.update(one.getPageId(), cmsPage);
        }
        //页面不存在，则新增页面
        return this.add(cmsPage);
    }

    @Override
    public CmsPostPageResult postPageQuick(CmsPage cmsPage) {
        //存储页面信息到数据库
        CmsPageResult cmsPageResult = this.savePage(cmsPage);
        if (!cmsPageResult.isSuccess()) {
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_SAVEPAGEERROR);
        }
        //得到页面id
        String pageId = cmsPageResult.getCmsPage().getPageId();
        //执行页面静态化(保存到GridFS，向MQ发送消息)
        ResponseResult responseResult = this.postPage(pageId);
        if (!responseResult.isSuccess()) {
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_HTMLISNULL);
        }
        //获取站点id，获取域名
        String siteId = cmsPageResult.getCmsPage().getSiteId();
        CmsSite cmsSite = this.findCmsSiteById(siteId);
        //拼接页面url，pageUrl=站点域名+站点web路径+页面web路径+pageName
        String pageUrl = cmsSite.getSiteDomain() + cmsSite.getSiteWebPath() +
                cmsPageResult.getCmsPage().getPageWebPath() + cmsPageResult.getCmsPage().getPageName();
        //返回发布后的pageUrl
        return new CmsPostPageResult(SUCCESS,pageUrl);
    }

    //*********************************************************************************************************

    /**
     * 保存页面到GridFS
     *
     * @param pageId
     * @param html
     * @return
     */
    private CmsPage saveHtml(String pageId, String html) throws IOException {
        //查询页面
        Optional<CmsPage> optional = cmsPageRepository.findById(pageId);
        if (!optional.isPresent()) {
            //页面不存在
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_CMSPAGEISNULL);
        }
        CmsPage cmsPage = optional.get();
        //保存页面之前先删除
        String htmlFileId = cmsPage.getHtmlFileId();
        if (StringUtils.isNotEmpty(htmlFileId)) {
            gridFsTemplate.delete(Query.query(Criteria.where("_id").is(htmlFileId)));
        }
        //保存html文件到GridFS
        InputStream inputStream = IOUtils.toInputStream(html, "UTF-8");
        ObjectId objectId = gridFsTemplate.store(inputStream, cmsPage.getPageName());
        //文件id
        String fileId = objectId.toString();
        //将生成的文件id添加到CmsPage
        cmsPage.setHtmlFileId(fileId);
        //将更新后的文件id保存到数据库
        cmsPageRepository.save(cmsPage);
        return cmsPage;
    }

    /**
     * 向mq发送消息
     *
     * @param pageId
     */
    private void sendPostPage(String pageId) {
        Optional<CmsPage> optional = cmsPageRepository.findById(pageId);
        if (!optional.isPresent()) {
            //页面不存在
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_CMSPAGEISNULL);
        }
        CmsPage cmsPage = optional.get();
        Map msgMap = new HashMap();
        //设置消息内容
        msgMap.put("pageId", pageId);
        String msg = JSON.toJSONString(msgMap);
        //将siteId作为routingKey
        String siteId = cmsPage.getSiteId();
        //向指定交换机发送消息，指定路由key，指定消息内容
        this.rabbitTemplate.convertAndSend(RabbitConfig.EX_ROUTING_CMS_POSTPAGE, siteId, msg);
    }

    /**
     * 获取页面数据
     * 根据页面信息cmsPage中的dataurl获页面数据
     * 使用restTemplate模板根据url远程获取数据
     *
     * @param cmsPage
     * @return
     */
    public Map getModel(CmsPage cmsPage) {
        //从页面信息中获取到数据url
        String dataUrl = cmsPage.getDataUrl();
        if (StringUtils.isEmpty(dataUrl)) {
            //从页面信息中找不到获取数据的url
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_DATAURLISNULL);
        }
        //调用restTemplate请求模板远程请求dataUrl获取数据，获取数据类型为Map
        ResponseEntity<Map> forEntity = restTemplate.getForEntity(dataUrl, Map.class);
        //获取远程调用接口返回的Map数据
        Map body = forEntity.getBody();
        return body;
    }

    /**
     * 获取页面模板
     * 根据页面信息cmsPage中的模板id获取页面模板
     * 文件存放到MongoDB的文件系统GridFs中
     * 表fs.files存放文件信息
     * 表fs.chunks按大小分割存放的文件内容
     *
     * @param cmsPage
     * @return
     */
    public String getTemplate(CmsPage cmsPage) {
        //从页面信息中获取到模板Id
        String templateId = cmsPage.getTemplateId();
        if (StringUtils.isEmpty(templateId)) {
            //页面模板为空
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }
        //根据模板id获取模板数据
        Optional<CmsTemplate> optionalCmsTemplate = cmsTemplateRepository.findById(templateId);
        if (optionalCmsTemplate.isPresent()) {
            CmsTemplate cmsTemplate = optionalCmsTemplate.get();
            //获取模板文件id，根据templateFileId下载文件
            String templateFileId = cmsTemplate.getTemplateFileId();
            try {
                //根据模板文件id获取文件内容，GridFs文件系统会自动从表fs.files中获取文件具体内容
                GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(templateFileId)));
                //根据文件id打开下载流下载文件
                GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
                //创建GridFsResource，获取下载文件中的流对象
                GridFsResource gridFsResource = new GridFsResource(gridFSFile, gridFSDownloadStream);
                //获取流对象中的数据
                String content = IOUtils.toString(gridFsResource.getInputStream(), "UTF-8");
                return content;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 页面静态化
     * 根据页面数据和页面模板进行页面静态化
     *
     * @param model
     * @param template
     * @return
     */
    public String generateHtml(Map model, String template) {
        //创建freemarker配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
        //创建模板加载器
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        //将字符串形式的模板添加到模板加载器中
        stringTemplateLoader.putTemplate("template", template);
        //将模板加载器设置到freemarker的配置类中
        configuration.setTemplateLoader(stringTemplateLoader);
        try {
            //从配置类中获取模板
            Template template1 = configuration.getTemplate("template");
            //根据模板和数据生成静态化页面(字符串形式)
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template1, model);
            return html;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据站点Id获得站点信息
     *
     * @param siteId
     * @return
     */
    private CmsSite findCmsSiteById(String siteId) {
        Optional<CmsSite> optional = cmsSiteRepository.findById(siteId);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

}

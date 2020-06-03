package com.sfs.managecms.controller;

import com.sfs.framework.web.BaseController;
import com.sfs.managecms.service.CmsPageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

/**
 * 页面预览
 * 页面静态化功能相关(Freemarker)
 * Cms页面预览和课程详情界面的页面
 *
 * @author yjw
 * on 2019/9/20
 */
@Controller
public class CmsPagePreviewController extends BaseController {

    @Autowired
    private CmsPageService cmsPageService;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * cms管理工程页面预览功能调用
     * 使用window.onload打开新的页面进行预览
     *
     * @param pageId
     */
    @RequestMapping(value = "/cms/preview/{pageId}", method = RequestMethod.GET)
    public void preview(@PathVariable("pageId") String pageId) throws IOException {
        //调用页面静态化方法，获取页面静态化之后的页面
        String pageHtml = cmsPageService.getPageHtml(pageId);
        if (StringUtils.isNotEmpty(pageHtml)) {
            //使用response将页面输出到浏览器
            ServletOutputStream servletOutputStream = response.getOutputStream();
            //设置返回的字符为html页面格式，使nginx配置的SSI技术生效
            response.setHeader("Content-type", "text/html;charset=utf-8");
            //将页面静态化的字符输出到浏览器
            servletOutputStream.write(pageHtml.getBytes("utf-8"));
        }
    }

    /**
     * 课程详情模板数据加载的测试
     *
     * @param map
     * @return
     */
//    @GetMapping("/courseViewTest")
//    public String course(Map map) {
//        ResponseEntity<Map> forEntity = restTemplate.getForEntity("http://localhost:31200/course/courseview/4028e581617f945f01617f9dabc40000", Map.class);
//        Map body = forEntity.getBody();
//        map.putAll(body);
//        return "course";
//    }


}

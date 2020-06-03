package com.sfs.managecms;

import com.sfs.managecms.service.CmsPageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by yjw
 * on 2019/9/20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageServiceTest {

    @Autowired
    CmsPageService cmsPageService;

    /**
     * 测试页面静态化的方法
     */
    @Test
    public void testGetPageHtml(){
        String pageHtml = cmsPageService.getPageHtml("5d84408e3ca78f30f0530f53");
        System.out.println(pageHtml);
    }

}

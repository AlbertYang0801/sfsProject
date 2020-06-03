package com.sfs.managecourse;

import com.sfs.framework.domain.cms.CmsPage;
import com.sfs.managecourse.client.CmsPageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Feign远程调用的测试类
 * @author yjw
 * @date 2019/11/22 9:57
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestFeign {

    @Autowired
    private CmsPageClient cmsPageClient;

    @Test
    public void testFeign(){
        CmsPage cmsPage = cmsPageClient.findById("5e5fb3233770fd74d4282a79");
        System.out.println(cmsPage);
    }


}

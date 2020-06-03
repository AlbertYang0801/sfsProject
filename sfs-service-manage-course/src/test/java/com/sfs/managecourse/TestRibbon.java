package com.sfs.managecourse;

import com.sfs.framework.domain.cms.CmsPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Ribbon负载均衡的测试
 * 通过RestTemplate从指定服务远程调用接口
 * 服务名为sfs-service-manage-cms的有两个工程
 * 在调用的时候会进行轮询方式的负载均衡调用
 * @author yjw
 * @date 2019/11/21 17:42
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRibbon {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testRibbon(){
        String serviceId = "sfs-service-manage-cms";
        for(int i=0;i<10;i++){
            ResponseEntity<CmsPage> data = restTemplate.getForEntity("http://"+serviceId+"/cms/page/get/5a754adf6abb500ad05688d9",CmsPage.class);
            CmsPage cmsPage = data.getBody();
            System.out.println(cmsPage);
        }
    }


}

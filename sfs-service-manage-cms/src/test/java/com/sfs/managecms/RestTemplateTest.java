package com.sfs.managecms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * RestTemplate请求模板的测试
 * @author yjw
 * @create 2019/8/16 16:48
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RestTemplateTest {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 测试RestTemplate请求模板的get请求的使用
     */
    @Test
    public void testRestTemplate() {
        //调用RestTemplate的get请求方法获取返回实体，指定返回类型为Map类型
        ResponseEntity<Map> forEntity = restTemplate.getForEntity("http://localhost:31001/cms/config/getModel/5a791725dd573c3574ee333f", Map.class);
        //获取返回实体中的数据
        Map body = forEntity.getBody();
        System.out.println(body);
    }

}


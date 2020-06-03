package com.sfs.auth;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author yjw
 * @date 2020/5/5 16:01
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRedis {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 测试redis的连接和添加
     */
    @Test
    public void addRedis(){
        String key = "user_token:9734b68f‐cf5e‐456f‐9bd6‐df578c711390";
        Map<String,String> map = new HashMap<>();
        map.put("id","101");
        map.put("username","从零开始");
        String value = JSON.toJSONString(map);
        //向redis中存储字符串
        stringRedisTemplate.boundValueOps(key).set(value,60, TimeUnit.SECONDS);
        //读取过期时间，已过去返回-2
        Long expire = stringRedisTemplate.getExpire(key);
        System.out.println(expire);
        //根据key获取value
        String s = stringRedisTemplate.opsForValue().get(key);
        System.out.println(s);
    }


}

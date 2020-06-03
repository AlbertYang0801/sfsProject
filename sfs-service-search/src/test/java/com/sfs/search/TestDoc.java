package com.sfs.search;

import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.rest.RestStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Elasticsearch文档操作的测试
 *
 * @author yjw
 * @date 2019/12/24 14:34
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDoc {

    @Autowired
    RestClient restClient;

    @Autowired
    RestHighLevelClient restHighLevelClient;

    //设置文档id
    private static String docId = "%{id}";

    /**
     * 添加文档的测试
     */
    @Test
    public void addDoc() throws IOException {
        //创建文档数据
        Map map = new HashMap<>();
        map.put("name", "spring开发基础");
        map.put("description", "spring 在java领域非常流行，java程序员都在用");
        map.put("studymodel", "201001");
        map.put("pic", "group1/M00/00/01/wKhlQFqO4MmAOP53AAAcwDwm6SU490.jpg");
        map.put("timestamp", "2018-07-04 18:28:58");
        //索引请求对象
        IndexRequest indexRequest = new IndexRequest("sfs_course", "doc");
        //指定索引文档内容
        indexRequest.source(map);
        //创建索引响应对象
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest);
        //获取响应结果
        DocWriteResponse.Result result = indexResponse.getResult();
        System.out.println(result);
    }

    /**
     * 查询文档的测试
     */
    @Test
    public void queryDoc() throws IOException {
        //创建查询文档请求，传入参数为index、type、docId
        GetRequest getRequest = new GetRequest("sfs_course", "doc", docId);
        //创建查询文档响应
        GetResponse getResponse = restHighLevelClient.get(getRequest);
        //判断查询是否成功
        boolean exists = getResponse.isExists();
        System.out.println(exists);
        //从响应结果获取文档数据
        Map map = getResponse.getSourceAsMap();
        System.out.println(map);
    }

    /**
     * 更新文档的测试
     * @throws IOException
     */
    @Test
    public void updateDoc() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("sfs_course", "doc", docId);
        Map map = new HashMap();
        map.put("description", "更新描述");
        updateRequest.doc(map);
        UpdateResponse update = restHighLevelClient.update(updateRequest);
        RestStatus status = update.status();
        System.out.println(status);
    }

    /**
     * 删除文档
     */
    @Test
    public void deleteDoc() throws IOException {
        String id = "'%{id}'";
        //删除索引请求对象
        DeleteRequest deleteRequest = new DeleteRequest("sfs_course","doc", id);
        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest);
        DocWriteResponse.Result result = deleteResponse.getResult();
        System.out.println(result);
    }

}



package com.sfs.search;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Elasticsearch索引库操作的测试
 * 创建索引库，删除索引库
 * @author yjw
 * @date 2019/12/24 11:07
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestIndex {

    /**
     * ES高等级客户端
     */
    @Autowired
    RestHighLevelClient restHighLevelClient;

    /**
     * ES低等级客户端
     */
    @Autowired
    RestClient restClient;

    /**
     * 创建索引库的测试
     * 为索引库创建映射
     */
    @Test
    public void CreateIndex() throws IOException {
        //创建指定索引请求对象
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("sfs_course");
        //设置创建索引时的参数，设置分片数和副本数
        createIndexRequest.settings(Settings.builder().put("number_of_shards",1)
                .put("number_of_replicas",0));
        //为指定索引库创建映射，并指定映射内容类型为JSON
        createIndexRequest.mapping("doc","{\n" +
                "\t\"properties\": {\n" +
                "\t\"name\": {\n" +
                "\t\t\"type\": \"text\",\n" +
                "\t\t\"analyzer\":\"ik_max_word\",\n" +
                "\t\t\"search_analyzer\":\"ik_smart\"\n" +
                "\t},\n" +
                "\t\"description\": {\n" +
                "\t\t\"type\": \"text\",\n" +
                "\t\t\"analyzer\":\"ik_max_word\",\n" +
                "\t\t\"search_analyzer\":\"ik_smart\"\n" +
                "\t},\n" +
                "\t\t\"pic\":{\n" +
                "\t\t\"type\":\"text\",\n" +
                "\t\t\"index\":false\n" +
                "\t},\n" +
                "\t\t\"studymodel\":{\n" +
                "\t\t\"type\":\"text\"\n" +
                "\t\t},\n" +
                "\t\t\"timestamp\":{\n" +
                "\t\t\t\"type\": \"date\",\n" +
                "\t\t\t\"format\": \"yyyy-MM-dd HH:mm:ss||yyyy-MM-dd\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}", XContentType.JSON);
        //创建索引操作客户端
        IndicesClient client = restHighLevelClient.indices();
        //创建索引库
        CreateIndexResponse createIndexResponse = client.create(createIndexRequest);
        //创建索引响应结果
        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println(acknowledged);
    }

    /**
     * 删除索引库的测试
     */
    @Test
    public void deleteIndex() throws IOException {
        //删除指定索引库
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("sfs_course");
        //创建索引操作客户端
        IndicesClient indices = restHighLevelClient.indices();
        //删除索引
        DeleteIndexResponse deleteIndexResponse = indices.delete(deleteIndexRequest);
        //删除索引响应结果
        boolean acknowledged = deleteIndexResponse.isAcknowledged();
        System.out.println(acknowledged);
    }


}

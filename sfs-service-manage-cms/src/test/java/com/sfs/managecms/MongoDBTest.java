package com.sfs.managecms;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

/**
 * 连接MongoDB的测试
 * Created by yjw
 * on 2019/10/9
 */
public class MongoDBTest {

    @Test
    public void testConnection(){
        //MongoClient client = new MongoClient("localhost",27017);
        //创建连接MongoDB数据库的url，用户名:root 密码:123456
        MongoClientURI clientURI = new MongoClientURI("mongodb://root:123456@localhost:27017");
        //连接MongoDB客户端
        MongoClient client = new MongoClient(clientURI);
        //连接xc_cms数据库
        MongoDatabase database = client.getDatabase("sfs_cms");
        //获取user_test集合
        MongoCollection<Document> collection = database.getCollection("user_test");
        //获取user_test集合第一条记录
        Document first = collection.find().first();
        //转换为json
        String json = first.toJson();
        System.out.println(json);
    }

}

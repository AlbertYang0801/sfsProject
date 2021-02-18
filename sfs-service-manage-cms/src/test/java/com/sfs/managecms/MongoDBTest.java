package com.sfs.managecms;

import com.alibaba.fastjson.JSONArray;
import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.Document;
import org.junit.Test;
import org.springframework.stereotype.Component;


import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.bson.Document;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

/**
 * 连接MongoDB的测试
 * Created by yjw
 * on 2019/10/9
 */
public class MongoDBTest {

    @Test
    public void testConnection() {
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

    /**
     * 导出指定数据库所有集合文件
     */
    @Test
    public void testAllCollections(){
        //MongoClient client = new MongoClient("localhost",27017);
        //创建连接MongoDB数据库的url，用户名:root 密码:123456
        MongoClientURI clientURI = new MongoClientURI("mongodb://root:123456@localhost:27017");
        //连接MongoDB客户端
        MongoClient client = new MongoClient(clientURI);
        //连接xc_cms数据库
        MongoDatabase database = client.getDatabase("sfs_media");
        MongoIterable<String> collectionNames = database.listCollectionNames();
        MongoCursor<String> iterator = collectionNames.iterator();
        System.out.println(collectionNames);
        while (iterator.hasNext()){
            String collectionName = iterator.next();
            //获取集合
            MongoCollection<Document> collection = database.getCollection(collectionName);
            JSONArray jsonArray = new JSONArray();
            MongoCursor<Document> document = collection.find().iterator();
            while (document.hasNext()){
                jsonArray.add(document.next().toJson());
            }
            File txt = new File("D:\\sfs\\mongoDBjson\\" + collectionName + ".json");
            if (!txt.exists()) {
                try {
                    txt.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            byte bytes[] = jsonArray.toJSONString().getBytes();
            int b = bytes.length;
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(txt);
                fos.write(bytes, 0, b);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }



}

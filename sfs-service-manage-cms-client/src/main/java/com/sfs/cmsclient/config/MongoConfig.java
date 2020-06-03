package com.sfs.cmsclient.config;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MongoDB配置类
 * 主要是将GridFS打开下载功能
 * @author  yjw
 * on 2019/9/19
 */
@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.database}")
    String db;

    /**
     * 将MongoDB的下载器注入到容器中
     * GridFSBucket用于打开下载流
     * @param mongoClient
     * @return
     */
    @Bean
    public GridFSBucket getGridFsBucket(MongoClient mongoClient){
        MongoDatabase database = mongoClient.getDatabase(db);
        GridFSBucket bucket = GridFSBuckets.create(database);
        return bucket;
    }

}


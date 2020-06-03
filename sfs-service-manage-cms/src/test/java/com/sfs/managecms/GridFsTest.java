package com.sfs.managecms;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * GridFs的操作文件测试
 * Created by yjw
 * on 2019/9/19
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GridFsTest {

    @Autowired
    GridFsTemplate gridFsTemplate;

    @Autowired
    GridFSBucket gridFSBucket;

    /**
     * GridFs存储文件的测试
     * 将文件按大小256k分割存到mongodb数据库
     * 将分割后的文件分记录存到fs.files表
     * 将完整文件信息存到fs.chunks表
     *
     * @throws FileNotFoundException
     */
    @Test
    public void testGridFs() throws FileNotFoundException {
        //获取要存储的文件
        File file = new File("D:/xc/logs/index_banner.ftl");
        //将要存储的文件写入输入流
        FileInputStream fileInputStream = new FileInputStream(file);
        //文件开始存储
        ObjectId objectId = gridFsTemplate.store(fileInputStream, "轮播图测试文件03", "");
        //获取存储的文件id
        String fileId = objectId.toString();
        System.out.println(fileId);
    }

    /**
     * GridFs读取文件的测试
     * 读取数据库中的文件，以字符形式展示
     *
     * @throws IOException
     */
    @Test
    public void getFile() throws IOException {
        //根据文件id查询文件
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is("5da324a53ca78f3d3c49da61")));
        //使用GridFsBucket打开一个下载流对象
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        //创建GridFsResource对象，获取流
        GridFsResource gridFsResource = new GridFsResource(gridFSFile, gridFSDownloadStream);
        //从流中取数据
        String s = IOUtils.toString(gridFsResource.getInputStream(), "UTF-8");
        System.out.println(s);
    }

    /**
     * GridFs删除文件的测试
     */
    @Test
    public void deleteFile(){
        //根据文件id删除fs.files表和fs.chunks表中的记录
        gridFsTemplate.delete(Query.query(Criteria.where("_id").is("5da324a53ca78f0d989795c4")));
    }


}

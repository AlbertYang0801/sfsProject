package com.sfs.managecms;

import com.sfs.model.Student;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * FreeMarker模板静态化测试
 * Created by yjw
 * on 2019/10/13
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FreemarkerTest {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 使用模板文件加数据生成静态化文件
     * 指定目录输出静态html文件
     *
     * @throws IOException
     * @throws TemplateException
     */
    @Test
    public void testGeneratehtml() throws IOException, TemplateException {
        //创建freemarker配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
        //获取类路径
        String classpath = this.getClass().getResource("/").getPath();
        //设置模板路径
        configuration.setDirectoryForTemplateLoading(new File(classpath + "/templates/"));
        //设置字符集
        configuration.setDefaultEncoding("UTF-8");
        //加载模板文件
        Template template = configuration.getTemplate("test1.ftl");
        //获取数据模型
        Map map = getMap();
        //将静态化文件转换为String字符(将静态模板和数据模型结合生成静态html)
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        //将字符写入输入流
        InputStream inputStream = IOUtils.toInputStream(content);
        //输出文件到指定路径的html
        FileOutputStream fileOutputStream = new FileOutputStream(new File("E:/Chrome/test1.html"));
        //将输入文件写入输出文件
        IOUtils.copy(inputStream, fileOutputStream);
        //关闭输入输出流
        inputStream.close();
        fileOutputStream.close();
    }

    /**
     * 使用指定字符串和数据生成静态化文件
     * 指定目录输出静态化html文件
     *
     * @throws IOException
     * @throws TemplateException
     */
    @Test
    public void testGenerateHtmlByString() throws IOException, TemplateException {
        //设置配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
        //设置模板字符串
        String templateString = "" +
                "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                " 名称：${name}\n" +
                " </body>\n" +
                "</html>";
        //设置模板加载器
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        //将字符串设置到模板加载器
        stringTemplateLoader.putTemplate("template", templateString);
        //在配置类中设置模板加载器
        configuration.setTemplateLoader(stringTemplateLoader);
        //根据模板加载器定义模板
        Template template = configuration.getTemplate("template", "utf-8");
        //定义数据
        Map map = new HashMap();
        map.put("name", "黑马程序员");
        //根据模板和数据生成静态化html内容数据的字符(获取生成的html文件里的内容的字符串形式)
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        //将静态化模板字符数据写入输入流
        InputStream inputStream = IOUtils.toInputStream(content);
        //设置输出流的文件地址
        FileOutputStream fileOutputStream = new FileOutputStream(new File("E:/Chrome/test1.html"));
        //将文件输入流写入到输出流，输出到指定位置
        IOUtils.copy(inputStream, fileOutputStream);
        //关闭文件流
        inputStream.close();
        fileOutputStream.close();

    }

    //数据模型
    private Map getMap() {
        Map<String, Object> map = new HashMap<>();
        //向数据模型放数据
        map.put("name", "黑马程序员");
        Student stu1 = new Student();
        stu1.setName("小明");
        stu1.setAge(18);
        stu1.setMoney(1000.86f);
        stu1.setBirthday(new Date());
        Student stu2 = new Student();
        stu2.setName("小红");
        stu2.setMoney(200.1f);
        stu2.setAge(19);
//      stu2.setBirthday(new Date());
        List<Student> friends = new ArrayList<>();
        friends.add(stu1);
        stu2.setFriends(friends);
        stu2.setBestFriend(stu1);
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
        //向数据模型放数据
        map.put("stus", stus);
        //准备map数据
        HashMap<String, Student> stuMap = new HashMap<>();
        stuMap.put("stu1", stu1);
        stuMap.put("stu2", stu2);
        //向数据模型放数据
        map.put("stu1", stu1);
        //向数据模型放数据
        map.put("stuMap", stuMap);
        return map;
    }



}

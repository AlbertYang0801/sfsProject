package com.sfs.managecms;

import com.sfs.framework.domain.cms.CmsPage;
import com.sfs.framework.domain.cms.CmsPageParam;
import com.sfs.managecms.dao.CmsPageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Mongodb的dao接口继承MongoRepository的基础方法测试
 * @author yjw
 * @create 2019/8/16 16:48
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {

    //注入CmsPage的dao层接口
    @Autowired
    private CmsPageRepository cmsPageRepository;

    /**
     * 测试cmsPage接口的findAll()方法
     */
    @Test
    public void testFindAll(){
        List<CmsPage> all = cmsPageRepository.findAll();
        System.out.println(all);
    }

    /**
     * 测试cmsPage接口的分页查询方法
     */
    @Test
    public void testPageAll(){
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page,size);
        Page<CmsPage> all = cmsPageRepository.findAll(pageable);
        System.out.println(all.getContent());
    }

    /**
     * 测试cmsPage的带条件的分页查询方法
     */
    @Test
    public void testPageAllByExample(){
        //设置分页参数到Pageable
        int page=0;
        int size=10;
        Pageable pageable = PageRequest.of(page,size);
        //创建查询条件对象，根据查询条件参数进行精确查询
        CmsPage cmsPage = new CmsPage();
        cmsPage.setSiteId("5a751fab6abb5044e0d19ea1");
        cmsPage.setPageName("index_banner.html");
        cmsPage.setPageAliase("轮播");
        //创建条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        //设置条件匹配器匹配机制，根据pageAliase字段参数匹配，ExampleMatcher.GenericPropertyMatchers.contains()方法是查询该字段包含指定内容
        exampleMatcher = exampleMatcher.withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        //创建example查询对象
        Example<CmsPage> example = Example.of(cmsPage,exampleMatcher);
        //传入example查询对象和分页参数进行查询
        Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);
        System.out.println(all);                    //分页查询结果:   包含(total:总记录数,content:查询出的数据,page:分页参数)
        System.out.println(all.getTotalPages());    //获取查询出来的总页数
        System.out.println(all.getTotalElements()); //获取查询出来的记录总数
        List<CmsPage> list = all.getContent();      //获取查询出来的数据
        System.out.println(list);
    }

    /**
     * 测试cmsPage接口的save()添加方法
     */
    @Test
    public void testSave(){
        //创建实体类
        CmsPage cmsPage = new CmsPage();
        cmsPage.setSiteId("s01");
        cmsPage.setTemplateId("t01");
        cmsPage.setPageName("测试页面");
        cmsPage.setPageCreateTime(new Date());
        List<CmsPageParam> cmsPageParams = new ArrayList<>();
        CmsPageParam cmsPageParam = new CmsPageParam();
        cmsPageParam.setPageParamName("param1");
        cmsPageParam.setPageParamValue("value1");
        cmsPageParams.add(cmsPageParam);
        cmsPage.setPageParams(cmsPageParams);
        //调用dao接口的save()方法
        cmsPageRepository.save(cmsPage);
        System.out.println(cmsPage);
    }

    /**
     * 测试cmsPage接口的delete()删除方法
     */
    @Test
    public void testDelete(){
        cmsPageRepository.deleteById("5d77174b3ca78f3318f5e6d4");
    }

    /**
     * 测试cmsPage接口的更新方法
     * 接口不提供update()方法
     * 更新流程为:
     * 根据id查询数据
     * 判断非空
     * 调用save()方法保存修改后的对象
     */
    @Test
    public void testUpdate(){
        //查询对象,使用Optional容器封装，将非空检测标准化
        Optional<CmsPage> optional = cmsPageRepository.findById("5d7717d43ca78f2c0cbb1c51");
        //使用Optional的isPresent()方法判断非空
        if(optional.isPresent()){
            //对象非空,使用Optional的get()方法获取保存在Optional容器的对象
            CmsPage cmsPage = optional.get();
            cmsPage.setPageName("又一次修改");
            //保存修改后的对象
            cmsPageRepository.save(cmsPage);
        }
    }

    /**
     * 测试cmsPage接口的自定义方法findByPageName()
     */
    @Test
    public void testFindByPageName(){
        CmsPage cmsPage = cmsPageRepository.findByPageName("又一次修改");
        System.out.println(cmsPage);
    }

    /**
     * 测试cmsPage接口的自定义方法findByPageNameAndSiteId()
     */
    @Test
    public void testFindByPageNameAndSiteID(){
        CmsPage cmsPage = cmsPageRepository.findByPageNameAndSiteId("又一次修改", "s01");
        System.out.println(cmsPage);
    }
}

//关于修改方法的Optional<T>对象,是jdk1.8引入的类型,
//Optional是一个容器对象,包含了我们需要的对象,
//使用isPresent方法判断所包含的对象是否为空,
//isPresent方法返回false表示Optional包含的对象为空,返回true表示包含对象不为空
//可以使用get()方法取出包含的对象
//Optional的优点是:
//1.进行非空判断
//2.将对象非空检测标准化
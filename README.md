

# start from scratch
从零开始服务端代码

## 重要说明
本项目是以学成在线项目为基础进行扩展的，作为本人的软件工程毕设项目，完成度很高。适合同样想练手的同学参考代码和实现逻辑。
近期准备完善文档和项目优化，有需要可以留言，不喜勿喷。

```
         _______________________________________________        
        |   _      __        __                         |       
________|  | | /| / / ___   / / ____ ___   __ _  ___    |_______
\       |  | |/ |/ / / -_) / / / __// _ \ /  ' \/ -_)   |      /
 \      |  |__/|__/  \__/ /_/  \__/ \___//_/_/_/\__/    |     / 
 /      |_______________________________________________|     \ 
/__________)                                        (__________\
```


## 项目介绍
>本项目全名为start from scratch（简称SFS），中文名为从零开始。SFS向个人提供在线教育,为平台提供教学服务，个人用户可以根据自己选择进行学习，老师和学生可以通过平台完成整个教学和学习的过程，市场上类似的平台有：沪江网校等， SFS包括门户、学习中心、教学管理中心、系统管理等功能模块。包含登陆认证、权限管理、课程搜索等技术模块。而且技术上跟进主流。
本文主要描述的是基于微服务的SFS在线学习网站的教学管理中心和系统管理中心两个系统后台的设计与实现。用户进入教学管理中心和系统管理中心都需要进行登录，能登录的用户身份包括教学管理员、系统管理员和超级管理员。教学管理中心主要是对网站的课程信息和课程媒资文件等进行管理，教学管理中心的参与者是教学管理员。系统管理中心主要是对网站页面进行内容管理，和对用户信息进行管理，系统管理中心后台主要用户是系统管理员。而超级管理员拥有着两个系统的所有权限，能进入系统管理中心，也能进入教学管理中心。



## 总体设计
![总体设计图](https://images.gitee.com/uploads/images/2021/0130/132614_f970eebb_5573516.png)


## 技术架构

本项目采用前后端分离模式，通过nginx代理转发。
- 服务端：使用Spring Boot构建、采用Spring Cloud微服务架构、Nginx作为代理服务器、负载均衡等。
- 持久层：数据库使用MySQL、MongoDB、Redis等。
- 数据访问层：使用Spring Data JPA 、Mybatis、Spring Data Mongodb、、Spring Data Redis 、RabbitMQ、HLS、搜索功能使用ElasticSearch、图片服务器使用FastDFS、文件服务器使用GridFS等。
- 业务层：Spring IOC、Aop事务控制、Spring Task任务调度、Feign、Ribbon等。
- 控制层：Spring MVC、RestTemplate、Spring Security Oauth2+JWT等。
- 微服务：Eureka、Zuul、Hystrix、Spring Cloud Conﬁg等。


## 项目环境

- JDK-1.8
- SpringBoot-2.0.1
- Mysql-5.1
- MongoDB
- redis-3.0.4
- RabbitMQ-3.7.18
- CentOS-7.6
- FastDFS
- ElasticSearch
- nginx-1.14.0
- FFmpeg-2018

## 环境安装

#### MongoDB安装

- [MongoDB安装步骤](https://blog.csdn.net/qq_40389276/article/details/99629494)

#### FastDFS

- [Centos7.6安装FastDFS步骤及遇到的错误解决](https://blog.csdn.net/qq_40389276/article/details/103069871)

- [FastDFS和nginx整合步骤](https://blog.csdn.net/qq_40389276/article/details/103089394)

- [Centos7.6设置FastDFS和nginx开机自动启动](https://blog.csdn.net/qq_40389276/article/details/103116590)


## 项目启动总结
>项目采用前后端分类架构，后端采用微服务架构，前端使用vue+webpack，启动不同的模块。
项目需要启动的其他程序有虚拟机(安装了图片服务器FastDFS，设置了开机自启动)、ElasticSearch相关程序、Nginx。

### 前端
1. 启动动态门户-搜索工程：`sfs-ui-pc-teach-dev`
    - 端口:`10000`
2. 启动系统管理中心工程：`sfs-ui-pc-sysmanage-dev `
    - 端口:`11000`
3. 启动教学管理中心工程：`sfs-ui-pc-teach-dev`
    - 端口:`12000`
4. 启动学习中心工程：`sfs-ui-pc-teach-dev`
    - 端口:`13000`

### 后端
1. 启动注册中心工程：`sfs-govern-center`
    - 端口:`50101`
2. 启动cms服务工程：`sfs-service-manage-cms`
    - 端口:`31001`
3. 启动cms消费者工程：`sfs-service-manage-cms-client`
    - 端口:31000
4. 启动文件系统工程：`sfs-service-base-filesystem`
    - 端口:`22100`
5. 启动课程管理工程：`sfs-service-manage-course`
    - 端口:`31200`
6. 启动搜索工程：`sfs-service-search`
    - 端口:`40100`
7. 启动媒资工程：`sfs-service-manage-media`
    - 端口:`31400`
8. 启动媒资处理工程：`sfs-service-manage-media-processor`
    - 端口:`31450`

### 虚拟机
虚拟机`CentOS 7.6_SFS`里安装`FastDFS`和`Redis`，并设置开机自启动。注意下方信息为个人安装虚拟机信息!

- 虚拟机IP:`192.168.126.110`
- 用户名:`root`
- 密码:`123456`


FastDFS安装和设置开机自启动参考博客地址：[FastDFS图片服务器安装步骤及遇到的问题博客目录](https://blog.csdn.net/qq_40389276/category_9515622.html)


### ElasticSearch
1. 启动`elasticsearch-6.2.1\bin\elasticsearch.bat`
2. 访问`localhost:9200`，出现配置信息则启动成功
3. 启动`es可视化head插件`，进入`elasticsearch-head`目录，
	进入`cmd`窗口，运行` npm run start `命令
4. 访问`localhost:9100`，出现可视化界面则启动成功

5. 启动`logstash`,进入`cmd`，运行命令:
    - 导入课程索引:
    ``` 
    logstash.bat -f ..\config\mysql.conf
    ```
    - 导入课程计划媒资信息索引:
     ```
    logstash.bat -f ..\config\mysql_course_media.conf
     ```

### nginx
`nginx`安装在`windows系统`，设置自启动。



## 功能模块

### 门户网站
### 搜索课程
### 学习中心
### 用户中心


### 教学管理中心

[sfs-service-manage-course](sfs-service-manage-course/README.md)

### 系统管理中心
[sfs-service-manage-cms](sfs-service-manage-cms/README.md)



## 资源清单

- Mysql的sql语句

  [mysql](resource/mysql)

- MongoDB建表语句

  [MongoDB](resource/mongoDBjson)

- nginx配置文件

  [nginx.conf](resource/nginx)





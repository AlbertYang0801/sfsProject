# 教学管理中心
---
## 概述

教学管理中心的核心是对课程资源和视频资源进行后台管理，系统主要使用者是`教学管理员`。`教学管理员`可以是网站的合作教师或者是教育机构的教师，是教学管理中心最重要的参与者。

## 模块介绍

教学管理中心主要包含`课程管理`模块和`媒资管理`两个模块。

- 课程管理模块主要是对整个学习网站包含的课程信息进行管理，该模块主要包含`课程基本信息管理`、`课程图片信息管理`、`课程章节管理`和`课程发布管理`。

- 媒资管理主要是对网站所有的媒资文件进行管理，包含`上传媒资文件`和`媒资文件信息管理`。

<img src="https://cdn.jsdelivr.net/gh/AlbertYang0801/pic-bed@main/img/20210226184907.png" alt="教学管理用例图" style="zoom: 67%;" />

### 课程管理

课程管理模块是教学管理中心的一个子模块，对网站拥有的所有课程进行管理。包括`新增课程基本信息`、`管理课程`、`课程图片`、`课程计划`、`课程预览`和`课程发布`等操作。

![我的课程](https://cdn.jsdelivr.net/gh/AlbertYang0801/pic-bed@main/img/20210226184927.png)

#### 模块地址

[sfs-service-manage-course](sfs-service-manage-course)

#### 1. 新增课程基本信息
新增课程基本信息可设置课程名称、课程等级、等展示信息，还可设置课程分类，用于课程搜索。代码可全局搜索`CourseController`类。

![新增课程](https://images.gitee.com/uploads/images/2021/0130/205008_877ccd54_5573516.png "新增课程.png")



#### 2. 管理课程

可修改课程名称、课程等级、等展示信息，还可设置课程分类，用于课程搜索。代码可全局搜索`CourseController`类。

![管理课程基本信息](https://images.gitee.com/uploads/images/2021/0130/205017_84c7f77b_5573516.png "新增课程基本信息.png")




#### 3. 课程图片管理

课程图片的管理使用了`FastDFS`图片服务器，包含图片上传和图片删除操作。图片上传是将图片文件上传到图片服务器，并且将返回的图片地址存到数据库。图片删除只删除数据库中的数据，不删除服务器的图片文件。页面通过图片服务器的url进行图片展示。


FastDFS安装见博客地址：[FastDFS图片服务器安装步骤及遇到的问题博客目录](https://blog.csdn.net/qq_40389276/category_9515622.html)


![图片管理](https://images.gitee.com/uploads/images/2021/0130/210207_d7c1ae04_5573516.png "图片.png")



- 上传图片到服务器

将图片上传到图片服务器`FastDFS`，并将图片访问地址保存到数据库。代码可全局搜索`FileSystemController`类。

- 图片信息管理

包含查询图片信息、保存图片信息、删除图片信息。代码可全局搜索`CourseController`类。

#### 4.课程计划

课程计划是课程信息的一部分，对应的就是课程的章节结构，在播放视频的时候可以根据课程计划任意选择章节播放。

![](https://cdn.jsdelivr.net/gh/AlbertYang0801/pic-bed@main/img/20210226182613.png)



#### 5. 课程预览和课程发布





### 媒资管理
> 媒资管理模块是教学管理中心的一个子模块，主要包含上传文件和我的媒资两个模块。我的媒资模块包括文件查询和文件删除等操作，教学管理员登陆系统后可以进行上传文件和管理媒资文件等操作。


#### 模块地址

[sfs-service-manage-media](sfs-service-manage-media)

[sfs-service-manage-media-processor](sfs-service-manage-media-processor)

#### 上传文件
---
![上传文件](https://images.gitee.com/uploads/images/2021/0130/202257_02b8ae44_5573516.png "上传文件.png")

1. 文件断点续传
>上传文件模块实现断点续传将视频文件保存到指定路径，由于上传的文件多是视频文件，文件大小较大，所以采用了断点续传的上传方案来保证系统的高可用。在上传文件开始时将文件分割为多个块文件，分别上传块文件，到所有块文件上传结束合并这些块文件生成和原始一致的文件。如果中途文件上传失败，下次上传时可从上次失败的块文件开始上传，大大的节省系统资源。

代码见：[断点续传代码Controller类](src\main\java\com\sfs\managemedia\controller\MediaUploadController.java)

开发文档见：[视频上传第一步-断点续传开发文档](https://gitee.com/zztiyjw/sfsProject/wikis/pages?sort_id=3482288&doc_id=1219120)


2. 文件处理
>由于页面流媒体播放协议选用了HLS协议，所以还需要将上传后的视频文件进行处理，将视频文件转换成m3u8格式的视频文件，实现视频的边下载边播放。

代码见：[媒资处理mq消费者类](src\main\java\com\sfs\mediaprocess\mq\MediaProcessTask.java)

开发文档见：[视频上传第二步-文件处理开发文档](https://gitee.com/zztiyjw/sfsProject/wikis/pages?sort_id=3482289&doc_id=1219120)


#### 我的媒资
---
> 我的媒资可以对上传后的媒资信息进行管理，可查询媒资文件，删除媒资文件。删除媒资文件只删除数据库里的媒资数据，不删除本地媒资文件。


![我的媒资](https://images.gitee.com/uploads/images/2021/0130/202311_218af0fa_5573516.png "我的媒资.png")


代码见：[我的媒资Controller类](src\main\java\com\sfs\managemedia\controller\MediaFileController.java)



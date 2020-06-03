package com.sfs.framework.domain.filesystem;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * 图片上传的模型类
 * @author yjw on 2018/2/5.
 */
@Data
@ToString
@Document(collection = "filesystem")
public class FileSystem {

    /**
     * fastDFS返回的文件路径
     */
    @Id
    private String fileId;
    /**
     * 文件请求路径
     */
    private String filePath;
    /**
     * 文件大小
     */
    private long fileSize;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 图片宽度
     */
    private int fileWidth;
    /**
     * 图片高度
     */
    private int fileHeight;
    /**
     * 用户id，用于授权
     */
    private String userId;
    /**
     * 业务key：文件系统服务为其他子系统提供的一个业务标识字段，各子系统根据自己需求去使用
     */
    private String businesskey;
    /**
     * 业务标签，标识此文件来自哪个系统
     */
    private String filetag;
    /**
     * 文件元信息
     */
    private Map metadata;

}

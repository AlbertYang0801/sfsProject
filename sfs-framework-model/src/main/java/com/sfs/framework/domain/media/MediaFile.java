package com.sfs.framework.domain.media;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @Author: mrt.
 * @Description:
 * @Date:Created in 2018/1/24 10:04.
 * @Modified By:
 */
@Data
@ToString
@Document(collection = "media_file")
public class MediaFile {
    /*
    文件id、名称、大小、文件类型、文件状态（未上传、上传完成、上传失败）、上传时间、视频处理方式、视频处理状态、hls_m3u8,hls_ts_list、课程视频信息（课程id、章节id）
     */


    /**
     * 文件id，为文件的Md5值
     */
    @Id
    private String fileId;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件原始名称
     */
    private String fileOriginalName;
    /**
     * 文件路径（文件相对路径）
     */
    private String filePath;
    /**
     * 文件url
     */
    private String fileUrl;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * mimetype(文件媒体类型)
     */
    private String mimeType;
    /**
     * 文件大小
     */
    private Long fileSize;
    /**
     * 文件状态(未上传:301001、上传完成:301002、上传失败:301003)
     */
    private String fileStatus;
    /**
     * 上传时间
     */
    private Date uploadTime;
    /**
     * 处理状态(处理中:303001、处理成功:303002、处理失败:303003、无需处理:303004)
     */
    private String processStatus;
    /**
     * hls处理
     */
    private MediaFileProcess_m3u8 mediaFileProcess_m3u8;
    /**
     * tag标签用于查询
     */
    private String tag;


}

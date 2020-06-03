package com.sfs.managemedia.service;

import com.sfs.framework.domain.media.response.CheckChunkResult;
import com.sfs.framework.model.response.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 媒资管理
 *
 * @author yjw
 * @date 2020/4/2 22:27
 */
public interface MediaUploadService {


    /**
     * 文件注册
     * 1.检查上传文件是否存在
     * 2.不存在则创建文件目录
     * @param fileMd5
     * @param fileName
     * @param fileSize
     * @param mimetype
     * @param fileExt
     * @return
     */
    ResponseResult register(String fileMd5, String fileName, String fileSize, String mimetype, String fileExt);

    /**
     * 检查分块
     * 1.检查当前分块是否已经上传
     * 2.未上传检查分块上传路径是否存在，不存在则创建
     * @param fileMd5 文件Md
     * @param chunk 当前分块下标
     * @param chunkSize 分块大小
     * @return
     */
    CheckChunkResult checkChunk(String fileMd5, Integer chunk, Integer chunkSize);

    /**
     * 上传分块
     * @param file
     * @param chunk
     * @param fileMd5
     * @return
     */
    ResponseResult uploadChunk(MultipartFile file, Integer chunk, String fileMd5);

    /**
     * 合并分块
     * @param fileMd5
     * @param fileName
     * @param fileSize
     * @param mimetype
     * @param fileExt
     * @return
     */
    ResponseResult mergeChunks(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt);

}

package com.sfs.api.media;

import com.sfs.framework.domain.media.response.CheckChunkResult;
import com.sfs.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

/**
 * 媒资管理接口
 * @author yjw
 * @date 2020/4/2 22:18
 */
@Api(value = "媒资管理接口",description = "媒资管理接口，提供文件上传，文件处理等接口")
public interface MediaUploadControllerApi {

    /**
     * 文件上传注册接口
     * @param fileMd5 文件Md5
     * @param fileName
     * @param fileSize
     * @param mimetype
     * @param fileExt 文件扩展名
     * @return
     */
    @ApiOperation("文件上传注册")
    public ResponseResult register(String fileMd5,String fileName,String fileSize,String mimetype,String fileExt);

    /**
     * 分块检查
     * @param fileMd5
     * @param chunk
     * @param chunkSize
     * @return
     */
    @ApiOperation("分块检查")
    public CheckChunkResult checkChunk(String fileMd5,Integer chunk,Integer chunkSize);

    /**
     * 上传分块
     * @param file
     * @param chunk
     * @param fileMd5
     * @return
     */
    @ApiOperation("上传分块")
    public ResponseResult uploadChunk(MultipartFile file,Integer chunk,String fileMd5);

    /**
     * 合并分块文件
     * @param fileMd5
     * @param fileName
     * @param fileSize
     * @param mimetype
     * @param fileExt
     * @return
     */
    @ApiOperation("合并分块")
    public ResponseResult mergeChunks(String fileMd5,String fileName,Long fileSize,String mimetype,String fileExt);


}

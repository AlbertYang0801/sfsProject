package com.sfs.managemedia.controller;

import com.sfs.api.media.MediaUploadControllerApi;
import com.sfs.framework.domain.media.response.CheckChunkResult;
import com.sfs.framework.model.response.ResponseResult;
import com.sfs.managemedia.service.MediaUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 媒资管理
 *
 * @author yjw
 * @date 2020/4/2 22:26
 */
@RestController
@RequestMapping("/media/upload")
public class MediaUploadController implements MediaUploadControllerApi {

    @Autowired
    private MediaUploadService mediaUploadService;

    /**
     * 文件上传注册
     *
     * @param fileMd5  文件Md5
     * @param fileName
     * @param fileSize
     * @param mimetype
     * @param fileExt  文件扩展名
     * @return
     */
    @PostMapping("/register")
    @Override
    public ResponseResult register(@RequestParam("fileMd5") String fileMd5, @RequestParam("fileName") String fileName,
                                   @RequestParam("fileSize") String fileSize, @RequestParam("mimetype") String mimetype,
                                   @RequestParam("fileExt") String fileExt) {
        return mediaUploadService.register(fileMd5, fileName, fileSize, mimetype, fileExt);
    }

    /**
     * 检查分块
     *
     * @param fileMd5
     * @param chunk
     * @param chunkSize
     * @return
     */
    @PostMapping("/checkchunk")
    @Override
    public CheckChunkResult checkChunk(@RequestParam("fileMd5") String fileMd5, @RequestParam("chunk") Integer chunk,
                                       @RequestParam("chunkSize") Integer chunkSize) {
        return mediaUploadService.checkChunk(fileMd5, chunk, chunkSize);
    }

    /**
     * 上传分块
     *
     * @param file
     * @param chunk
     * @param fileMd5
     * @return
     */
    @PostMapping("/uploadchunk")
    @Override
    public ResponseResult uploadChunk(@RequestParam("file") MultipartFile file,
                                      @RequestParam("chunk") Integer chunk, @RequestParam("fileMd5") String fileMd5) {
        return mediaUploadService.uploadChunk(file, chunk, fileMd5);
    }

    /**
     * 合并分块
     *
     * @param fileMd5
     * @param fileName
     * @param fileSize
     * @param mimetype
     * @param fileExt
     * @return
     */
    @PostMapping("/mergechunks")
    @Override
    public ResponseResult mergeChunks(@RequestParam("fileMd5") String fileMd5, @RequestParam("fileName") String fileName,
                                      @RequestParam("fileSize") Long fileSize, @RequestParam("mimetype") String mimetype,
                                      @RequestParam("fileExt") String fileExt) {
        return mediaUploadService.mergeChunks(fileMd5, fileName, fileSize, mimetype, fileExt);
    }

}

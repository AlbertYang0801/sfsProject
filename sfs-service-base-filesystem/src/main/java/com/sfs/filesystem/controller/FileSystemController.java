package com.sfs.filesystem.controller;

import com.sfs.api.filesystem.FileSystemControllerApi;
import com.sfs.filesystem.service.FileSystemService;
import com.sfs.framework.domain.filesystem.response.UploadFileResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yjw
 * @date 2019/11/18 14:46
 */
@RestController
@RequestMapping("/filesystem")
public class FileSystemController implements FileSystemControllerApi {

    @Autowired
    private FileSystemService fileSystemService;

    /**
     * 上传文件到FastDFS
     * 保存上传后的文件信息到数据库
     *
     * @param multipartFile 文件信息
     * @param fileTag       文件标签
     * @param businessKey   业务key
     * @param metadata      元信息，json格式
     * @returne
     */
    @Override
    @PostMapping("/upload")
    public UploadFileResult upload(@RequestParam("file") MultipartFile multipartFile,
                                   @RequestParam(value = "fileTag", required = true) String fileTag,
                                   @RequestParam(value = "businessKey", required = false) String businessKey,
                                   @RequestParam(value = "metedata", required = false) String metadata) {
        return fileSystemService.upload(multipartFile, fileTag, businessKey, metadata);
    }

}

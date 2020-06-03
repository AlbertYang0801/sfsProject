package com.sfs.filesystem.service;

import com.sfs.framework.domain.filesystem.response.UploadFileResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件系统管理Service接口
 *
 * @author yjw
 * @date 2019/11/18 14:44
 */
public interface FileSystemService {

    /**
     * 上传文件到FastDFS
     * @param multipartFile
     * @param fileTag
     * @param businessKey
     * @param metadata
     * @return
     */
    UploadFileResult upload(MultipartFile multipartFile, String fileTag,
                            String businessKey, String metadata);

}

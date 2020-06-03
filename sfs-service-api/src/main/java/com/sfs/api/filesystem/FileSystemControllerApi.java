package com.sfs.api.filesystem;

import com.sfs.framework.domain.filesystem.response.UploadFileResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

/**
 * FastDFS文件系统接口
 *
 * @author yjw
 * @date 2019/11/18 14:38
 */
@Api(value = "文件系统接口", tags = {"文件系统接口"})
public interface FileSystemControllerApi {

    /**
     * 上传文件接口
     * @param multipartFile
     * @param fileTag
     * @param businessKey
     * @param metadata
     * @return
     */
    @ApiOperation(value = "文件上传")
    UploadFileResult upload(MultipartFile multipartFile,
                            String fileTag, String businessKey, String metadata);

}

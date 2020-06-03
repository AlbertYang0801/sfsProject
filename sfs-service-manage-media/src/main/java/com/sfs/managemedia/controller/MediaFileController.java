package com.sfs.managemedia.controller;

import com.sfs.api.media.MediaFileControllerApi;
import com.sfs.framework.domain.media.request.QueryMediaFileRequest;
import com.sfs.framework.model.response.QueryResponseResult;
import com.sfs.framework.model.response.ResponseResult;
import com.sfs.managemedia.service.MediaFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 我的媒资
 *
 * @author yjw
 * @date 2020/4/9 23:47
 */
@RestController
@RequestMapping("/media/file")
public class MediaFileController implements MediaFileControllerApi {

    @Autowired
    private MediaFileService mediaFileService;

    /**
     * 我的媒资查询媒资文件列表
     * 支持分页查询
     * 查询参数有：文件原始名称、文件标签、处理状态
     * @param page
     * @param size
     * @param queryMediaFileRequest
     * @return
     */
    @GetMapping("/list/{page}/{size}")
    @Override
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size,
                                        QueryMediaFileRequest queryMediaFileRequest) {
        return mediaFileService.findList(page, size, queryMediaFileRequest);

    }

    /**
     * 根据文件id删除媒资信息
     * @param fileId
     * @return
     */
    @DeleteMapping("/delete/{fileId}")
    @Override
    public ResponseResult deleteMedia(@PathVariable("fileId") String fileId) {
        return mediaFileService.deleteMedia(fileId);
    }


}

package com.sfs.api.media;

import com.sfs.framework.domain.media.request.QueryMediaFileRequest;
import com.sfs.framework.model.response.QueryResponseResult;
import com.sfs.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 我的媒资接口
 *
 * @author yjw
 * @date 2020/4/9 23:47
 */
@Api(value = "媒资文件管理", description = "媒资文件管理接口")
public interface MediaFileControllerApi {

    /**
     * 媒资文件列表查询，支持分页
     *
     * @param page
     * @param size
     * @param queryMediaFileRequest
     * @return
     */
    @ApiOperation("查询媒资文件列表")
    public QueryResponseResult findList(int page, int size, QueryMediaFileRequest queryMediaFileRequest);

    /**
     * 删除媒资文件
     *
     * @param fileId
     * @return
     */
    @ApiOperation("删除媒资文件")
    public ResponseResult deleteMedia(String fileId);


}

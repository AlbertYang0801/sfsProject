package com.sfs.managemedia.service;

import com.sfs.framework.domain.media.request.QueryMediaFileRequest;
import com.sfs.framework.model.response.QueryResponseResult;
import com.sfs.framework.model.response.ResponseResult;

/**
 * @author yjw
 * @date 2020/4/10 0:02
 */
public interface MediaFileService {

    QueryResponseResult findList(int page, int size, QueryMediaFileRequest queryMediaFileRequest);

    ResponseResult deleteMedia(String fileId);


}

package com.sfs.framework.domain.media.request;

import com.sfs.framework.model.request.RequestData;
import lombok.Data;

/**
 * @author yjw
 * @date 2020/4/9 23:47
 */
@Data
public class QueryMediaFileRequest extends RequestData {

    /**
     * 文件原始名
     */
    private String fileOriginalName;
    /**
     * 处理状态
     */
    private String processStatus;
    /**
     * tag标签用于查询
     */
    private String tag;

}

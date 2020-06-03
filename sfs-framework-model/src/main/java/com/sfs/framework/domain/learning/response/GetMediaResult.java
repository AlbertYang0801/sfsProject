package com.sfs.framework.domain.learning.response;

import com.sfs.framework.model.response.ResponseResult;
import com.sfs.framework.model.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 在线学习返回的封装实体类
 * @author yjw
 * @date 2020/4/25 18:29
 */
@Data
@ToString
@NoArgsConstructor
public class GetMediaResult extends ResponseResult {

    public GetMediaResult(ResultCode resultCode,String fileUrl){
        super(resultCode);
        this.fileUrl = fileUrl;
    }

    /**
     * 媒资文件播放地址
     */
    private String fileUrl;

}

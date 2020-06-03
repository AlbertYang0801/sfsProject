package com.sfs.framework.domain.media.response;

import com.google.common.collect.ImmutableMap;
import com.sfs.framework.model.response.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;


/**
 * Created by admin on 2018/3/5.
 */
@ToString
public enum MediaCode implements ResultCode {
    UPLOAD_FILE_REGISTER_FAIL(false,22001,"上传文件在系统注册失败，请刷新页面重试！"),
    UPLOAD_FILE_REGISTER_EXIST(false,22002,"上传文件在系统已存在！"),
    CHUNK_FILE_EXIST_CHECK(true,22003,"分块文件在系统已存在！"),
    MERGE_FILE_FAIL(false,22004,"合并文件失败，文件在系统已存在！"),
    MERGE_FILE_CHECKFAIL(false,22005,"合并文件校验失败！"),
    UPLOAD_CHUNK_FILE_IS_NULL(false,22006,"上传块文件内容为空！"),
    CREATE_CHUNK_FILE_FAIL(false,22007,"创建块文件目录失败！"),
    UPLOAD_CHUNK_FILE_FAIL(false,22008,"上传块文件失败！"),
    MERGE_FILE_CERETE_FAIL(false,22009,"合并文件目录创建失败！"),
    MERGE_FILE_IS_FAIL(false,22010,"合并文件失败，合并过程出错！"),
    DELETE_CHUNK_IS_FAIL(false,22011,"删除分块文件出错！"),
    SELECT_VIDEO_IS_FAIL(false,22012,"查询视频信息失败！"),
    AVI_TO_MP4_IS_FAIL(false,22013,"avi格式转为mp4格式失败！"),
    DELETE_FILE_IS_FAIL(false,22012,"删除文件，文件id为空！"),;

    //操作代码
    @ApiModelProperty(value = "媒资系统操作是否成功", example = "true", required = true)
    boolean success;

    //操作代码
    @ApiModelProperty(value = "媒资系统操作代码", example = "22001", required = true)
    int code;
    //提示信息
    @ApiModelProperty(value = "媒资系统操作提示", example = "文件在系统已存在！", required = true)
    String message;
    private MediaCode(boolean success,int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }
    private static final ImmutableMap<Integer, MediaCode> CACHE;

    static {
        final ImmutableMap.Builder<Integer, MediaCode> builder = ImmutableMap.builder();
        for (MediaCode commonCode : values()) {
            builder.put(commonCode.code(), commonCode);
        }
        CACHE = builder.build();
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}

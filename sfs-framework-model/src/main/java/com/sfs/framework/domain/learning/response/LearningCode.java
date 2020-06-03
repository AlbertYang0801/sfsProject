package com.sfs.framework.domain.learning.response;

import com.google.common.collect.ImmutableMap;
import com.sfs.framework.model.response.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;


/**
 * @author admin
 * @date 2018/3/5
 */
@ToString
public enum LearningCode implements ResultCode {

    
    LEARNING_GET_MEDIA_ERROR(false, 40100, "获取视频播放地址出错！"),
    LEARNING_MSG_LOGIN(false, 40101, "用户请登录,以便于记录学习信息！"),
    LEARNING_COURSE_EXISIT(false, 40102, "该课程已收藏！");

    //操作代码
    @ApiModelProperty(value = "操作是否成功", example = "true", required = true)
    boolean success;

    //操作代码
    @ApiModelProperty(value = "操作代码", example = "22001", required = true)
    int code;
    //提示信息
    @ApiModelProperty(value = "操作提示", example = "操作过于频繁！", required = true)
    String message;

    private LearningCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    private static final ImmutableMap<Integer, LearningCode> CACHE;

    static {
        final ImmutableMap.Builder<Integer, LearningCode> builder = ImmutableMap.builder();
        for (LearningCode commonCode : values()) {
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

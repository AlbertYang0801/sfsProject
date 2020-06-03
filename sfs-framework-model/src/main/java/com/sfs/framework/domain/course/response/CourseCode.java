package com.sfs.framework.domain.course.response;

import com.google.common.collect.ImmutableMap;
import com.sfs.framework.model.response.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;


/**
 * @author admin
 * @date 2018/3/5
 */
@ToString
public enum CourseCode implements ResultCode {

    COURSE_DENIED_DELETE(false, 31001, "删除课程失败，只允许删除本机构的课程！"),
    COURSE_PUBLISH_PERVIEWISNULL(false, 31002, "还没有进行课程预览！"),
    COURSE_PUBLISH_CDETAILERROR(false, 31003, "创建课程详情页面出错！"),
    COURSE_PUBLISH_COURSEIDISNULL(false, 31004, "课程Id为空！"),
    COURSE_PUBLISH_VIEWERROR(false, 31005, "发布课程视图出错！"),

    COURSE_MEDIS_URLISNULL(false, 31101, "选择的媒资文件访问地址为空！"),
    COURSE_MEDIS_NAMEISNULL(false, 31102, "选择的媒资文件名称为空！"),
    COURSE_PUBLISH_TEACHPLANISNULL(false, 31103, "添加课程计划关键信息为空！"),
    COURSE_SELECT_TEACHPLANISNULL(false, 31109, "查询课程计划信息为空！"),

    COURSE_SELECT_PARENTISNULL(false, 31104, "查询父节点信息为空！"),
    COURSE_PUBLISH_COURSEBASEISNULL(false, 31105, "课程关键信息为空！"),
    COURSE_SELECT_COURSEBASEISNULL(false, 31106, "查询课程信息为空！"),
    COURSE_SELECT_COURSEMARKETISNULL(false, 31107, "查询课程营销信息为空！"),
    COURSE_SELECT_COURSEPICISNULL(false, 31108, "查询课程图片信息为空！"),
    COURSE_PUBLISH_CREATE_INDEX_ERROR(false, 31109, "创建课程索引信息失败！"),
    SAVE_MEDIA_PARAM_IS_NULL(false, 31110, "绑定课程计划与媒资传入主要参数为空！"),
    COURSE_MEDIA_TEACHPLAN_GRADEERROR(false, 31111, "绑定视频的课程计划级别不是叶子节点！");

    //操作代码
    @ApiModelProperty(value = "操作是否成功", example = "true", required = true)
    boolean success;

    //操作代码
    @ApiModelProperty(value = "操作代码", example = "22001", required = true)
    int code;
    //提示信息
    @ApiModelProperty(value = "操作提示", example = "操作过于频繁！", required = true)
    String message;

    private CourseCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    private static final ImmutableMap<Integer, CourseCode> CACHE;

    static {
        final ImmutableMap.Builder<Integer, CourseCode> builder = ImmutableMap.builder();
        for (CourseCode commonCode : values()) {
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

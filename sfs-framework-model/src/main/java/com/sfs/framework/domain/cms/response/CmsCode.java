package com.sfs.framework.domain.cms.response;

import com.sfs.framework.model.response.ResultCode;
import lombok.ToString;

/**
 * Created by mrt on 2018/3/5.
 */
@ToString
public enum CmsCode implements ResultCode {

    CMS_ADDPAGE_EXISTSNAME(false,24001,"页面名称等信息已存在！"),
    CMS_GENERATEHTML_DATAURLISNULL(false,24002,"从页面信息中找不到获取数据的url！"),
    CMS_GENERATEHTML_DATAISNULL(false,24003,"根据页面的数据url获取不到数据！"),
    CMS_GENERATEHTML_TEMPLATEISNULL(false,24004,"页面模板为空！"),
    CMS_GENERATEHTML_HTMLISNULL(false,24005,"生成的静态html为空！"),
    CMS_GENERATEHTML_SAVEHTMLERROR(false,24006,"保存静态html出错！"),
    CMS_COURSE_PERVIEWISNULL(false,24007,"预览页面为空！"),
    CMS_POSTPAGE_PAGEISNULL(false,24008,"发布页面为空！"),
    CMS_ADDPAGE_CMSPAGEISNULL(false,24009,"新增页面不存在"),
    CMS_ADDPAGE_CMSSITEISNULL(false,24010,"站点不存在"),
    CMS_ADDPAGE_CMSTEMPLATEISNULL(false,24011,"添加的模板数据不存在"),
    CMS_ADDPAGE_UPLOADTEMPLATEISERROR(false,24012,"上传模板文件出错"),
    CMS_ADDPAGE_TEMPLATEIDISNULL(false,24013,"模板id为空"),
    CMS_ADDPAGE_SAVEPAGEERROR(false,24014,"保存页面失败！");

    //操作代码
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;
    private CmsCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
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

package com.sfs.framework.domain.cms.response;

import com.sfs.framework.domain.cms.CmsPage;
import com.sfs.framework.model.response.ResponseResult;
import com.sfs.framework.model.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author  2018/3/31.
 */
@Data
@NoArgsConstructor
public class CmsPageResult extends ResponseResult {
    CmsPage cmsPage;
    public CmsPageResult(ResultCode resultCode,CmsPage cmsPage) {
        super(resultCode);
        this.cmsPage = cmsPage;
    }
}

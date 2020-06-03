package com.sfs.framework.domain.ucenter.request;

import com.sfs.framework.model.request.RequestData;
import lombok.Data;
import lombok.ToString;

/**
 * Created by admin on 2018/3/5.
 */
@Data
@ToString
public class QueryUserRequest extends RequestData {

    String username;
    String name;
    String utype;

}

package com.sfs.framework.domain.ucenter.ext;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 认证服务申请令牌实体类
 * @author yjw on 2018/5/21.
 */
@Data
@ToString
@NoArgsConstructor
public class AuthToken {

    /**
     * 身份令牌内容，用于校验用户是否认证(内容较长，存入redis)
     */
    String access_token;

    /**
     * 刷新令牌内容
     */
    String refresh_token;

    /**
     * 令牌标识，用于授权(内容较短，存入cookie)
     */
    String jwt_token;

}

package com.sfs.auth.client;

import com.sfs.framework.client.SfsServiceList;
import com.sfs.framework.domain.ucenter.ext.SfsUserExt;
import com.sfs.framework.domain.ucenter.request.LoginRequest;
import com.sfs.framework.model.response.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 远程调用用户中心的接口
 * @author yjw
 * @date 2020/5/6 1:49
 */
@FeignClient(value = SfsServiceList.SFS_SERVICE_UCENTER)
public interface UserClient {

    /**
     * 根据用户名获得用户信息和用户扩展信息
     * @param username
     * @return
     */
    @GetMapping("/ucenter/getuserext/{username}")
    public SfsUserExt getUserext(@PathVariable("username")String username);


    /**
     * 用户注册
     * @param loginRequest
     * @return
     */
    @PostMapping("/ucenter/register")
    public ResponseResult register(@RequestBody LoginRequest loginRequest);

}

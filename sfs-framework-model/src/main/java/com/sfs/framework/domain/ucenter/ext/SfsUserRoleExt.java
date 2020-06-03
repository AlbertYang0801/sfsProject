package com.sfs.framework.domain.ucenter.ext;

import com.sfs.framework.domain.ucenter.SfsUser;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author yjw
 * @date 2020/5/13 2:44
 */
@Data
@ToString
public class SfsUserRoleExt extends SfsUser {

    List<String> roleIdList;

}

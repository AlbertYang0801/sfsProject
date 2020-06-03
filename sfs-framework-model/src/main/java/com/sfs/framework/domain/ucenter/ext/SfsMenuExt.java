package com.sfs.framework.domain.ucenter.ext;

import com.sfs.framework.domain.course.ext.CategoryNode;
import com.sfs.framework.domain.ucenter.SfsMenu;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by admin on 2018/3/20.
 */
@Data
@ToString
public class SfsMenuExt extends SfsMenu {

    List<CategoryNode> children;
}

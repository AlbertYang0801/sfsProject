package com.sfs.framework.domain.course.ext;

import com.sfs.framework.domain.course.Category;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 分类列表的扩展实体类
 * @author yjw
 */
@Data
@ToString
public class CategoryNode extends Category {

    List<CategoryNode> children;

}

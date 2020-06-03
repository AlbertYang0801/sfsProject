package com.sfs.framework.domain.system;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 数据字典包含的数据值
 * @author  admin on 2018/2/6.
 */
@Data
@ToString
public class SysDictionaryValue {

    /**
     * 属性id
     */
    @Field("sd_id")
    private String sdId;

    /**
     * 属性名称
     */
    @Field("sd_name")
    private String sdName;

    /**
     * 属性状态
     */
    @Field("sd_status")
    private String sdStatus;

}

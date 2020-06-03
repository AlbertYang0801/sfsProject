package com.sfs.managecourse.service.impl;

import com.sfs.managecourse.dao.SysDictionaryRepository;
import com.sfs.managecourse.service.SysDictionaryService;
import com.sfs.framework.domain.system.SysDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 数据字典管理
 * @author yjw
 * @date 2019/11/12
 */
@Service
public class SysDictionaryServiceImpl implements SysDictionaryService {

    @Autowired
    private SysDictionaryRepository sysDictionaryRepository;

    @Override
    public SysDictionary findBydType(String type) {
        return sysDictionaryRepository.findBydType(type);
    }

}

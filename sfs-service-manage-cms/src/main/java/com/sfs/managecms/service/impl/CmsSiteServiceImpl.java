package com.sfs.managecms.service.impl;

import com.sfs.framework.domain.cms.CmsSite;
import com.sfs.framework.model.response.QueryResponseResult;
import com.sfs.framework.model.response.QueryResult;
import com.sfs.managecms.dao.CmsSiteRepository;
import com.sfs.managecms.service.CmsSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sfs.framework.model.response.CommonCode.SUCCESS;

/**
 * Created by yjw
 * on 2019/9/14
 */
@Service
public class CmsSiteServiceImpl implements CmsSiteService {

    @Autowired
    private CmsSiteRepository cmsSiteRepository;

    @Override
    public QueryResponseResult findSiteList() {
        List<CmsSite> all = cmsSiteRepository.findAll();
        QueryResult queryResult = new QueryResult();
        queryResult.setList(all);
        queryResult.setTotal(all.size());
        QueryResponseResult queryResponseResult = new QueryResponseResult(SUCCESS,queryResult);
        return queryResponseResult;
    }

}

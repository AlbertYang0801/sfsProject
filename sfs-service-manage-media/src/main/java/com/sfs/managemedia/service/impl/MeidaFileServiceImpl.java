package com.sfs.managemedia.service.impl;

import com.sfs.framework.domain.media.MediaFile;
import com.sfs.framework.domain.media.request.QueryMediaFileRequest;
import com.sfs.framework.domain.media.response.MediaCode;
import com.sfs.framework.exception.ExceptionCast;
import com.sfs.framework.model.response.CommonCode;
import com.sfs.framework.model.response.QueryResponseResult;
import com.sfs.framework.model.response.QueryResult;
import com.sfs.framework.model.response.ResponseResult;
import com.sfs.managemedia.dao.MediaFileRepository;
import com.sfs.managemedia.service.MediaFileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author yjw
 * @date 2020/4/10 0:02
 */
@Service
public class MeidaFileServiceImpl implements MediaFileService {


    @Autowired
    private MediaFileRepository mediaFileRepository;

    @Override
    public QueryResponseResult findList(int page, int size, QueryMediaFileRequest queryMediaFileRequest) {
        if (queryMediaFileRequest == null) {
            queryMediaFileRequest = new QueryMediaFileRequest();
        }
        if (page <= 0) {
            page = 1;
        }
        page = page - 1;
        if (size <= 0) {
            size = 10;
        }
        //1.设置分页参数
        Pageable pageable = PageRequest.of(page, size);
        //2.设置查询参数对象
        MediaFile mediaFile = new MediaFile();
        //设置文件原始名称为查询条件
        if (StringUtils.isNotEmpty(queryMediaFileRequest.getFileOriginalName())) {
            mediaFile.setFileOriginalName(queryMediaFileRequest.getFileOriginalName());
        }
        //设置处理状态为查询条件
        if (StringUtils.isNotEmpty(queryMediaFileRequest.getProcessStatus())) {
            mediaFile.setProcessStatus(queryMediaFileRequest.getProcessStatus());
        }
        //设置标签为查询条件
        if (StringUtils.isNotEmpty(queryMediaFileRequest.getTag())) {
            mediaFile.setTag(queryMediaFileRequest.getTag());
        }
        //3.创建条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                //设置文件原始名称模糊匹配（contains）
                .withMatcher("fileOriginalName", ExampleMatcher.GenericPropertyMatchers.contains())
                //设置标签模糊匹配
                .withMatcher("tag", ExampleMatcher.GenericPropertyMatchers.contains());
        //设置处理状态精确匹配（exact）,不设置则默认精确匹配
//                .withMatcher("processStatus",ExampleMatcher.GenericPropertyMatchers.exact());

        //4.自定义查询对象，传入查询参数，和条件匹配器
        Example<MediaFile> example = Example.of(mediaFile, exampleMatcher);
        //5.根据查询对象和分页参数进行查询
        Page<MediaFile> mediaFiles = mediaFileRepository.findAll(example, pageable);
        QueryResult queryResult = new QueryResult();
        //从查询结果集取出数据总数
        queryResult.setTotal(mediaFiles.getTotalElements());
        //从查询结果集取出数据集合
        queryResult.setList(mediaFiles.getContent());
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    @Override
    public ResponseResult deleteMedia(String fileId) {
        if(StringUtils.isEmpty(fileId)){
            //提示删除文件为空
            ExceptionCast.cast(MediaCode.DELETE_FILE_IS_FAIL);
        }
        Optional<MediaFile> optional = mediaFileRepository.findById(fileId);
        if(!optional.isPresent()){
            //提示查询失败
            ExceptionCast.cast(MediaCode.SELECT_VIDEO_IS_FAIL);
        }
        mediaFileRepository.deleteById(fileId);
        return new ResponseResult(CommonCode.SUCCESS);
    }

}

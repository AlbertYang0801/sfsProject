package com.sfs.managemedia.dao;

import com.sfs.framework.domain.media.MediaFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yjw
 * @date 2020/4/9 23:47
 */
public interface MediaFileRepository extends MongoRepository<MediaFile,String> {


}

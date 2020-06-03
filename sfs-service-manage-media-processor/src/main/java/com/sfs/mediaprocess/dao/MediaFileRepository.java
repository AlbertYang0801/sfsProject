package com.sfs.mediaprocess.dao;

import com.sfs.framework.domain.media.MediaFile;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * @author yjw
 */
public interface MediaFileRepository extends MongoRepository<MediaFile,String> {

}

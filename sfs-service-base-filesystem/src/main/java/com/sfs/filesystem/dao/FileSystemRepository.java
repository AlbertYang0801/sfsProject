package com.sfs.filesystem.dao;

import com.sfs.framework.domain.filesystem.FileSystem;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 文件系统
 * @author yjw
 * @date 2019/11/18 14:42
 */
public interface FileSystemRepository extends MongoRepository<FileSystem,String> {

}

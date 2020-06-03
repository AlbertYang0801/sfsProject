package com.sfs.filesystem.service.impl;

import com.alibaba.fastjson.JSON;
import com.sfs.filesystem.dao.FileSystemRepository;
import com.sfs.filesystem.service.FileSystemService;
import com.sfs.framework.domain.filesystem.FileSystem;
import com.sfs.framework.domain.filesystem.response.FileSystemCode;
import com.sfs.framework.domain.filesystem.response.UploadFileResult;
import com.sfs.framework.exception.ExceptionCast;
import com.sfs.framework.model.response.CommonCode;
import org.apache.commons.lang3.StringUtils;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 文件系统管理
 *
 * @author yjw
 * @date 2019/11/18 14:48
 */
@Service
public class FileSystemServiceImpl implements FileSystemService {

    @Value("${sfs.fastdfs.tracker_servers}")
    String tracker_servers;

    @Value("${sfs.fastdfs.connect_timeout_in_seconds}")
    int connect_timeout_in_seconds;

    @Value("${sfs.fastdfs.network_timeout_in_seconds}")
    int network_timeout_in_seconds;

    @Value("${sfs.fastdfs.charset}")
    String charset;

    @Autowired
    private FileSystemRepository fileSystemRepository;

    /**
     * 文件上传到FastDFS
     * 保存上传后的文件信息到数据库
     *
     * @param multipartFile
     * @param fileTag
     * @param businessKey
     * @param metadata
     * @return
     */
    @Override
    public UploadFileResult upload(MultipartFile multipartFile, String fileTag, String businessKey, String metadata) {
        if (multipartFile == null) {
            //返回异常，上传文件为空
            ExceptionCast.cast(FileSystemCode.FS_UPLOADFILE_FILEISNULL);
        }
        //上传文件到FastDFS
        String fileId = fdfs_upload(multipartFile);
        //创建文件信息实体类
        FileSystem fileSystem = new FileSystem();
        fileSystem.setFileId(fileId);
        fileSystem.setFilePath(fileId);
        fileSystem.setBusinesskey(businessKey);
        fileSystem.setFiletag(fileTag);
        fileSystem.setFileName(multipartFile.getOriginalFilename());
        fileSystem.setFileSize(multipartFile.getSize());
        fileSystem.setFileType(multipartFile.getContentType());
        //设置文件元数据
        if (StringUtils.isNotEmpty(metadata)) {
            //将json字符串转换为Map集合
            Map map = JSON.parseObject(metadata, Map.class);
            fileSystem.setMetadata(map);
        }
        FileSystem save = fileSystemRepository.save(fileSystem);
        if(save!=null){
            return new UploadFileResult(CommonCode.SUCCESS, fileSystem);
        }else{
            return new UploadFileResult(CommonCode.FAIL,null);
        }
    }

    /**
     * 加载FastDFS的配置
     */
    private void initFastDFS() {
        try {
            //根据tracker服务器地址加载FastDFS
            ClientGlobal.initByTrackers(tracker_servers);
            //设置http连接超时时间
            ClientGlobal.setG_connect_timeout(connect_timeout_in_seconds);
            //设置tracker和storage之间的网络通信超时时间
            ClientGlobal.setG_network_timeout(network_timeout_in_seconds);
            //设置服务器编码
            ClientGlobal.setG_charset(charset);
        } catch (Exception e) {
            e.printStackTrace();
            //返回异常，初始化文件系统错误
            ExceptionCast.cast(FileSystemCode.FS_INITFDFSERROR);
        }
    }

    /**
     * 上传文件到FastDFS，返回文件Id
     *
     * @param file
     * @return
     */
    private String fdfs_upload(MultipartFile file) {
        try {
            //初始化FastDFS配置
            initFastDFS();
            //创建tracker客户端
            TrackerClient trackerClient = new TrackerClient();
            //获取tracker调度服务器连接
            TrackerServer trackerServer = trackerClient.getConnection();
            //获取storage存储服务器连接
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            //创建storage服务器客户端
            StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
            //获取文件字节流
            byte[] bytes = file.getBytes();
            //获取文件原始名
            String originalFilename = file.getOriginalFilename();
            //获取文件扩展名，如:jpg,png
            String extname = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            //上传文件
            String fileId = storageClient1.upload_file1(bytes, extname, null);
            return fileId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

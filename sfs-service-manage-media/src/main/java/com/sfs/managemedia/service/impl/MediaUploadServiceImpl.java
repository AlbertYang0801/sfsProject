package com.sfs.managemedia.service.impl;

import com.alibaba.fastjson.JSON;
import com.sfs.framework.domain.media.MediaFile;
import com.sfs.framework.domain.media.response.CheckChunkResult;
import com.sfs.framework.domain.media.response.MediaCode;
import com.sfs.framework.exception.ExceptionCast;
import com.sfs.framework.model.response.CommonCode;
import com.sfs.framework.model.response.ResponseResult;
import com.sfs.managemedia.config.RabbitmqConfig;
import com.sfs.managemedia.dao.MediaFileRepository;
import com.sfs.managemedia.service.MediaUploadService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

/**
 * 媒资管理
 * * @author yjw
 *
 * @date 2020/4/2 22:28
 */
@Service
public class MediaUploadServiceImpl implements MediaUploadService {

    private static final Logger logger = LoggerFactory.getLogger(MediaUploadServiceImpl.class);

    /**
     * 自定义文件上传路径
     */
    @Value("${sfs-service-manage-media.upload-location}")
    String uploadPath;

    @Autowired
    private MediaFileRepository mediaFileRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 视频处理路由
     */
    @Value("${sfs-service-manage-media.mq.routingkey-media-video}")
    public String routingkey_media_video;

    @Override
    public ResponseResult register(String fileMd5, String fileName, String fileSize, String mimetype, String fileExt) {
        //1.获取文件全路径，判断文件是否存在
        String filePath = getFilePath(fileMd5, fileExt);
        File file = new File(filePath);
        Optional<MediaFile> optionalMediaFile = mediaFileRepository.findById(fileMd5);
        //判断文件是否存在在指定路径和文件信息是否存在于数据库
        if (file.exists() && optionalMediaFile.isPresent()) {
            //提示上传的文件已存在
            ExceptionCast.cast(MediaCode.UPLOAD_FILE_REGISTER_EXIST);
        }
        //2.根据文件信息创建文件上传目录
        boolean fileFold = createFileFold(fileMd5);
        if (!fileFold) {
            //提示文件注册失败
            ExceptionCast.cast(MediaCode.UPLOAD_FILE_REGISTER_FAIL);
        }
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    public CheckChunkResult checkChunk(String fileMd5, Integer chunk, Integer chunkSize) {
        //获取块文件存放目录
        String chunkFileFolderPath = getChunkFileFolderPath(fileMd5);
        //根据块文件目录创建对象（当前块文件所在路径 = 块文件路径+块文件下标）
        File file = new File(chunkFileFolderPath + chunk);
        //判断当前分块文件是否已经存在
        if (file.exists()) {
            //块文件已存在
            return new CheckChunkResult(CommonCode.SUCCESS, true);
        } else {
            //块文件不存在
            return new CheckChunkResult(CommonCode.SUCCESS, false);
        }
    }

    @Override
    public ResponseResult uploadChunk(MultipartFile file, Integer chunk, String fileMd5) {
        if (file == null) {
            //提示上传文件内容为空
            ExceptionCast.cast(MediaCode.UPLOAD_CHUNK_FILE_IS_NULL);
        }
        //创建块文件目录
        boolean chunkFilePath = createChunkFilePath(fileMd5);
        if (!chunkFilePath) {
            //创建块文件目录失败
            ExceptionCast.cast(MediaCode.CREATE_CHUNK_FILE_FAIL);
        }
        //块文件路径 = 块文件目录+块文件下标（没有扩展名）
        File chunkFile = new File(getChunkFileFolderPath(fileMd5) + chunk);
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            //获取文件输入流
            inputStream = file.getInputStream();
            //根据块文件路径创建文件输出流
            outputStream = new FileOutputStream(chunkFile);
            //将文件从输入流复制到文件输出流，输出到指定目录
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            //提示上传块文件失败
            ExceptionCast.cast(MediaCode.UPLOAD_CHUNK_FILE_FAIL);
        } finally {
            try {
                //关闭输入流
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                //关闭输出流
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    public ResponseResult mergeChunks(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) {
        //获取块文件的路径
        String chunkFileFolderPath = getChunkFileFolderPath(fileMd5);
        File chunkFileFolder = new File(chunkFileFolderPath);
        //如果块文件目录不存在，则创建
        if (!chunkFileFolder.exists()) {
            chunkFileFolder.mkdirs();
        }
        //获得文件所在全路径
        String filePath = getFilePath(fileMd5, fileExt);
        //创建合并文件File对象
        File mergeFile = new File(filePath);
        //合并后的文件若存在先删除
        if (mergeFile.exists()) {
            //删除之前的合并文件
            mergeFile.delete();
        }
        boolean newFile = false;
        try {
            //根据合并后的文件路径创建新的合并文件（文件全路径 = D:/sfs/video/0/1/01..../01....mp4）
            newFile = mergeFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!newFile) {
            //提示合并文件创建失败
            ExceptionCast.cast(MediaCode.MERGE_FILE_CERETE_FAIL);
        }
        //获取块文件目录下的块文件集合（升序排列）
        List<File> chunkFileList = getChunkFileList(chunkFileFolder);
        //合并文件
        mergeFile = mergeFile(chunkFileList, mergeFile);
        if (mergeFile == null) {
            //提示合并文件失败，合并过程出错
            ExceptionCast.cast(MediaCode.MERGE_FILE_IS_FAIL);
        }
        //合并文件成功之后，删除分块文件
        boolean deleteChunks = deleteChunks(fileMd5);
        if (!deleteChunks) {
            //提示删除分块文件出错
            ExceptionCast.cast(MediaCode.DELETE_CHUNK_IS_FAIL);
        }
        //根据合并文件获取Md5值和传入的文件Md5值进行比较校验文件正确性
        Boolean checkMd5 = checkFileMd5(fileMd5, mergeFile);
        if (!checkMd5) {
            //提示合并文件校验失败
            ExceptionCast.cast(MediaCode.MERGE_FILE_CHECKFAIL);
        }
        //保存文件信息到数据库
        MediaFile mediaFile = new MediaFile();
        mediaFile.setFileId(fileMd5);
        //设置文件名称 = 文件Md5值+文件扩展名
        mediaFile.setFileName(fileMd5 + "." + fileExt);
        //设置文件原始名称
        mediaFile.setFileOriginalName(fileName);
        //设置文件路径为相对路径
        mediaFile.setFilePath(getFileRelativePath(fileMd5));
        mediaFile.setFileSize(fileSize);
        mediaFile.setUploadTime(new Date());
        //设置文件媒体类型
        mediaFile.setMimeType(mimetype);
        //设置文件类型（文件扩展名）
            mediaFile.setFileType(fileExt);
    //设置状态为上传成功
        mediaFile.setFileStatus("301002");
        mediaFileRepository.save(mediaFile);
    //向MQ发送消息(合并文件完成后，发送消息通知消费者处理视频文件)
    sendProcessVideoMsg(mediaFile.getFileId());
        return new ResponseResult(CommonCode.SUCCESS);
}


    //******************************************************************************************************************


    /**
     * 根据文件唯一标识和文件扩展名获得文件完整路径
     * 一级目录：md5的第一个字符
     * 二级目录：md5的第二个字符
     * 三级目录：md5
     * 文件名：md5+文件扩展名
     *
     * @param fileMd5 文件md5值
     * @param fileExt 文件扩展名
     * @return
     */
    private String getFilePath(String fileMd5, String fileExt) {
        //文件全路径 = D:/sfs/video/0/1/01..../01....mp4
        String filePath = uploadPath + fileMd5.substring(0, 1) + "/" + fileMd5.substring(1, 2) + "/" + fileMd5 + "/" + fileMd5 + "." + fileExt;
        return filePath;
    }

    /**
     * 得到文件所在的目录
     *
     * @param fileMd5
     * @return
     */
    private String getFileFolderPath(String fileMd5) {
        String fileFoladerPath = uploadPath + fileMd5.substring(0, 1) + "/" + fileMd5.substring(1, 2) + "/" + fileMd5 + "/";
        return fileFoladerPath;
    }

    /**
     * 得到文件相对路径
     *
     * @param fileMd5
     * @return
     */
    private String getFileRelativePath(String fileMd5) {
        String fileRelativePath = fileMd5.substring(0, 1) + "/" + fileMd5.substring(1, 2) + "/" + fileMd5 + "/";
        return fileRelativePath;
    }

    /**
     * 根据文件绝对路径创建文件夹
     *
     * @param fileMd5
     * @return
     */
    private boolean createFileFold(String fileMd5) {
        //获得文件上传目录，绝对路径
        String fileFolderPath = getFileFolderPath(fileMd5);
        File fileFolder = new File(fileFolderPath);
        if (!fileFolder.exists()) {
            boolean mkdirs = fileFolder.mkdirs();
            return mkdirs;
        }
        return true;
    }

    /**
     * 获取块文件目录
     *
     * @param fileMd5
     * @return
     */
    private String getChunkFileFolderPath(String fileMd5) {
        String fileChunkFolderPath = getFileFolderPath(fileMd5) + "chunks" + "/";
        return fileChunkFolderPath;
    }

    /**
     * 创建块文件目录
     *
     * @param fileMd5
     * @return
     */
    private boolean createChunkFilePath(String fileMd5) {
        //获取块文件目录
        String chunkFilePath = getChunkFileFolderPath(fileMd5);
        File file = new File(chunkFilePath);
        //如果块文件目录不存在
        if (!file.exists()) {
            //创建块文件目录
            boolean mkdirs = file.mkdirs();
            return mkdirs;
        }
        return true;
    }

    /**
     * 根据块文件目录获取快文件集合（根据名称排序）
     *
     * @param chunkFileFolder 块文件目录File对象
     * @return
     */
    private List<File> getChunkFileList(File chunkFileFolder) {
        //根据块文件目录对象获取目录下所有文件
        File[] chunkFiles = chunkFileFolder.listFiles();
        //将文件数组转换为集合对象
        List<File> list = Arrays.asList(chunkFiles);
        //创建比较器根据名称对集合进行比较，升序排列
        Collections.sort(list, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                //根据名称进行比较
                if ((Integer.parseInt(o1.getName()) > Integer.parseInt(o2.getName()))) {
                    return 1;
                }
                return -1;
            }
        });
        return list;
    }

    /**
     * 合并文件
     *
     * @param chunkList 升序排列后的块文件集合
     * @param mergeFile 合并文件的File对象
     * @return
     */
    private File mergeFile(List<File> chunkList, File mergeFile) {
        try {
            //根据合并文件路径创建操作文件对象
            RandomAccessFile rand_write = new RandomAccessFile(mergeFile, "rw");
            //设置指针指向文件首位
            rand_write.seek(0);
            //创建字符缓冲区
            byte[] b = new byte[1024];
            //便利块文件
            for (File file : chunkList) {
                //读取块文件数据
                RandomAccessFile rand_read = new RandomAccessFile(file, "r");
                int len = -1;
                //将块文件数据写入缓冲区，写入成功后，len = 1024
                while ((len = rand_read.read(b)) != -1) {
                    //从字符缓冲区的0子节开始到len长度将字节写入合并文件对象
                    rand_write.write(b, 0, len);
                }
                //关闭读取块文件对象
                rand_read.close();
            }
            //关闭合并文件写入对象
            rand_write.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return mergeFile;
    }

    /**
     * 合并文件成功后删除分块文件
     *
     * @param fileMd5
     * @return
     */
    private boolean deleteChunks(String fileMd5) {
        //获取块文件目录
        String chunkFileFolderPath = getChunkFileFolderPath(fileMd5);
        //分块文件夹对象
        File chunksFile = new File(chunkFileFolderPath);
        //分块文件数组
        File[] chunks = chunksFile.listFiles();
        List<File> list = Arrays.asList(chunks);
        for (File file : list) {
            //循环删除每个分块文件
            file.delete();
        }
        //分块文件全部删除后，删除chunks文件夹
        boolean delete = chunksFile.delete();
        if (delete) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 校验文件中的Md5值和传入的Md5值是否一致
     *
     * @param fileMd5
     * @param mergeFile
     * @return
     */
    private Boolean checkFileMd5(String fileMd5, File mergeFile) {
        if (fileMd5 == null || mergeFile == null) {
            return false;
        }
        InputStream fileInputStream = null;
        try {
            //创建文件输入流
            fileInputStream = new FileInputStream(mergeFile);
            //从文件输入流中获取文件的Md5值
            String md5 = DigestUtils.md5Hex(fileInputStream);
            //若文件Md5值和传入的Md5值一致，返回true
            if (md5.equalsIgnoreCase(fileMd5)) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 生产者向消费者发送消息处理视频
     * 消息内容为视频id
     *
     * @param mediaId
     */
    private void sendProcessVideoMsg(String mediaId) {
        Optional<MediaFile> optional = mediaFileRepository.findById(mediaId);
        if (!optional.isPresent()) {
            //提示查询视频信息失败
            ExceptionCast.cast(MediaCode.SELECT_VIDEO_IS_FAIL);
        }
        Map map = new HashMap();
        map.put("mediaId", mediaId);
        String msg = JSON.toJSONString(map);
        try {
            logger.info("开始发送视频处理的消息...");
            //开始发送消息,指定交换机、路由key、和消息内容
            this.rabbitTemplate.convertAndSend(RabbitmqConfig.EX_MEDIA_PROCESSTASK, routingkey_media_video, msg);
            logger.info("发送视频处理消息结束....");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件夹及文件夹里内容
     *
     * @param args
     */
    public static void main(String[] args) {
        File file = new File("D:\\sfs\\video\\c\\5\\c5c75d70f382e6016d2f506d134eee11\\chunks");
        File[] files = file.listFiles();
        List<File> list = Arrays.asList(files);
        for (File file1 : list) {
            file1.delete();
        }
        file.delete();
    }


}


/**
 * 合并块文件步骤
 * 1.获取块文件目录下的所有文件
 * 2.将所有块文件根据名称进行排序（块文件只有下标，排序后为0，1，2，3...)
 * 3.根据合并文件目录创建写对象
 * 4.遍历块文件
 * 5.根据块文件创建读对象，获的块文件放入字符缓冲区，并设置块文件长度len
 * 6.将字符缓冲区的块文件从0开始到len写入到合并文件中
 * 7.循环结束，合并块文件成功
 */
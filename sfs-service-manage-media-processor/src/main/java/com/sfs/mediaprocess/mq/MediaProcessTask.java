package com.sfs.mediaprocess.mq;

import com.alibaba.fastjson.JSON;
import com.sfs.framework.domain.media.MediaFile;
import com.sfs.framework.domain.media.MediaFileProcess_m3u8;
import com.sfs.framework.domain.media.response.MediaCode;
import com.sfs.framework.exception.ExceptionCast;
import com.sfs.framework.utils.HlsVideoUtil;
import com.sfs.framework.utils.Mp4VideoUtil;
import com.sfs.mediaprocess.dao.MediaFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 监听视频处理指定队列
 * 处理视频，将视频由avi格式转为mp4格式，再转为m3u8格式
 *
 * @author yjw
 * @date 2020/4/7 22:14
 */
@Component
public class MediaProcessTask {

    private static final Logger logger = LoggerFactory.getLogger(MediaProcessTask.class);

    /**
     * ffmpeg程序路径
     */
    @Value("${sfs-service-manage-media.ffmpeg-path}")
    String ffmpeg_path;

    /**
     * 上传视频存放目录
     */
    @Value("${sfs-service-manage-media.video-location}")
    String videoPath;

    @Autowired
    private MediaFileRepository mediaFileRepository;

    /**
     * 监听视频处理队列
     * 只支持处理avi格式视频，将avi格式视频转化为mp4格式，再将mp4格式转换为m3u8格式。
     * 指定容器工厂
     */
    @RabbitListener(queues = "${sfs-service-manage-media.mq.queue-media-video-processor}",
            containerFactory = "customContainerFactory")
    public void receiveMediaProcessTask(String msg) {
        //接收消息内容
        Map map = JSON.parseObject(msg, Map.class);
        logger.info("消费者接受的消息内容为：" + map);
        //从消息中获取处理的视频id
        String mediaId = (String) map.get("mediaId");
        //获取视频信息
        Optional<MediaFile> optional = mediaFileRepository.findById(mediaId);
        if (!optional.isPresent()) {
            //提示查询视频信息失败
            ExceptionCast.cast(MediaCode.SELECT_VIDEO_IS_FAIL);
        }
        MediaFile mediaFile = optional.get();
        //判断视频格式，除avi格式为需要处理外，其他格式类型为不需要处理
        String fileType = mediaFile.getFileType();
        if (fileType == null || !(fileType.equals("avi") || fileType.equals("mp4"))) {
            //不是avi格式的视频设置处理状态为无需处理
            mediaFile.setProcessStatus("303004");
            mediaFileRepository.save(mediaFile);
            return;
        } else {
            //avi格式视频设置处理状态为处理中
            mediaFile.setProcessStatus("303001");
            mediaFileRepository.save(mediaFile);
        }
        //m3u8分片内容集合
        List<String> tsList = new ArrayList<>();
        //******************************************avi格式转换为mp4格式********************************************
        if (Objects.equals(fileType, "avi")) {
            //avi格式转为mp4格式
            Boolean aviToMp4 = aviToMp4(mediaFile);
            if (aviToMp4) {
                //mp4格式生成m3u8格式文件
                tsList = mp4ToM3u8(mediaFile);
            } else {
                //提示avi格式转为mp4格式失败
                ExceptionCast.cast(MediaCode.AVI_TO_MP4_IS_FAIL);
            }
        }
        //******************************************mp4格式转换为m3u8格式********************************************
        if (Objects.equals(fileType, "mp4")) {
            //mp4格式转换为m3u8格式文件
            tsList = mp4ToM3u8(mediaFile);
        }
        //设置处理状态为处理成功
        mediaFile.setProcessStatus("303002");
        //设置m3u8分片列表
        MediaFileProcess_m3u8 mediaFileProcess_m3u8 = new MediaFileProcess_m3u8();
        mediaFileProcess_m3u8.setTslist(tsList);
        mediaFile.setMediaFileProcess_m3u8(mediaFileProcess_m3u8);
        //设置m3u8文件的相对路径
        mediaFile.setFileUrl(mediaFile.getFilePath() + "hls/" + mediaFile.getFileId() + ".m3u8");
        mediaFile.setUploadTime(new Date());
        //更新视频信息
        mediaFileRepository.save(mediaFile);
    }

    /**
     * avi格式视频转换为mp4格式
     *
     * @param mediaFile
     * @return
     */
    private Boolean aviToMp4(MediaFile mediaFile) {
        //源视频路径
        String video_path = videoPath + mediaFile.getFilePath() + mediaFile.getFileName();
        //生成的mp4视频名称：文件Md5值即Id.mp4
        String mp4_name = mediaFile.getFileId() + ".mp4";
        //生成的mp4视频存放路径
        String mp4folder_path = videoPath + mediaFile.getFilePath();
        //将avi格式视频转换为mp4格式
        Mp4VideoUtil mp4VideoUtil = new Mp4VideoUtil(ffmpeg_path, video_path, mp4_name, mp4folder_path);
        //执行转换，判断转换是否成功，根据生成的视频时间是否和原来的一致判断
        String result = mp4VideoUtil.generateMp4();
        //avi转换mp4不成功
        if (result == null || !result.equals("success")) {
            //设置处理状态为处理失败
            mediaFile.setProcessStatus("303003");
            MediaFileProcess_m3u8 mediaFileProcess_m3u8 = new MediaFileProcess_m3u8();
            //操作失败写入错误日志
            mediaFileProcess_m3u8.setErrormsg(result);
            mediaFile.setMediaFileProcess_m3u8(mediaFileProcess_m3u8);
            //更新视频信息
            mediaFileRepository.save(mediaFile);
            return false;
        } else {
            return true;
        }
    }

    /**
     * mp4格式转换为m3u8格式文件
     *
     * @param mediaFile
     * @return
     */
    private List<String> mp4ToM3u8(MediaFile mediaFile) {
        //mp4视频源路径
        String video_path = videoPath + mediaFile.getFilePath() + mediaFile.getFileId() + ".mp4";
        //生成的m3u8文件名
        String m3u8_name = mediaFile.getFileId() + ".m3u8";
        //生成的m3u8文件存放路径
        String m3u8folder_path = videoPath + mediaFile.getFilePath() + "hls/";
        //将mp4格式视频生成m3u8格式文件
        HlsVideoUtil hlsVideoUtil = new HlsVideoUtil(ffmpeg_path, video_path, m3u8_name, m3u8folder_path);
        //执行转换，判断转换是否成功，（1）根据生成的视频时间是否和原来一致（2）判断分片内容是否为空
        String result = hlsVideoUtil.generateM3u8();
        if (result == null || !result.equals("success")) {
            //设置处理状态为处理失败
            mediaFile.setProcessStatus("303003");
            MediaFileProcess_m3u8 mediaFileProcess_m3u8 = new MediaFileProcess_m3u8();
            //操作失败写入错误日志
            mediaFileProcess_m3u8.setErrormsg(result);
            mediaFile.setMediaFileProcess_m3u8(mediaFileProcess_m3u8);
            //更新视频信息
            mediaFileRepository.save(mediaFile);
            return null;
        }
        //获取m3u8分片列表
        List<String> tsList = hlsVideoUtil.get_ts_list();
        return tsList;
    }

}


/**
 * 视频处理流程：
 * 1.从mq接收视频处理消息，获取处理视频的mediaId
 * 2.跟据mediaId查询视频信息，判断视频格式是否为avi，若不是avi格式，则设置处理状态为无需处理。直接返回。
 * 3.若为avi格式视频，则设置处理状态为处理中
 * 4.调用工具类将avi视频转换为mp4格式视频
 * 5.mp4视频存放路径为：videoPath + filePath
 * videoPath:配置文件设置根目录；
 * filePath:视频文件存放相对路径,格式为 文件Md5第一个字符/文件Md5第二个字符/文件Md5/
 * 生成的mp4视频名称为：文件Md5.mp4
 * 6.判断mp4文件是否转换成功：根据生成的mp4文件时长和avi格式文件时长比较，若一致，则转换成功，返回字符串success。
 * 7.转换成mp4文件后，调用工具类将mp4文件转换为m3u8格式文件
 * 8.m3u8格式文件存放路径为：videoPath+filePath+"hls/"
 * 生成的m3u8视频名称为：文件Md5.m3u8
 * 9.判断m3u8格式文件是否转换成功:
 * 根据生成文件的时长和mp4文件时长进行比较，若一致，则转换成功，返回字符串success。
 * 判断生成的分片长度是否完整
 * 10.m3u8格式文件生成成功后，将分片信息和m3u8文件相对路径存入数据库。
 */
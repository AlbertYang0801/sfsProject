package com.sfs.mediaprocess;

import com.sfs.framework.utils.HlsVideoUtil;
import com.sfs.framework.utils.Mp4VideoUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-07-12 9:11
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestProcessBuilder {




    @Test
    public void testProcessBuilder() throws IOException {
        //创建ProcessBuilder对象
        ProcessBuilder processBuilder = new ProcessBuilder();
        //设置执行的第三方程序(命令)
//        processBuilder.command("ping","127.0.0.1");
        processBuilder.command("ipconfig");
        //将标准输入流和错误输入流进行合并，通过标准输入流读取信息就能拿到第三方程序输出
        processBuilder.redirectErrorStream(true);
        //启动进程
        Process process = processBuilder.start();
        //读取输入流
        InputStream inputStream = process.getInputStream();
        //将字节流转换为字符流
        InputStreamReader reader = new InputStreamReader(inputStream, "gbk");
        //字符缓冲区
        char[] chars = new char[1024];
        int len = -1;
        //将字符流存入字符缓冲区
        while ((len = reader.read(chars)) != -1) {
            //从字符缓冲区中获取字符
            String s = new String(chars, 0, len);
            System.out.println(s);
        }
        reader.close();
        inputStream.close();
    }

    /**
     * 测试使用ffmpeg将avi视频转换为mp4格式
     */
    @Test
    public void testFfmpeg() {
        List<String> command = new ArrayList<>();
        //添加ffmpag程序路径
        command.add("D:\\sfs\\FFmpeg\\ffmpeg-20180227-fa0c9d6-win64-static\\bin\\ffmpeg.exe");
        command.add("-i");
        //添加转换后视频输出路径
        command.add("D:\\sfs\\FFmpeg\\ffmpeg-test\\1.avi");
        //覆盖输出文件
        command.add("-y");
        command.add("-c:v");
        command.add("libx264");
        command.add("-s");
        command.add("1280x720");
        command.add("-pix_fmt");
        command.add("yuv420p");
        command.add("-b:a");
        command.add("63k");
        command.add("-b:v");
        command.add("753k");
        command.add("-r");
        command.add("18");
        command.add("D:\\sfs\\FFmpeg\\ffmpeg-test\\1.mp4");
        ProcessBuilder processBuilder = new ProcessBuilder();
        //设置执行的第三方程序
        processBuilder.command(command);
        processBuilder.redirectErrorStream(true);
        try {
            //启动执行第三方程序的进程
            Process process = processBuilder.start();
            //从进程中获取输入流
            InputStream inputStream = process.getInputStream();
            //将输入字节流转为字符流
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "gbk");
            char[] chars = new char[1024];
            int len = -1;
            StringBuffer stringBuffer = new StringBuffer();
            //读取字符流
            while ((len = inputStreamReader.read(chars)) != -1) {
                String s = new String(chars, 0, len);
                stringBuffer.append(s);
                System.out.println(s);
            }
            inputStreamReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用工具类将avi格式视频转换为mp4格式视频
     */
    @Test
    public void testMp4Util() {
        //ffmpeg的程序路径
        String ffmpeg_path = "D:\\sfs\\FFmpeg\\ffmpeg-20180227-fa0c9d6-win64-static\\bin\\ffmpeg.exe";
        //源视频路径
        String video_path = "D:\\sfs\\FFmpeg\\ffmpeg-test\\1.avi";
        //生成视频名称
        String mp4_name = "1.mp4";
        //生成视频路径
        String mp4folder_path = "D:\\sfs\\FFmpeg\\ffmpeg-test\\";

        Mp4VideoUtil mp4VideoUtil = new Mp4VideoUtil(ffmpeg_path, video_path, mp4_name, mp4folder_path);
        String s = mp4VideoUtil.generateMp4();
        System.out.println(s);
    }

    /**
     * mp4视频转换为m3u8格式
     */
    @Test
    public void testM3u8Util() {
        //ffmpeg的程序路径
        String ffmpeg_path = "D:\\sfs\\FFmpeg\\ffmpeg-20180227-fa0c9d6-win64-static\\bin\\ffmpeg.exe";
        //mp4格式源视频路径
        String video_path = "D:\\sfs\\FFmpeg\\ffmpeg-test\\1.mp4";
        //生成的m3u8文件名
        String m3u8_name = "1.m3u8";
        //生成的m3u9文件存放路径
        String m3u8floder_path = "D:\\sfs\\FFmpeg\\ffmpeg-test\\hls\\";
        HlsVideoUtil hlsVideoUtil = new HlsVideoUtil(ffmpeg_path,video_path,m3u8_name,m3u8floder_path);
        String s = hlsVideoUtil.generateM3u8();
        System.out.println(s);
    }


}

package com.sfs.managemedia.dao;

import com.sfs.framework.domain.media.MediaFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

/**
 * 测试上传视频文件
 *
 * @author yjw
 * @date 2019/12/31 15:44
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMedia {

    @Autowired
    MediaFileRepository mediaFileRepository;

    /**
     * 测试文件分块
     */
    @Test
    public void testChunk() throws IOException {
        //读取源文件
        File sourceFile = new File("D:\\sfs\\FFmpeg\\ffmpeg-test\\lucene.avi");
        //块文件目录
        String chunkFile = "D:\\sfs\\ffmpeg\\chunk-test\\";

        //创建根目录
        File file = new File(chunkFile);
        if (!file.exists()) {
            file.mkdirs();
        }

        //定义块文件大小,设置为1MB
        long chunkFileSize = 1 * 1024 * 1024;
        //计算块数(源文件大小/块文件大小)
        long chunkNum = (long) Math.ceil(sourceFile.length() * 1.0 / chunkFileSize);
        if (chunkNum <= 0) {
            //设置默认块数为1
            chunkNum = 1;
        }

        //创建字符缓冲区
        byte[] b = new byte[1024];
        //使用RandomAccessFile访问文件
        RandomAccessFile rad_read = new RandomAccessFile(sourceFile, "r");

        //执行分块
        for (int i = 0; i < chunkNum; i++) {
            //在根目录下创建分块文件
            File file1 = new File(chunkFile + i);
            boolean newFile = file1.createNewFile();
            if (newFile) {
                //使用RandomAccessFile向分块文件中写入数据
                RandomAccessFile rad_write = new RandomAccessFile(file1, "rw");
                int len = -1;
                //从读取文件对象获取数据写入到字节数组
                while ((len = rad_read.read(b)) != -1) {
                    //将数据写入块文件（数据源字节数组，数据起始，写入数据的长度）
                    rad_write.write(b, 0, len);
                    if (file1.length() > chunkFileSize) {
                        break;
                    }
                }
                rad_write.close();
            }
        }
        rad_read.close();
    }

    /**
     * 测试合并分块
     */
    @Test
    public void testMerge() throws IOException {
        //块文件目录
        File chunkFolder = new File("D:/sfs/FFmpeg/chunk-test/");
        //合并文件
        File mergeFile = new File("D:/sfs/FFmpeg/ffmpeg-test/lucene.mp4");
        if (mergeFile.exists()){
            mergeFile.delete();
        }
        //创建新的合并文件
        mergeFile.createNewFile();
        //根据合并文件创建写文件对象
        RandomAccessFile rand_write = new RandomAccessFile(mergeFile,"rw");
        //设置指针指向文件首位
        rand_write.seek(0);
        //创建字节缓冲区
        byte[] b = new byte[1024];
        //分块文件列表
        File[] listFiles = chunkFolder.listFiles();
        //转换为集合，进行排序
        List<File> files = Arrays.asList(listFiles);
        //使用比较器将分块文件从小到大排列
        Collections.sort(files, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if(Integer.parseInt(o1.getName())<Integer.parseInt(o2.getName())){
                    return -1;
                }
                return 1;
            }
        });
        //合并分块文件
        for (File chunkFile : files) {
            //根据分块文件创建读对象
            RandomAccessFile rand_read = new RandomAccessFile(chunkFile,"rw");
            int len = -1;
            //将分块文件数据写入到字符缓冲区
            while((len = rand_read.read(b))!=-1){
                //将数据从字符缓冲区写入合并文件
                rand_write.write(b,0,len);
            }
            rand_read.close();
        }
        rand_write.close();
    }

    /**
     * 插入数据
     */
    @Test
    public void testInsertMedia() {
        MediaFile mediaFile = new MediaFile();
        mediaFile.setFileId("1");
        mediaFile.setFileName("测试");
        mediaFile.setFileOriginalName("测试原始名称");
        mediaFile.setFilePath("文件路径");
        mediaFile.setFileUrl("url");
        mediaFile.setFileType("文档");
        mediaFile.setMimeType("类型文件");
        mediaFile.setFileSize(2000L);
        mediaFile.setFileStatus("完成");
        mediaFile.setUploadTime(new Date());
        mediaFile.setProcessStatus("成功");
        mediaFile.setMediaFileProcess_m3u8(null);
        mediaFile.setTag("tag");
        mediaFileRepository.insert(mediaFile);
    }

    @Test
    public void testFile() throws IOException {
        String path = "D:\\sfs\\FFmpeg\\testFile\\";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        for (int i = 0; i < 5; i++) {
            File file1 = new File(path + i);
            file1.createNewFile();
            System.out.println(file1);
        }
    }


}


//Math.ceil()方法返回大于或等于浮点数的值，类型为double。例如:Math.ceil(50.1)=51.0
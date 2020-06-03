package com.sfs.managecourse.util;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

public class QRCodeUtil {
	private static final String CHARSET = "utf-8";
    private static final String FORMAT_NAME = "JPG";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 600;
    // LOGO宽度
    private static final int WIDTH = 200;
    // LOGO高度
    private static final int HEIGHT = 200;

    
    private static BufferedImage createImage(String content, String imgPath,boolean needCompress) throws Exception {
        HashMap hints = new HashMap();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        @SuppressWarnings("unchecked")
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, (Map<EncodeHintType, ?>) hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000: 0xFFFFFFFF);
            }
        }
        if (imgPath == null || "".equals(imgPath))return image;
        // 插入图片
        QRCodeUtil.insertImage(image, imgPath, needCompress);
        return image;
    }
   
    /**
     * @param content 内容  
     * @param logoImage Logo图片地址  
     * @param bottomDes 底部描述
     * @param needCompress 是否压缩Logo大小 
     * @param needDescription 是否需要底部描述  
     * @return
     * @throws Exception
     */
    private static BufferedImage createImage(String content,String logoImage, String bottomDes, boolean needCompress) throws Exception {    
    	HashMap hints = new HashMap();    
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); //容错级别 H->30%    
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);    
        hints.put(EncodeHintType.MARGIN, 1);    
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);    
        int width = bitMatrix.getWidth();    
        int height = bitMatrix.getHeight();   
        int tempHeight = height;  
        boolean needDescription=(null==bottomDes&&"".equals(bottomDes));  
        if (needDescription) {  
            tempHeight += 30;  
        }   
        BufferedImage image = new BufferedImage(width, tempHeight, BufferedImage.TYPE_INT_RGB);   
        for (int x = 0; x < width; x++) {    
            for (int y = 0; y < height; y++) {    
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);    
            }    
        }    
        if(null==logoImage)return image;    
        // 插入图片    
        QRCodeUtil.insertImage(image, logoImage, needCompress);  
        if(needDescription) return image;  
        QRCodeUtil.addFontImage(image, bottomDes);  //添加底部描述
        return image;    
    }   
   
    private static void insertImage(BufferedImage source, String imgPath,
            boolean needCompress) throws Exception {
        File file = new File(imgPath);
        if (!file.exists()) {
            System.err.println(""+imgPath+"   该文件不存在！");
            return;
        }
        Image src = ImageIO.read(new File(imgPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (width > WIDTH) {
                width = WIDTH;
            }
            if (height > HEIGHT) {
                height = HEIGHT;
            }
            Image image = src.getScaledInstance(width, height,
                    Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    /** 
     * 添加 底部图片文字 
     * @param source   图片源 
     * @param declareText 文字本文 
     */  
    private static void addFontImage(BufferedImage source, String declareText) {  
        BufferedImage textImage = FontImage.getImage(declareText, QRCODE_SIZE, 50);  
        Graphics2D graph = source.createGraphics(); 

        int width = textImage.getWidth(null);    
        int height = textImage.getHeight(null);    
        Image src =textImage;    
        graph.drawImage(src, 0, QRCODE_SIZE - 30, width, height, null);     
        graph.dispose();    
    }
    public static void encode(String content, String imgPath, String destPath,
            boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath,
                needCompress);
        mkdirs(destPath);
        String file = new Random().nextInt(99999999)+".jpg";
        ImageIO.write(image, FORMAT_NAME, new File(destPath+"/"+file));
    }

   
    public static void mkdirs(String destPath) {
        File file =new File(destPath);   
        //当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }

    public static void encode(String content, String imgPath, String destPath)
            throws Exception {
        QRCodeUtil.encode(content, imgPath, destPath, false);
    }
    public static void encode(String content, String destPath,
            boolean needCompress) throws Exception {
        QRCodeUtil.encode(content, null, destPath, needCompress);
    }
    public static void encode(String content, String destPath) throws Exception {
        QRCodeUtil.encode(content, null, destPath, false);
    }
    public static void encode(String content, String imgPath,
            OutputStream output, boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath,
                needCompress);
        ImageIO.write(image, FORMAT_NAME, output);
    }
    public static void encode(String content, OutputStream output)
            throws Exception {
        QRCodeUtil.encode(content, null, output, false);
    }
    /** 
     *  
     * @param content  内容     
     * @param logoImage  图片源LOGO 
     * @param bottomDes 底部描述文字 
     * @param picName   图片名 
     * @param destPath     保存二维码 地址 (没有该目录会自动创建) 
     * @param needCompress 是否压缩logo 
     * @throws Exception 
     */  
    public static void encode(String content, String logoImage,String bottomDes,String picName,String destPath,    
            boolean needCompress) throws Exception {            
        BufferedImage image=QRCodeUtil.createImage(content, logoImage,bottomDes,needCompress);    

        mkdirs(destPath);    
        String file = picName+".png";    
        ImageIO.write(image, FORMAT_NAME, new File(destPath+"/"+file));    
    }
   
    @SuppressWarnings("unchecked")
	public static String decode(File file) throws Exception {
        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(
                image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable hints = new Hashtable();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, (Map<DecodeHintType, ?>) hints);
        String resultStr = result.getText();
        return resultStr;
    }
 
    public static String decode(String path) throws Exception {
        return QRCodeUtil.decode(new File(path));
    }
    
    @Test
    public void test() throws Exception{
    	 String text = "www.zztiyjw.cn:89";
        QRCodeUtil.encode(text, "D:\\Java\\奋斗的小牛.jpg", "", "", "D:\\Java", true);
    }

}

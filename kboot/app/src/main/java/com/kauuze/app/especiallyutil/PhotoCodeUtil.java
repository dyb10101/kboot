package com.kauuze.app.especiallyutil;


import com.kauuze.app.include.Rand;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 生成图片验证码工具类
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-24 12:30
 */
public class PhotoCodeUtil {
    // 图片的宽度。  
    private int width = 120;
    // 图片的高度。
    private int height = 40;
    // 验证码字符个数  
    private int codeCount = 6;
    // 验证码干扰线数  
    private int lineCount = 50;
    // 验证码  
    private String code = null;  
    // 验证码图片Buffer  
    private BufferedImage buffImg = null;  
  
    private char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',  
            'I', 'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',  
            'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9' };  
  
    // 生成随机数  
    private Random random = new Random();  
  
    public PhotoCodeUtil() {
        this.createCode();  
    }

    /**
     *
     * @return photoCodeId photo code width height suffixName type
     */
    public static Map<String,String> getPhotoCode(){
        Map<String,String> map = new HashMap<>();
        PhotoCodeUtil instance = new PhotoCodeUtil();
        String code = instance.getCode();
        map.put("photoCodeId", Rand.getUUID());
        map.put("width",String.valueOf(instance.width));
        map.put("height",String.valueOf(instance.height));
        map.put("suffixName","png");
        map.put("type","base64");
        map.put("code",code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedImage bufferedImage = instance.getBuffImg();
        try {
            ImageIO.write(bufferedImage, "png", outputStream);
        } catch (IOException e) {
        }
        BASE64Encoder encoder = new BASE64Encoder();
        map.put("photo",encoder.encode(outputStream.toByteArray()));
        return map;
    }
    /** 
     *  
     * @param width 
     *            图片宽 
     * @param height 
     *            图片高 
     */  
    public PhotoCodeUtil(int width, int height) {
        this.width = width;  
        this.height = height;  
        this.createCode();  
    }  
  
    /** 
     *  
     * @param width 
     *            图片宽 
     * @param height 
     *            图片高 
     * @param codeCount 
     *            字符个数 
     * @param lineCount 
     *            干扰线条数 
     */  
    public PhotoCodeUtil(int width, int height, int codeCount, int lineCount) {
        this.width = width;  
        this.height = height;  
        this.codeCount = codeCount;  
        this.lineCount = lineCount;  
        this.createCode();  
    }  
  
    public void createCode() {  
        int codeX = 0;  
        int fontHeight = 0;  
        fontHeight = height - 5;// 字体的高度  
        codeX = width / (codeCount+3);// 每个字符的宽度  
  
        // 图像buffer  
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
        Graphics2D g = buffImg.createGraphics();  
  
        // 将图像填充为白色  
        g.setColor(Color.WHITE);  
        g.fillRect(0, 0, width, height);  
  
        // 创建字体  
        ImgFontByte imgFont = new ImgFontByte();  
        Font font = imgFont.getFont(fontHeight);  
        g.setFont(font);  
  
        // 绘制干扰线  
        for (int i = 0; i < lineCount; i++) {  
            int xs = getRandomNumber(width);  
            int ys = getRandomNumber(height);  
            int xe = xs + getRandomNumber(width / 8);  
            int ye = ys + getRandomNumber(height / 8);  
            g.setColor(getRandomColor());  
            g.drawLine(xs, ys, xe, ye);  
        }  
  
        StringBuffer randomCode = new StringBuffer();  
        // 随机产生验证码字符  
        for (int i = 0; i < codeCount; i++) {  
            String strRand = String.valueOf(codeSequence[random  
                    .nextInt(codeSequence.length)]);  
            // 设置字体颜色  
            g.setColor(getRandomColor());  
            // 设置字体位置  
            g.drawString(strRand, (i + 1) * codeX,  
                    getRandomNumber(height / 2) + 25);  
            randomCode.append(strRand);  
        }  
        code = randomCode.toString();  
    }  
  
    /** 获取随机颜色 */  
    private Color getRandomColor() {  
        int r = getRandomNumber(255);  
        int g = getRandomNumber(255);  
        int b = getRandomNumber(255);  
        return new Color(r, g, b);  
    }  
  
    /** 获取随机数 */  
    private int getRandomNumber(int number) {  
        return random.nextInt(number);  
    }  
  
    public void write(String path) throws IOException {  
        OutputStream sos = new FileOutputStream(path);  
        this.write(sos);  
    }  
  
    public void write(OutputStream sos) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(buffImg, "png", outputStream);
        sos.close();
    }  
  
    public BufferedImage getBuffImg() {  
        return buffImg;  
    }  
  
    public String getCode() {  
        return code;  
    }  
  
    /** 字体样式类 */  
    class ImgFontByte {  
        public Font getFont(int fontHeight) {  
            try {  
                File file = new File("classpath:font");
                   InputStream in = new FileInputStream(file);
                    Font baseFont = Font.createFont(Font.TRUETYPE_FONT, in); 
                return baseFont.deriveFont(Font.PLAIN, fontHeight);  
            } catch (Exception e) {  
                return new Font("Arial", Font.PLAIN, fontHeight);  
            }  
        }  
    }
}
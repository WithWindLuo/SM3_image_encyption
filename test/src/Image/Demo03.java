package Image;

import Chaox.MyTools;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author:
 * @Date: 2020/4/23 21:20
 * @Description: 给图片加红线
 */

public class Demo03 {
    public static void main(String[] args) throws IOException {
        //获取本地图片
        String imagePath = "test\\img\\Lenna2.jpg";
//        String imagePath = "D:\\test.jpg";
        BufferedImage bi = ImageIO.read(new FileInputStream(imagePath));

//        MyTools.saveColorTxt(bi,"test\\color3.txt");

        //获取图像的宽度和高度
        int width = bi.getWidth();
        int height = bi.getHeight();
        int[] pot = new int[width * height];
//        System.out.println(bi);
//        System.out.println(bi.TYPE_3BYTE_BGR);
        System.out.println(width + "*" + height);
        int k = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j <= width / 2; j++) {//行扫描
                int k1 = bi.getRGB(j, i);
                int k2 = bi.getRGB(width - j - 1, i);
                bi.setRGB(j, i, k2);
                bi.setRGB(width - j - 1, i, k1);
            }
        }


        ImageIO.write(bi, "jpg", new FileOutputStream("test\\img\\Lenna1.jpg"));




    }


}



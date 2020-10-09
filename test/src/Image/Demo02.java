package Image;

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

public class Demo02 {
    public static void main(String[] args) throws IOException {
        //获取网络图片
//        URL url = new URL("https://profile.csdnimg.cn/A/2/3/3_u013248535");
//        BufferedImage bi = (BufferedImage) ImageIO.read(url);


        //获取本地图片
        String imagePath = "test\\img\\Lenna2.jpg";
        BufferedImage bi= ImageIO.read(new FileInputStream(imagePath));


        //获取图像的宽度和高度
        int width = bi.getWidth();
        int height = bi.getHeight();
        int[] pot = new int[width * height];
//        System.out.println(bi);
//        System.out.println(bi.TYPE_3BYTE_BGR);
        System.out.println(width + "*" + height);
        int k = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {//行扫描
                pot[k] = bi.getRGB(j, i);
                System.out.println(k + ":" + pot[k]);
                k++;
            }
        }
        //加红线
//        for (int i = height-40; i <height-20 ; i++) {
//            for (int j = 0; j < width; j++) {
//                bi.setRGB(j,i,-1238236);
//            }
//        }
        ImageIO.write(bi,"JPG",new FileOutputStream("test\\img\\Lenna1.jpg"));


    }
}

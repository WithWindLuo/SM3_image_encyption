package Chaox;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.*;
import java.util.Arrays;

/**
* @Author:
* @Date: 2020/4/24 2:55
* @Description: 图像置乱解密函数
*/

public class ImageDecrypt {
    public static void main(String[] args) throws IOException {
        //获取图片
        String imagePath = "test\\LennaGray_encrypt.gif";
//        String imagePath = "test\\Lenna_encrypt.jpg";
        File imageFile = new File(imagePath);
        BufferedImage bi = ImageIO.read(new FileInputStream(imageFile));


        //设置密钥
        double x = 0.5;
        double u = 3.986542664451;

        System.out.println(bi.getType());
        //获取图像的宽度和高度
        int width = bi.getWidth();
        int height = bi.getHeight();
        System.out.println(""+width+height);
        String[] originHashArr = MyTools.LogisticSM3Hash(x, u, height* width);
        String[] alteredHashArr = new String[width * height];

        //数组复制
        System.arraycopy(originHashArr, 0, alteredHashArr, 0, width * height);
//        System.out.println(originHashArr[50]);
//        System.out.println(alteredHashArr[50]);

        //将alterHashArr 按增序排序
        Arrays.sort(alteredHashArr);


        //获取图片像素的的RGB信息
        int[] originImageRGB = MyTools.getRGB_Arr(bi);
        int[] alteredImageRGB = null;     //用于存储灰度逆置乱后的图片的RGB信息数组
//        bi.getRGB(0,0,bi.getWidth(),bi.getHeight(),originImageRGB,0,bi.getWidth());


        //灰度逆置乱
        if(true){
            //获取一个长度为height*width的一维Logistic映射
            double[] logisticArr = MyTools.getLogisticArray(x, u, height * width);
            ColorModel colorModel = ColorModel.getRGBdefault();
            if (originImageRGB != null) {
                int k = 0;
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        //灰度图的RGB3个分量都一样
                        int r = colorModel.getRed(originImageRGB[k]);
                        r = r ^ (int) (logisticArr[k] * 255);
                        bi.setRGB(j, i, MyTools.convertRGB_Pixel(r, r, r));
                        k++;
                    }
                }
            }
        }
        alteredImageRGB = MyTools.getRGB_Arr(bi);

        //将缓冲区中的像素逆置乱
        for (int i = 0; i < height * width; i++) {
            int position = MyTools.arraySearch(originHashArr,alteredHashArr[i]);
//            System.out.println(""+i+":"+position);
            bi.setRGB(i % width, i / width, alteredImageRGB[position]);
        }




        //写入文件
        String fileName = imageFile.getName();
        String[] fileNameArr = fileName.split("\\.");
        //新路径
        String newImagePath =
                imageFile.getParent() + "\\" + fileNameArr[0] + "_decrypt." + fileNameArr[1];
        ImageIO.write(bi, /*fileNameArr[1]*/ "png",
                new FileOutputStream(newImagePath));
    }
}

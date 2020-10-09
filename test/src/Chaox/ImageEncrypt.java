package Chaox;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.*;
import java.util.Arrays;

/**
 * @Author:
 * @Date: 2020/4/23 22:31
 * @Description: 图像置乱加密加密函数
 */

public class ImageEncrypt {
    public static void main(String[] args) throws IOException {
        //获取图片
        String imagePath = "test\\LennaGray.gif";
//        String imagePath = "D:\\desk.jpg";
        File imageFile = new File(imagePath);
        BufferedImage bi = ImageIO.read(new FileInputStream(imageFile));


        //设置密钥
        double x = 0.5;
        double u = 3.986542664451;
        //获取图像的宽度和高度
        int width = bi.getWidth();
        int height = bi.getHeight();
        String[] originHashArr = MyTools.LogisticSM3Hash(x, u, height * width);
        String[] alteredHashArr = new String[width * height];

        //数组复制
        System.arraycopy(originHashArr, 0, alteredHashArr, 0, width * height);
//        System.out.println(originHashArr[50]);
//        System.out.println(alteredHashArr[50]);

        //将alterHashArr 按增序排序
        Arrays.sort(alteredHashArr);

        //存储颜色文本
        MyTools.saveColorTxt(bi, "test\\color1.txt");


        //获取图片像素的的RGB信息
        int[] originImageRGB = MyTools.getRGB_Arr(bi);
        int[] alteredImageRGB = null;     //用于存储像素置乱后的图片的RGB信息数组
//        bi.getRGB(0,0,bi.getWidth(),bi.getHeight(),originImageRGB,0,bi.getWidth());


        //将缓冲区中的像素置乱
        for (int i = 0; i < height * width; i++) {
            int position = Arrays.binarySearch(alteredHashArr, originHashArr[i]);
            bi.setRGB(i % width, i / width, originImageRGB[position]);
        }


        //灰度置乱
        if(true){
            alteredImageRGB = MyTools.getRGB_Arr(bi);
            //获取一个长度为height*width的一维Logistic映射
            double[] logisticArr = MyTools.getLogisticArray(x, u, height * width);
            ColorModel colorModel = ColorModel.getRGBdefault();
            if (originImageRGB != null) {
                int k = 0;
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        //灰度图的RGB3个分量都一样
                        int r = colorModel.getRed(alteredImageRGB[k]);
                        r = r ^ (int) (logisticArr[k] * 255);
                        bi.setRGB(j, i, MyTools.convertRGB_Pixel(r, r, r));
                        k++;
                    }
                }
            }
        }

        //写入文件，文件名与路径处理
        String fileName = imageFile.getName();
        String[] fileNameArr = fileName.split("\\.");

        //加密后另存为新文件
        String newImagePath =
                imageFile.getParent() + "\\" + fileNameArr[0] + "_encrypt." + fileNameArr[1];

        FileOutputStream fos = new FileOutputStream(newImagePath);
        ImageIO.write(bi, /*fileNameArr[1]*/ "png",
                fos);
        fos.close();


    }


}


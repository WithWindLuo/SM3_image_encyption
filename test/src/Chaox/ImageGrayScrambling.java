package Chaox;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.*;

/**
 * @Author:
 * @Date: 2020/4/24 22:09
 * @Description: 实现黑白图片的灰度置乱或逆置乱
 */

public class ImageGrayScrambling {
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

        //获取图片像素的的RGB信息
        int[] originImageRGB = MyTools.getRGB_Arr(bi);
        int[] alteredImageRGB = null;        //用于存储灰度置乱后的RGB像素

        //获取一个长度为height*width的一维Logistic映射
        double[] logisticArr = MyTools.getLogisticArray(x, u, height * width);


        System.out.println(bi.getRGB(0,0));
        //灰度置乱
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


        System.out.println(bi.getRGB(0,0));

        alteredImageRGB = MyTools.getRGB_Arr(bi);
        //灰度逆置乱
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
        System.out.println(bi.getRGB(0,0));

        //写入文件，文件名与路径处理
        String fileName = imageFile.getName();
        String[] fileNameArr = fileName.split("\\.");
        //加密后另存为新文件
        String newImagePath =
                imageFile.getParent() + "\\" + fileNameArr[0] + "_grayScr." + fileNameArr[1];
        ImageIO.write(bi, /*fileNameArr[1]*/ "png",
                new FileOutputStream(newImagePath));


    }


}

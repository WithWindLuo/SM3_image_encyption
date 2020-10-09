package Chaox;

import testSM3AndLogistic.MD5Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;

/**
 * @Author:
 * @Date: 2020/4/24 1:38
 * @Description: 测试某些不熟悉的函数
 */

public class dedmom {
    public static void main(String[] args) throws Exception {
        File file = new File("D:\\test\\Lenna.jpg");
        System.out.println(file.getPath());
        System.out.println(file.getParent());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getParentFile());
        System.out.println(file.getName());

        String fileName = file.getName();
        String[] fileNameArr = fileName.split("\\.");
        System.out.println(file.getParent() + "\\" + fileNameArr[0] + "_encrypt." + fileNameArr[1]);

        int a = -99999999;
        System.out.println(a);

        System.out.println(MD5Utils.createFileMD5("test\\color1.txt"));
        System.out.println(MD5Utils.createMD5("test\\color1.txt"));

        //获取RGB分量
        String imagePath = "test\\LennaGray.gif";
        File imageFile = new File(imagePath);
        BufferedImage bi = ImageIO.read(new FileInputStream(imageFile));
        int[] originImageArr = MyTools.getRGB_Arr(bi);
        ColorModel colorModel = ColorModel.getRGBdefault();
        int rgb = Objects.requireNonNull(originImageArr)[111131];
        System.out.println("int:" + rgb);
        int r = colorModel.getRed(rgb);
        int g = colorModel.getGreen(rgb);
        int b = colorModel.getBlue(rgb);
        System.out.println("r:" + r);
        System.out.println("g:" + g);
        System.out.println("b:" + b);
        System.out.println(MyTools.convertRGB_Pixel(r, g, b));

//        MyTools.saveColorTxt("test\\Lenna.jpg", "test\\color1.txt");
//        MyTools.saveColorTxt("test\\Lenna_encrypt_decrypt.jpg", "test\\color2.txt");
//        MyTools.saveColorTxt("test\\LennaGray.gif", "test\\color3.txt");
//        MyTools.saveColorTxt("test\\LennaGray_encrypt_decrypt.gif", "test\\color4.txt");


//        MyTools.saveColorTxt("test\\Lenna_gray_encrypt_decrypt.gif","test\\color3.txt");

        double x = 3.145648445645645616551;
        System.out.println(x);
    }


}

package Chaox;




import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.*;
import java.util.Arrays;
import java.util.Objects;


/**
 * @Author:
 * @Date: 2020/4/23 22:33
 * @Description:
 */

public class MyTools {
    public static void main(String[] args) {
        double x = 0.7;
//        double x = 0.50001;
        double u = 2;
        for (int i = 0; i < 100; i++) {
            x = x * u * (1 - x);
            System.out.println(x);
        }
    }


    //输入参数x,u,m,返回包含m个一维Logistic映射元素的数组
    public static double[] getLogisticArray(double x, double u, int m) {
        double[] arr = new double[m];
        for (int i = 0; i < m; i++) {
            x = x * u * (1 - x);
            arr[i] = x;
        }
        return arr;
    }

    //输入参数,生成一定包含m个一维Logistic映射元素的SM3值的数组
    public static String[] LogisticSM3Hash(double x, double u, int m) {
        String[] HashArr = new String[m];
        for (int i = 0; i < m; i++) {
            x = x * u * (1 - x);
//            System.out.println(SM3.hash(String.valueOf(x)));
//            Thread.sleep(100);
            HashArr[i] = testSM3AndLogistic.SM3.hash(String.valueOf(x));
        }




        return HashArr;
    }

    //返回关键字key在数组arr中的索引
    public static int arraySearch(String[] arr, String key) {
        for (int i = 0; i < arr.length; i++) {
            if (key == arr[i]) {
                return i;
            }
        }
        return -1;
    }

    //将图片像素的颜色另存为txt文本
    public static void saveColorTxt(BufferedImage bi, File file) throws FileNotFoundException {
        PrintStream ps = new PrintStream(file);
        for (int i = 0; i < bi.getHeight(); i++) {
            for (int j = 0; j < bi.getWidth(); j++) {
                ps.println(bi.getRGB(j, i));
            }
        }
        ps.close();
    }

    //将图片像素的颜色另存为txt文本 重载
    public static void saveColorTxt(BufferedImage bi, String desPath) throws FileNotFoundException {
        PrintStream ps = new PrintStream(desPath);
        for (int i = 0; i < bi.getHeight(); i++) {
            for (int j = 0; j < bi.getWidth(); j++) {
                ps.println(bi.getRGB(j, i));
            }
        }
        ps.close();
    }

    public static void saveColorTxt(String imagePath, String desPath) throws IOException {
        File imageFile = new File(imagePath);
        BufferedImage bi = ImageIO.read(new FileInputStream(imageFile));
        PrintStream ps = new PrintStream(desPath);
        for (int i = 0; i < bi.getHeight(); i++) {
            for (int j = 0; j < bi.getWidth(); j++) {
                ps.println(bi.getRGB(j, i));
            }
        }
        ps.close();
    }


    //获取图片像素的RGB颜色数组
    public static int[] getRGB_Arr(BufferedImage bi) {
        if (bi == null) {
            return null;
        }

        int height = bi.getHeight();
        int width = bi.getWidth();
        int[] RGB_Arr = new int[bi.getWidth() * bi.getWidth()];

        int k = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                RGB_Arr[k] = bi.getRGB(j, i);
                k++;
            }
        }
        return RGB_Arr;
    }

    //将RGB转换为Int型整数像素
    public static int convertRGB_Pixel(int r, int g, int b) {
        return (0xFF << 24) | (r << 16) | (g << 8) | b;
    }

    //像素位置置乱
    public static BufferedImage positionScrambling(
            String[] originHashArr,String[] alteredHashArr,BufferedImage bi){
        //获取图片像素的的RGB信息
        int[] originImageRGB = MyTools.getRGB_Arr(bi);
        //用于存储像素置乱后的图片的RGB信息数组
        //将缓冲区中的像素置乱
        for (int i = 0; i < bi.getHeight() * bi.getWidth(); i++) {
            int position = Arrays.binarySearch(alteredHashArr, originHashArr[i]);
            bi.setRGB(i % bi.getWidth(), i / bi.getWidth(), originImageRGB[position]);
        }
        return bi;
    }

    //图像加密
    public static void ImageEncrypt(File imageFile, double x, double u, boolean isGrayScrambling) throws IOException {
        BufferedImage bi = ImageIO.read(new FileInputStream(imageFile));
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
        //将缓冲区中的像素置乱
        for (int i = 0; i < height * width; i++) {
            int position = Arrays.binarySearch(alteredHashArr, originHashArr[i]);
            bi.setRGB(i % width, i / width, Objects.requireNonNull(originImageRGB)[position]);
        }
        alteredImageRGB = MyTools.getRGB_Arr(bi);


        //RGB分量置乱
        if(!isGrayScrambling){
            //获取一个长度为height*width的一维Logistic映射
            double[] logisticArr = MyTools.getLogisticArray(x, u, height * width);
            ColorModel colorModel = ColorModel.getRGBdefault();
            if (originImageRGB != null) {
                int k = 0;
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        //灰度图的RGB3个分量都一样
                        int r = colorModel.getRed(alteredImageRGB[k]);
                        int g = colorModel.getGreen(alteredImageRGB[k]);
                        int b = colorModel.getBlue(alteredImageRGB[k]);
                        r = r ^ (int) (logisticArr[k] * 255);
                        g = g ^ (int) (logisticArr[k] * 255);
                        b = b ^ (int) (logisticArr[k] * 255);
                        bi.setRGB(j, i, MyTools.convertRGB_Pixel(r, g, b));
                        k++;
                    }
                }
            }
        }


        //灰度置乱
        if (isGrayScrambling) {
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
        ImageIO.write(bi, /*fileNameArr[1]*/ "png", fos);
        fos.close();

    }

    //重载
    public static void ImageEncrypt(String imagePath, double x, double u, boolean isGrayScrambling) throws IOException {
        ImageEncrypt(new File(imagePath), x, u, isGrayScrambling);
    }

    //图像解密
    public static void ImageDecrypt(File imageFile, double x, double u, boolean isGrayScrambling) throws IOException {
        BufferedImage bi = ImageIO.read(new FileInputStream(imageFile));

        System.out.println(bi.getType());
        //获取图像的宽度和高度
        int width = bi.getWidth();
        int height = bi.getHeight();
        System.out.println("" + width + height);
        String[] originHashArr = MyTools.LogisticSM3Hash(x, u, height * width);
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




        //RGB分量逆置乱
        if(!isGrayScrambling){
            //获取一个长度为height*width的一维Logistic映射
            double[] logisticArr = MyTools.getLogisticArray(x, u, height * width);
            ColorModel colorModel = ColorModel.getRGBdefault();
            if (originImageRGB != null) {
                int k = 0;
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        //灰度图的RGB3个分量都一样
                        int r = colorModel.getRed(originImageRGB[k]);
                        int g = colorModel.getGreen(originImageRGB[k]);
                        int b = colorModel.getBlue(originImageRGB[k]);
                        r = r ^ (int) (logisticArr[k] * 255);
                        g = g ^ (int) (logisticArr[k] * 255);
                        b = b ^ (int) (logisticArr[k] * 255);
                        bi.setRGB(j, i, MyTools.convertRGB_Pixel(r, g, b));
                        k++;
                    }
                }
            }
        }


        //灰度逆置乱
        if (isGrayScrambling) {
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
            int position = MyTools.arraySearch(originHashArr, alteredHashArr[i]);
//            System.out.println(""+i+":"+position);
            bi.setRGB(i % width, i / width, alteredImageRGB[position]);
        }


        //写入文件
        String fileName = imageFile.getName();
        String[] fileNameArr = fileName.split("\\.");
        //新路径
        String newImagePath =
                imageFile.getParent() + "\\" + fileNameArr[0] + "_decrypt." + fileNameArr[1];
        FileOutputStream fos = new FileOutputStream(newImagePath);
        ImageIO.write(bi, /*fileNameArr[1]*/ "png", fos);
        fos.close();


    }

    //重载
    public static void ImageDecrypt(String imagePath, double x, double u, boolean isGrayScrambling) throws IOException {
        ImageDecrypt(new File(imagePath), x, u, isGrayScrambling);
    }

    //生成灰度直方图
    public static void creatGray(String imagePath, JFrame jf){
        File f = new File(imagePath);
        new ImageAnalysisUI(f,jf);
    }

    //生成RGB分量直方图
    public static void createRGB(String imagePath){
        File pythonDire = new File("test");
        String cmd = "python " + pythonDire.getAbsolutePath() + "\\histogram-rgb.py" + " --image "
                +  imagePath;
        System.out.println(cmd);
        try {
            Process process =  Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





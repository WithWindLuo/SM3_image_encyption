package Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Author:
 * @Date: 2020/4/23 5:08
 * @Description:
 * ImageBuffer 基础学习
 */

public class Demo {
    public static void main(String[] args) throws IOException {
        String imagePath = "test\\Lenna.jpg";
        BufferedImage bi = ImageIO.read(new FileInputStream(imagePath));
        int imageWidth = bi.getWidth();
        int imageHeight = bi.getHeight();
        System.out.println(imageHeight + "*" + imageWidth);
        System.out.println("getType():"+bi.getType());

    }
}

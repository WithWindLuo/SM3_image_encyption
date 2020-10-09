package SM3;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;

/**
 * @Author:
 * @Date: 2020/4/22 6:27
 * @Description: 一维Logistic映射
 */

public class Logistic {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        double u = 3.986542664451;
        double x = 0.5;
        String[] originHashArr = new String[512 * 512];
        for (int i = 0; i < 512 * 512; i++) {
            x = x * u * (1 - x);
//            System.out.println(SM3.hash(String.valueOf(x)));
//            Thread.sleep(100);
            originHashArr[i] = SM3.hash(String.valueOf(x));
        }

        PrintStream ps1 = new PrintStream("test\\sm3_logistic_Original.txt");
        for (String s : originHashArr) {
//            System.out.println(s);s
            ps1.println(s);
        }
        ps1.close();


        //数组排序
        Arrays.sort(originHashArr);

//        for (String s : originHashArr) {
//            System.out.println(s);
//        }
        //查找出现位置
//        String key = "fff5d30e8ff667ce09941ea6ddcd31f048ad6cd63a650f2a08fab5fdb26f6437";
//        System.out.println(Arrays.binarySearch(originHashArr,key));


//输出到文件中去
        PrintStream ps2 = new PrintStream("test\\sm3_logistic_IncreasingOrder.txt");
        for (String s : originHashArr) {
            ps2.println(s);
        }
        ps2.close();
    }
}

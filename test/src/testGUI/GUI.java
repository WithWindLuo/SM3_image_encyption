package testGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

public class GUI {

    private Frame jf;
    private PrintStream jtext;



    public static void main(String[] args) throws Exception {

        GUI hj = new GUI();


        // 主窗体
        JFrame f = new JFrame("11");

        // 主窗体设置大小
        f.setSize(400, 300);

        // 主窗体设置位置
        f.setLocation(200, 200);

        // 主窗体中的组件设置为绝对定位
        f.setLayout(null);

        // 按钮组件
        JButton select = new JButton("选择文件");



        // JButton encryption = new JButton("加密");

         select.addActionListener(new ActionListener() {

             // 当按钮被点击时，就会触发 ActionEvent事件
             // actionPerformed 方法就会被执行
             public void actionPerformed(ActionEvent e) {
                     hj.open();
             }
         });


        // 同时设置组件的大小和位置
        select.setBounds(50, 50, 150, 30);
        //  encryption.setBounds(50, 70, 280, 30);


        // 把按钮加入到主窗体中
        f.add(select);
        //   f.add(encryption);

        // 关闭窗体的时候，退出程序
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 让窗体变得可见
        f.setVisible(true);


    }


    //选择加密文件
    public void open() {
        FileDialog fdopen = new FileDialog(jf, "打开", FileDialog.LOAD);
        fdopen.setVisible(true);

        String filePath = fdopen.getDirectory();
        String fileName= fdopen.getFile();

        System.out.println(""+filePath+fileName);
    }
}

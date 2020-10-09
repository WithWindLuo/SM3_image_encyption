package Chaox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * @Author:
 * @Date: 2020/4/24 6:09
 * @Description: 程序启动的主函数
 */

public class Main {
    public static void main(String[] args) throws IOException {
        String originalImagePath;
        String encryptedImagePath;
        String decryptedImagePath;


        //主窗体
        JFrame jf = new JFrame("基于SM3的图像加密");

        //窗体设置大小
        jf.setSize(600,420);

        //设置窗口绝对位置
        jf.setLocation(300,200);

        // 主窗体中的组件设置为绝对定位
        jf.setLayout(null);


        //上次执行的是加密还是解密标识,加密为"1",解密为"0";
        JLabel flagLabel = new JLabel("1");


        //路径标签
        JLabel originalImagePathLabel = new JLabel("路径");
        originalImagePathLabel.setText("请选择图像文件");
        originalImagePathLabel.setBounds(60,0,500,50);


        //密钥标签以及文本框
        JLabel keyLabel = new JLabel("密钥：");
        JLabel xLabel = new JLabel("x：");
        JTextArea xTextArea = new JTextArea();
        JLabel uLabel = new JLabel("u：");
        JTextArea uTextArea = new JTextArea();
        int key_X = 20;
        int key_Y = 90;
        int key_height = 20;
        keyLabel.setBounds(key_X,key_Y,100,key_height);
        xLabel.setBounds(key_X+40,key_Y,20,key_height);
        xTextArea.setBounds(key_X+60,key_Y,100,key_height);
        uLabel.setBounds(key_X+190,key_Y,20,key_height);
        uTextArea.setBounds(key_X+210,key_Y,150,key_height);

        //彩色图片or灰度图片复选框
        String[] jcbArr = {"彩色图片","灰度图片"};
        JComboBox<String> cmb = new JComboBox<>(jcbArr);
        cmb.setBounds(key_X+400,key_Y,120,key_height);


        //选择图像按钮组件
        JButton chooseImageButton = new JButton("请选择需要加密/解密的图像");
        //设置按钮大小和位置
        chooseImageButton.setBounds(140,50,280,30);
        //按钮监听事件
        chooseImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                open(jf,originalImagePathLabel);
            }
        });



        //加密图像按钮组件
        JButton imageEncryptButton = new JButton("执行加密");
        imageEncryptButton.setBounds(140,130,280,30);
        imageEncryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        flagLabel.setText("1");
                        String s = originalImagePathLabel.getText();
                        originalImagePathLabel.setText(s+"    正在进行加密");
                        try {
                            double x = Double.parseDouble(xTextArea.getText());
                            double u = Double.parseDouble(uTextArea.getText());
//                    MyTools.ImageEncrypt(s,x,u,false);
                            boolean isGray;
                            if(cmb.getSelectedIndex() == 0){
                                isGray = false;       //不是灰度图片
                            }else{
                                isGray = true;
                            }
                            MyTools.ImageEncrypt(s,x,u,isGray);

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        originalImagePathLabel.setText(s+"    已加密完成！");
                    }
                }).start();
            }

        });




        //加密图像按钮组件
        JButton imageDecryptButton = new JButton("执行解密");
        imageDecryptButton.setBounds(140,180,280,30);
        imageDecryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        flagLabel.setText("0");
                        String s = originalImagePathLabel.getText();
                        originalImagePathLabel.setText(s+"    正在进行解密");
                        try {
                            double x = Double.parseDouble(xTextArea.getText());
                            double u = Double.parseDouble(uTextArea.getText());
//                    MyTools.ImageEncrypt(s,x,u,false);
                            boolean isGray;
                            if(cmb.getSelectedIndex() == 0){
                                isGray = false;       //不是灰度图片
                            }else{
                                isGray = true;
                            }
                            MyTools.ImageDecrypt(s,x,u,isGray);

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        originalImagePathLabel.setText(s+"    已解密完成！");
                    }
                }).start();
            }

        });

        //打开图片按钮
        JButton openImageButton = new JButton("打开被加密/解密图片");
        openImageButton.setBounds(140,230,280,30);
        openImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = originalImagePathLabel.getText();
                String imageType = s.substring(s.indexOf('.'),s.indexOf(' '));

                String suffix;      //文件名后缀"_encrypt"或者"_decrypt"
                if(flagLabel.getText().equals("1")){
                    suffix = "_encrypt";
                }else{
                    suffix = "_decrypt";
                }
                File file = new File(s.substring(0,s.lastIndexOf('.'))+suffix+imageType);
                System.out.println(file.getAbsolutePath());
                Runtime runtime = Runtime.getRuntime();
                try {
                    runtime.exec("cmd /c " + file.getAbsolutePath());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        });


        //生成灰度直方图片按钮
        JButton creatGrayHisButton = new JButton("生成灰度直方图");
        creatGrayHisButton.setBounds(140,280,280,30);
        creatGrayHisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyTools.creatGray(originalImagePathLabel.getText(),jf);
            }

        });

        //生成RGB直方图片按钮
        JButton creatRGBHisButton = new JButton("生成RGB分量直方图");
        creatRGBHisButton.setBounds(140,330,280,30);
        creatRGBHisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyTools.createRGB(originalImagePathLabel.getText());
            }

        });


        //组件加入主窗口
        //按钮
        jf.add(chooseImageButton);
        jf.add(imageEncryptButton);
        jf.add(imageDecryptButton);
        jf.add(openImageButton);
        jf.add(creatGrayHisButton);
        jf.add(creatRGBHisButton);
        //路径标签
        jf.add(originalImagePathLabel);
        //加密密钥
        jf.add(keyLabel);
        jf.add(xLabel);
        jf.add(xTextArea);
        jf.add(uLabel);
        jf.add(uTextArea);
        //下拉列表
        jf.add(cmb);

        //关闭窗口时执行的操作
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        jf.setVisible(true);
    }




    //选择加密文件
    public static void open(JFrame jf , JLabel l) {
        FileDialog fdopen = new FileDialog(jf, "打开", FileDialog.LOAD);
        fdopen.setVisible(true);

        String filePath = fdopen.getDirectory()+fdopen.getFile();

        System.out.println(filePath);
        if(!"nullnull".equals(filePath)){
            l.setText(filePath);
        }else{
            l.setText("请选择需要加密或解密的图像");
        }
    }
}

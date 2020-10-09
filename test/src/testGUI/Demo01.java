package testGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* @Author: 
* @Date: 2020/4/25 23:11 
* @Description: 简单的例子
*/

public class Demo01 {
    public static void main(String[] args) {
        //主窗体
        JFrame f = new JFrame("LOL");

        //窗体设置大小
        f.setSize(400,300);

        //设置窗口绝对位置
        f.setLocation(200,200);

        // 主窗体中的组件设置为绝对定位
        f.setLayout(null);

        JLabel l = new JLabel();
        ImageIcon i  = new ImageIcon("test\\shana.png");
        l.setIcon(i);
        l.setBounds(80,120,i.getIconWidth(),i.getIconHeight());

        //按钮组件
        JButton b = new JButton("Hello world!");
        //设置按钮大小和位置
        b.setBounds(50,50,280,30);
        //按钮监听事件
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                l.setVisible(false);
            }
        });
        //将按钮加入主窗口
        f.add(b);
        f.add(l);


        //关闭窗口时执行的操作
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        f.setVisible(true);
    }
}

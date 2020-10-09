package testGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Demo02 {
    public static void main(String[] args) {


        //主窗体
        JFrame f = new JFrame("LOL");
        f.setSize(400,300);
        f.setLocation(200,200);
        f.setLayout(null);


        //普通的窗体，带最大和最小化按钮，而对话框却不带
        // 根据外部窗体实例化JDialog
        JDialog d = new JDialog(f);
        // 设置为模态
        d.setModal(true);

        d.setTitle("模态的对话框");
        d.setSize(200, 100);
        d.setLocation(200, 200);
        d.setLayout(null);
        JButton b = new JButton("一键秒对方基地挂");
        b.setBounds(50, 50, 200, 30);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hello");
            }
        });

        d.add(b);




        f.setVisible(true);
        d.setVisible(true);
    }
}
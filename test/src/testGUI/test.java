package testGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class test {
    public static void main(String[] args) {
        //主窗体
        JFrame f = new JFrame("LOL");

        //窗体设置大小
        f.setSize(400,300);

        //设置窗口绝对位置
        f.setLocation(200,200);

        // 主窗体中的组件设置为绝对定位
        f.setLayout(null);



        String[] cmbArr= {"1","2"};
        JComboBox<String> cmb=new JComboBox<>(cmbArr);    //创建JComboBox
        cmb.setBounds(50,20,100,20);
//        cmb.addItem("--请选择--");    //向下拉列表中添加一项
//        cmb.addItem("身份证");
//        cmb.addItem("驾驶证");
//        cmb.addItem("军官证");




        JLabel l = new JLabel();
        ImageIcon i  = new ImageIcon("test\\shana.png");
        l.setIcon(i);
        l.setBounds(80,120,i.getIconWidth(),i.getIconHeight());

        //按钮组件
        JButton b = new JButton("Hello world!");
        //设置按钮大小和位置
        b.setBounds(50,160,280,30);
        //按钮监听事件
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.setText("123123");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.out.println(cmb.getSelectedItem());

            }
        });











        f.add(cmb);
        f.add(b);
        f.add(l);







        //关闭窗口时执行的操作
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        f.setVisible(true);
    }
}

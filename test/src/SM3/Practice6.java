package SM3;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Practice6 {
    public static void main(String[] args) {
        int gap = 10;
        JFrame f = new JFrame("SM3");
        f.setSize(410, 400);
        f.setLocation(200, 200);
        f.setLayout(null);

        JPanel pInput = new JPanel();
        pInput.setBounds(gap, gap, 375, 120);
        pInput.setLayout(new GridLayout(4,3,gap,gap));




        JLabel location = new JLabel("原字符串:");
        JTextField locationText = new JTextField();
//
//
//        JLabel type = new JLabel("公司类型:");
//        JTextField typeText = new JTextField();
//
//
//        JLabel name = new JLabel("公司名称:");
//        JTextField nameText = new JTextField();
//
//
//        JLabel bossname = new JLabel("老板名称:");
//        JTextField bossnameText = new JTextField();
//
//
//        JLabel money = new JLabel("金额:");
//        JTextField moneyText = new JTextField();
//
//
//        JLabel product = new JLabel("产品:");
//        JTextField productText = new JTextField();
//
//
//        JLabel measure = new JLabel("价格计量单位:");
//        JTextField measureText = new JTextField();


        JButton b = new JButton("生成SM3摘要字符串");

        pInput.add(location);
        pInput.add(locationText);
//        pInput.add(type);
//        pInput.add(typeText);
//        pInput.add(name);
//        pInput.add(nameText);
//        pInput.add(bossname);
//        pInput.add(bossnameText);
//        pInput.add(money);
//        pInput.add(moneyText);
//        pInput.add(product);
//        pInput.add(productText);
//        pInput.add(measure);
//        pInput.add(measureText);

        //文本域
        JTextArea ta = new JTextArea();
        ta.setLineWrap(true);
        b.setBounds(140, 100, 120, 30);
        ta.setBounds(gap, 200, 375, 120);



        f.add(pInput);
        f.add(b);
        f.add(ta);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        f.setVisible(true);
        //鼠标监听
        b.addActionListener(new ActionListener(){
            boolean checkedpass = true;
            public void actionPerformed(ActionEvent e){
                checkedpass = true;
                checkEmpty(locationText,"地名");
//                checkEmpty(typeText,"公司类型");
//                checkEmpty(nameText,"公司名称");
//                checkEmpty(bossnameText,"老板姓名");
//                checkNumber(moneyText,"金额");
//                checkEmpty(productText,"产品");
//                checkEmpty(measureText,"价格计量单位");

                String location = locationText.getText();
//                String type = typeText.getText();
//                String companyName = nameText.getText();
//                String bossName = bossnameText.getText();
//                String money = moneyText.getText();
//                String product = productText.getText();
//                String unit = measureText.getText();

                if(checkedpass){
                    String hash_value = SM3.hash(location);
//                    String result= String.format(model, location,type,companyName,bossName,money,product,unit,unit,unit,product,bossName);
                    ta.setText("");
                    ta.append(hash_value);
                }

            }

            //检验是否为空
            private void checkEmpty(JTextField tf, String msg){
                if(!checkedpass)
                    return;
                String value = tf.getText();
                if(value.length()==0){
                    JOptionPane.showMessageDialog(f, msg + " 不能为空");
                    tf.grabFocus();
                    checkedpass = false;
                }
            }
            //检验输入金额必须是整数
            private void checkNumber(JTextField tf, String msg){
                if(!checkedpass)
                    return;
                String value = tf.getText();
                try {
                    Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(f, msg + " 必须是整数");
                    tf.grabFocus();
                    checkedpass = false;
                }
            }


        });

    }




}



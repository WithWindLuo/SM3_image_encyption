package testGUI;

import javax.swing.*;
import java.io.File;

public class testJFileChooser {
    public static void main(String[] args) {

        JFileChooser jf = new JFileChooser();
        jf.setSelectedFile(new File("c:\\我的报表.xls"));
        int value = jf.showSaveDialog(null);
        jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jf.setFileHidingEnabled(false);
        if (value == JFileChooser.APPROVE_OPTION) {
            File getPath = jf.getSelectedFile();
            System.out.println(getPath.getAbsolutePath());
            // TODO
        } else {
            // TODO
        }
    }
}
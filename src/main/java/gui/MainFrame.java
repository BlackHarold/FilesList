package gui;

import entity.FileToTable;
import tablemodel.mTableModel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.SQLException;

public class MainFrame extends JFrame {
    
    
    private static void init() throws SQLException {
        
        mTableModel model = new mTableModel();
        JTable table;
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        JFrame mFrame = new JFrame("FileChooser");
        JButton btn = new JButton("Add file");
//        слушатель кнопки
        btn.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
//            0 = JFileChooser.FILES_ONLY
            fc.setFileSelectionMode(0);
            fc.showOpenDialog(null);
            File selectedFile = fc.getSelectedFile();
            if (selectedFile != null) {
                FileToTable fileToTable = new FileToTable(selectedFile.getName(), selectedFile.getPath(), String.valueOf(selectedFile.length() / 1024L));
            }
        });
        
        
        mFrame.setLocationRelativeTo(null);
        mFrame.setLayout(new GridBagLayout());
//        Располагаю элементы на форме
        mFrame.add(sp, new GridBagConstraints(0, 0, 4, 1, 1, 1,
                GridBagConstraints.CENTER, 0, new Insets(1, 1, 1, 1), 0, 0));
        mFrame.add(btn, new GridBagConstraints(3, 1, 1, 1, 0, 0,
                GridBagConstraints.EAST, 0, new Insets(0, 0, 1, 1), 0, 0));
        
        mFrame.setSize(600, 400);
        mFrame.setDefaultCloseOperation(3);
        mFrame.setVisible(true);
        mFrame.pack();
        
    }
    
    public static void main(String[] args) throws SQLException {
        init();
    }
}

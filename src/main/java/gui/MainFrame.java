package gui;

import dao.ConnectionDAO;
import entity.FileToTable;
import tablemodel.mTableModel;

import javax.swing.*;
import javax.swing.event.RowSorterEvent;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.File;
import java.sql.SQLException;

public class MainFrame extends JFrame {
    
    
    private static void init() throws SQLException {
        
        ConnectionDAO conn;
        
        mTableModel model = new mTableModel();
        JTable table;
        table = new JTable(model);
    
        table.setAutoCreateRowSorter(true);
        TableRowSorter<?> sorter = (TableRowSorter<?>)table.getRowSorter();
        sorter.setSortsOnUpdates(true);
        sorter.addRowSorterListener(e -> {
            if (e.getType() == RowSorterEvent.Type.SORT_ORDER_CHANGED) {
            
            }
        }  );
        
        
        JScrollPane sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(600, 400));
        JFrame mFrame = new JFrame("FileChooser");
        JButton addBtn = new JButton("Add file");
        JButton delBtn = new JButton("Delete row");


//        слушатель кнопки
        addBtn.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();

//            0 = JFileChooser.FILES_ONLY
            fc.setFileSelectionMode(0);
            fc.showOpenDialog(null);
            File selectedFile = fc.getSelectedFile();
            
            if (selectedFile != null) {
                FileToTable fileToTable = new FileToTable(
                        selectedFile.getName(),
                        selectedFile.getPath(),
                        String.valueOf(selectedFile.length() / 1024L + " KB"));
                System.out.println(fileToTable.getSize());
                
                try {
                    new ConnectionDAO().insertFileToTable(fileToTable);
                    table.setModel(new mTableModel());
                    table.repaint();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        
        delBtn.addActionListener(e -> {
            if (table.getSelectedRow() != -1) {
                try {
                    int row = table.getSelectedRow();
                    new ConnectionDAO().deleteFilefromTable((Integer) table.getValueAt(row, 0));
                    table.setModel(new mTableModel());
                    table.repaint();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                
            }
        });
        
        
        mFrame.setLocationRelativeTo(null);
        mFrame.setLayout(new GridBagLayout());
//        Располагаю элементы на форме
        mFrame.add(sp, new GridBagConstraints(0, 0, 6, 5, 6, 5,
                GridBagConstraints.CENTER, 0, new Insets(1, 1, 1, 1), 0, 0));
        mFrame.add(addBtn, new GridBagConstraints(5, 5, 1, 1, 0, 0,
                GridBagConstraints.EAST, 0, new Insets(0, 0, 1, 1), 0, 0));
        mFrame.add(delBtn, new GridBagConstraints(4, 5, 1, 1, 0, 0,
                GridBagConstraints.WEST, 0, new Insets(0, 0, 1, 1), 0, 0));
        
        mFrame.setSize(600, 400);
        mFrame.setDefaultCloseOperation(3);
        mFrame.setVisible(true);
        mFrame.pack();
        
    }
    
    public static void main(String[] args) throws SQLException {
        init();
    }
}

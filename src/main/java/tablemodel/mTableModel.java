package tablemodel;

import dao.ConnectionDAO;
import entity.FileToTable;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.*;

public class mTableModel extends AbstractTableModel {
    
    private Vector<String> listHeader = new Vector<>();
    private Vector<FileToTable> allFiles;
    ConnectionDAO conn;
    
    public mTableModel() throws SQLException {
        
        ConnectionDAO conn = new ConnectionDAO();
    
        allFiles = conn.listFiles();
        
        listHeader.add(0, "Номер");
        listHeader.add(1, "Название");
        listHeader.add(2, "Расположение");
        listHeader.add(3, "Размер");
        }
    
    public int getRowCount() {
        return allFiles.size();
    }
    
    public int getColumnCount() {
        return listHeader.size();
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return listHeader.get(columnIndex);
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
    
        FileToTable fileToTable = allFiles.get(rowIndex);
    
        switch (columnIndex) {
            case 0:
                return fileToTable.getId();
            case 1:
                return fileToTable.getName();
            case 2:
                return fileToTable.getLocation();
            case 3:
                return fileToTable.getSize();
            default:
                return "";
        }
        
    }
    
    
}

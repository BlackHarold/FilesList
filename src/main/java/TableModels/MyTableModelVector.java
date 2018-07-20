package TableModels;

import DAO.TableData;

import javax.swing.table.AbstractTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class MyTableModelVector extends AbstractTableModel {
    
    private static Connection con;
    final String query = "SELECT * FROM APP.FILELIST";
    private Vector<String> vectorHeader = new Vector<String>();
    private Vector vectorData = new Vector();
    
    public MyTableModelVector(Connection con) throws SQLException {
        this.con = con;
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            int number = rs.getInt(1);
            String name = rs.getString(2);
            String location = rs.getString(3);
            Double size = rs.getDouble(4);
            vectorData.addElement(new TableData(number, name, location, size));
        }
        rs.close();
        stmt.close();
        
        for (Object each : vectorData) {
            System.out.println(each.toString());
        }
        
        vectorHeader.add(0, "Номер");
        vectorHeader.add(1, "Имя");
        vectorHeader.add(2, "Расположение");
        vectorHeader.add(3, "Размер");
    }
    
    @Override
    public int getRowCount() {
        return vectorData.size();
    }
    
    @Override
    public int getColumnCount() {
        return vectorHeader.size();
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return vectorHeader.get(columnIndex);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        vectorHeader.get(columnIndex);
        
//        System.out.println(vectorHeader.get(columnIndex));
        
        TableData tableData = (TableData) vectorData.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return tableData.number;
            case 1:
                return tableData.name;
            case 2:
                return tableData.location;
            case 3:
                return tableData.size;
            default:
                return "";
        }
    }
}

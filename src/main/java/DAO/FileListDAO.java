package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class FileListDAO {
    
    private static final String SELECT
            = "SELECT * FROM filelist";
    private static final String SELECT_ONE
            = "SELECT * FROM filelist WHERE number=?";
    private static final String INSERT
            = "INSERT INTO filelist (name, location, size) VALUES (?, ?, ?, ?)";
    private static final String UPDATE
            = "UPDATE filelist SET name=?, location=? size=? WHERE number=?";
    private static final String DELETE
            = "DELETE FROM filelist WHERE number=?";
    
    
    public String addContact(TableData td) {
        return null;
    }
    
    
    public void updateContact(TableData td) {
    }
    
    public void deleteContact(int number) {
    
    }
    
    
    public TableData getTableData(int number) {
        TableData td = null;
        return td;
    }
    
    public List<TableData> findTd() {
        List<TableData> td = new LinkedList<>();
        return td;
    }
    
    private TableData fillTd(ResultSet rs) throws SQLException {
        TableData td = new TableData();
        td.name = rs.getString("NAME");
        td.location = rs.getString("LOCATION");
        td.size = rs.getDouble("SIZE");
        return td;
    }
    
    
}

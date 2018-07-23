package dao;

import entity.FileToTable;

import java.sql.*;
import java.util.Vector;

public class ConnectionDAO {
    
    private String jdbcURL;
    private String jdcbUserName;
    private String jdbcPassword;
    private Connection jdbcConn;
    
    public ConnectionDAO() {
        this.jdbcURL = "jdbc:derby:G:/Java/Projects/JTableDerbyEmbedded/src/main/resources/db";
    }
    
    public ConnectionDAO(String jdbcURL) {
        this.jdbcURL = jdbcURL;
    }
    
    public ConnectionDAO(String jdbcURL, String jdbcUserName, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdcbUserName = jdbcUserName;
        this.jdbcPassword = jdbcPassword;
    }
    
    
    //    Методы соединения/отключения
    private void connect() {
        try {
            if (jdbcConn == null || jdbcConn.isClosed()) {
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                jdbcConn = DriverManager.getConnection(jdbcURL);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void disconnect() {
        try {
            if (jdbcConn != null && !jdbcConn.isClosed()) {
                jdbcConn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //    Вставка файла в базу
    public boolean insertFileToTable(FileToTable fileToTable) throws SQLException {
        
        String sql = "INSERT INTO DEMO.FILE_LIST (name, location, size) VALUES (?, ?, ?)";
        connect();
        PreparedStatement stmt = jdbcConn.prepareStatement(sql);
        stmt.setString(1, fileToTable.getName());
        stmt.setString(2, fileToTable.getLocation());
        stmt.setString(3, fileToTable.getSize());
        
        boolean rowInserted = stmt.executeUpdate() > 0;
        stmt.close();
        disconnect();
        
        return rowInserted;
    }
    
    //    Получаем все файлы из базы
    public Vector<FileToTable> listFiles() throws SQLException {
    
        Vector<FileToTable> listAllFiles = new Vector<>();
        
        String sql = "SELECT * FROM DEMO.FILE_LIST";
        
        connect();
        
        Statement stmt = jdbcConn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String location = rs.getString(3);
            String size = rs.getString(4);
            
            listAllFiles.addElement(new FileToTable(id, name, location, size));
        }
        
        rs.close();
        stmt.close();
        
        disconnect();
        
        return listAllFiles;
    }
    
    public boolean deleteFilefromTable(int id) throws SQLException {
        
        String sql = "DELETE FROM DEMO.FILE_LIST WHERE id = ?";
        
        connect();
        
        PreparedStatement stmt = jdbcConn.prepareStatement(sql);
        stmt.setInt(1, id);
        
        boolean rowDeleted = stmt.executeUpdate() > 0;
        stmt.close();
        disconnect();
        
        return rowDeleted;
    }
    
    public boolean updateFile(FileToTable fileToTable) throws SQLException {
        
        String sql = "UPDATE DEMO.FILE_LIST SET name=?, location=?,size=?"
        + " WHERE id = ?";
        
        connect();
    
        PreparedStatement stmt = jdbcConn.prepareStatement(sql);
        stmt.setString(1, fileToTable.getName());
        stmt.setString(2, fileToTable.getLocation());
        stmt.setString(3, fileToTable.getSize());
        stmt.setInt(4, fileToTable.getId());
        
        
        boolean rowUpdated = stmt.executeUpdate() > 0;
        stmt.close();
        disconnect();
        
        return rowUpdated;
    }
    
    public FileToTable getFile(int id) throws SQLException {
        FileToTable fileToTable = null;
        String sql = "SELECT * FROM DEMO.FILE_LIST WHERE id=?";
        
        connect();
    
        PreparedStatement stmt = jdbcConn.prepareStatement(sql);
        stmt.setInt(1, id);
    
        ResultSet rs = stmt.executeQuery();
    
        if (rs.next()) {
            String name = rs.getString("name");
            String location = rs.getString("location");
            String size = rs.getString("size");
    
            fileToTable = new FileToTable(id, name, location, size);
        }
        
        rs.close();
        stmt.close();
        
        return fileToTable;
    }
    
}

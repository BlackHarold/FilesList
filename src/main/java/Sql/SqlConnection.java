package Sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SqlConnection {
    
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String URL = "jdbc:derby:G:\\Java\\Development\\Derby_DB\\DB;";
    private static Connection con;
    
    public static Connection getConnection() {
        try {
            
            Class.forName(DRIVER)/*.newInstance()*/;
            if (con == null) {
                con = DriverManager.getConnection(URL);
            }
            
        } catch (ClassNotFoundException | SQLException /*| InstantiationException | IllegalAccessException*/ e) {
            Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, e);
        }
        return con;
    }
}

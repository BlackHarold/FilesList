import Archiv.VectorsForModel;
import Sql.SqlConnection;
import TableModels.MyTableModelVector;
import TableModels.MyTableRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.Connection;
import java.sql.SQLException;

public class Start {
    
    
    public static void main(String[] args) {
        
        try {
            Connection con = SqlConnection.getConnection();
            
            if (con != null) {
                System.out.println("Connection established!");
            }
            
            System.out.println("Обработка отбора");
            System.out.println("Построение модели");
            TableModel tbm = new MyTableModelVector(con);
            System.out.println("Передача данных в таблицу");
            JTable jTable = new JTable(tbm);
            
            jTable.setDefaultRenderer(Object.class, new MyTableRenderer());
            JScrollPane jScrollPane = new JScrollPane(
                    jTable,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            JFrame frame = new JFrame("Данные в JTable");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(jScrollPane);
            frame.pack();
            frame.setVisible(true);
            con.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

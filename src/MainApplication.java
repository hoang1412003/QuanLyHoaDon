import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.SwingUtilities;

import pesistence.MySQLConnection;
import presentation.Views.HoaDonView;

public class MainApplication {
    public static void main(String[] args) {
        Connection connection = null;
        try {
             String url = "jdbc:mysql://localhost:3306/hoadon";
            String username = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url, username, password);

            HoaDonView view = new HoaDonView();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new HoaDonView();
                }
            });
            

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            // Close the connection to the database
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    }
}
}
package pesistence;

import domain.HoaDon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class HoaDonDAO {
    protected Connection conn;

    public HoaDonDAO(Connection conn) {
        MySQLConnection  mySQLConnection = new MySQLConnection(conn);
        this.conn = mySQLConnection.getConnection();
    }

    public abstract void themHoaDon(HoaDon hoaDon);
    

    public abstract void suaHoaDon(HoaDon hoaDon);
    
    public abstract void xoaHoaDon(int maKH);
    
    public Connection getConn() {
        return conn;
    }
}

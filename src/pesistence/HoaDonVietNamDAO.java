package pesistence;

import domain.HoaDon;
import domain.HoaDonVietNam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HoaDonVietNamDAO extends HoaDonDAO {

    public HoaDonVietNamDAO(Connection conn) {
        super(conn);
    }

    public void themHoaDon(HoaDon hoaDon) {
        String sql = "INSERT INTO HoaDonNuocNgoai (hoaDonId, quocTich) VALUES (?, ?)";
        
    }

    public void suaHoaDon(HoaDon hoaDon){

    }

    public void xoaHoaDon(int maKH){
        
    }
}

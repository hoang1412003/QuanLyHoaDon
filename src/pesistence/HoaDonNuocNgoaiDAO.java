package pesistence;

import domain.HoaDon;
import domain.HoaDonNuocNgoai;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class HoaDonNuocNgoaiDAO implements HoaDonDAO {
    private HoaDonGateway hoaDonGateway;

    public HoaDonNuocNgoaiDAO(HoaDonGateway hoaDonGateway) {
        this.hoaDonGateway = hoaDonGateway;
    }

    public void themHoaDon(HoaDon hoaDon) {
        hoaDonGateway.themHoaDon(hoaDon);
    }

    public void suaHoaDon(HoaDon hoaDon) {
        hoaDonGateway.suaHoaDon(hoaDon);
    }

    public void xoaHoaDon(int maKH) {
        hoaDonGateway.xoaHoaDon(maKH);
    }

    public HoaDon getHoaDonByMaKH(int maKH) {
        return hoaDonGateway.getHoaDonByMaKH(maKH);
    }

    public List<HoaDon> getAllHoaDon() {
        return hoaDonGateway.getAllHoaDon();
    }
}

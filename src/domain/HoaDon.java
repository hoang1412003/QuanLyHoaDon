package domain;
import java.time.LocalDate;
public abstract class HoaDon {
    protected int maKH;
    protected String hoTenKH;
    protected LocalDate ngayRaHoaDon;
    protected double soLuong;
    protected double donGia;

    public HoaDon(int maKH, String hoTenKH, LocalDate ngayRaHoaDon, double soLuong, double donGia) {
        this.maKH = maKH;
        this.hoTenKH = hoTenKH;
        this.ngayRaHoaDon = ngayRaHoaDon;
        this.soLuong = soLuong;
        this.donGia = donGia;
    };

    public int getMaKH() {
        return maKH;
    }

    public String getHoTenKH() {
        return hoTenKH;
    }

    public LocalDate getNgayRaHoaDon() {
        return ngayRaHoaDon;
    }

    public double getSoLuong() {
        return soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public abstract double tinhThanhTien();
}

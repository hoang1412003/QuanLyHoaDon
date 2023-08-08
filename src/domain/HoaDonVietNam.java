package domain;

import java.time.LocalDate;


public class HoaDonVietNam extends HoaDon {
    private String loaiKhachHang;
    private double dinhMuc;
    private double thanhTien;

    
    public HoaDonVietNam(int maKH, String hoTenKH, LocalDate ngayRaHoaDon, double soLuong, double donGia, String loaiKhachHang, double dinhMuc) {
        super(maKH, hoTenKH, ngayRaHoaDon, soLuong, donGia);
        this.loaiKhachHang = loaiKhachHang;
        this.dinhMuc = dinhMuc;
    }

    public String getLoaiKhachHang() {
        return loaiKhachHang;
    }
    public double getDinhMuc() {
        return dinhMuc;
    }
    public double tinhThanhTien() {
        double thanhTien;
        if (super.soLuong <= dinhMuc) {
            thanhTien = super.soLuong * super.donGia;
        } else {
            thanhTien = super.soLuong * super.donGia * dinhMuc + (super.soLuong - dinhMuc) * super.donGia * 2.5;
        }
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

}

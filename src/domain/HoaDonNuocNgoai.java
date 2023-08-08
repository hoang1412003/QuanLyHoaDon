package domain;

import java.time.LocalDate;

public class HoaDonNuocNgoai extends HoaDon{
    private String quocTich;
    private double thanhTien;

    public HoaDonNuocNgoai(int maKH, String hoTenKH, LocalDate ngayRaHoaDon, double soLuong, double donGia, String quocTich) {
        super(maKH, hoTenKH, ngayRaHoaDon, soLuong, donGia);
        this.quocTich = quocTich;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public double tinhThanhTien(){
        double thanhTien;
        thanhTien = super.soLuong * super.donGia;
        return  thanhTien;
    }
    
     public void setThanhTien(double thanhTien) {
         this.thanhTien = thanhTien;
     }
}

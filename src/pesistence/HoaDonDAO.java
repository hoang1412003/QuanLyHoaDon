package pesistence;

import domain.HoaDon;

import java.util.List;

public interface HoaDonDAO {

    void themHoaDon(HoaDon hoaDon);
    
    void suaHoaDon(HoaDon hoaDon);
    
    void xoaHoaDon(int maKH);
    
    HoaDon getHoaDonByMaKH(int maKH);
    
    List<HoaDon> getAllHoaDon();
}

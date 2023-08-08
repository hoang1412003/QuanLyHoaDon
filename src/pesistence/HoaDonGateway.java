package pesistence;

import java.util.List;

import domain.HoaDon;

public interface HoaDonGateway {
    void themHoaDon(HoaDon hoaDon);
    
    void suaHoaDon(HoaDon hoaDon);
    
    void xoaHoaDon(int maKH);
    
    HoaDon getHoaDonByMaKH(int maKH);
    
    List<HoaDon> getAllHoaDon();
}

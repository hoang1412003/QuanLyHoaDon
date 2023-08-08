package domain;

import java.util.List;

public interface HoaDonService {
    void themHoaDon(HoaDon hoaDon);
    void suaHoaDon(HoaDon hoaDon);
    void xoaHoaDon(int maKH);
    HoaDon getHoaDonByMaKH(int maKH);
    List<HoaDon> getAllHoaDon();
}

package domain;

import java.util.List;

import pesistence.HoaDonDAO;
import pesistence.HoaDonVNJdbcGateway;
import pesistence.HoaDonVietNamDAO;

public class HoaDonVNService implements HoaDonService {
    private HoaDonDAO hoaDonDAO;
    public HoaDonVNService (){
        hoaDonDAO = new HoaDonVietNamDAO(new HoaDonVNJdbcGateway());
    }
    @Override
    public void themHoaDon(HoaDon hoaDon) {
        hoaDonDAO.themHoaDon(hoaDon);
    };
    @Override
    public void suaHoaDon(HoaDon hoaDon) {
        hoaDonDAO.suaHoaDon(hoaDon);
    };
    @Override
    public void xoaHoaDon(int maKH) {
        hoaDonDAO.xoaHoaDon(maKH);
    };
    @Override
    public HoaDon getHoaDonByMaKH(int maKH) {
        return hoaDonDAO.getHoaDonByMaKH(maKH);
    };
    @Override
    public List<HoaDon> getAllHoaDon(){
        return hoaDonDAO.getAllHoaDon();
    };
}

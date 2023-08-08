package pesistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.HoaDon;
import domain.HoaDonVietNam;

public class HoaDonVNJdbcGateway implements HoaDonGateway {
    private Connection connection;
    // String url = "jdbc:mysql://localhost:3306/hoadon";
    //         String username = "root";
    //         String password = "123456";
    //         connection = DriverManager.getConnection(url, username, password);
    public HoaDonVNJdbcGateway() {
        // Initialize the database connection here (replace dbUrl, username, and password with your SQL Server credentials)
        String url = "jdbc:mysql://localhost:3306/hoadon";
        String username = "root";
        String password = "123456";
        
        try {
           connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // PreparedStatement statement = connection.prepareStatement(sql)
    public void themHoaDon(HoaDon hoaDon) {
        String sql = "INSERT INTO hoadonvietnam (maKH, hoTen, ngayRaHD, soLuong, donGia, doiTuong, dinhMuc, thanhTien) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
        preparedStatement.setInt(1, hoaDon.getMaKH());
        preparedStatement.setString(2, hoaDon.getHoTenKH());
        preparedStatement.setDate(3, java.sql.Date.valueOf(hoaDon.getNgayRaHoaDon()));
        preparedStatement.setDouble(4, hoaDon.getSoLuong());
        preparedStatement.setDouble(5, hoaDon.getDonGia());
        preparedStatement.setString(6, ((HoaDonVietNam) hoaDon).getLoaiKhachHang());
        preparedStatement.setDouble(7, ((HoaDonVietNam) hoaDon).getDinhMuc());
        preparedStatement.setDouble(8, hoaDon.tinhThanhTien());
        preparedStatement.executeUpdate();
        System.out.println("Thêm dữ liệu thành công!");
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Lỗi khi thêm dữ liệu vào cơ sở dữ liệu");
    }
    };
    
    public void suaHoaDon(HoaDon hoaDon) {
        String sql = "UPDATE hoadonvietnam SET hoTen = ?, ngayRaHD = ?, soLuong = ?, donGia = ?, doiTuong = ?, dinhMuc = ?, thanhTien = ? WHERE maKH = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, hoaDon.getHoTenKH());
            preparedStatement.setDate(2, java.sql.Date.valueOf(hoaDon.getNgayRaHoaDon()));
            preparedStatement.setDouble(3, hoaDon.getSoLuong());
            preparedStatement.setDouble(4, hoaDon.getDonGia());
    
            if (hoaDon instanceof HoaDonVietNam) {
                HoaDonVietNam hoaDonVN = (HoaDonVietNam) hoaDon;
                preparedStatement.setString(5, hoaDonVN.getLoaiKhachHang());
                preparedStatement.setDouble(6, hoaDonVN.getDinhMuc());
                preparedStatement.setDouble(7, hoaDon.tinhThanhTien()); 
            } else {
                preparedStatement.setNull(5, java.sql.Types.VARCHAR);
                preparedStatement.setNull(6, java.sql.Types.DOUBLE);
                preparedStatement.setNull(7, java.sql.Types.DOUBLE);
            }
    
            preparedStatement.setInt(8, hoaDon.getMaKH());
    
            preparedStatement.executeUpdate();
            System.out.println("Cập nhật dữ liệu thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi cập nhật dữ liệu vào cơ sở dữ liệu");
        }
    }
    

    

    
    public void xoaHoaDon(int maKH) {
        String sql = "DELETE FROM hoadonvietnam WHERE maKH = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, maKH);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public HoaDon getHoaDonByMaKH(int maKH) {
        String query = "SELECT * FROM hoadonvietnam WHERE maKH = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, maKH);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String hoTen = resultSet.getString("hoTen");
                    java.sql.Date ngayRaHD = resultSet.getDate("ngayRaHD");
                    double soLuong = resultSet.getDouble("soLuong");
                    double donGia = resultSet.getDouble("donGia");
                    String loaiKhachHang = resultSet.getString("doiTuong");
                    double dinhMuc = resultSet.getDouble("dinhMuc");
                    double thanhTien = resultSet.getDouble("thanhTien");
    
                    HoaDonVietNam hoaDonVN = new HoaDonVietNam(maKH, hoTen, ngayRaHD.toLocalDate(), soLuong, donGia, loaiKhachHang, dinhMuc);
                    hoaDonVN.setThanhTien(thanhTien);
    
                    return hoaDonVN;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi truy vấn dữ liệu từ cơ sở dữ liệu");
        }
        return null; // Trả về null nếu không tìm thấy hoặc xảy ra lỗi
    }
    
    
    public List<HoaDon> getAllHoaDon() {
        List<HoaDon> hoaDons = new ArrayList<>();
        String query = "SELECT * FROM hoadonvietnam";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int maKH = resultSet.getInt("maKH");
                String hoTen = resultSet.getString("hoTen");
                java.sql.Date ngayRaHD = resultSet.getDate("ngayRaHD");
                double soLuong = resultSet.getDouble("soLuong");
                double donGia = resultSet.getDouble("donGia");
                String loaiKhachHang = resultSet.getString("doiTuong");
                double dinhMuc = resultSet.getDouble("dinhMuc");
                double thanhTien = resultSet.getDouble("thanhTien");
                
                HoaDonVietNam hoaDonVN = new HoaDonVietNam(maKH, hoTen, ngayRaHD.toLocalDate(), soLuong, donGia, loaiKhachHang, dinhMuc);
                hoaDonVN.setThanhTien(thanhTien);
                
                hoaDons.add(hoaDonVN);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi truy vấn dữ liệu từ cơ sở dữ liệu");
        }
        return hoaDons;
    }
}

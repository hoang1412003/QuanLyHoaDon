package presentation.Controller;

import pesistence.HoaDonDAO;
import presentation.Views.HoaDonManagementView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import domain.HoaDon;
import domain.HoaDonService;
import domain.HoaDonVNService;
import domain.HoaDonVietNam;

public class HoaDonVNController {
    private HoaDonManagementView view;
    private HoaDon hoaDon;
    private HoaDonService hoaDonService;

    public HoaDonVNController(){

    };
   public HoaDonVNController(HoaDonManagementView view) {
    this.view = view;
    initControllerVN();
   }

   public void initControllerVN() {
    this.view.getThemButtonVN().addActionListener(e -> themHoaDonVN());
    this.view.getSuaButtonVN().addActionListener(e -> suaHoaDonVN());
    this.view.getXoaButtonVN().addActionListener(e -> xoaHoaDonVN());
    this.view.getTimKiemButtonVN().addActionListener(e -> timKiemHoaDonVN());
    this.view.getTinhTongButtonVN().addActionListener(e -> tinhTongHoaDonVN());
    this.view.getXuatThangButtonVN().addActionListener(e -> xuatThangHoaDonVN());
   }

   public void themHoaDonVN() {
    hoaDonService = new HoaDonVNService();
    
    // Kiểm tra ràng buộc cho các trường dữ liệu đầu vào
    String maKHText = view.getMaKHTFVN().getText();
    String hoTenKH = view.getHoTenKHTFVN().getText();
    String ngayRaHD = view.getNgayRaHDVN().getText();
    String soLuongText = view.getSoLuongTFVN().getText();
    String donGiaText = view.getDonGiaTFVN().getText();
    String dinhMucText = view.getDinhMucTFVN().getText();
    
    if (maKHText.isEmpty() || hoTenKH.isEmpty() || ngayRaHD.isEmpty() ||
        soLuongText.isEmpty() || donGiaText.isEmpty() || dinhMucText.isEmpty()) {
        JOptionPane.showMessageDialog(
            view.getFrame(),
            "Vui lòng điền đầy đủ thông tin hóa đơn.",
            "Lỗi",
            JOptionPane.ERROR_MESSAGE
        );
        return;
    }
    
    try {
        int maKHVN = Integer.parseInt(maKHText);
        
        // Kiểm tra xem mã khách hàng đã tồn tại chưa
        if (hoaDonService.getHoaDonByMaKH(maKHVN) != null) {
            JOptionPane.showMessageDialog(
                view.getFrame(),
                "Mã khách hàng đã tồn tại. Vui lòng chọn mã khách hàng khác.",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        LocalDate ngayGdVN = LocalDate.parse(ngayRaHD, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String doiTuongKhachHangVN = view.getDoiTuongKhachHangVN();
        double soLuongVN = Double.parseDouble(soLuongText);
        double donGiaVN = Double.parseDouble(donGiaText);
        double dinhMucVN = Double.parseDouble(dinhMucText);
        
        // Thêm hóa đơn sau khi kiểm tra và chuyển đổi dữ liệu thành công
        hoaDon = new HoaDonVietNam(maKHVN, hoTenKH, ngayGdVN, soLuongVN, donGiaVN, doiTuongKhachHangVN, dinhMucVN);
        hoaDonService.themHoaDon(hoaDon);
        
        this.view.clearFields(); // Xóa dữ liệu trường nhập sau khi thêm thành công
        this.loadHoaDonVNList(); // Tải lại danh sách hóa đơn
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(
            view.getFrame(),
            "Mã khách hàng, số lượng, đơn giá, và định mức phải là số.",
            "Lỗi",
            JOptionPane.ERROR_MESSAGE
        );
    } catch (DateTimeParseException e) {
        JOptionPane.showMessageDialog(
            view.getFrame(),
            "Ngày ra hóa đơn không hợp lệ. Vui lòng nhập theo định dạng dd/MM/yyyy.",
            "Lỗi",
            JOptionPane.ERROR_MESSAGE
        );
    }
}



public void suaHoaDonVN() {
    String maKHText = view.getMaKHTFVN().getText();
    String hoTenKH = view.getHoTenKHTFVN().getText();
    String ngayRaHD = view.getNgayRaHDVN().getText();
    String doiTuongKhachHang = (String) view.getDoiTuongKhachHangCBVN().getSelectedItem();
    String soLuongText = view.getSoLuongTFVN().getText();
    String donGiaText = view.getDonGiaTFVN().getText();
    String dinhMucText = view.getDinhMucTFVN().getText();

    if (maKHText.isEmpty() || hoTenKH.isEmpty() || ngayRaHD.isEmpty() ||
        doiTuongKhachHang.isEmpty() || soLuongText.isEmpty() || 
        donGiaText.isEmpty() || dinhMucText.isEmpty()) {
        JOptionPane.showMessageDialog(
            view.getFrame(),
            "Vui lòng điền đầy đủ thông tin hóa đơn.",
            "Lỗi",
            JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    try {
        int maKHVN = Integer.parseInt(maKHText);

        // Kiểm tra xem mã khách hàng đã tồn tại chưa (trừ trường hợp cập nhật cùng mã)
        HoaDon hoaDonByMaKH = hoaDonService.getHoaDonByMaKH(maKHVN);
        if (hoaDonByMaKH != null && hoaDonByMaKH instanceof HoaDonVietNam && hoaDonByMaKH.getMaKH() != maKHVN) {
            JOptionPane.showMessageDialog(
                view.getFrame(),
                "Mã khách hàng đã tồn tại. Vui lòng chọn mã khách hàng khác.",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        LocalDate ngayGdVN = LocalDate.parse(ngayRaHD, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        double soLuongVN = Double.parseDouble(soLuongText);
        double donGiaVN = Double.parseDouble(donGiaText);
        double dinhMucVN = Double.parseDouble(dinhMucText);
        HoaDon hoaDonVN = new HoaDonVietNam(maKHVN, hoTenKH, ngayGdVN, soLuongVN, donGiaVN, doiTuongKhachHang, dinhMucVN);
        hoaDonService.suaHoaDon(hoaDonVN);

        loadHoaDonVNList();
        view.clearFields();
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(
            view.getFrame(),
            "Mã khách hàng, số lượng, đơn giá, và định mức phải là số.",
            "Lỗi",
            JOptionPane.ERROR_MESSAGE
        );
    } catch (DateTimeParseException e) {
        JOptionPane.showMessageDialog(
            view.getFrame(),
            "Ngày ra hóa đơn không hợp lệ. Vui lòng nhập theo định dạng dd/MM/yyyy.",
            "Lỗi",
            JOptionPane.ERROR_MESSAGE
        );
    }
}


public void xoaHoaDonVN() {
    String maKHTxt = this.view.showInputDialogForMaKH();
    if (maKHTxt != null && !maKHTxt.isEmpty()) {
        try {
            int maKH = Integer.parseInt(maKHTxt);
            
            // Kiểm tra xem mã khách hàng đã tồn tại trong bảng và có phải là hóa đơn Việt Nam hay không
            HoaDon hoaDonByMaKH = hoaDonService.getHoaDonByMaKH(maKH);
            if (hoaDonByMaKH == null || !(hoaDonByMaKH instanceof HoaDonVietNam)) {
                this.view.showInvalidMaKHErrorMessage();
                return;
            }
            
            hoaDonService.xoaHoaDon(maKH);
            this.view.showXoaHoaDonSuccessMessage();
            loadHoaDonVNList();
        } catch (NumberFormatException e) {
            this.view.showInvalidMaKHErrorMessage();
        }
    }
}

public void timKiemHoaDonVN() {
    String maKH = view.showInputTimKiem();
    if (maKH != null && !maKH.isEmpty()) {
        HoaDon hoaDon = hoaDonService.getHoaDonByMaKH(Integer.parseInt(maKH));
        if (hoaDon != null && hoaDon instanceof HoaDonVietNam) {
            HoaDonVietNam hoaDonVN = (HoaDonVietNam) hoaDon;

            // Tạo một hàng mới để thêm vào bảng dữ liệu
            Object[] row = new Object[]{
                hoaDonVN.getMaKH(),
                hoaDonVN.getHoTenKH(),
                hoaDonVN.getNgayRaHoaDon().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                hoaDonVN.getLoaiKhachHang(),
                hoaDonVN.getSoLuong(),
                hoaDonVN.getDonGia(),
                hoaDonVN.getDinhMuc(),
                hoaDonVN.tinhThanhTien()
            };

            // Thêm hàng mới vào bảng dữ liệu
            DefaultTableModel model = view.getVietNamTableModel();
            model.setRowCount(0); // Xóa dữ liệu cũ trước khi thêm dòng mới
            model.addRow(row);

            // Hiển thị thông tin kết quả tìm kiếm lên giao diện
            view.getMaKHTFVN().setText(Integer.toString(hoaDonVN.getMaKH()));
            view.getHoTenKHTFVN().setText(hoaDonVN.getHoTenKH());
            view.getNgayRaHDVN().setText(hoaDonVN.getNgayRaHoaDon().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            view.getDoiTuongKhachHangCBVN().setSelectedItem(hoaDonVN.getLoaiKhachHang());
            view.getSoLuongTFVN().setText(Double.toString(hoaDonVN.getSoLuong()));
            view.getDonGiaTFVN().setText(Double.toString(hoaDonVN.getDonGia()));
            view.getDinhMucTFVN().setText(Double.toString(hoaDonVN.getDinhMuc()));

            // Ẩn các thành phần không liên quan đến tìm kiếm
            view.getThemButtonVN().setVisible(false);
            view.getSuaButtonVN().setVisible(false);
            view.getXoaButtonVN().setVisible(false);
            view.getTinhTongButtonVN().setVisible(false);
            view.getXuatThangButtonVN().setVisible(false);
        } else {
            JOptionPane.showMessageDialog(
                view.getFrame(),
                "Không tìm thấy hóa đơn Việt Nam với mã khách hàng " + maKH,
                "Không tìm thấy",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}

   public void tinhTongHoaDonVN() {
    System.out.println("checkingdddddddddddddddddddddddddddddd");
   }
   public void xuatThangHoaDonVN() {
    System.out.println("checkingdddddddddddddddddddddddddddddd");
   }
   
   public void loadHoaDonVNList() {
    hoaDonService = new HoaDonVNService();
    List<HoaDon> hoaDonList = hoaDonService.getAllHoaDon(); // Lấy danh sách tất cả hóa đơn
    DefaultTableModel vietNamTableModel = this.view.getVietNamTableModel();
    vietNamTableModel.setRowCount(0); // Xóa dữ liệu trước đó
    
    DateTimeFormatter dateFormatterDB = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter dateFormatterUI = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    for (HoaDon hoaDon : hoaDonList) {
        if (hoaDon instanceof HoaDonVietNam) {
            HoaDonVietNam hoaDonVN = (HoaDonVietNam) hoaDon;
            LocalDate ngayRaHD = hoaDonVN.getNgayRaHoaDon(); // Đối tượng LocalDate
            String ngayRaHDFormatted = ngayRaHD.format(dateFormatterUI); // Định dạng thành chuỗi
            
            Object[] rowData = {
                hoaDonVN.getMaKH(),
                hoaDonVN.getHoTenKH(),
                ngayRaHDFormatted, // Chuỗi đã định dạng theo "dd/MM/yyyy"
                hoaDonVN.getLoaiKhachHang(),
                hoaDonVN.getSoLuong(),
                hoaDonVN.getDonGia(),
                hoaDonVN.getDinhMuc(),
                hoaDonVN.tinhThanhTien()
            };
            vietNamTableModel.addRow(rowData);
        }
    }
}
}

    
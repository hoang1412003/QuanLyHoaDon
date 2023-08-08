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
import domain.HoaDonNuocNgoai;
import domain.HoaDonNuocNgoaiService;

public class HoaDonNNController {
    private HoaDonManagementView view;
    private HoaDon hoaDon;
    private HoaDonService hoaDonService;

    public HoaDonNNController(){

    };

    public HoaDonNNController(HoaDonManagementView view) {
        this.view = view;
        initControllerNN();
    }

    public void initControllerNN() {
        this.view.getThemButtonNN().addActionListener(e -> themHoaDonNN());
        this.view.getSuaButtonNN().addActionListener(e -> suaHoaDonNN());
        this.view.getXoaButtonNN().addActionListener(e -> xoaHoaDonNN());
        this.view.getTimKiemButtonNN().addActionListener(e -> timKiemHoaDonNN());
        this.view.getTinhTongButtonNN().addActionListener(e -> tinhTongHoaDonNN());
        this.view.getXuatThangButtonNN().addActionListener(e -> xuatThangHoaDonNN());
    }

    public void themHoaDonNN() {
    hoaDonService = new HoaDonNuocNgoaiService();
    
    // Kiểm tra ràng buộc cho các trường dữ liệu đầu vào
    String maKHText = view.getMaKHTFNN().getText();
    String hoTenKH = view.getHoTenKHTFNN().getText();
    String ngayRaHD = view.getNgayRaHDNN().getText();
    String quocTich = view.getQuocTichTF().getText();
    String soLuongText = view.getSoLuongTF().getText();
    String donGiaText = view.getDonGiaTF().getText();
    
    if (maKHText.isEmpty() || hoTenKH.isEmpty() || ngayRaHD.isEmpty() ||
        quocTich.isEmpty() || soLuongText.isEmpty() || donGiaText.isEmpty()) {
        JOptionPane.showMessageDialog(
            view.getFrame(),
            "Vui lòng điền đầy đủ thông tin hóa đơn.",
            "Lỗi",
            JOptionPane.ERROR_MESSAGE
        );
        return;
    }
    
    try {
        int maKHNN = Integer.parseInt(maKHText);
        
        // Kiểm tra xem mã khách hàng đã tồn tại chưa
        if (hoaDonService.getHoaDonByMaKH(maKHNN) != null) {
            JOptionPane.showMessageDialog(
                view.getFrame(),
                "Mã khách hàng đã tồn tại. Vui lòng chọn mã khách hàng khác.",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        LocalDate ngayGdNN = LocalDate.parse(ngayRaHD, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        double soLuongNN = Double.parseDouble(soLuongText);
        double donGiaNN = Double.parseDouble(donGiaText);
        
        // Thêm hóa đơn sau khi kiểm tra và chuyển đổi dữ liệu thành công
        hoaDon = new HoaDonNuocNgoai(maKHNN, hoTenKH, ngayGdNN, soLuongNN, donGiaNN, quocTich);
        hoaDonService.themHoaDon(hoaDon);
        
        this.view.clearFieldsNN(); // Xóa dữ liệu trường nhập sau khi thêm thành công
        this.loadHoaDonNNList(); // Tải lại danh sách hóa đơn
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(
            view.getFrame(),
            "Mã khách hàng, số lượng, và đơn giá phải là số.",
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


public void suaHoaDonNN() {
    String maKHText = view.getMaKHTFNN().getText();
    String hoTenKH = view.getHoTenKHTFNN().getText();
    String ngayRaHD = view.getNgayRaHDNN().getText();
    String quocTich = view.getQuocTichTF().getText();
    String soLuongText = view.getSoLuongTF().getText();
    String donGiaText = view.getDonGiaTF().getText();

    if (maKHText.isEmpty() || hoTenKH.isEmpty() || ngayRaHD.isEmpty() ||
        quocTich.isEmpty() || soLuongText.isEmpty() || donGiaText.isEmpty()) {
        JOptionPane.showMessageDialog(
            view.getFrame(),
            "Vui lòng điền đầy đủ thông tin hóa đơn.",
            "Lỗi",
            JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    try {
        int maKHNN = Integer.parseInt(maKHText);

        // Kiểm tra xem mã khách hàng đã tồn tại chưa (trừ trường hợp cập nhật cùng mã)
        HoaDon hoaDonByMaKH = hoaDonService.getHoaDonByMaKH(maKHNN);
        if (hoaDonByMaKH != null && !(hoaDonByMaKH instanceof HoaDonNuocNgoai)) {
            JOptionPane.showMessageDialog(
                view.getFrame(),
                "Mã khách hàng đã tồn tại. Vui lòng chọn mã khách hàng khác.",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        LocalDate ngayGdNN = LocalDate.parse(ngayRaHD, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        double soLuongNN = Double.parseDouble(soLuongText);
        double donGiaNN = Double.parseDouble(donGiaText);

        HoaDon hoaDonNN = new HoaDonNuocNgoai(maKHNN, hoTenKH, ngayGdNN, soLuongNN, donGiaNN, quocTich);
        hoaDonService.suaHoaDon(hoaDonNN);

        loadHoaDonNNList();
        view.clearFieldsNN();
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(
            view.getFrame(),
            "Mã khách hàng, số lượng, và đơn giá phải là số.",
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


public void xoaHoaDonNN() {
    String maKHTxt = this.view.showInputDialogForMaKH();
    if (maKHTxt != null && !maKHTxt.isEmpty()) {
        try {
            int maKH = Integer.parseInt(maKHTxt);
            
            // Kiểm tra xem mã khách hàng đã tồn tại trong bảng chưa
            HoaDon hoaDonByMaKH = hoaDonService.getHoaDonByMaKH(maKH);
            if (hoaDonByMaKH == null || !(hoaDonByMaKH instanceof HoaDonNuocNgoai)) {
                this.view.showInvalidMaKHErrorMessage();
                return;
            }
            
            hoaDonService.xoaHoaDon(maKH);
            this.view.showXoaHoaDonSuccessMessage();
            loadHoaDonNNList();
        } catch (NumberFormatException e) {
            this.view.showInvalidMaKHErrorMessage();
        }
    }
}


    public void timKiemHoaDonNN() {
        String maKH = view.showInputTimKiem();
        if (maKH != null && !maKH.isEmpty()) {
            HoaDon hoaDon = hoaDonService.getHoaDonByMaKH(Integer.parseInt(maKH));
            if (hoaDon != null && hoaDon instanceof HoaDonNuocNgoai) {
                HoaDonNuocNgoai hoaDonNN = (HoaDonNuocNgoai) hoaDon;

                // Tạo một hàng mới để thêm vào bảng dữ liệu
                Object[] row = new Object[]{
                    hoaDonNN.getMaKH(),
                    hoaDonNN.getHoTenKH(),
                    hoaDonNN.getNgayRaHoaDon().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    hoaDonNN.getQuocTich(),
                    hoaDonNN.getSoLuong(),
                    hoaDonNN.getDonGia(),
                    hoaDonNN.tinhThanhTien()
                };

                // Thêm hàng mới vào bảng dữ liệu
                DefaultTableModel model = view.getNuocNgoaiTableModel();
                model.setRowCount(0); // Xóa dữ liệu cũ trước khi thêm dòng mới
                model.addRow(row);

                // Hiển thị thông tin kết quả tìm kiếm lên giao diện
                view.getMaKHTFNN().setText(Integer.toString(hoaDonNN.getMaKH()));
                view.getHoTenKHTFNN().setText(hoaDonNN.getHoTenKH());
                view.getNgayRaHDNN().setText(hoaDonNN.getNgayRaHoaDon().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                view.getQuocTichTF().setText(hoaDonNN.getQuocTich());
                view.getSoLuongTF().setText(Double.toString(hoaDonNN.getSoLuong()));
                view.getDonGiaTF().setText(Double.toString(hoaDonNN.getDonGia()));

                // Ẩn các thành phần không liên quan đến tìm kiếm
                view.getThemButtonNN().setVisible(false);
                view.getSuaButtonNN().setVisible(false);
                view.getXoaButtonNN().setVisible(false);
                view.getTinhTongButtonNN().setVisible(false);
                view.getXuatThangButtonNN().setVisible(false);
            } else {
                JOptionPane.showMessageDialog(
                    view.getFrame(),
                    "Không tìm thấy hóa đơn nước ngoài với mã khách hàng " + maKH,
                    "Không tìm thấy",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }

    public void tinhTongHoaDonNN() {
        System.out.println("checking nước ngoài");
    }

    public void xuatThangHoaDonNN() {
        System.out.println("checking nước ngoài");
    }

    public void loadHoaDonNNList() {
        hoaDonService = new HoaDonNuocNgoaiService();
        List<HoaDon> hoaDonList = hoaDonService.getAllHoaDon();
        DefaultTableModel nuocNgoaiTableModel = this.view.getNuocNgoaiTableModel();
        nuocNgoaiTableModel.setRowCount(0);

        DateTimeFormatter dateFormatterDB = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateFormatterUI = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (HoaDon hoaDon : hoaDonList) {
            if (hoaDon instanceof HoaDonNuocNgoai) {
                HoaDonNuocNgoai hoaDonNN = (HoaDonNuocNgoai) hoaDon;
                LocalDate ngayRaHD = hoaDonNN.getNgayRaHoaDon();
                String ngayRaHDFormatted = ngayRaHD.format(dateFormatterUI);

                Object[] rowData = {
                    hoaDonNN.getMaKH(),
                    hoaDonNN.getHoTenKH(),
                    ngayRaHDFormatted,
                    hoaDonNN.getQuocTich(),
                    hoaDonNN.getSoLuong(),
                    hoaDonNN.getDonGia(),
                    hoaDonNN.tinhThanhTien()
                };
                nuocNgoaiTableModel.addRow(rowData);
            }
        }
    }
}

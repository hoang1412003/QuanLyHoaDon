// package presentation.Controller;

// import pesistence.HoaDonDAO;
// import presentation.Views.HoaDonVietNamView;
// import presentation.Views.HoaDonNuocNgoaiView;

// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// public class HoaDonController {
//     private HoaDonDAO hoaDonDAO;
//     private HoaDonVietNamView hoaDonVietNamView;
//     private HoaDonNuocNgoaiView hoaDonNuocNgoaiView;

//     public HoaDonController(HoaDonDAO hoaDonDAO, HoaDonVietNamView hoaDonVietNamView, HoaDonNuocNgoaiView hoaDonNuocNgoaiView) {
//         this.hoaDonDAO = hoaDonDAO;
//         this.hoaDonVietNamView = hoaDonVietNamView;
//         this.hoaDonNuocNgoaiView = hoaDonNuocNgoaiView;

//         hoaDonVietNamView.addThemListener(new ThemListener());
//         hoaDonVietNamView.addSuaListener(new SuaListener());
//         hoaDonVietNamView.addXoaListener(new XoaListener());

//         hoaDonNuocNgoaiView.addThemListener(new ThemListener());
//         hoaDonNuocNgoaiView.addSuaListener(new SuaListener());
//         hoaDonNuocNgoaiView.addXoaListener(new XoaListener());
//     }

//     // ...

//     class ThemListener implements ActionListener {
//         @Override
//         public void actionPerformed(ActionEvent e) {
//             // Thực hiện xử lý thêm hóa đơn
//             String hoTen = hoaDonVietNamView.getHoTen();
//             // Lấy các thông tin khác từ giao diện
//             // Khởi tạo HoaDon hoặc HoaDonVietNam hoặc HoaDonNuocNgoai

//             hoaDonDAO.themHoaDon(hoaDon); // Gọi phương thức thêm từ DAO
//         }
//     }

//     class SuaListener implements ActionListener {
//         @Override
//         public void actionPerformed(ActionEvent e) {
//             // Thực hiện xử lý sửa hóa đơn
//             int maKH = hoaDonVietNamView.getMaKH();
//             // Lấy các thông tin khác từ giao diện
//             // Gọi phương thức sửa từ DAO
//         }
//     }

//     class XoaListener implements ActionListener {
//         @Override
//         public void actionPerformed(ActionEvent e) {
//             // Thực hiện xử lý xóa hóa đơn
//             int maKH = hoaDonVietNamView.getMaKH();
//             // Gọi phương thức xóa từ DAO
//         }
//     }
// }

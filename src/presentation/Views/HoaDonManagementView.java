package presentation.Views;

import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import domain.HoaDonService;
import domain.HoaDonVNService;
import presentation.Controller.HoaDonVNController;

public class HoaDonManagementView {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private DefaultTableModel vietNamTableModel;
    
    private HoaDonVNController hoaDonController;
    

    
    
    // viet nam
    
    private JPanel inputPanelVN;
    private JLabel titleLabelVN;
    private JTextField maKHTFVN;
    private JTextField hoTenKHTFVN;
    private JTextField ngayRaHDVN;
    private JTextField soLuongTFVN;
    private JTextField donGiaTFVN;
    private JTextField dinhMucTFVN;
    private JComboBox<String> doiTuongKhachHangCBVN;
    

    private JButton themButtonVN;
    private JButton suaButtonVN;
    private JButton xoaButtonVN;
    private JButton tinhTongButtonVN;
    private JButton xuatThangButtonVN;
    private JButton timKiemButtonVN;
    
    //Nước ngoài
    private JPanel inputPanelNN;
     private JLabel titleLabelNN;
    private JTextField maKHTFNN;
    private JTextField hoTenKHTFNN;
    private JTextField ngayRaHDNN;
    private JTextField quocTichTF;
    private JTextField soLuongTF;
    private JTextField donGiaTF;
    private JButton themButtonNN;
    private JButton suaButtonNN;
    private JButton xoaButtonNN;
    private JButton tinhTongButtonNN;
    private JButton xuatThangButtonNN;
    private JButton timKiemButtonNN;
    private JButton tinhTrungBinhButtonNN;
    private DefaultTableModel nuocNgoaiTableModel;




    public HoaDonManagementView() {

        frame = new JFrame("Quản lý danh sách hoá đơn tiền điện");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createSelectPanel(), "SelectPanel");
        mainPanel.add(createHoaDonVietNamPanel(), "HoaDonVietNam");
        mainPanel.add(createHoaDonNuocNgoaiPanel(), "HoaDonNuocNgoai");
        
        frame.add(mainPanel);
    }


    public void display() {
        frame.setVisible(true);
        frame.pack();
    }

    private JPanel createSelectPanel() {
        
        JPanel panel = new JPanel(new FlowLayout());

        JButton hoaDonVietNamButton = new JButton("Hóa đơn Việt Nam");
        JButton hoaDonNuocNgoaiButton = new JButton("Hóa đơn Nước ngoài");

        hoaDonVietNamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "HoaDonVietNam");
            }
        });

        hoaDonNuocNgoaiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "HoaDonNuocNgoai");
            }
        });

        panel.add(hoaDonVietNamButton);
        panel.add(hoaDonNuocNgoaiButton);

        JPanel buttonPanel = new JPanel(); 
        

        // Xử lý sự kiện nút Trở về

        return panel;
    }

    private JPanel createHoaDonVietNamPanel() {

        
        JPanel panel = new JPanel(new BorderLayout());

        inputPanelVN = new JPanel(new GridLayout(9, 2));
        
        titleLabelVN = new JLabel("Hóa đơn Việt Nam");
        maKHTFVN = new JTextField(20);
        hoTenKHTFVN = new JTextField(20);
        ngayRaHDVN = new JTextField(20);
        soLuongTFVN = new JTextField(20);
        donGiaTFVN = new JTextField(20);
        dinhMucTFVN = new JTextField(20);
        doiTuongKhachHangCBVN = new JComboBox<>(new String[]{"Sinh hoạt", "Kinh doanh", "Sản xuất"});

        inputPanelVN.add(new JLabel("Mã khách hàng:"));
        inputPanelVN.add(maKHTFVN);
        inputPanelVN.add(new JLabel("Họ tên khách hàng:"));
        inputPanelVN.add(hoTenKHTFVN);
        inputPanelVN.add(new JLabel("Ngày ra hóa đơn:"));
        inputPanelVN.add(ngayRaHDVN);
        inputPanelVN.add(new JLabel("Đối tượng khách hàng:"));
        inputPanelVN.add(doiTuongKhachHangCBVN);
        inputPanelVN.add(new JLabel("Số lượng:"));
        inputPanelVN.add(soLuongTFVN);
        inputPanelVN.add(new JLabel("Đơn giá:"));
        inputPanelVN.add(donGiaTFVN);
        inputPanelVN.add(new JLabel("Định mức:"));
        inputPanelVN.add(dinhMucTFVN);

        themButtonVN = new JButton("Thêm");
        suaButtonVN = new JButton("Sửa");
        xoaButtonVN = new JButton("Xóa");
        tinhTongButtonVN = new JButton("Tính tổng");
        xuatThangButtonVN = new JButton("Xuất hóa đơn trong tháng");
        timKiemButtonVN = new JButton("Tìm kiếm");

        

        

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(themButtonVN);
        buttonPanel.add(suaButtonVN);
        buttonPanel.add(xoaButtonVN);
        buttonPanel.add(tinhTongButtonVN);
        buttonPanel.add(xuatThangButtonVN);
        buttonPanel.add(timKiemButtonVN);
        
        vietNamTableModel = new DefaultTableModel(
                new Object[]{"Mã KH", "Họ tên", "Ngày ra hóa đơn", "Đối tượng KH", "Số lượng", "Đơn giá", "Định mức", "Thành tiền"}, 0);
        JTable table = new JTable(vietNamTableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(buttonPanel, BorderLayout.NORTH);
        bottomPanel.add(scrollPane, BorderLayout.CENTER);

        panel.add(titleLabelVN, BorderLayout.NORTH);
        panel.add(inputPanelVN, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH); 

        

// Thêm MouseListener để lắng nghe sự kiện mouseClicked cho bảng
table.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String maKH = vietNamTableModel.getValueAt(selectedRow, 0).toString();
            String hoTenKH = vietNamTableModel.getValueAt(selectedRow, 1).toString();
            String ngayRaHD = vietNamTableModel.getValueAt(selectedRow, 2).toString();
            String doiTuongKhachHang = vietNamTableModel.getValueAt(selectedRow, 3).toString();
            String soLuong = vietNamTableModel.getValueAt(selectedRow, 4).toString();
            String donGia = vietNamTableModel.getValueAt(selectedRow, 5).toString();
            String dinhMuc = vietNamTableModel.getValueAt(selectedRow, 6).toString();

            // Hiển thị thông tin của hóa đơn tương ứng trong các ô input
            maKHTFVN.setText(maKH);
            hoTenKHTFVN.setText(hoTenKH);
            ngayRaHDVN.setText(ngayRaHD);
            doiTuongKhachHangCBVN.setSelectedItem(doiTuongKhachHang);
            soLuongTFVN.setText(soLuong);
            donGiaTFVN.setText(donGia);
            dinhMucTFVN.setText(dinhMuc);
        }
    }
});

        return panel;
    }

    private JPanel createHoaDonNuocNgoaiPanel() {
        JPanel panel = new JPanel(new BorderLayout());
    
        inputPanelNN = new JPanel(new GridLayout(7, 2));
    
        titleLabelNN = new JLabel("Hóa đơn nước ngoài");
        maKHTFNN = new JTextField(20);
        hoTenKHTFNN = new JTextField(20);
        ngayRaHDNN = new JTextField(20);
        quocTichTF = new JTextField(20);
        soLuongTF = new JTextField(20);
        donGiaTF = new JTextField(20);
    
        inputPanelNN.add(new JLabel("Mã khách hàng:"));
        inputPanelNN.add(maKHTFNN);
        inputPanelNN.add(new JLabel("Họ tên khách hàng:"));
        inputPanelNN.add(hoTenKHTFNN);
        inputPanelNN.add(new JLabel("Ngày ra hóa đơn:"));
        inputPanelNN.add(ngayRaHDNN);
        inputPanelNN.add(new JLabel("Quốc tịch:"));
        inputPanelNN.add(quocTichTF);
        inputPanelNN.add(new JLabel("Số lượng:"));
        inputPanelNN.add(soLuongTF);
        inputPanelNN.add(new JLabel("Đơn giá:"));
        inputPanelNN.add(donGiaTF);
    
        themButtonNN = new JButton("Thêm");
        suaButtonNN = new JButton("Sửa");
        xoaButtonNN = new JButton("Xóa");
        timKiemButtonNN = new JButton("Tìm kiếm");
        tinhTongButtonNN = new JButton("Tính tổng");
        xuatThangButtonNN = new JButton("Xuất hóa đơn nước ngoài trong tháng");
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(themButtonNN);
        buttonPanel.add(suaButtonNN);
        buttonPanel.add(xoaButtonNN);
        buttonPanel.add(timKiemButtonNN);
        buttonPanel.add(tinhTongButtonNN);
        buttonPanel.add(xuatThangButtonNN);
    
        nuocNgoaiTableModel = new DefaultTableModel(
                new Object[]{"Mã KH", "Họ tên", "Ngày ra hóa đơn", "Quốc tịch", "Số lượng", "Đơn giá", "Thành tiền"}, 0);
        JTable tableNN = new JTable(nuocNgoaiTableModel);
    
        JScrollPane scrollPaneNN = new JScrollPane(tableNN);
    
        JPanel bottomPanelNN = new JPanel(new BorderLayout());
        bottomPanelNN.add(buttonPanel, BorderLayout.NORTH);
        bottomPanelNN.add(scrollPaneNN, BorderLayout.CENTER);
    
        panel.add(titleLabelNN, BorderLayout.NORTH);
        panel.add(inputPanelNN, BorderLayout.CENTER);
        panel.add(bottomPanelNN, BorderLayout.SOUTH);
    
        // Thêm MouseListener để lắng nghe sự kiện mouseClicked cho bảng
        tableNN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tableNN.getSelectedRow();
                if (selectedRow >= 0) {
                    String maKH = nuocNgoaiTableModel.getValueAt(selectedRow, 0).toString();
                    String hoTenKH = nuocNgoaiTableModel.getValueAt(selectedRow, 1).toString();
                    String ngayRaHD = nuocNgoaiTableModel.getValueAt(selectedRow, 2).toString();
                    String quocTich = nuocNgoaiTableModel.getValueAt(selectedRow, 3).toString();
                    String soLuong = nuocNgoaiTableModel.getValueAt(selectedRow, 4).toString();
                    String donGia = nuocNgoaiTableModel.getValueAt(selectedRow, 5).toString();
    
                    // Hiển thị thông tin của hóa đơn nước ngoài tương ứng trong các ô input
                    maKHTFNN.setText(maKH);
                    hoTenKHTFNN.setText(hoTenKH);
                    ngayRaHDNN.setText(ngayRaHD);
                    quocTichTF.setText(quocTich);
                    soLuongTF.setText(soLuong);
                    donGiaTF.setText(donGia);
                }
            }
        });
    
        return panel;
    }
    

    public void clearFields() {
        // Xóa dữ liệu trên các trường nhập liệu trong panel Hóa đơn Việt Nam
        maKHTFVN.setText("");
        hoTenKHTFVN.setText("");
        ngayRaHDVN.setText("");
        doiTuongKhachHangCBVN.setSelectedIndex(0);
        soLuongTFVN.setText("");
        donGiaTFVN.setText("");
        dinhMucTFVN.setText("");
    }

    public String showInputTimKiem() {
        while (true) {
            String input = JOptionPane.showInputDialog(
                this.getFrame(),
                "Nhập mã khách hàng để tìm hóa đơn:",
                "Tìm hóa đơn",
                JOptionPane.PLAIN_MESSAGE
            );
    
            if (input == null) {
                return null; // Người dùng đã hủy
            }
    
            if (input.matches("\\d+")) {
                return input; // Trả về chuỗi số hợp lệ
            } else {
                JOptionPane.showMessageDialog(
                    this.getFrame(),
                    "Vui lòng chỉ nhập số.",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
    
    public String showInputDialogForMaKH() {
        return JOptionPane.showInputDialog(
            this.getFrame(),
            "Nhập mã khách hàng để xóa hóa đơn:",
            "Xóa hóa đơn",
            JOptionPane.PLAIN_MESSAGE
        );
    }
    
    public void showXoaHoaDonSuccessMessage() {
        JOptionPane.showMessageDialog(
            this.getFrame(),
            "Xóa hóa đơn thành công!",
            "Thông báo",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    public void showInvalidMaKHErrorMessage() {
        JOptionPane.showMessageDialog(
            this.getFrame(),
            "Mã khách hàng không hợp lệ.",
            "Lỗi",
            JOptionPane.ERROR_MESSAGE
        );
    }
    
    // viet nam
    public JTextField getMaKHTFVN() {
        return maKHTFVN;
    }
    public JTextField getHoTenKHTFVN() {
        return hoTenKHTFVN;
    }
    public JTextField getNgayRaHDVN() {
        return ngayRaHDVN;
    }
    public String getDoiTuongKhachHangVN() {
        return (String) doiTuongKhachHangCBVN.getSelectedItem();
    }    
    public JTextField getSoLuongTFVN() {
        return soLuongTFVN;
    }
    public JTextField getDonGiaTFVN() {
        return donGiaTFVN;
    }
    public JTextField getDinhMucTFVN() {
        return dinhMucTFVN;
    }
    public JButton getThemButtonVN() {
        return themButtonVN;
    }
    public JButton getSuaButtonVN() {
        return suaButtonVN;
    }
    public JButton getXoaButtonVN() {
        return xoaButtonVN;
    }
    public JButton getTimKiemButtonVN() {
        return timKiemButtonVN;
    }
    public JButton getTinhTongButtonVN() {
        return tinhTongButtonVN;
    }
    public JButton getXuatThangButtonVN() {
        return xuatThangButtonVN;
    }
    public DefaultTableModel getVietNamTableModel() {
        return vietNamTableModel;
    }

    public JComboBox<String> getDoiTuongKhachHangCBVN() {
        return doiTuongKhachHangCBVN;
    }
    
    public JFrame getFrame() {
        return frame;
    }

    //NN
    public void clearFieldsNN() {
        maKHTFNN.setText("");
        hoTenKHTFNN.setText("");
        ngayRaHDNN.setText("");
        quocTichTF.setText("");
        soLuongTF.setText("");
        donGiaTF.setText("");
    }

    public JTextField getMaKHTFNN() {
        return maKHTFNN;
    }

    public JTextField getHoTenKHTFNN() {
        return hoTenKHTFNN;
    }

    public JTextField getNgayRaHDNN() {
        return ngayRaHDNN;
    }

    public JTextField getQuocTichTF() {
        return quocTichTF;
    }

    public JTextField getSoLuongTF() {
        return soLuongTF;
    }

    public JTextField getDonGiaTF() {
        return donGiaTF;
    }

    public JButton getThemButtonNN() {
        return themButtonNN;
    }

    public JButton getSuaButtonNN() {
        return suaButtonNN;
    }

    public JButton getXoaButtonNN() {
        return xoaButtonNN;
    }

    public JButton getTimKiemButtonNN() {
        return timKiemButtonNN;
    }

    public JButton getTinhTongButtonNN() {
        return tinhTongButtonNN;
    }

    public JButton getXuatThangButtonNN() {
        return xuatThangButtonNN;
    }

    public DefaultTableModel getNuocNgoaiTableModel() {
        return nuocNgoaiTableModel;
    }

}
package presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HoaDonVietNamView {
    private JFrame frame;
    private JLabel titleLabel;
    private JTextField maKHTF;
    private JTextField hoTenKHTF;
    private JTextField ngayRaHD;
    private JComboBox<String> loaiKhachHangCB;
    private JTextField soLuongTF;
    private JTextField donGiaTF;
    private JTextField dinhMucTF;
    private JButton themButton;
    private JButton xoaButton;
    private JButton suaButton;

    public HoaDonVietNamView() {
        frame = new JFrame("Hóa đơn Việt Nam");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2));

        titleLabel = new JLabel("Hóa đơn Việt Nam");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        maKHTF = new JTextField();
        hoTenKHTF = new JTextField();
        ngayRaHD = new JTextField();
        loaiKhachHangCB = new JComboBox<>(new String[]{"Sinh hoạt", "Kinh doanh", "Sản xuất"});
        soLuongTF = new JTextField();
        donGiaTF = new JTextField();
        dinhMucTF = new JTextField();
        themButton = new JButton("Thêm");
        xoaButton = new JButton("Xóa");
        suaButton = new JButton("Sửa");

        panel.add(new JLabel("Mã khách hàng:"));
        panel.add(maKHTF);
        panel.add(new JLabel("Họ tên khách hàng:"));
        panel.add(hoTenKHTF);
        panel.add(new JLabel("Ngày ra hóa đơn:"));
        panel.add(ngayRaHD);
        panel.add(new JLabel("Loại khách hàng:"));
        panel.add(loaiKhachHangCB);
        panel.add(new JLabel("Số lượng:"));
        panel.add(soLuongTF);
        panel.add(new JLabel("Đơn giá:"));
        panel.add(donGiaTF);
        panel.add(new JLabel("Định mức:"));
        panel.add(dinhMucTF);
        panel.add(themButton);
        panel.add(xoaButton);
        panel.add(suaButton);

        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(false);
    }

    public void addThemListener(ActionListener listener) {
        themButton.addActionListener(listener);
    }

    public void addSuaListener(ActionListener listener) {
        suaButton.addActionListener(listener);
    }

    public void addXoaListener(ActionListener listener) {
        xoaButton.addActionListener(listener);
    }

    public void show() {
        frame.setVisible(true);
    }
}

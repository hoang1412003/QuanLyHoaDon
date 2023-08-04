package presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HoaDonNuocNgoaiView {
    private JFrame frame;
    private JLabel titleLabel;
    private JTextField maKHTF;
    private JTextField hoTenKHTF;
    private JTextField ngayRaHD;
    private JTextField quocTichTF;
    private JTextField soLuongTF;
    private JTextField donGiaTF;
    private JButton themButton;
    private JButton xoaButton;
    private JButton suaButton;

    public HoaDonNuocNgoaiView() {
        frame = new JFrame("Hóa đơn Nước ngoài");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        titleLabel = new JLabel("Hóa đơn Nước ngoài");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        maKHTF = new JTextField();
        hoTenKHTF = new JTextField();
        ngayRaHD = new JTextField();
        quocTichTF = new JTextField();
        soLuongTF = new JTextField();
        donGiaTF = new JTextField();
        themButton = new JButton("Thêm");
        xoaButton = new JButton("Xóa");
        suaButton = new JButton("Sửa");

        panel.add(new JLabel("Mã khách hàng:"));
        panel.add(maKHTF);
        panel.add(new JLabel("Họ tên khách hàng:"));
        panel.add(hoTenKHTF);
        panel.add(new JLabel("Ngày ra hóa đơn:"));
        panel.add(ngayRaHD);
        panel.add(new JLabel("Quốc tịch:"));
        panel.add(quocTichTF);
        panel.add(new JLabel("Số lượng:"));
        panel.add(soLuongTF);
        panel.add(new JLabel("Đơn giá:"));
        panel.add(donGiaTF);
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


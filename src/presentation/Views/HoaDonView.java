package presentation.Views;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HoaDonView {
    private JFrame frame;
    private JButton vietNamButton;
    private JButton nuocNgoaiButton;

    public HoaDonView() {
        frame = new JFrame("Quản lý hoá đơn tiền điện");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new FlowLayout());

        vietNamButton = new JButton("Hóa đơn Việt Nam");
        nuocNgoaiButton = new JButton("Hóa đơn Nước ngoài");

        vietNamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HoaDonVietNamView vietNamView = new HoaDonVietNamView();
                vietNamView.show();
            }
        });

        nuocNgoaiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HoaDonNuocNgoaiView nuocNgoaiView = new HoaDonNuocNgoaiView();
                nuocNgoaiView.show();
            }
        });

        frame.add(vietNamButton);
        frame.add(nuocNgoaiButton);

        frame.setVisible(true);
    }

    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(new Runnable() {
    //         @Override
    //         public void run() {
    //             new HoaDonView();
    //         }
    //     });
    // }
}


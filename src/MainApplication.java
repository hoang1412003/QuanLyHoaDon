import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.SwingUtilities;

import domain.HoaDon;
import pesistence.HoaDonDAO;

import pesistence.HoaDonVietNamDAO;
import presentation.Controller.HoaDonNNController;
import presentation.Controller.HoaDonVNController;
import presentation.Views.HoaDonManagementView;


public class MainApplication {
    public static void main(String[] args) {
        
        
            HoaDonManagementView view = new HoaDonManagementView();
            HoaDonVNController hoaDonVNController = new HoaDonVNController(view);
            HoaDonNNController hoaDonNNController = new HoaDonNNController(view);
            
            view.display();

            hoaDonVNController.loadHoaDonVNList();
            hoaDonNNController.loadHoaDonNNList();

       
    }
}
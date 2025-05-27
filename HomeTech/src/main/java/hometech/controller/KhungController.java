package hometech.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller cho khung chính của ứng dụng (khung.fxml)
 * Quản lý navigation và load các trang con vào center area
 */
public class KhungController implements Initializable {

    @FXML
    private BorderPane mainBorderPane; // Reference đến BorderPane chính
    
    @FXML
    private Button buttonTrangChu;
    
    @FXML
    private Button buttonCanHo;
    
    @FXML
    private Button buttonCuDan;
    
    @FXML
    private Button buttonKhoanThu;
    
    @FXML
    private Button buttonTaiKhoan;
    
    @FXML
    private Button buttonHoSo;
    
    @FXML
    private Button buttonDangXuat;
    
    @FXML
    private Label labelScreenName;
    
    @FXML
    private Label labelAccountName;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("KhungController được khởi tạo");
        
        // Load trang chủ danh sách khi khởi động
        loadTrangChuDanhSach();
    }
    
    /**
     * Load trang chủ danh sách vào center area
     */
    private void loadTrangChuDanhSach() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeTech/trang_chu_danh_sach.fxml"));
            Parent trangChuContent = loader.load();
            
            // Set content vào center của BorderPane
            mainBorderPane.setCenter(trangChuContent);
            updateScreenLabel("Trang chủ");
            
        } catch (IOException e) {
            System.err.println("Không thể load trang chủ danh sách: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Xử lý click button "Trang chủ"
     */
    @FXML
    public void goToTrangChu(ActionEvent event) {
        loadTrangChuDanhSach();
    }
    
    /**
     * Xử lý click button "Căn hộ"
     */
    @FXML
    public void goToCanHo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeTech/quan_ly_can_ho.fxml"));
            Parent content = loader.load();
            mainBorderPane.setCenter(content);
            
            updateScreenLabel("Quản lý căn hộ");
            System.out.println("Chuyển đến quản lý căn hộ");
            
        } catch (Exception e) {
            System.err.println("Không thể load trang căn hộ: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Xử lý click button "Cư dân"
     */
    @FXML
    public void goToCuDan(ActionEvent event) {
        try {
            // TODO: Tạo trang quản lý cư dân
            updateScreenLabel("Quản lý cư dân");
            System.out.println("Chuyển đến quản lý cư dân");
            
        } catch (Exception e) {
            System.err.println("Không thể load trang cư dân: " + e.getMessage());
        }
    }
    
    /**
     * Xử lý click button "Khoản thu"
     */
    @FXML
    public void goToKhoanThu(ActionEvent event) {
        try {
            // TODO: Tạo trang quản lý khoản thu
            updateScreenLabel("Quản lý khoản thu");
            System.out.println("Chuyển đến quản lý khoản thu");
            
        } catch (Exception e) {
            System.err.println("Không thể load trang khoản thu: " + e.getMessage());
        }
    }
    
    /**
     * Xử lý click button "Tài khoản"
     */
    @FXML
    public void goToTaiKhoan(ActionEvent event) {
        try {
            // TODO: Tạo trang quản lý tài khoản
            updateScreenLabel("Quản lý tài khoản");
            System.out.println("Chuyển đến quản lý tài khoản");
            
        } catch (Exception e) {
            System.err.println("Không thể load trang tài khoản: " + e.getMessage());
        }
    }
    
    /**
     * Xử lý click button "Hồ sơ"
     */
    @FXML
    public void goToHoSo(ActionEvent event) {
        try {
            // TODO: Tạo trang hồ sơ
            updateScreenLabel("Hồ sơ cá nhân");
            System.out.println("Chuyển đến hồ sơ");
            
        } catch (Exception e) {
            System.err.println("Không thể load trang hồ sơ: " + e.getMessage());
        }
    }
    
    /**
     * Xử lý click button "Đăng xuất"
     */
    @FXML
    public void handleDangXuat(ActionEvent event) {
        // TODO: Implement logout functionality
        System.out.println("Đăng xuất khỏi hệ thống");
    }
    
    /**
     * Method tiện ích để load một trang FXML vào center
     */
    public void loadPageToCenter(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent content = loader.load();
            mainBorderPane.setCenter(content);
        } catch (IOException e) {
            System.err.println("Không thể load trang: " + fxmlPath + " - " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Load chi tiết căn hộ vào center
     */
    public void loadChiTietCanHo() {
        loadPageToCenter("/HomeTech/chi_tiet_can_ho.fxml");
        updateScreenLabel("Chi tiết căn hộ");
    }
    
    /**
     * Cập nhật label tên màn hình
     */
    private void updateScreenLabel(String screenName) {
        if (labelScreenName != null) {
            labelScreenName.setText(screenName);
        }
    }
    
    /**
     * Thiết lập tên tài khoản
     */
    public void setAccountName(String accountName) {
        if (labelAccountName != null) {
            labelAccountName.setText(accountName);
        }
    }
    
    /**
     * Getter cho BorderPane chính (để các controller khác có thể truy cập)
     */
    public BorderPane getMainBorderPane() {
        return mainBorderPane;
    }
} 
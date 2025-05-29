package hometech.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.io.IOException;

/**
 * Utility class để quản lý navigation giữa các trang
 */
public class NavigationUtil {
    
    /**
     * Mở trang Home List (trang chủ danh sách)
     * @param currentStage Stage hiện tại (có thể null để tạo stage mới)
     */
    public static void openHomeList(Stage currentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(NavigationUtil.class.getResource("/HomeTech/trang_chu_danh_sach.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            
            if (currentStage == null) {
                currentStage = new Stage();
            }
            
            currentStage.setScene(scene);
            currentStage.setTitle("Home Tech - Trang Chủ Danh Sách");
            currentStage.show();
            
        } catch (IOException e) {
            System.err.println("Không thể mở trang Home List: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Mở trang Home List trong cửa sổ mới
     */
    public static void openHomeListInNewWindow() {
        openHomeList(null);
    }
    
    /**
     * Mở trang chủ từ một ActionEvent (button click)
     * @param event ActionEvent từ button
     */
    public static void goToHomePage(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            openHomeList(stage);
        } catch (Exception e) {
            System.err.println("Không thể chuyển đến trang chủ: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Mở trang chủ từ một Node bất kỳ
     * @param node Node để lấy stage
     */
    public static void goToHomePage(Node node) {
        try {
            Stage stage = (Stage) node.getScene().getWindow();
            openHomeList(stage);
        } catch (Exception e) {
            System.err.println("Không thể chuyển đến trang chủ: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Tạo Scene cho trang chủ (không hiển thị ngay lập tức)
     * @return Scene của trang chủ
     */
    public static Scene createHomePageScene() {
        try {
            FXMLLoader loader = new FXMLLoader(NavigationUtil.class.getResource("/HomeTech/trang_chu_danh_sach.fxml"));
            Parent root = loader.load();
            return new Scene(root);
        } catch (IOException e) {
            System.err.println("Không thể tạo Scene cho trang chủ: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Mở trang chi tiết căn hộ
     * @param currentStage Stage hiện tại
     */
    public static void openChiTietCanHo(Stage currentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(NavigationUtil.class.getResource("/HomeTech/chi_tiet_can_ho.fxml"));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            currentStage.setTitle("Chi Tiết Căn Hộ");
            currentStage.show();
            
        } catch (IOException e) {
            System.err.println("Không thể mở trang Chi Tiết Căn Hộ: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 
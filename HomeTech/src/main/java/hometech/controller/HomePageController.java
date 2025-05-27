package hometech.controller;

import hometech.util.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

/**
 * Controller helper cho các chức năng liên quan đến trang chủ
 */
public class HomePageController {

    /**
     * Xử lý sự kiện khi nhấn nút "Về Trang Chủ"
     * Có thể được gọi từ FXML với onAction="#goToHomePage"
     */
    @FXML
    public void goToHomePage(ActionEvent event) {
        try {
            NavigationUtil.goToHomePage(event);
        } catch (Exception e) {
            showErrorAlert("Không thể chuyển đến trang chủ", e.getMessage());
        }
    }
    
    /**
     * Xử lý sự kiện khi nhấn nút "Về Trang Chủ" từ một Node bất kỳ
     */
    public void goToHomePage(Node node) {
        try {
            NavigationUtil.goToHomePage(node);
        } catch (Exception e) {
            showErrorAlert("Không thể chuyển đến trang chủ", e.getMessage());
        }
    }
    
    /**
     * Mở trang chủ trong cửa sổ mới
     */
    @FXML
    public void openHomePageInNewWindow() {
        try {
            NavigationUtil.openHomeListInNewWindow();
        } catch (Exception e) {
            showErrorAlert("Không thể mở trang chủ", e.getMessage());
        }
    }
    
    /**
     * Phương thức tiện ích để thêm chức năng "Về Trang Chủ" vào button
     * @param button Button cần thêm chức năng
     */
    public void addHomeNavigationToButton(Button button) {
        button.setOnAction(this::goToHomePage);
        button.setText("Về Trang Chủ");
    }
    
    /**
     * Hiển thị thông báo lỗi
     */
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Lỗi");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    /**
     * Hiển thị thông báo thành công
     */
    protected void showSuccessAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thành công");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
} 
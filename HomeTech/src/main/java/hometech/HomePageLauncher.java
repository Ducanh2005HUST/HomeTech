package hometech;

import hometech.util.NavigationUtil;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Launcher để mở trực tiếp trang chủ
 */
public class HomePageLauncher extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Sử dụng NavigationUtil để mở trang chủ
            NavigationUtil.openHomeList(primaryStage);
            primaryStage.setMaximized(true); // Mở ở chế độ toàn màn hình
            
        } catch (Exception e) {
            System.err.println("Lỗi khi khởi động trang chủ: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Phương thức chính để chạy ứng dụng
     */
    public static void main(String[] args) {
        System.out.println("Đang khởi động trang chủ HomeTech...");
        launch(args);
    }
    
    /**
     * Phương thức tiện ích để mở trang chủ từ các class khác
     */
    public static void openHomePage() {
        NavigationUtil.openHomeListInNewWindow();
    }
} 
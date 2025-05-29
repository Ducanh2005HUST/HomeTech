package hometech;

import hometech.util.NavigationUtil;

/**
 * Launcher đơn giản để mở trang chủ nhanh chóng
 */
public class QuickHomeLauncher {
    
    /**
     * Mở trang chủ trong cửa sổ mới
     */
    public static void openHomePage() {
        NavigationUtil.openHomeListInNewWindow();
    }
    
    /**
     * Phương thức main để chạy trực tiếp
     */
    public static void main(String[] args) {
        System.out.println("Mở trang chủ HomeTech...");
        
        // Khởi động JavaFX Application thread
        javafx.application.Platform.startup(() -> {
            openHomePage();
        });
        
        // Giữ cho thread main không thoát ngay lập tức
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
} 
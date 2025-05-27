# Hướng Dẫn Sử Dụng Các Hàm Mở Trang Chủ

## Tổng Quan
Dự án HomeTech đã được trang bị các hàm tiện ích để mở trang chủ một cách dễ dàng từ nhiều ngữ cảnh khác nhau.

## Các Cách Mở Trang Chủ

### 1. Chạy Trực Tiếp Từ Maven
```bash
mvn exec:java
```
Lệnh này sẽ chạy `HomePageLauncher` và mở trang chủ ngay lập tức.

### 2. Chạy Với Class Cụ Thể
```bash
mvn exec:java -Dexec.mainClass=hometech.HomePageLauncher
```

### 3. Sử Dụng NavigationUtil (Trong Code)
```java
import hometech.util.NavigationUtil;

// Mở trang chủ trong cửa sổ hiện tại
NavigationUtil.openHomeList(currentStage);

// Mở trang chủ trong cửa sổ mới
NavigationUtil.openHomeListInNewWindow();

// Chuyển đến trang chủ từ ActionEvent (button click)
NavigationUtil.goToHomePage(event);

// Chuyển đến trang chủ từ Node bất kỳ
NavigationUtil.goToHomePage(someNode);
```

### 4. Sử Dụng HomePageController
```java
import hometech.controller.HomePageController;

public class MyController extends HomePageController {
    
    @FXML
    public void onBackToHomeClick(ActionEvent event) {
        goToHomePage(event); // Method từ HomePageController
    }
    
    @FXML
    public void openNewHomeWindow() {
        openHomePageInNewWindow(); // Method từ HomePageController
    }
}
```

### 5. Sử Dụng Trong FXML
Trong file FXML, bạn có thể gán action cho button:
```xml
<Button text="Về Trang Chủ" onAction="#goToHomePage" />
<Button text="Mở Trang Chủ Mới" onAction="#openHomePageInNewWindow" />
```

### 6. Chạy Từ Class Khác
```java
import hometech.HomePageLauncher;

// Trong method main hoặc bất kỳ đâu
HomePageLauncher.openHomePage();
```

## Các Class Và Method Chính

### NavigationUtil
- `openHomeList(Stage currentStage)`: Mở trang chủ trong stage cụ thể
- `openHomeListInNewWindow()`: Mở trang chủ trong cửa sổ mới
- `goToHomePage(ActionEvent event)`: Chuyển đến trang chủ từ button click
- `goToHomePage(Node node)`: Chuyển đến trang chủ từ node bất kỳ
- `createHomePageScene()`: Tạo Scene cho trang chủ (không hiển thị ngay)

### HomePageLauncher
- `main(String[] args)`: Entry point để chạy ứng dụng
- `openHomePage()`: Method static để mở trang chủ

### HomePageController
- `goToHomePage(ActionEvent event)`: Xử lý sự kiện từ FXML
- `openHomePageInNewWindow()`: Mở cửa sổ trang chủ mới
- `addHomeNavigationToButton(Button button)`: Thêm chức năng navigation cho button

### Home_list (Controller chính)
- `refreshHomePage()`: Refresh trang chủ hiện tại
- `goToHomePage(ActionEvent event)`: Chuyển đến trang chủ
- `openHomePageInNewWindow()`: Mở trang chủ trong cửa sổ mới

## Lưu Ý
- Trang chủ được định nghĩa bởi file FXML: `/HomeTech/trang_chu_danh_sach.fxml`
- Controller tương ứng: `hometech.controller.Home_list`
- Tất cả các method đều có xử lý lỗi và hiển thị thông báo khi cần thiết
- Ứng dụng sẽ tự động maximize cửa sổ khi mở trang chủ

## Ví Dụ Sử Dụng

### Thêm Button "Về Trang Chủ" Vào Controller Bất Kỳ
```java
public class MyController {
    
    @FXML
    private Button btnHome;
    
    @FXML
    public void initialize() {
        // Tự động thêm chức năng về trang chủ
        HomePageController helper = new HomePageController();
        helper.addHomeNavigationToButton(btnHome);
    }
    
    @FXML
    public void goBack(ActionEvent event) {
        NavigationUtil.goToHomePage(event);
    }
}
```

### Mở Trang Chủ Từ Menu
```java
@FXML
public void onMenuHomeClick() {
    NavigationUtil.openHomeListInNewWindow();
}
```

Chúc bạn sử dụng thành công! 
# Hướng Dẫn Sử Dụng Khung Navigation

## 🎯 Tổng Quan
Bạn đã có hệ thống navigation hoàn chỉnh với `khung.fxml` làm layout chính và `trang_chu_danh_sach.fxml` được load vào center area!

## 🏗️ Cấu Trúc Hệ Thống

### 1. Khung Chính (khung.fxml)
- **Layout**: BorderPane với sidebar menu (left) và header (top)
- **Controller**: `KhungController.java`
- **Chức năng**: Container chính, load các trang con vào center area

### 2. Trang Chủ Danh Sách (trang_chu_danh_sach.fxml)
- **Vị trí**: Được load vào center area của khung chính
- **Controller**: `Home_list.java`
- **Chức năng**: Hiển thị dashboard với danh sách căn hộ, cư dân, khoản thu

### 3. Chi Tiết Căn Hộ (chi_tiet_can_ho.fxml)
- **Vị trí**: Được load vào center area khi click "Xem Chi Tiết"
- **Controller**: Sử dụng controller mặc định hoặc tạo riêng
- **Chức năng**: Hiển thị thông tin chi tiết căn hộ

## 🚀 Cách Chạy Ứng Dụng

### Chạy Khung Chính
```bash
mvn exec:java
```

### Hoặc chạy với class cụ thể
```bash
mvn exec:java -Dexec.mainClass=hometech.KhungLauncher
```

## 📋 Luồng Navigation

### 1. Khởi Động
1. `KhungLauncher` load `khung.fxml`
2. `KhungController` tự động load `trang_chu_danh_sach.fxml` vào center
3. Sidebar menu sẵn sàng để navigation

### 2. Navigation Từ Sidebar
- **Trang chủ** → Load `trang_chu_danh_sach.fxml`
- **Căn hộ** → TODO: Tạo trang quản lý căn hộ
- **Cư dân** → TODO: Tạo trang quản lý cư dân
- **Khoản thu** → TODO: Tạo trang quản lý khoản thu
- **Tài khoản** → TODO: Tạo trang quản lý tài khoản

### 3. Navigation Từ Trang Chủ
- Chọn căn hộ từ table
- Click "Xem Chi Tiết" → Load `chi_tiet_can_ho.fxml` vào center

## 🔧 Các Method Quan Trọng

### KhungController
```java
// Load trang chủ danh sách
private void loadTrangChuDanhSach()

// Xử lý click menu
@FXML public void goToTrangChu(ActionEvent event)
@FXML public void goToCanHo(ActionEvent event)
@FXML public void goToCuDan(ActionEvent event)
@FXML public void goToKhoanThu(ActionEvent event)
@FXML public void goToTaiKhoan(ActionEvent event)

// Utility methods
public void loadPageToCenter(String fxmlPath)
public void loadChiTietCanHo()
```

### Home_list Controller
```java
// Navigation trong cùng khung
@FXML public void goToChiTietCanHoInFrame()

// Navigation cửa sổ mới (fallback)
@FXML public void goToChiTietCanHo()

// Xử lý selection từ table
@FXML public void handleXemChiTietCanHo()
```

## 💡 Cách Sử Dụng Trong Code

### 1. Load trang mới vào center từ KhungController
```java
// Trong KhungController
public void goToCanHo(ActionEvent event) {
    loadPageToCenter("/HomeTech/quan_ly_can_ho.fxml");
    updateScreenLabel("Quản lý căn hộ");
}
```

### 2. Load trang từ controller con (như Home_list)
```java
// Trong Home_list hoặc controller khác
public void goToSomePage() {
    try {
        javafx.scene.Node root = someNode.getScene().getRoot();
        if (root instanceof javafx.scene.layout.BorderPane) {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                getClass().getResource("/HomeTech/some_page.fxml"));
            javafx.scene.Parent content = loader.load();
            ((javafx.scene.layout.BorderPane) root).setCenter(content);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```

### 3. Thêm button navigation trong FXML
```xml
<!-- Trong trang con, thêm button để chuyển trang -->
<Button text="Xem Chi Tiết" onAction="#goToChiTietCanHoInFrame" />
<Button text="Quay Lại Trang Chủ" onAction="#goToTrangChu" />
```

## 🎨 Tùy Chỉnh Giao Diện

### 1. Cập nhật tên màn hình
```java
// Trong KhungController
private void updateScreenLabel(String screenName) {
    if (labelScreenName != null) {
        labelScreenName.setText(screenName);
    }
}
```

### 2. Thay đổi tên tài khoản
```java
// Trong KhungController
public void setAccountName(String accountName) {
    if (labelAccountName != null) {
        labelAccountName.setText(accountName);
    }
}
```

## 🔍 Test Navigation

### 1. Test Khung Chính
```bash
mvn compile
mvn exec:java
```

### 2. Kiểm tra các chức năng
- ✅ Khung chính hiển thị với sidebar
- ✅ Trang chủ danh sách load tự động vào center
- ✅ Click "Trang chủ" → Reload trang chủ danh sách
- ✅ Click "Căn hộ", "Cư dân", etc. → In ra console (TODO)
- ✅ Chọn căn hộ và click "Xem Chi Tiết" → Load chi tiết vào center

## 🚧 Mở Rộng Hệ Thống

### 1. Thêm trang mới
1. Tạo file FXML mới (ví dụ: `quan_ly_can_ho.fxml`)
2. Tạo controller tương ứng
3. Cập nhật method trong `KhungController`:
```java
@FXML
public void goToCanHo(ActionEvent event) {
    loadPageToCenter("/HomeTech/quan_ly_can_ho.fxml");
    updateScreenLabel("Quản lý căn hộ");
}
```

### 2. Thêm navigation từ trang con
```java
// Trong controller của trang con
public void goToAnotherPage() {
    javafx.scene.Node root = someNode.getScene().getRoot();
    if (root instanceof javafx.scene.layout.BorderPane) {
        // Load trang mới vào center
        // ... code load FXML
    }
}
```

### 3. Thêm breadcrumb navigation
- Thêm Label hoặc HBox vào header
- Cập nhật breadcrumb khi chuyển trang
- Cho phép click để quay lại trang trước

## ⚠️ Lưu Ý Quan Trọng

1. **Đường dẫn FXML**: Luôn bắt đầu với `/HomeTech/`
2. **Controller linking**: Đảm bảo `fx:controller` được khai báo đúng
3. **Error handling**: Luôn có try-catch khi load FXML
4. **Memory management**: Các trang cũ sẽ được garbage collected tự động
5. **State management**: Dữ liệu của trang cũ sẽ mất khi chuyển trang mới

## 🎉 Kết Luận

Bây giờ bạn đã có:
- ✅ Khung chính với sidebar navigation
- ✅ Trang chủ danh sách được load tự động
- ✅ Navigation từ trang chủ sang chi tiết căn hộ
- ✅ Hệ thống mở rộng dễ dàng cho các trang mới

Chúc bạn phát triển ứng dụng thành công! 🚀 
# Hướng Dẫn Navigation từ Homepage sang Home_list

## Tổng Quan
Bây giờ bạn đã có thể chuyển hướng từ homepage (khung chính) sang home_list (trang chủ danh sách) thành công!

## Cấu Trúc Navigation

### 1. Khung Chính (khung.fxml)
- **File**: `src/main/resources/HomeTech/khung.fxml`
- **Controller**: `hometech.controller.KhungController`
- **Chức năng**: Layout chính với sidebar menu

### 2. Trang Chủ Danh Sách (trang_chu_danh_sach.fxml)
- **File**: `src/main/resources/HomeTech/trang_chu_danh_sach.fxml`
- **Controller**: `hometech.controller.Home_list`
- **Chức năng**: Hiển thị danh sách căn hộ, cư dân, khoản thu

## Cách Chạy Ứng Dụng

### 1. Chạy Khung Chính
```bash
mvn exec:java
```
Hoặc
```bash
mvn exec:java -Dexec.mainClass=hometech.KhungLauncher
```

### 2. Chạy Trực Tiếp Trang Chủ
```bash
mvn exec:java -Dexec.mainClass=hometech.HomePageLauncher
```

## Luồng Navigation

### Từ Khung Chính → Trang Chủ
1. Mở khung chính bằng `KhungLauncher`
2. Click button **"Trang chủ"** trong sidebar
3. Ứng dụng sẽ chuyển sang `trang_chu_danh_sach.fxml`

### Từ Trang Chủ → Chi Tiết Căn Hộ
1. Trong trang chủ, chọn căn hộ từ table
2. Click button **"Xem Chi Tiết"** hoặc gọi method `handleXemChiTietCanHo()`
3. Ứng dụng sẽ chuyển sang `chi_tiet_can_ho.fxml`

## Các Method Navigation

### KhungController
```java
// Chuyển đến trang chủ
@FXML
public void goToTrangChu(ActionEvent event)

// Chuyển đến quản lý căn hộ  
@FXML
public void goToCanHo(ActionEvent event)

// Chuyển đến quản lý cư dân
@FXML
public void goToCuDan(ActionEvent event)

// Chuyển đến quản lý khoản thu
@FXML
public void goToKhoanThu(ActionEvent event)

// Chuyển đến quản lý tài khoản
@FXML
public void goToTaiKhoan(ActionEvent event)
```

### Home_list Controller
```java
// Chuyển về trang chủ
@FXML
public void goToHomePage(ActionEvent event)

// Refresh trang chủ
@FXML
public void refreshHomePage()

// Chuyển đến chi tiết căn hộ
@FXML
public void goToChiTietCanHo()

// Xử lý chọn căn hộ và xem chi tiết
@FXML
public void handleXemChiTietCanHo()
```

## Sử Dụng Trong Code

### 1. Chuyển đến trang chủ từ bất kỳ đâu
```java
import hometech.util.NavigationUtil;

// Từ ActionEvent
NavigationUtil.goToHomePage(event);

// Từ Node
NavigationUtil.goToHomePage(someNode);

// Mở trong cửa sổ mới
NavigationUtil.openHomeListInNewWindow();
```

### 2. Chuyển đến chi tiết căn hộ
```java
// Mở chi tiết căn hộ đơn giản
NavigationUtil.openChiTietCanHo(currentStage);

// Từ Home_list controller
goToChiTietCanHo();
```

### 3. Thiết lập button trong FXML
```xml
<!-- Button chuyển về trang chủ -->
<Button text="Về Trang Chủ" onAction="#goToHomePage" />

<!-- Button xem chi tiết căn hộ -->
<Button text="Xem Chi Tiết" onAction="#handleXemChiTietCanHo" />
```

## Lỗi Thường Gặp & Cách Khắc Phục

### 1. Lỗi: Controller không được tìm thấy
**Nguyên nhân**: Chưa khai báo `fx:controller` trong FXML
**Giải pháp**: Thêm `fx:controller="hometech.controller.KhungController"` vào thẻ root

### 2. Lỗi: Method không tồn tại
**Nguyên nhân**: Method trong FXML không khớp với controller
**Giải pháp**: Kiểm tra tên method và annotation `@FXML`

### 3. Lỗi: FXML không load được
**Nguyên nhân**: Đường dẫn file FXML sai
**Giải pháp**: Kiểm tra đường dẫn trong `getResource()`

## Test Navigation

### 1. Test Khung Chính
```bash
mvn compile
mvn exec:java
```

### 2. Kiểm tra các button
- Click "Trang chủ" → Chuyển sang home_list
- Click "Căn hộ" → In ra console
- Click "Cư dân" → In ra console
- Click "Khoản thu" → In ra console

### 3. Test từ Trang Chủ
- Chọn căn hộ trong table
- Click "Xem Chi Tiết" → Chuyển sang chi tiết căn hộ

## Mở Rộng

Bạn có thể mở rộng navigation bằng cách:

1. **Thêm trang mới**: Tạo FXML và Controller mới
2. **Thêm method trong NavigationUtil**: Để navigation dễ dàng hơn
3. **Thêm validation**: Kiểm tra dữ liệu trước khi chuyển trang
4. **Thêm animation**: Hiệu ứng chuyển trang mượt mà

Chúc bạn thành công với navigation! 🎉 
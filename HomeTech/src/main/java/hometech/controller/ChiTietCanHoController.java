package hometech.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import hometech.model.dto.canHo.CanHoChiTietDto;
import hometech.model.dto.cuDan.CuDanTrongCanHoDto;
import hometech.model.dto.phuongTien.PhuongTienDto;
import hometech.model.dto.hoaDon.HoaDonDto;

/**
 * Controller cho trang chi tiết căn hộ
 */
public class ChiTietCanHoController implements Initializable {

    // Header buttons - Tab navigation
    @FXML private Button buttonThongTin;
    @FXML private Button buttonCuDan;
    @FXML private Button buttonPhuongTien;
    @FXML private Button buttonThuPhi;
    @FXML private Button button_close_up;

    // Tab content panels
    @FXML private AnchorPane anchorPaneThongTin;
    @FXML private AnchorPane anchorPaneCuDan;
    @FXML private AnchorPane anchorPanePhuongTien;
    @FXML private AnchorPane anchorPaneThuPhi;

    // Thông tin căn hộ labels
    @FXML private Label labelMaCanHo;
    @FXML private Label labelSoNha;
    @FXML private Label labelTang;
    @FXML private Label labelToa;
    @FXML private Label labelDienTich;
    @FXML private Label labelTrangThaiCanHo;
    @FXML private Label labelTinhTrangSuDung;
    @FXML private Label labelTinhTrangKiThuat;

    // Thông tin chủ sở hữu labels
    @FXML private VBox vBoxChuSoHuu;
    @FXML private Label labelMaDinhDanh;
    @FXML private Label labelHoVaTen;
    @FXML private Label labelNgaySinh;
    @FXML private Label labelGioiTinh;
    @FXML private Label labelTrangThaiChuSoHuu;
    @FXML private Label labelSoDienThoai;
    @FXML private Label labelEmail;

    // Cư dân tab
    @FXML private TableView<CuDanTrongCanHoDto> tableViewCuDan;
    @FXML private TableColumn<CuDanTrongCanHoDto, String> tableColumnMaDinhDanhCuDan;
    @FXML private TableColumn<CuDanTrongCanHoDto, String> tableColumnHoVaTenCuDan;
    @FXML private TableColumn<CuDanTrongCanHoDto, String> tableColumnQuanHeChuHo;
    @FXML private TableColumn<CuDanTrongCanHoDto, String> tableColumnNgaySinhCuDan;
    @FXML private TableColumn<CuDanTrongCanHoDto, String> tableColumnTrangThaiCuDan;
    @FXML private ComboBox<String> comboBoxSoKetQuaCuDan;
    @FXML private TextField textFieldTimKiemCuDan;
    @FXML private Button buttonTimKiemCuDan;

    // Phương tiện tab
    @FXML private TableView<PhuongTienDto> tableViewPhuongTien;
    @FXML private TableColumn<PhuongTienDto, String> tableColumnMaPhuongTien;
    @FXML private TableColumn<PhuongTienDto, String> tableColumnBienSoXe;
    @FXML private TableColumn<PhuongTienDto, String> tableColumnLoaiPhuongTien;
    @FXML private TableColumn<PhuongTienDto, String> tableColumnNgayDangKi;
    @FXML private TextField textFieldMaSoXe;
    @FXML private ComboBox<String> comboBoxLoaiPhuongTien;
    @FXML private Button buttonTimKiemPhuongTien;
    @FXML private ComboBox<String> comboBoxSoKetQuaPhuongTien;
    @FXML private Label labelHienThiKetQuaPhuongTien;

    // Thu phí tab
    @FXML private TableView<HoaDonDto> tableViewThuPhi;
    @FXML private TableColumn<HoaDonDto, String> tableColumnMaHoaDon;
    @FXML private TableColumn<HoaDonDto, String> tableColumnTenKhoanThu;
    @FXML private TableColumn<HoaDonDto, Integer> tableColumnSoTien;
    @FXML private TableColumn<HoaDonDto, String> tableColumnHanThu;
    @FXML private TableColumn<HoaDonDto, String> tableColumnThaoTacThuPhi;
    @FXML private TextField textFieldTenKhoanThu;
    @FXML private ComboBox<String> comboBoxLoaiKhoanThu;
    @FXML private Button buttonTimKiemThuPhi;
    @FXML private Label labelTongSoTien;
    @FXML private Button buttonThuToanBo;
    @FXML private Button buttonXemLichSu;
    @FXML private CheckBox checkBoxKhongTinhBatBuoc;

    // Data
    private CanHoChiTietDto currentCanHo;
    private ObservableList<CuDanTrongCanHoDto> cuDanList;
    private ObservableList<PhuongTienDto> phuongTienList;
    private ObservableList<HoaDonDto> hoaDonList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ChiTietCanHoController được khởi tạo");
        
        // Khởi tạo tab navigation
        setupTabNavigation();
        
        // Khởi tạo tables
        setupTables();
        
        // Khởi tạo combo boxes
        setupComboBoxes();
        
        // Load dữ liệu mẫu (sẽ thay thế bằng dữ liệu thật)
        loadSampleData();
        
        // Hiển thị tab thông tin đầu tiên
        showThongTinTab();
    }

    /**
     * Thiết lập navigation giữa các tab
     */
    private void setupTabNavigation() {
        buttonThongTin.setOnAction(e -> showThongTinTab());
        buttonCuDan.setOnAction(e -> showCuDanTab());
        buttonPhuongTien.setOnAction(e -> showPhuongTienTab());
        buttonThuPhi.setOnAction(e -> showThuPhiTab());
        button_close_up.setOnAction(e -> handleClose());
    }

    /**
     * Thiết lập các table
     */
    private void setupTables() {
        // Setup table cư dân
        if (tableColumnMaDinhDanhCuDan != null) {
            tableColumnMaDinhDanhCuDan.setCellValueFactory(cellData -> 
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getMaDinhDanh()));
        }
        if (tableColumnHoVaTenCuDan != null) {
            tableColumnHoVaTenCuDan.setCellValueFactory(cellData -> 
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getHoVaTen()));
        }
        if (tableColumnQuanHeChuHo != null) {
            tableColumnQuanHeChuHo.setCellValueFactory(cellData -> 
                new javafx.beans.property.SimpleStringProperty("Cư dân")); // CuDanTrongCanHoDto không có quanHeChuHo
        }
        if (tableColumnNgaySinhCuDan != null) {
            tableColumnNgaySinhCuDan.setCellValueFactory(cellData -> 
                new javafx.beans.property.SimpleStringProperty(
                    cellData.getValue().getNgaySinh() != null ? 
                    cellData.getValue().getNgaySinh().toString() : ""));
        }
        if (tableColumnTrangThaiCuDan != null) {
            tableColumnTrangThaiCuDan.setCellValueFactory(cellData -> 
                new javafx.beans.property.SimpleStringProperty("Đang cư trú")); // CuDanTrongCanHoDto không có trangThaiCuTru
        }

        // Setup table phương tiện
        if (tableColumnMaPhuongTien != null) {
            tableColumnMaPhuongTien.setCellValueFactory(cellData -> 
                new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getSoThuTu())));
        }
        if (tableColumnBienSoXe != null) {
            tableColumnBienSoXe.setCellValueFactory(cellData -> 
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getBienSo()));
        }
        if (tableColumnLoaiPhuongTien != null) {
            tableColumnLoaiPhuongTien.setCellValueFactory(cellData -> 
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getLoaiPhuongTien()));
        }
        if (tableColumnNgayDangKi != null) {
            tableColumnNgayDangKi.setCellValueFactory(cellData -> 
                new javafx.beans.property.SimpleStringProperty(
                    cellData.getValue().getNgayDangKy() != null ? 
                    cellData.getValue().getNgayDangKy().toString() : ""));
        }

        // Setup table thu phí
        if (tableColumnMaHoaDon != null) {
            tableColumnMaHoaDon.setCellValueFactory(cellData -> 
                new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().getMaHoaDon())));
        }
        if (tableColumnTenKhoanThu != null) {
            tableColumnTenKhoanThu.setCellValueFactory(cellData -> 
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTenKhoanThu()));
        }
        if (tableColumnSoTien != null) {
            tableColumnSoTien.setCellValueFactory(cellData -> 
                new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getSoTien()));
        }
        if (tableColumnHanThu != null) {
            tableColumnHanThu.setCellValueFactory(cellData -> 
                new javafx.beans.property.SimpleStringProperty(
                    cellData.getValue().getNgayNop() != null ? 
                    cellData.getValue().getNgayNop().toString() : "Chưa nộp"));
        }
    }

    /**
     * Thiết lập các combo box
     */
    private void setupComboBoxes() {
        // Combo box số kết quả hiển thị
        ObservableList<String> soKetQuaItems = FXCollections.observableArrayList("10", "25", "50", "100");
        if (comboBoxSoKetQuaCuDan != null) {
            comboBoxSoKetQuaCuDan.setItems(soKetQuaItems);
            comboBoxSoKetQuaCuDan.setValue("10");
        }
        if (comboBoxSoKetQuaPhuongTien != null) {
            comboBoxSoKetQuaPhuongTien.setItems(soKetQuaItems);
            comboBoxSoKetQuaPhuongTien.setValue("10");
        }

        // Combo box loại phương tiện
        ObservableList<String> loaiPhuongTienItems = FXCollections.observableArrayList(
            "Xe máy", "Ô tô", "Xe đạp", "Xe tải", "Xe khác");
        if (comboBoxLoaiPhuongTien != null) {
            comboBoxLoaiPhuongTien.setItems(loaiPhuongTienItems);
        }

        // Combo box loại khoản thu
        ObservableList<String> loaiKhoanThuItems = FXCollections.observableArrayList(
            "Phí quản lý", "Phí dịch vụ", "Phí bảo trì", "Phí khác");
        if (comboBoxLoaiKhoanThu != null) {
            comboBoxLoaiKhoanThu.setItems(loaiKhoanThuItems);
        }
    }

    /**
     * Load dữ liệu mẫu (sẽ thay thế bằng service thật)
     */
    private void loadSampleData() {
        // Tạo dữ liệu mẫu cho căn hộ
        currentCanHo = new CanHoChiTietDto();
        currentCanHo.setMaCanHo("CH001");
        currentCanHo.setSoNha("101");
        currentCanHo.setTang("1");
        currentCanHo.setToaNha("A");
        currentCanHo.setDienTich(75.5);
        currentCanHo.setTrangThaiKiThuat("Tốt");
        currentCanHo.setTrangThaiSuDung("Đang sử dụng");

        // Load thông tin lên UI
        updateThongTinCanHo();
        
        // Khởi tạo danh sách
        cuDanList = FXCollections.observableArrayList();
        phuongTienList = FXCollections.observableArrayList();
        hoaDonList = FXCollections.observableArrayList();
        
        // Set data cho tables
        if (tableViewCuDan != null) {
            tableViewCuDan.setItems(cuDanList);
        }
        if (tableViewPhuongTien != null) {
            tableViewPhuongTien.setItems(phuongTienList);
        }
        if (tableViewThuPhi != null) {
            tableViewThuPhi.setItems(hoaDonList);
        }
    }

    /**
     * Cập nhật thông tin căn hộ lên UI
     */
    private void updateThongTinCanHo() {
        if (currentCanHo == null) return;

        if (labelMaCanHo != null) labelMaCanHo.setText(currentCanHo.getMaCanHo());
        if (labelSoNha != null) labelSoNha.setText(currentCanHo.getSoNha());
        if (labelTang != null) labelTang.setText(currentCanHo.getTang());
        if (labelToa != null) labelToa.setText(currentCanHo.getToaNha());
        if (labelDienTich != null) labelDienTich.setText(String.valueOf(currentCanHo.getDienTich()) + " m²");
        if (labelTinhTrangKiThuat != null) labelTinhTrangKiThuat.setText(currentCanHo.getTrangThaiKiThuat());
        if (labelTinhTrangSuDung != null) labelTinhTrangSuDung.setText(currentCanHo.getTrangThaiSuDung());
        
        // Cập nhật thông tin chủ sở hữu nếu có
        if (currentCanHo.getChuHo() != null) {
            if (labelMaDinhDanh != null) labelMaDinhDanh.setText(currentCanHo.getChuHo().getMaDinhDanh());
            if (labelHoVaTen != null) labelHoVaTen.setText(currentCanHo.getChuHo().getHoVaTen());
            if (labelNgaySinh != null) {
                labelNgaySinh.setText("N/A"); // ChuHoDto không có ngaySinh
            }
            if (labelGioiTinh != null) labelGioiTinh.setText("N/A"); // ChuHoDto không có gioiTinh
            if (labelTrangThaiChuSoHuu != null) labelTrangThaiChuSoHuu.setText(currentCanHo.getChuHo().getTrangThaiCuTru());
            if (labelSoDienThoai != null) labelSoDienThoai.setText(currentCanHo.getChuHo().getSoDienThoai());
            if (labelEmail != null) labelEmail.setText(currentCanHo.getChuHo().getEmail());
            
            // Hiển thị VBox chủ sở hữu
            if (vBoxChuSoHuu != null) vBoxChuSoHuu.setVisible(true);
        } else {
            // Ẩn VBox chủ sở hữu nếu chưa có chủ
            if (vBoxChuSoHuu != null) vBoxChuSoHuu.setVisible(false);
        }
    }

    // Tab navigation methods
    @FXML
    private void showThongTinTab() {
        hideAllTabs();
        if (anchorPaneThongTin != null) {
            anchorPaneThongTin.setVisible(true);
        }
        updateTabStyles("thongtin");
        System.out.println("Hiển thị tab Thông tin");
    }

    @FXML
    private void showCuDanTab() {
        hideAllTabs();
        if (anchorPaneCuDan != null) {
            anchorPaneCuDan.setVisible(true);
        }
        updateTabStyles("cudan");
        System.out.println("Hiển thị tab Cư dân");
    }

    @FXML
    private void showPhuongTienTab() {
        hideAllTabs();
        if (anchorPanePhuongTien != null) {
            anchorPanePhuongTien.setVisible(true);
        }
        updateTabStyles("phuongtien");
        System.out.println("Hiển thị tab Phương tiện");
    }

    @FXML
    private void showThuPhiTab() {
        hideAllTabs();
        if (anchorPaneThuPhi != null) {
            anchorPaneThuPhi.setVisible(true);
        }
        updateTabStyles("thuphi");
        System.out.println("Hiển thị tab Thu phí");
    }

    private void hideAllTabs() {
        if (anchorPaneThongTin != null) anchorPaneThongTin.setVisible(false);
        if (anchorPaneCuDan != null) anchorPaneCuDan.setVisible(false);
        if (anchorPanePhuongTien != null) anchorPanePhuongTien.setVisible(false);
        if (anchorPaneThuPhi != null) anchorPaneThuPhi.setVisible(false);
    }

    private void updateTabStyles(String activeTab) {
        // Reset all tab styles
        if (buttonThongTin != null) buttonThongTin.getStyleClass().removeAll("active-tab");
        if (buttonCuDan != null) buttonCuDan.getStyleClass().removeAll("active-tab");
        if (buttonPhuongTien != null) buttonPhuongTien.getStyleClass().removeAll("active-tab");
        if (buttonThuPhi != null) buttonThuPhi.getStyleClass().removeAll("active-tab");

        // Set active tab style
        switch (activeTab) {
            case "thongtin":
                if (buttonThongTin != null) buttonThongTin.getStyleClass().add("active-tab");
                break;
            case "cudan":
                if (buttonCuDan != null) buttonCuDan.getStyleClass().add("active-tab");
                break;
            case "phuongtien":
                if (buttonPhuongTien != null) buttonPhuongTien.getStyleClass().add("active-tab");
                break;
            case "thuphi":
                if (buttonThuPhi != null) buttonThuPhi.getStyleClass().add("active-tab");
                break;
        }
    }

    // Search and filter methods
    @FXML
    private void handleTimKiemCuDan() {
        String keyword = textFieldTimKiemCuDan != null ? textFieldTimKiemCuDan.getText() : "";
        System.out.println("Tìm kiếm cư dân với từ khóa: " + keyword);
        // TODO: Implement search logic
    }

    @FXML
    private void handleTimKiemPhuongTien() {
        String maSoXe = textFieldMaSoXe != null ? textFieldMaSoXe.getText() : "";
        String loaiPhuongTien = comboBoxLoaiPhuongTien != null ? comboBoxLoaiPhuongTien.getValue() : "";
        System.out.println("Tìm kiếm phương tiện - Mã số xe: " + maSoXe + ", Loại: " + loaiPhuongTien);
        // TODO: Implement search logic
    }

    @FXML
    private void handleTimKiemThuPhi() {
        String tenKhoanThu = textFieldTenKhoanThu != null ? textFieldTenKhoanThu.getText() : "";
        String loaiKhoanThu = comboBoxLoaiKhoanThu != null ? comboBoxLoaiKhoanThu.getValue() : "";
        System.out.println("Tìm kiếm thu phí - Tên khoản thu: " + tenKhoanThu + ", Loại: " + loaiKhoanThu);
        // TODO: Implement search logic
    }

    // Thu phí actions
    @FXML
    private void handleThuToanBo() {
        boolean baoGomBatBuoc = checkBoxKhongTinhBatBuoc != null ? !checkBoxKhongTinhBatBuoc.isSelected() : true;
        System.out.println("Thu toàn bộ - Bao gồm bắt buộc: " + baoGomBatBuoc);
        
        // TODO: Implement thu toàn bộ logic
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thu phí");
        alert.setHeaderText("Thu toàn bộ");
        alert.setContentText("Chức năng thu toàn bộ sẽ được triển khai!");
        alert.showAndWait();
    }

    @FXML
    private void handleXemLichSu() {
        System.out.println("Xem lịch sử thu phí");
        
        // TODO: Implement xem lịch sử logic
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Lịch sử");
        alert.setHeaderText("Lịch sử thu phí");
        alert.setContentText("Chức năng xem lịch sử sẽ được triển khai!");
        alert.showAndWait();
    }

    @FXML
    private void handleClose() {
        System.out.println("Đóng trang chi tiết căn hộ");
        
        // Quay về trang chủ hoặc đóng cửa sổ
        try {
            // Tìm BorderPane chính và load lại trang chủ
            javafx.scene.Node root = button_close_up.getScene().getRoot();
            if (root instanceof javafx.scene.layout.BorderPane) {
                javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                    getClass().getResource("/HomeTech/trang_chu_danh_sach.fxml"));
                javafx.scene.Parent homeContent = loader.load();
                ((javafx.scene.layout.BorderPane) root).setCenter(homeContent);
                System.out.println("Đã quay về trang chủ");
            }
        } catch (Exception e) {
            System.err.println("Không thể quay về trang chủ: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Set dữ liệu căn hộ từ bên ngoài
     */
    public void setCanHoData(CanHoChiTietDto canHoData) {
        this.currentCanHo = canHoData;
        updateThongTinCanHo();
        
        // Load danh sách con nếu có
        if (canHoData.getCuDanList() != null && cuDanList != null) {
            cuDanList.setAll(canHoData.getCuDanList());
        }
        if (canHoData.getPhuongTienList() != null && phuongTienList != null) {
            phuongTienList.setAll(canHoData.getPhuongTienList());
        }
        if (canHoData.getHoaDonList() != null && hoaDonList != null) {
            hoaDonList.setAll(canHoData.getHoaDonList());
        }
        
        // Cập nhật tổng số tiền
        updateTongSoTien();
    }

    /**
     * Cập nhật tổng số tiền từ danh sách hóa đơn
     */
    private void updateTongSoTien() {
        if (hoaDonList != null && labelTongSoTien != null) {
            int tongTien = hoaDonList.stream()
                .mapToInt(hd -> hd.getSoTien() != null ? hd.getSoTien() : 0)
                .sum();
            labelTongSoTien.setText(String.format("%,d VNĐ", tongTien));
        }
    }
} 
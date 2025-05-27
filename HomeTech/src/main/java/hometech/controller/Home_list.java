package hometech.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import hometech.util.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class Home_list implements Initializable {

    @FXML
    private BarChart<?, ?> barChartDanCu;

    @FXML
    private Button buttonNhapExcelCanHo;

    @FXML
    private Button buttonNhapExcelCuDan;

    @FXML
    private Button buttonNhapExcelTaiKhoan;

    @FXML
    private Button buttonSeeAllCanHo;

    @FXML
    private Button buttonSeeAllCuDan;

    @FXML
    private Button buttonSeeAllKhoanThu;

    @FXML
    private Button buttonThemCanHo;

    @FXML
    private Button buttonThemCuDan;

    @FXML
    private Button buttonThemKhoanThu;

    @FXML
    private Button buttonThemTaiKhoan;

    @FXML
    private Button buttonTimKiemCanHo;

    @FXML
    private Button buttonTimKiemCuDan;

    @FXML
    private Button buttonTimKiemCuDan1;

    @FXML
    private Button buttonTimKiemCuDan11;

    @FXML
    private Button buttonXuatExcelCanHo;

    @FXML
    private Button buttonXuatExcelCuDan;

    @FXML
    private Button buttonXuatExcelKhoanThu;

    @FXML
    private Button buttonXuatExcelTaiKhoan;

    @FXML
    private ComboBox<?> comboBoxResultNumber;

    @FXML
    private ComboBox<?> comboBoxResultNumberCuDan;

    @FXML
    private ComboBox<?> comboBoxResultNumberKhoanThu;

    @FXML
    private ComboBox<?> comboBoxResultNumberKhoanThu1;

    @FXML
    private ComboBox<?> comboBoxTang;

    @FXML
    private ComboBox<?> comboBoxToa;

    @FXML
    private ComboBox<?> comboBoxTrangThai;

    @FXML
    private ComboBox<?> comboBoxTrangThaiCuDan;

    @FXML
    private ComboBox<?> comboBoxTrangThaiHoaDon;

    @FXML
    private ComboBox<?> comboBoxTrangThaiTaiKhoan;

    @FXML
    private ComboBox<?> comboBoxVaiTro;

    @FXML
    private GridPane gridPaneTrangChu;

    @FXML
    private Label labelBienDongDanCu;

    @FXML
    private Label labelCanHo;

    @FXML
    private Label labelCanHoNumber;

    @FXML
    private Label labelCuDanNumber;

    @FXML
    private Label labelCuDanNumber1;

    @FXML
    private Label labelDanCu;

    @FXML
    private Label labelDanCu1;

    @FXML
    private Label labelHienThiKetQua;

    @FXML
    private Label labelHienThiKetQuaCuDan;

    @FXML
    private Label labelHienThiKetQuaKhoanThu;

    @FXML
    private Label labelHienThiKetQuaKhoanThu1;

    @FXML
    private Label labelKetQuaHienThi;

    @FXML
    private Label labelKetQuaHienThiCuDan;

    @FXML
    private Label labelKetQuaHienThiKhoanThu;

    @FXML
    private Label labelKetQuaHienThiKhoanThu1;

    @FXML
    private Label labelKhoanThuThangNay;

    @FXML
    private Label labelTongSoCan;

    @FXML
    private Label labelTongSoCuDan;

    @FXML
    private Label labelTongSoCuDan1;

    @FXML
    private PieChart pieChartKhoanThu;

    @FXML
    private ScrollPane scrollPaneCanHo;

    @FXML
    private ScrollPane scrollPaneCuDan;

    @FXML
    private ScrollPane scrollPaneKhoanThu;

    @FXML
    private ScrollPane scrollPaneTaiKhoan;

    @FXML
    private TableView<?> tabelViewCanHo;

    @FXML
    private TableView<?> tabelViewCuDan;

    @FXML
    private TableView<?> tabelViewKhoanThu;

    @FXML
    private TableView<?> tabelViewTaiKhoan;

    @FXML
    private TableColumn<?, ?> tableColumnBoPhanQuanLy;

    @FXML
    private TableColumn<?, ?> tableColumnChuSoHuu;

    @FXML
    private TableColumn<?, ?> tableColumnEmail;

    @FXML
    private TableColumn<?, ?> tableColumnGioiTinh;

    @FXML
    private TableColumn<?, ?> tableColumnHoVaTen;

    @FXML
    private TableColumn<?, ?> tableColumnHoVaTenTaiKhoan;

    @FXML
    private TableColumn<?, ?> tableColumnKiThuat;

    @FXML
    private TableColumn<?, ?> tableColumnLoaiKhoanThu;

    @FXML
    private TableColumn<?, ?> tableColumnMaCanHo;

    @FXML
    private TableColumn<?, ?> tableColumnMaCanHoCuDan;

    @FXML
    private TableColumn<?, ?> tableColumnMaDinhDanh;

    @FXML
    private TableColumn<?, ?> tableColumnMaKhoanThu;

    @FXML
    private TableColumn<?, ?> tableColumnNgayCapNhat;

    @FXML
    private TableColumn<?, ?> tableColumnNgaySinh;

    @FXML
    private TableColumn<?, ?> tableColumnNgayTao;

    @FXML
    private TableColumn<?, ?> tableColumnSoHuu;

    @FXML
    private TableColumn<?, ?> tableColumnSuDung;

    @FXML
    private TableColumn<?, ?> tableColumnTang;

    @FXML
    private TableColumn<?, ?> tableColumnTenKhoanThu;

    @FXML
    private TableColumn<?, ?> tableColumnThaoTac;

    @FXML
    private TableColumn<?, ?> tableColumnThaoTacCuDan;

    @FXML
    private TableColumn<?, ?> tableColumnThaoTacKhoanThu;

    @FXML
    private TableColumn<?, ?> tableColumnThaoTacTaiKhoan;

    @FXML
    private TableColumn<?, ?> tableColumnToaNha;

    @FXML
    private TableColumn<?, ?> tableColumnTrangThai;

    @FXML
    private TableColumn<?, ?> tableColumnTrangThaiCuDan;

    @FXML
    private TableColumn<?, ?> tableColumnTrangThaiHoaDon;

    @FXML
    private TableColumn<?, ?> tableColumnTrangThaiTaiKhoan;

    @FXML
    private TableColumn<?, ?> tableColumnVaiTro;

    @FXML
    private TextField textFieldBoPhanQuanLy;

    @FXML
    private TextField textFieldChuSoHuu;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldHoVaTen;

    @FXML
    private TextField textFieldHoVaTenTaiKhoan;

    @FXML
    private TextField textFieldLoaiKhoanThu;

    @FXML
    private TextField textFieldMaCanHo;

    @FXML
    private TextField textFieldMaCanHoCuDan;

    @FXML
    private TextField textFieldMaDinhDanh;

    @FXML
    private TextField textFieldMaKhoanThu;

    @FXML
    private TextField textFieldTenKhoanThu;

    /**
     * Khởi tạo controller
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Khởi tạo các component nếu cần
        System.out.println("Home_list controller được khởi tạo");
    }

    /**
     * Hàm mở trang chủ (refresh trang hiện tại)
     */
    @FXML
    public void refreshHomePage() {
        try {
            NavigationUtil.goToHomePage(gridPaneTrangChu);
        } catch (Exception e) {
            System.err.println("Không thể refresh trang chủ: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Hàm mở trang chủ từ ActionEvent
     */
    @FXML 
    public void goToHomePage(ActionEvent event) {
        NavigationUtil.goToHomePage(event);
    }
    
    /**
     * Hàm mở trang chủ trong cửa sổ mới
     */
    @FXML
    public void openHomePageInNewWindow() {
        NavigationUtil.openHomeListInNewWindow();
    }

    /**
     * Chuyển đến trang chi tiết căn hộ (đơn giản)
     */
    @FXML
    public void goToChiTietCanHo() {
        try {
            Stage currentStage = (Stage) gridPaneTrangChu.getScene().getWindow();
            NavigationUtil.openChiTietCanHo(currentStage);
        } catch (Exception e) {
            System.err.println("Không thể mở trang chi tiết căn hộ: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Chuyển đến chi tiết căn hộ trong cùng khung (load vào center area)
     */
    @FXML
    public void goToChiTietCanHoInFrame() {
        try {
            // Tìm KhungController từ scene root
            javafx.scene.Node root = gridPaneTrangChu.getScene().getRoot();
            if (root instanceof javafx.scene.layout.BorderPane) {
                // Load chi tiết căn hộ vào center area
                javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/HomeTech/chi_tiet_can_ho.fxml"));
                javafx.scene.Parent chiTietContent = loader.load();
                
                ((javafx.scene.layout.BorderPane) root).setCenter(chiTietContent);
                
                // Cập nhật label tên màn hình nếu có thể
                updateScreenLabelInFrame("Chi tiết căn hộ");
                
                System.out.println("Đã chuyển đến trang chi tiết căn hộ trong khung");
            }
        } catch (Exception e) {
            System.err.println("Không thể load chi tiết căn hộ vào khung: " + e.getMessage());
            e.printStackTrace();
            // Fallback: mở trong cửa sổ mới
            goToChiTietCanHo();
        }
    }
    
    /**
     * Cập nhật label tên màn hình trong khung chính
     */
    private void updateScreenLabelInFrame(String screenName) {
        try {
            javafx.scene.Node root = gridPaneTrangChu.getScene().getRoot();
            if (root instanceof javafx.scene.layout.BorderPane) {
                javafx.scene.layout.BorderPane borderPane = (javafx.scene.layout.BorderPane) root;
                
                // Tìm header area (top)
                javafx.scene.Node topArea = borderPane.getTop();
                if (topArea instanceof javafx.scene.layout.AnchorPane) {
                    javafx.scene.layout.AnchorPane headerPane = (javafx.scene.layout.AnchorPane) topArea;
                    
                    // Tìm label có fx:id="labelScreenName"
                    javafx.scene.Node labelNode = headerPane.lookup("#labelScreenName");
                    if (labelNode instanceof javafx.scene.control.Label) {
                        ((javafx.scene.control.Label) labelNode).setText(screenName);
                        System.out.println("Đã cập nhật tên màn hình: " + screenName);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Không thể cập nhật label tên màn hình: " + e.getMessage());
        }
    }
    
    /**
     * Xử lý sự kiện khi nhấn button "Xem Chi Tiết" từ table
     */
    @FXML
    public void handleXemChiTietCanHo() {
        // Lấy item được select từ table
        Object selectedItem = tabelViewCanHo.getSelectionModel().getSelectedItem();
        
        if (selectedItem != null) {
            // Mở trang chi tiết căn hộ trong cùng khung
            goToChiTietCanHoInFrame();
        } else {
            // Hiển thị thông báo chưa chọn căn hộ
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
            alert.setTitle("Chưa chọn căn hộ");
            alert.setHeaderText("Vui lòng chọn căn hộ");
            alert.setContentText("Hãy chọn một căn hộ từ danh sách để xem chi tiết.");
            alert.showAndWait();
        }
    }

    @FXML 
    public void buttonSeeAllCanHo(){
        // Chuyển đến tab "Căn hộ" trong khung chính
        try {
            // Tìm KhungController từ scene root
            javafx.scene.Node root = gridPaneTrangChu.getScene().getRoot();
            if (root instanceof javafx.scene.layout.BorderPane) {
                // Load trang quản lý căn hộ vào center area
                javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/HomeTech/quan_ly_can_ho.fxml"));
                javafx.scene.Parent canHoContent = loader.load();
                
                ((javafx.scene.layout.BorderPane) root).setCenter(canHoContent);
                
                // Cập nhật label tên màn hình
                updateScreenLabelInFrame("Quản lý căn hộ");
                
                System.out.println("Đã chuyển đến tab Căn hộ");
            }
        } catch (Exception e) {
            System.err.println("Không thể chuyển đến tab Căn hộ: " + e.getMessage());
            e.printStackTrace();
            // Fallback: chuyển đến chi tiết căn hộ như trước
            goToChiTietCanHoInFrame();
        }
    }
    
    /**
     * Chuyển đến trang danh sách căn hộ (nếu có trang riêng)
     * TODO: Tạo trang quan_ly_can_ho.fxml nếu cần
     */
    @FXML
    public void goToQuanLyCanHo() {
        try {
            // Tìm KhungController từ scene root
            javafx.scene.Node root = gridPaneTrangChu.getScene().getRoot();
            if (root instanceof javafx.scene.layout.BorderPane) {
                // TODO: Thay đổi đường dẫn khi có trang quản lý căn hộ riêng
                // Hiện tại chuyển đến chi tiết căn hộ
                javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/HomeTech/chi_tiet_can_ho.fxml"));
                javafx.scene.Parent content = loader.load();
                
                ((javafx.scene.layout.BorderPane) root).setCenter(content);
                System.out.println("Đã chuyển đến trang quản lý căn hộ");
            }
        } catch (Exception e) {
            System.err.println("Không thể load trang quản lý căn hộ: " + e.getMessage());
            e.printStackTrace();
            // Fallback: chuyển đến chi tiết căn hộ
            goToChiTietCanHoInFrame();
        }
    }
    
    /**
     * Method thay thế cho buttonSeeAllCanHo nếu muốn tạo trang danh sách căn hộ riêng
     */
    @FXML
    public void viewAllApartments() {
        goToQuanLyCanHo();
    }
    
}

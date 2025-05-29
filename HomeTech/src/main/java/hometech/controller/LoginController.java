package hometech.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button buttonDangNhap;

    @FXML
    private Button buttonNhanDienKhuonMat;

    @FXML
    private Button buttonQuenMatKhau;

    @FXML
    private CheckBox checkBoxHienMatKhau;

    @FXML
    private PasswordField passwordFieldMatKhau;

    @FXML
    private StackPane stackRoot;

    @FXML
    private Text textError;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldMatKhau;
    @FXML
    private Label labelScreenName;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Login được khởi tạo");
        textFieldMatKhau.setVisible(false);
        passwordFieldMatKhau.setVisible(true);
        checkBoxHienMatKhau.setSelected(false);
    }

    @FXML
    void showPassword(ActionEvent event) {
        if (checkBoxHienMatKhau.isSelected()) {
            // Nếu được chọn thì hiển thị mật khẩu
            textFieldMatKhau.setText(passwordFieldMatKhau.getText());
            textFieldMatKhau.setVisible(true);
            passwordFieldMatKhau.setVisible(false);
        } else {
            // Nếu bỏ chọn thì ẩn mật khẩu
            passwordFieldMatKhau.setText(textFieldMatKhau.getText());
            passwordFieldMatKhau.setVisible(true);
            textFieldMatKhau.setVisible(false);
        }
    }
    @FXML
    void dangNhapPressed(ActionEvent event) {
        // Lấy tài khoản và mật khẩu từ các TextField
        String email = textFieldEmail.getText().trim();
        String password = passwordFieldMatKhau.getText().trim();

        // Kiểm tra tài khoản và mật khẩu
        if (email.equals("abcde") && password.equals("12345")) {
            try {
                // Tải file FXML mới (khung.fxml)
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/HomeTech/khung.fxml"));
                Parent root = loader.load();

                // Tạo cửa sổ mới (Stage)
                Stage newStage = new Stage();
                Scene newScene = new Scene(root);
                newStage.setScene(newScene);

                // Hiển thị cửa sổ mới
                newStage.show();

                // Đóng cửa sổ hiện tại (cửa sổ đăng nhập)
                Stage currentStage = (Stage) stackRoot.getScene().getWindow();
                currentStage.close();
            } catch (IOException e) {
                e.printStackTrace();
                // Xử lý lỗi nếu không thể tải file FXML
            }
        } else {
            // Hiển thị thông báo sai tài khoản hoặc mật khẩu
            textError.setText("Sai tài khoản hoặc mật khẩu");
            textError.setVisible(true);
        }
    }


}

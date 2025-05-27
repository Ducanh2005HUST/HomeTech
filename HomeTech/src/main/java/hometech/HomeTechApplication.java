package hometech;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import hometech.util.NavigationUtil;

public class HomeTechApplication extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        NavigationUtil.openHomeList(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}



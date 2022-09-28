package com.fxsczxcj.game2048;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("2048");
        stage.setScene(scene);
        stage.show();





        Platform.setImplicitExit(false);
        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("退出游戏");
            alert.setHeaderText(null);
            alert.setContentText("您是否确定退出?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Platform.exit();
                stage.close();
            }
        });
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
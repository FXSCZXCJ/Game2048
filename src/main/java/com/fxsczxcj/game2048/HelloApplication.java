package com.fxsczxcj.game2048;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.util.Optional;

public class HelloApplication extends Application {
    public static HelloController myController ;
    public static void main(String[] args) {
        launch(args);
    }

    @NotNull
    private static EventHandler<KeyEvent> getEventHandler() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.printf("Key:%s\n",keyEvent.getCode());
            }
        };
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        myController = fxmlLoader.getController();
        stage.setTitle("2048");
        stage.setScene(scene);
        stage.show();

        scene.setOnKeyPressed(getEventHandler());


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
package vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 480, 720);
        stage.setTitle("Hamburguesería"); // Cambiar el título de la ventana
        stage.setScene(scene);
        stage.setResizable(false); // Deshabilitar el redimensionamiento de la ventana
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
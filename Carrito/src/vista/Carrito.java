package vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import poo.DatosPedido;

import java.io.IOException;

public class Carrito extends Application {

    /**
     * Inicia la aplicación JavaFX.
     *
     * @param primaryStage El Stage principal de la aplicación.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            // Cargar el FXML
            FXMLLoader fxmlLoader = new FXMLLoader(Carrito.class.getResource("Carrito.fxml"));
            Parent root = fxmlLoader.load();

            // Crear la escena
            Scene scene = new Scene(root, 480, 720);

            // Configurar el Stage
            primaryStage.setTitle("Hamburguesería");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            // Mostrar la ventana
            primaryStage.show();
            // Obtener el controlador y guardarlo en DatosPedido
            DatosPedido.controladorCarrito = fxmlLoader.getController();
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Método principal de la aplicación.
     *
     * @param args Los argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
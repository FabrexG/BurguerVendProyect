/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package burguervenda.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class MonitorearPedidoController implements Initializable {

   @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnLlamda;

    @FXML
    private Button btnMensaje;
    
     @FXML
    private Label lbCodigo;
    
    @FXML
    private Label lbTiempo;
     
    @FXML
    private Label lbRestaurante;

    @FXML
    private ProgressBar pbMonitoreo;
    
    private String codigoEntrega;
    
    private String codigoVerificacion;
    
    private int time = 120; 
    
    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    
    private boolean running = true;

    @FXML
    void abrirChat(ActionEvent event) throws IOException {
        running = false;
        Parent root = FXMLLoader.load(getClass().getResource("/burguervenda/vistas/BotChat.fxml"));
        anchorPane.getChildren().setAll(root);
    }
    
    public String generarCodigo(int len) {
        Random rand = new Random();
        StringBuilder codigo = new StringBuilder(len);
        for(int i = 0; i < len; i++) {
            int index = rand.nextInt(CARACTERES.length());
            codigo.append(CARACTERES.charAt(index));
        }
        return codigo.toString();
    }
    @FXML
    void confirmarEntregaPedido() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/burguervenda/vistas/ConfirmarEntregaPedido.fxml"));
        Parent root = loader.load();
        ConfirmarEntregaPedidoController controller = loader.getController();
        controller.setCodigo(codigoVerificacion);
        anchorPane.getChildren().setAll(root);
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 1; i <= 100 && running; i++) {
                    updateProgress(i, 100);
                    Thread.sleep(75);
                }
                return null;
            }
        };
        codigoEntrega = generarCodigo(4);
        codigoVerificacion = generarCodigo(4);
        lbCodigo.setText(codigoEntrega);
        System.out.println(codigoVerificacion);
        
        task.setOnSucceeded(event -> {
            if (pbMonitoreo.getProgress() >= 1.0) {
                try {
                    confirmarEntregaPedido();
            } catch (IOException ex) {
                    Logger.getLogger(MonitorearPedidoController.class.getName()).log(Level.SEVERE, null, ex);
             }
            }
        });
        pbMonitoreo.progressProperty().bind(task.progressProperty());
        
        new Thread(task).start(); 
    }    
    
}

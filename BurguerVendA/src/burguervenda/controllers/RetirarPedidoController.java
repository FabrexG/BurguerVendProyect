/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package burguervenda.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Brian Miguel Escalona Maldonado
 */
public class RetirarPedidoController implements Initializable {

    /**
     * Initializes the controller class.
     */    
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnConfirmarPedido;

    @FXML
    private Label lbCodigo;

    @FXML
    private Label lbInfo;

    @FXML
    private Label lbInfo1;

    @FXML
    private Label lbInfo2;
    
    private String codigoEntrega;
    
    private String codigoVerificacion;
    
    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    
    public void cargarInfo(String tipo) {
        if(tipo == "AutoVend") {
            lbInfo.setText("Acercate hasta el AutoVend y di el número ");
            lbInfo1.setText("para que podamos darte tu orden ");
            lbInfo2.setText("¡Disfrutala!");
            codigoEntrega = generarCodigo(4);
            codigoVerificacion = generarCodigo(4);
            lbCodigo.setText(codigoEntrega);
            System.out.println(codigoVerificacion);
        } else {
            lbInfo.setText("Acercate hasta el mostrador y dile el número a alguien ");
            lbInfo1.setText("de nuesto personal para que podamos darte tu orden ");
            lbInfo2.setText("¡Disfrutala!");
            codigoEntrega = generarCodigo(4);
            codigoVerificacion = generarCodigo(4);
            lbCodigo.setText(codigoEntrega);
            System.out.println(codigoVerificacion);
        }
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
    void confirmarEntregaPedido(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/burguervenda/vistas/ConfirmarEntregaPedido.fxml"));
        Parent root = loader.load();
        ConfirmarEntregaPedidoController controller = loader.getController();
        controller.setCodigo(codigoVerificacion);
        anchorPane.getChildren().setAll(root);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

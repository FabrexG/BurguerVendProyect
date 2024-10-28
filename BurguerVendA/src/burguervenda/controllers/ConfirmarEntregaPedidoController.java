/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package burguervenda.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ConfirmarEntregaPedidoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button btnConfirmar;

    @FXML
    private TextField txtfieldCodigo;
    
    private String codigo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @FXML
    void confirmarPedido(ActionEvent event) {
        if(txtfieldCodigo.getText().equals(codigo)){
            Alert alerta = new Alert(AlertType.CONFIRMATION,"Pedido entregado",ButtonType.YES);
            alerta.setTitle("Pedido completado");
            alerta.show();
            Stage stage= (Stage) btnConfirmar.getScene().getWindow();
            stage.close();
        }
        else {
            Alert alerta = new Alert(AlertType.ERROR,"Error codigo invalido",ButtonType.YES);
            alerta.setTitle("Codigo invalido");
            alerta.show();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

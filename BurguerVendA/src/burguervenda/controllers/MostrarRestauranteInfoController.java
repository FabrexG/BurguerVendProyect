/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package burguervenda.controllers;

import burguervenda.clases.Restaurante;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class MostrarRestauranteInfoController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private Button btnSeleccionar;

    @FXML
    private CheckBox cbFavoritos;

    @FXML
    private Label lbDireccion;

    @FXML
    private Label lbInfo;

    @FXML
    private Label lbNombre;
    
    private Restaurante resSelect;

    @FXML
    void cerrarInformacion(ActionEvent event) {
        if(cbFavoritos.isSelected()){
            resSelect.setFavorito(true);
            System.out.println("Añadido");
        }
    }
    
    public void loadInfo(Restaurante res){
        resSelect=res;
        lbNombre.setText(res.getNombre());
        lbDireccion.setText(res.getDireccion());
        lbInfo.setText("Abre a las "+res.getInfoApertura());
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

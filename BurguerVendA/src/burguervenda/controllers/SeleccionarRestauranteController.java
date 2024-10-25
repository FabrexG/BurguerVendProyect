/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package burguervenda.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class SeleccionarRestauranteController implements Initializable {
    
    @FXML
    private ComboBox<String> cbEligeRestaurante;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> array = new ArrayList<>();
    array.add("Lomas estrella");
    array.add("Periferico");
    array.add("Calle 11");
    array.add("San Lorenzo");

    cbEligeRestaurante.getItems().addAll(array);
    }    
    
}

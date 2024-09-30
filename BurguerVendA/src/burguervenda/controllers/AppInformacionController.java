/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package burguervenda.controllers;

import static javafx.application.Platform.exit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class AppInformacionController {
    @FXML
    private Button btnSalir;

    @FXML
    void btnSalirOnAction(ActionEvent event) {
        Stage stage= (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
    
}

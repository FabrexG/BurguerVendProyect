/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author achit
 */
public class insertaVideo {
    
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnVideo;
    
    @FXML
    void regresar(MouseEvent event) throws IOException {
        Parent pay = FXMLLoader.load(getClass().getResource("/burguertech/VQuejas.fxml"));
	anchorPane.getChildren().setAll(pay);

    }


    
    
    
}

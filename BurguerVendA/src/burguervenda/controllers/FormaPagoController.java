package burguervenda.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import dto.Producto;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

public class FormaPagoController implements Initializable {

    @FXML
    private Label etiquetaTotal;

    @FXML
    private Button btnFiftyCent;
    @FXML
    private Button btnOne;
    @FXML
    private Button btnTwo;
    @FXML
    private Button btnFive;
    @FXML
    private Button btnTen;

    @FXML
    private Button btnTwenty;
    @FXML
    private Button btnFifty;
    @FXML
    private Button btnHundred;
    @FXML
    private Button btnTwoHundred;
    @FXML
    private Button btnFiveHundred;
    @FXML
    private Button btnThousand;
    @FXML
    private Button btnEliminarPedido;

    private double total = 1800;
    private double totalO = total; //Guardamos el total para poder generar el ticket
    private double cambio = 0; //Almacenamos el cambio que se generara

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        etiquetaTotal.setText("El total es: $" + total);
    }

    // Monedas
    @FXML
    void payFiftyCent(MouseEvent event) {
        procesarPago(0.5);
    }

    @FXML
    void payOne(MouseEvent event) {
        procesarPago(1.0);
    }

    @FXML
    void payTwo(MouseEvent event) {
        procesarPago(2.0);
    }

    @FXML
    void payFive(MouseEvent event) {
        procesarPago(5.0);
    }

    @FXML
    void payTen(MouseEvent event) {
        procesarPago(10.0);
    }

    // Billetes
    @FXML
    void payTwenty(MouseEvent event) {
        procesarPago(20.0);
    }

    @FXML
    void payFifty(MouseEvent event) {
        procesarPago(50.0);
    }

    @FXML
    void payHundred(MouseEvent event) {
        procesarPago(100.0);
    }

    @FXML
    void payTwoHundred(MouseEvent event) {
        procesarPago(200.0);
    }

    @FXML
    void payFiveHundred(MouseEvent event) {
        procesarPago(500.0);
    }

    @FXML
    void payThousand(MouseEvent event) {
        procesarPago(1000.0);
    }

    // Método para procesar pago
    private void procesarPago(double cantidad) {
        if (this.total > 0) {
            // Si el pago cubre o excede el total, calculamos el cambio
            if (cantidad >= this.total) {
                double cambio = cantidad - this.total; //Aqui calculamos el cambio
                etiquetaTotal.setText("Pago completado. Tu cambio es: $" + cambio);
                this.total = 0; // Completamos el pago ya que se queda en 0s
            } else {
                // Si no se paga totalmente, calculamos su nuevo total y lo vamos imprimiento
                this.total -= cantidad;
                etiquetaTotal.setText("Nuevo Total: $" + this.total);//Impimimos el total que va quedando y si no se cubre el total se va restando al resultado
            }
        } else {
            etiquetaTotal.setText("Tu cuenta ya está en $0.0. No es necesario continuar pagando.");
        }
    }

    //Usaremos este metodo como pruebas para el ticket 
    private void opImprimirTicket(){
        int res = JOptionPane.showConfirmDialog(null, "¿Deseas imprimir el ticket?", "Confirmar",JOptionPane.YES_NO_OPTION);
        
        if (res == JOptionPane.YES_OPTION){
            //Aqui vamos a imprimir el ticket
        }
        
        
        
        
    }
    
    //Implementamos un boton para poder eliminar el pedido y cuando se borra se reinicia el pedido
    public void eliminarPedido(MouseEvent event) {
        // Crear el cuadro de diálogo de confirmación
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Eliminar Pedido");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Estás seguro que deseas eliminar el pedido?");

        // Mostrar el cuadro de diálogo y esperar la respuesta del usuario
        Optional<ButtonType> result = alerta.showAndWait();

        // Verificar si el usuario hizo clic en OK (equivalente a YES_OPTION)
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Restablecemos el total
            total = totalO;
            etiquetaTotal.setText("Pedido eliminado. El total es: $" + total);
        }
    }

}

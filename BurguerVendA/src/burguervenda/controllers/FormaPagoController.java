package burguervenda.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import dto.Producto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
  
    /*private String getTotalPay(List<Producto> listaProductos) {
        double subTotal = 0;
        for (Producto producto : listaProductos) {
            subTotal += producto.getPrecio();
        }
        return String.valueOf(subTotal);
    }*/
}

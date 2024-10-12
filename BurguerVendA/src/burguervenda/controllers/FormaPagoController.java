package burguervenda.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dto.Producto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class FormaPagoController implements Initializable {

	@FXML
	private Label etiquetaTotal;

	@FXML
	private Button btnFiftyCent;
	@FXML
	private Button btnOne;

	private double total=5;

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

	@FXML
	void payFiftyCent(MouseEvent event) {
		if (this.total > 0) {
			this.total = this.total - 0.5;
			etiquetaTotal.setText("Nuevo Total: $" + this.total);
		} else {
			etiquetaTotal.setText("Tu cuenta se encuentra en $0.0, No es necesario continuar pagar...");
		}

	}

	@FXML
	void payOne(MouseEvent event) {
		
		if (this.total > 0) {
			this.total = this.total - 1.0;
			etiquetaTotal.setText("Nuevo Total: $" + this.total);
		} else {
			etiquetaTotal.setText("Tu cuenta se encuentra en $0.0, No es necesario con el pago");
		}
	}

	private String getTotalPay(List<Producto> listaProductos) {
		double subTotal = 0;
		for (Producto producto : listaProductos) {
			subTotal = producto.getPrecio() + total;
		}
		return String.valueOf(subTotal).toString();

	}

}

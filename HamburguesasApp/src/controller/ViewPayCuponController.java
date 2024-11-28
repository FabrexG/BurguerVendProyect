package controller;

import java.io.IOException;

import dao.Cupon;
import dto.CuponDto;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ViewPayCuponController {
	
	
	private Cupon cupon; 
	@FXML
	private Label cambiar;

	@FXML
	private TextField txtCupon;

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Label totalDescuento;
	

    @FXML
    private Label totalFinal;

	
	private float pago;
	
	public float getPago() {
		return pago;
	}
	private boolean cuponCanjeado = false;
	
	public void setPago(float pago) {
		this.pago = pago;
	}

	public String getTotalDescuento() {
		return totalDescuento.getText();
	}

	public void setTotalDescuento(Float totalDescuentoIn) {
		 Platform.runLater(() -> {
		        if (totalDescuentoIn != null) {
		        	this.setPago(totalDescuentoIn);
		            this.totalDescuento.setText("Total sin descuento: " + totalDescuentoIn);
		        } else {
		            System.out.println("totalDescuento es null");
		        }
		    });
	}

	@FXML
	void goBack(MouseEvent event) throws IOException {
		
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Index.fxml"));
		    Parent pay = loader.load();
		    IndexController controller = loader.getController();

		    if (cuponCanjeado && cupon != null) { // Verifica que el cupón exista
		        controller.setPrecioFinal(getDescuentoFinal(pago, cupon.getDescuento())); // Usa el descuento del cupón
		        controller.getBtnCupones().setDisable(true);
		    } else {
		        controller.setPrecioFinal(pago); // No se aplica descuento
		        controller.getBtnCupones().setDisable(false);
		    }

		    anchorPane.getChildren().setAll(pay);
    
	}

	@FXML
	void changeCupon(MouseEvent event) throws IOException {
		if (txtCupon.getText().trim().isEmpty()) {
	        Alert alert = new Alert(Alert.AlertType.WARNING);
	        alert.setTitle("Advertencia");
	        alert.setHeaderText("Campo vacío");
	        alert.setContentText("Por favor ingresa un código de cupón.");
	        alert.showAndWait();
	        return;
	    }

	    String codigoCupon = txtCupon.getText().trim();
	    CuponDto cuponDao = new CuponDto();
	    cupon = cuponDao.getCupon(codigoCupon); // Guardamos el cupón en la variable de instancia

	    if (cupon != null && cupon.getStatus() == 'T') {
	        totalFinal.setText(String.format("Total Final con Descuento: %.2f", getDescuentoFinal(pago, cupon.getDescuento())));
	        cuponDao.executeUpdateCuton(codigoCupon);
	        cuponCanjeado = true;
	    } else {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Cupón no válido");
	        alert.setContentText("El cupón no existe o ya ha sido canjeado.");
	        alert.showAndWait();
	    }

	}

	@FXML
	public void initialize() {
	
	}

	public float getDescuentoFinal(float total, float descuento) {
		float finalTotal = (float) (total - (total * descuento));
		return finalTotal;
	}
	
}

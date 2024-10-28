
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfDocument;
import com.lowagie.text.pdf.PdfWriter;

import dao.Producto;
import dto.ProductoDto;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class IndexController implements Initializable {

	@FXML
	private Button btnCredito;
	@FXML
	private Button btnCash;
	@FXML
	private Button btnCupones;
	@FXML
	private AnchorPane anchorPane;
	
	 @FXML
	 private Label lbPrecio;
	
    @FXML
    private TableView<Producto> tbProductos;
	
	@FXML
	private TableColumn<Producto, String> colNombre;

	@FXML
	private TableColumn<Producto, Float> colPrecio;

	private ProductoDto productoDto = new ProductoDto();
	
	@FXML
	void payToCredit(MouseEvent event) throws IOException {
		Parent pay = FXMLLoader.load(getClass().getResource("/view/ViewPayCredit.fxml"));
		anchorPane.getChildren().setAll(pay);
	}

	@FXML
	void payToCash(MouseEvent event) throws IOException {
		
		
		StringBuilder ticket = new StringBuilder();
	    ticket.append("----- TICKET DE COMPRA -----\n");
	    
	    for (Producto producto : tbProductos.getItems()) { 
	        ticket.append(producto.getNombre())
	              .append(" - $")
	              .append(producto.getPrecio())
	              .append("\n");
	    }
	    
	    ticket.append("---------------------------\n");
	    ticket.append(lbPrecio.getText());
	    ticket.append("\n---------------------------\n");

	    // Mostrar el ticket en un cuadro de diálogo
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setTitle("Ticket de Compra");
	    alert.setHeaderText(null);
	    alert.setContentText(ticket.toString());
	    alert.showAndWait();
	    
	    btnCredito.setDisable(true);
	    btnCash.setDisable(true);
	    btnCupones.setDisable(true);
	    
	    
	    
	    lbPrecio.setText("Total: $ 0.00");
	    
	    tbProductos.getItems().clear();
	}

	@FXML
	void payToCupones(MouseEvent event) throws IOException {
		Parent pay = FXMLLoader.load(getClass().getResource("/view/ViewPayCupon.fxml"));
		anchorPane.getChildren().setAll(pay);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		float totalPago = 0;
		
		colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colPrecio.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getPrecio()).asObject());

        ObservableList<Producto> listaProductos = FXCollections.observableArrayList(productoDto.getListProductos());
        tbProductos.setItems(listaProductos);
        for (Producto producto : listaProductos) {
        	totalPago = producto.getPrecio() + totalPago;
		}
        lbPrecio.setText("Total: $ " + totalPago);
	}
	
	public void createTicket() {
	
		
	}
}

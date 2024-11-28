
package controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
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
import javafx.scene.control.ButtonType;
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

	public Button getBtnCredito() {
		return btnCredito;
	}

	public void setBtnCredito(Button btnCredito) {
		this.btnCredito = btnCredito;
	}

	public Button getBtnCash() {
		return btnCash;
	}

	public void setBtnCash(Button btnCash) {
		this.btnCash = btnCash;
	}

	public Button getBtnCupones() {
		return btnCupones;
	}

	public void setBtnCupones(Button btnCupones) {
		this.btnCupones = btnCupones;
	}

	@FXML
	private Label lbPrecio;

	@FXML
	private TableView<Producto> tbProductos;

	public ObservableList<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(ObservableList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	@FXML
	private TableColumn<Producto, String> colNombre;

	@FXML
	private TableColumn<Producto, Float> colPrecio;

	private ProductoDto productoDto = new ProductoDto();
	
	

	private ObservableList<Producto> listaProductos = FXCollections.observableArrayList(productoDto.getListProductos());

	public class NumeroALetras {

		private static final String[] UNIDADES = { "", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho",
				"nueve", "diez", "once", "doce", "trece", "catorce", "quince", "dieciséis", "diecisiete", "dieciocho",
				"diecinueve" };

		private static final String[] DECENAS = { "", "", "veinte", "treinta", "cuarenta", "cincuenta", "sesenta",
				"setenta", "ochenta", "noventa" };

		private static final String[] CENTENAS = { "", "ciento", "doscientos", "trescientos", "cuatrocientos",
				"quinientos", "seiscientos", "setecientos", "ochocientos", "novecientos" };

		public static String convertirNumeroALetras(double numero) {
			if (numero == 0) {
				return "cero";
			}

			String parteEntera = String.valueOf((int) numero);
			String parteDecimal = String.valueOf(numero).split("\\.")[1];

			String letrasEnteras = convertirParteEntera(parteEntera);
			String letrasDecimales = convertirParteDecimal(parteDecimal);

			return letrasEnteras + " con " + letrasDecimales;
		}

		private static String convertirParteEntera(String parteEntera) {
			int longitud = parteEntera.length();

			if (longitud == 1) {
				return UNIDADES[Integer.parseInt(parteEntera)];
			} else if (longitud == 2) {
				if (Integer.parseInt(parteEntera) < 20) {
					return UNIDADES[Integer.parseInt(parteEntera)];
				} else {
					int decena = Integer.parseInt(parteEntera.substring(0, 1));
					int unidad = Integer.parseInt(parteEntera.substring(1));
					return DECENAS[decena] + (unidad != 0 ? " y " + UNIDADES[unidad] : "");
				}
			} else if (longitud == 3) {
				int centena = Integer.parseInt(parteEntera.substring(0, 1));
				int decena = Integer.parseInt(parteEntera.substring(1, 2));
				int unidad = Integer.parseInt(parteEntera.substring(2));

				String centenaTexto = CENTENAS[centena];
				String decenaTexto = DECENAS[decena];
				String unidadTexto = UNIDADES[unidad];

				return centenaTexto
						+ (decena != 0 || unidad != 0 ? " " + decenaTexto + (unidad != 0 ? " y " + unidadTexto : "")
								: "");
			}

			return "";
		}

		private static String convertirParteDecimal(String parteDecimal) {
			if (parteDecimal.length() == 1) {
				return UNIDADES[Integer.parseInt(parteDecimal)] + " centavos";
			} else if (parteDecimal.length() == 2) {
				return DECENAS[Integer.parseInt(parteDecimal.substring(0, 1))] + " y "
						+ UNIDADES[Integer.parseInt(parteDecimal.substring(1))] + " centavos";
			}
			return "";
		}
	}

	@FXML
	void payToCredit(MouseEvent event) throws IOException {
		// Guardar el estado actual de los botones
	    ButtonStateManager.isBtnCreditoDisabled = btnCredito.isDisable();
	    ButtonStateManager.isBtnCashDisabled = btnCash.isDisable();
	    ButtonStateManager.isBtnCuponesDisabled = btnCupones.isDisable();

	    // Cambiar de ventana
	    Parent pay = FXMLLoader.load(getClass().getResource("/view/ViewPayCredit.fxml"));
	    anchorPane.getChildren().setAll(pay);
		
		
	}

	@FXML
	void payToCash(MouseEvent event) throws IOException {
		// Ventana emergente de confirmación
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmar Pago");
		alert.setHeaderText("¿Estás seguro de que deseas pagar en efectivo?");
		alert.setContentText("El pago no se puede revertir una vez confirmado.");

		ButtonType buttonYes = new ButtonType("Sí");
		ButtonType buttonNo = new ButtonType("No");
		alert.getButtonTypes().setAll(buttonYes, buttonNo);

		// Mostrar la alerta y esperar la respuesta del usuario
		alert.showAndWait().ifPresent(response -> {
			if (response == buttonYes) {
				// Si el usuario presiona "Sí", continuar con la generación del PDF
				// Deshabilitar botones después de hacer el pago
				btnCredito.setDisable(true);
				btnCash.setDisable(true);
				btnCupones.setDisable(true);

				try {
					// Crear el documento PDF
					Document document = new Document();

					// Nombre del archivo PDF
					String archivoPDF = "Factura_" + System.currentTimeMillis() + ".pdf";

					// Crear el escritor de PDF que escribirá en el archivo
					PdfWriter.getInstance(document, new FileOutputStream(archivoPDF));
					document.open();

					// Definir la alineación central para todos los párrafos
					int alineacionCentro = Element.ALIGN_CENTER;
					String imagenPath = "C:\\Users\\Jonha\\OneDrive\\Desktop\\ASEGURAMIENTO\\logo.jpg"; // Cambia la
																										// ruta a la
																										// ubicación de
																										// tu imagen
					try {
						Image logo = Image.getInstance(imagenPath); // Cargar la imagen desde el archivo

						// Centrar la imagen y ajustarla al tamaño deseado
						logo.setAlignment(Element.ALIGN_CENTER);
						logo.scaleToFit(50, 50); // Ajustar el tamaño (ancho, alto)
						document.add(logo); // Agregar la imagen al documento
					} catch (Exception e) {
						System.out.println("Error al cargar la imagen: " + e.getMessage());
					}

					// Nombre de la empresa
					Paragraph nombreEmpresa = new Paragraph("BURGERTECH", new Font(Font.HELVETICA, 16, Font.BOLD));
					nombreEmpresa.setAlignment(alineacionCentro);
					document.add(nombreEmpresa);

					// Separador después del nombre de la empresa
					Paragraph separador1 = new Paragraph("-------------------------------");
					separador1.setAlignment(alineacionCentro); // Centrado
					document.add(separador1);

					// Fecha actual
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					String fechaActual = sdf.format(new Date());
					Paragraph fecha = new Paragraph("Fecha: " + fechaActual);
					fecha.setAlignment(alineacionCentro); // Centrado
					document.add(fecha);

					// Folio aleatorio
					String folio = "" + new Random().nextInt(1000000);
					Paragraph folioParrafo = new Paragraph("Folio: " + folio);
					folioParrafo.setAlignment(alineacionCentro); // Centrado
					document.add(folioParrafo);

					// Separador después del folio
					Paragraph separador2 = new Paragraph("-------------------------------");
					separador2.setAlignment(alineacionCentro); // Centrado
					document.add(separador2);

					// Dirección de la empresa
					String direccion = "Prol. San Isidro 151, San Lorenzo Tezonco,";
					Paragraph direccionParrafo = new Paragraph("Dirección de la empresa:");
					direccionParrafo.setAlignment(alineacionCentro); // Centrado
					document.add(direccionParrafo);

					String direccion2 = "Iztapalapa, 09790 Ciudad de México, CDMX";
					Paragraph direccionParrafo2 = new Paragraph("");
					direccionParrafo2.setAlignment(alineacionCentro); // Centrado
					document.add(direccionParrafo2);
					Paragraph direccionTexto = new Paragraph(direccion);
					direccionTexto.setAlignment(alineacionCentro); // Centrado
					document.add(direccionTexto);
					Paragraph direccionTexto2 = new Paragraph(direccion2);
					direccionTexto2.setAlignment(alineacionCentro); // Centrado
					document.add(direccionTexto2);

					// Separador después de la dirección
					Paragraph separador3 = new Paragraph("-------------------------------");
					separador3.setAlignment(alineacionCentro); // Centrado
					document.add(separador3);

					// Número de caja aleatorio
					String numeroCaja = "Favor de pasar a la caja: " + (new Random().nextInt(6) + 1);
					Paragraph numeroCajaParrafo = new Paragraph(numeroCaja);
					numeroCajaParrafo.setAlignment(alineacionCentro); // Centrado
					document.add(numeroCajaParrafo);

					// Detalle de productos
					Paragraph tituloTicket = new Paragraph("----- TICKET DE COMPRA -----");
					tituloTicket.setAlignment(alineacionCentro); // Centrado
					document.add(tituloTicket);

					for (Producto producto : tbProductos.getItems()) {
						Paragraph productoParrafo = new Paragraph(producto.getNombre() + " - $" + producto.getPrecio());
						productoParrafo.setAlignment(alineacionCentro); // Centrado
						document.add(productoParrafo);
					}

					// Extraer solo los números y los puntos decimales
					String totalTexto = lbPrecio.getText().replaceAll("[^0-9.,]", "").trim(); // Elimina cualquier cosa que no sea número o coma/punto
					totalTexto = totalTexto.replace(",", "."); // Reemplaza las comas por puntos (si las hay)

					// Intentar convertir el total a número
					try {
					    double totalEnNumero = Double.parseDouble(totalTexto); // Convierte el valor a número

					    // Convertir a letras
					    String totalEnLetras = NumeroALetras.convertirNumeroALetras(totalEnNumero); // Convierte a letras

					    // Agregar el total en números al PDF
					    Paragraph totalNumeros = new Paragraph(
					            "Total: $" + totalTexto + "MXN" + " " + "(" + totalEnLetras + ")");
					    totalNumeros.setAlignment(alineacionCentro); // Centrado
					    document.add(totalNumeros);

					} catch (NumberFormatException e) {
					    // Si la conversión falla, muestra un error
					    e.printStackTrace();
					    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
					    errorAlert.setTitle("Error");
					    errorAlert.setHeaderText("No se pudo convertir el total a número.");
					    errorAlert.setContentText("Asegúrate de que el total tenga el formato correcto.");
					    errorAlert.showAndWait();
					}
					// Cerrar el documento PDF
					document.close();

					// Abrir el PDF generado con el visor predeterminado
					if (Desktop.isDesktopSupported()) {
						Desktop.getDesktop().open(new File(archivoPDF));
					}

					// Limpiar la tabla de productos y restablecer el total
					tbProductos.getItems().clear(); // Limpiar la tabla
					lbPrecio.setText("Total: $ 0.00"); // Restablecer el total

				} catch (Exception e) {
					e.printStackTrace();

					// Mostrar un mensaje de error si algo sale mal
					Alert errorAlert = new Alert(Alert.AlertType.ERROR);
					errorAlert.setTitle("Error");
					errorAlert.setHeaderText("No se pudo generar el archivo PDF.");
					errorAlert.setContentText(e.getMessage());
					errorAlert.showAndWait();
				}

			} else {
				Alert cancelAlert = new Alert(Alert.AlertType.INFORMATION);
				cancelAlert.setTitle("Pago Cancelado");
				cancelAlert.setHeaderText("El pago en efectivo ha sido cancelado.");
				cancelAlert.setContentText("Por favor, seleccione otra forma de pago.");
				cancelAlert.showAndWait();

				 if (!btnCredito.isDisable()) {
				        btnCredito.setDisable(false);
				    }
				    if (!btnCash.isDisable()) {
				        btnCash.setDisable(false);
				    }
				    if (!btnCupones.isDisable()) {
				        btnCupones.setDisable(false);
				    }
			}});
	}

	@FXML
	void payToCupones(MouseEvent event) throws IOException {
		try {

			float total = 0;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ViewPayCuponController.fxml"));
			Parent pay = loader.load();

			ViewPayCuponController controller = loader.getController();

			for (Producto producto : listaProductos) {
				total = producto.getPrecio() + total;
			}
			
			controller.setPago(total);
			controller.setTotalDescuento(total);

			anchorPane.getChildren().setAll(pay);

		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			btnCredito.setDisable(ButtonStateManager.isBtnCreditoDisabled);
		    btnCash.setDisable(ButtonStateManager.isBtnCashDisabled);
		    btnCupones.setDisable(ButtonStateManager.isBtnCuponesDisabled);
		
		float totalPago = 0;

		colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
		colPrecio.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getPrecio()).asObject());

		tbProductos.setItems(listaProductos);
		for (Producto producto : listaProductos) {
			totalPago = producto.getPrecio() + totalPago;
		}
		lbPrecio.setText("Total: $ " + String.format("%.2f ", totalPago));
	}

	public void setPrecioFinal(float precio) {
		lbPrecio.setText(String.format("Total a Pagar: $%.2f", precio));
	}
	
	public class ButtonStateManager {
	    public static boolean isBtnCreditoDisabled = false;
	    public static boolean isBtnCashDisabled = false;
	    public static boolean isBtnCuponesDisabled = false;
	}
}

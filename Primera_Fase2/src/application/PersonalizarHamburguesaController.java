package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PersonalizarHamburguesaController extends Ingrediente {
	
	@FXML
	private Button btnCalcular;

    @FXML
    private Button btnAgregar;

    @FXML
    private CheckBox checkCarne;

    @FXML
    private CheckBox checkJitomate;

    @FXML
    private CheckBox checkLechuga;

    @FXML
    private CheckBox checkPepinillos;

    @FXML
    private CheckBox checkPredeterminada;

    @FXML
    private CheckBox checkQueso;

    @FXML
    private CheckBox checkSalsa;

    @FXML
    private AnchorPane pane;
   
    @FXML
    private Label lblTotal;

    @FXML
    private TextField txtPrecioTotal;
    
    
    
    
    
    
    private int precioHamburguesa;
    private String nombre;
    private String ingredientes;
    
   
	
	public String getIngredientes() {
		return ingredientes;
	}

	
	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getPrecioHamburguesa() {
		return precioHamburguesa;
	} 
	double precio = 30;
	
    
	 ObservableList<Hamburguesa> listaHamburguesa = FXCollections.observableArrayList();

    //Ingredientes ing = new Ingredientes(getPrecioCarne(), getPrecioQueso(), getPrecioPepino(), getPrecioSalsa(), getPrecioLechuga(),getPrecioJitomate());
    Ingrediente ing = new Ingrediente();
	public void setPrecioHamburguesa(int precioHamburguesa) {
		this.precioHamburguesa = precioHamburguesa;
		
		 // Carga El Precio De la Hamburguesa para  visualizar el costo de su Hamburguesa
        txtPrecioTotal.setText(String.valueOf(precioHamburguesa));
	}


	@FXML
    void btnCalcular_OneClick(ActionEvent event) {
		
		int precioAcumulado = precioHamburguesa;
		  if (checkCarne.isSelected()) {
			  precioAcumulado += ing.calcularCosto(30.0);
			  //checkPredeterminada.setSelected(false);
		  }
		  if (checkQueso.isSelected()) {
			  precioAcumulado += ing.calcularCosto(30.0);
			  //checkPredeterminada.setSelected(false);
		  }
		  if (checkJitomate.isSelected()) {
			  precioAcumulado += ing.calcularCosto(30.0);
			  //checkPredeterminada.setSelected(false);
		  }
		  if (checkSalsa.isSelected()) {
			  precioAcumulado += ing.calcularCosto(30.0);
			  //checkPredeterminada.setSelected(false);
		  }
		  
		  if (checkPepinillos.isSelected()) {
			  precioAcumulado += ing.calcularCosto(30.0);
			  //checkPredeterminada.setSelected(false);
		  }
		  if (checkLechuga.isSelected()) {
			  precioAcumulado += ing.calcularCosto(30.0);
			  //checkPredeterminada.setSelected(false);
		  }
		  checkPredeterminada.setSelected(false);
		  

	        // Mostrar el subtotal en el campo de texto
	        txtPrecioTotal.setText(String.valueOf(precioAcumulado));

    }
    
    
    @FXML
    void btnAgregarOne_Click(ActionEvent event) {
    	String precioFinal = txtPrecioTotal.getText();
    	//int precioF = Integer.parseInt(precioFinal);
        
    	List<Extra> extra = new ArrayList<>();
    	if(checkCarne.isSelected()) {
    		//extra.add(new Extra("Carne Extra",ing.getPrecioCarne()));
    		extra.add(new Extra("Carne Extra", 30.0));
    	}
    	if (checkJitomate.isSelected()) {
    		//extra.add(new Extra("Jitomate Extra",ing.getPrecioJitomate()));
    		extra.add(new Extra("Jitomate Extra",30.0));
    	}
    	if (checkLechuga.isSelected()) {
    		//extra.add(new Extra("Lechuga Extra",ing.getPrecioLechuga()));
    		extra.add(new Extra("Lechuga Extra",30.0));
    	}
    	if (checkPepinillos.isSelected()) {
    		//extra.add(new Extra("Pepinillos Extra",ing.getPrecioPepino()));
    		extra.add(new Extra("Pepinillos Extra",30.0));
    	}
    	if (checkQueso.isSelected()) {
    		//extra.add(new Extra("Queseo Extra", ing.getPrecioQueso()));
    		extra.add(new Extra("Queseo Extra", 30.0));
    	}
    	if (checkSalsa.isSelected()) {
    		//extra.add(new Extra("Salsa Extra",ing.getPrecioSalsa()));
    		extra.add(new Extra("Salsa Extra",30.0));
    	}
    	if (checkPredeterminada.isSelected()) {
    		
    	}

    	String[] partes = getIngredientes().split(","); // Dividir los ingredientes por comas
        List<Ingrediente> ingredientes = new ArrayList<>();
        for (String parte : partes) {
            ingredientes.add(new Ingrediente(parte.trim(),0)); // Añadir cada ingrediente a la lista
        }
        
    	
    	Hamburguesa hamburguesa = new Hamburguesa(getNombre(),extra,ingredientes,precioHamburguesa,precioHamburguesa);
    	listaHamburguesa.add(hamburguesa);
    	System.out.println(hamburguesa.descripcion());
    	Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Confirmación del Pedido");
        alert.setContentText("El Precio Final De La Hamburguesa  es: " + precioFinal);
        alert.showAndWait();
        
        
        guardarHamburguesasEnArchivo();
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("GraficasHamburguesa.fxml"));
            Parent loginPane = loader.load();
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(new Scene(loginPane));
           
          } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void predetermindo_checkbox(ActionEvent event) {
    	
    	if (checkPredeterminada.isSelected()) {
    		checkCarne.setSelected(false);
    		checkJitomate.setSelected(false);
    		checkLechuga.setSelected(false);
    		checkPepinillos.setSelected(false);
    		checkQueso.setSelected(false);
    		checkSalsa.setSelected(false);
    		
    	  }  
    	

    }
 // Metodo para guardar la lista de hamburguesas en un archivo de texto
    public void guardarHamburguesasEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("hamburguesas.txt", true))) {
        	writer.write("\n===== Lista de Hamburguesas =====\n");
            for (Hamburguesa hamburguesa : listaHamburguesa) {
                writer.write(hamburguesa.descripcion());
                writer.newLine();
            }
            writer.write("\n===== Fin de la Lista =====\n");
            System.out.println("Hamburguesas guardadas en archivo exitosamente.");
        } catch (IOException e) {
            System.err.println("Error Al Guardar EL Pedido Al Archivo: " + e.getMessage());
        }
    }
    
    
}

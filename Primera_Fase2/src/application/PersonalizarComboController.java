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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;


public class PersonalizarComboController extends Ingrediente {
    @FXML private CheckBox CheckBox1;//Refrescos 
    @FXML private CheckBox CheckBox2;
    @FXML private CheckBox CheckBox3;
    @FXML private Button btnAgregar;
    @FXML private Button btnCalcular;
    @FXML private CheckBox complemento1CheckBox;
    @FXML private CheckBox complemento2CheckBox;
    @FXML private CheckBox complemento3CheckBox;
    @FXML private TextField txtPrecioFinal;

    
    ObservableList <Combo> listaCombo = FXCollections.observableArrayList();// Lista para almacenar los combos

    //IngredientesCombo complemento = new IngredientesCombo(getPrecioCombo(), getRefrescoManzanita(), getRefrescoMirinda(), getPrecioPapasGrandes(), getPrecioPapasMedianas(), getPrecioCarne());
    Ingrediente complemento = new Ingrediente();
    private int precioCombo;
    private String nombre;
    private String ingredientes;
    
    

   
	public String getIngredientes() {
		return ingredientes;
	}
	
	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}
	public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getPrecioCombo() { return precioCombo; }
    public void setPrecioCombo(int precioCombo) {
        this.precioCombo = precioCombo;
        txtPrecioFinal.setText(String.valueOf(precioCombo));
    }

    @FXML void btnAgregar_OneClick(ActionEvent event) {
        String precioFinal = txtPrecioFinal.getText();
        int precio = Integer.parseInt(txtPrecioFinal.getText());
        List<Extra> extras = new ArrayList<>();
        if (CheckBox1.isSelected()){
        	//extras.add(new Extra("Refresco Pepsi",complemento.getRefrescoPepsi()));
        	extras.add(new Extra("Refresco Pepsi",0.0));
        	CheckBox2.setSelected(false);
        	CheckBox3.setSelected(false);
         }else if(CheckBox2.isSelected()){
        	 //extras.add(new Extra("Refresco Manzana", getRefrescoManzanita()));
        	 extras.add(new Extra("Refresco Manzana",0.0));
        	 
         }else if (CheckBox3.isSelected()) {
        	 //extras.add(new Extra("Refresco Mirinda",getRefrescoMirinda()));
        	 extras.add(new Extra("Refresco Mirinda",0.0));
         }
        if (complemento1CheckBox.isSelected()) {
           //extras.add(new Extra("Papas Grandes", complemento.getPrecioPapasGrandes()));
            extras.add(new Extra("Papas Grandes",50.0));
        }
        if (complemento2CheckBox.isSelected()) {
            //extras.add(new Extra("Papas Medianas", complemento.getPrecioPapasMedianas()));
            extras.add(new Extra("Papas Medianas",0.0));
        }
        if (complemento3CheckBox.isSelected()) {
            //extras.add(new Extra("Carne", complemento.getPrecioCarne()));
            extras.add(new Extra("Carne",50.0));
        }
        
        
        
        String[] partes = getIngredientes().split(","); // Dividir los ingredientes por comas
        List<Ingrediente> ingredientes = new ArrayList<>();
        for (String parte : partes) {
            ingredientes.add(new Ingrediente(parte.trim(),0)); // Añadir cada ingrediente a la lista
        }
        // Crear el combo con los ingredientes seleccionados
        Combo combo = new Combo(nombre, precio,precioCombo,ingredientes, extras);
        
        listaCombo.add(combo); // Agregar el combo a la lista

        // Imprimir la descripción del combo en la consola
        System.out.println(combo.descripcionCompleta());

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Confirmación del Pedido");
        alert.setContentText("El Precio Final del Combo es: " + precioFinal);
        alert.showAndWait();
        guardarComboEnArchivo();
    }

    @FXML void btnCalcular_OneClick(ActionEvent event) {
        int subtotal = precioCombo;
       
        if (CheckBox1.isSelected()) {
            CheckBox2.setSelected(false);
            CheckBox3.setSelected(false);
            subtotal += complemento.calcularCosto(0.0);
        } else if (CheckBox2.isSelected()) {
            CheckBox1.setSelected(false);
            CheckBox3.setSelected(false);
            subtotal += complemento.calcularCosto(0.0);
        } else if (CheckBox3.isSelected()) {
            CheckBox1.setSelected(false);
            CheckBox2.setSelected(false);
            subtotal += complemento.calcularCosto(0.0);
        }

        if (complemento1CheckBox.isSelected()) {
            subtotal += complemento.calcularCosto(50.0);
            complemento2CheckBox.setSelected(false);
        }
        if (complemento2CheckBox.isSelected()) {
            subtotal += complemento.calcularCosto(0.0);
            complemento1CheckBox.setSelected(false);
        }
        
        
        if (complemento3CheckBox.isSelected()) {
            subtotal += complemento.calcularCosto(50.0);
        }

        txtPrecioFinal.setText(String.valueOf(subtotal));
    }
 // Metodo para guardar la lista de Combos en un archivo de texto
    public void guardarComboEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("combos.txt", true))) {
        	writer.write("\n===== Lista de Combos =====\n");
            for (Combo combo : listaCombo) {
                writer.write(combo.descripcionCompleta());
                writer.newLine();
            }
            writer.write("\n===== Fin de la Lista =====\n");
            System.out.println("Combo Guardado En El Archivo Exictosamente.");
        } catch (IOException e) {
            System.err.println("Error Al Guardar EL Pedido Al Archivo: " + e.getMessage());
        }
    }
    
    
}

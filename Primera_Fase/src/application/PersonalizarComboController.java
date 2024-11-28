package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.AnchorPane;
import conexion.ConectarBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;


public class PersonalizarComboController extends Ingrediente {
	 ConectarBD con = new ConectarBD();
	
	
    @FXML private CheckBox CheckBox1;//Refrescos 
    @FXML private CheckBox CheckBox2;
    @FXML private CheckBox CheckBox3;
    @FXML private Button btnAgregar;
    @FXML private Button btnCalcular;
    @FXML private CheckBox complemento1CheckBox;
    @FXML private CheckBox complemento2CheckBox;
    @FXML private CheckBox complemento3CheckBox;
    @FXML private TextField txtPrecioFinal;
    @FXML private AnchorPane pane;
   
    
    ObservableList <Combo> listaCombo = FXCollections.observableArrayList();// Lista para almacenar los combos

    //IngredientesCombo complemento = new IngredientesCombo(getPrecioCombo(), getRefrescoManzanita(), getRefrescoMirinda(), getPrecioPapasGrandes(), getPrecioPapasMedianas(), getPrecioCarne());
    Ingrediente complemento = new Ingrediente();
    private int precioCombo;
    private String nombre;
    private String ingredientes;
    private int idCombo;
    
    
    

   
	/**
	 * @return the idCombo
	 */
	public int getIdCombo() {
		return idCombo;
	}

	/**
	 * @param idCombo the idCombo to set
	 */
	public void setIdCombo(int idCombo) {
		this.idCombo = idCombo;
	}

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
        insertarPedido(idCombo);
        if (CheckBox1.isSelected()){
        	//extras.add(new Extra("Refresco Pepsi",complemento.getRefrescoPepsi()));
        	extras.add(new Extra("Refresco Pepsi",0.0));
        	//insertarExtra(idCombo, 9, 1);
        	insertarIngrediente(idCombo, 7, 1);
        	CheckBox2.setSelected(false);
        	CheckBox3.setSelected(false);
         }else if(CheckBox2.isSelected()){
        	 //extras.add(new Extra("Refresco Manzana", getRefrescoManzanita()));
        	 extras.add(new Extra("Refresco Manzana",0.0));
        	 //insertarExtra(idCombo, 10, 1);
        	 insertarIngrediente(idCombo, 8, 1);
        	 
         }else if (CheckBox3.isSelected()) {
        	 //extras.add(new Extra("Refresco Mirinda",getRefrescoMirinda()));
        	 extras.add(new Extra("Refresco Mirinda",0.0));
        	 //insertarExtra(idCombo, 11, 1);
        	 insertarIngrediente(idCombo, 9, 1);
         }
        if (complemento1CheckBox.isSelected()) {
           //extras.add(new Extra("Papas Grandes", complemento.getPrecioPapasGrandes()));
            extras.add(new Extra("Papas Grandes",50.0));
            insertarExtra(idCombo, 7, 1);
            //insertarIngrediente(idCombo, 11, 1);
        }
        if (complemento2CheckBox.isSelected()) {
            //extras.add(new Extra("Papas Medianas", complemento.getPrecioPapasMedianas()));
            extras.add(new Extra("Papas Medianas",0.0));
            //insertarExtra(idCombo, 12, 1);
            insertarIngrediente(idCombo, 6, 1);
        }
        if (complemento3CheckBox.isSelected()) {
            //extras.add(new Extra("Carne", complemento.getPrecioCarne()));
            extras.add(new Extra("Carne",50.0));
            insertarExtra(idCombo, 8, 1);
            //insertarIngrediente(idCombo, 12, 1);
           
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
        //guardarComboEnArchivo();
        
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("infoCombo.fxml"));
            Parent loginPane = loader.load();
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(new Scene(loginPane));
            InfoController controller = loader.getController();
            controller.setDesc(combo.descripcionCompleta());
           
          } catch (IOException e) {
            e.printStackTrace();
        }
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
        actualizarPrecioFinal(idCombo, subtotal);
        
    }
    
    private void insertarExtra(int idCombo, int idExtra, int cantidad) {
        String consulta = "INSERT INTO Combo_Extra (id_Combo, id_Extra, cantidad) VALUES (?, ?, ?)";
        try {
            con.conectarBDOracle();
            PreparedStatement stmt = con.cn.prepareStatement(consulta);
            stmt.setInt(1, idCombo);
            stmt.setInt(2, idExtra);
            stmt.setInt(3, cantidad);
            
            stmt.executeUpdate();
            System.out.println("Extra insertado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar el extra: " + e.getMessage());
        }
    }

    private void actualizarPrecioFinal(int idCombo, int nuevoPrecioFinal) {
        String consulta = "UPDATE Combo SET precio_final = ? WHERE id_Combo = ?";
        try {
            con.conectarBDOracle();
            PreparedStatement stmt = con.cn.prepareStatement(consulta);
            
            stmt.setInt(1, nuevoPrecioFinal); // Nuevo precio con ingredientes extras
            stmt.setInt(2, idCombo);    // ID de la hamburguesa a modificar
            
            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Precio final actualizado correctamente.");
            } else {
                System.out.println("No se encontro el id del combo con el ID proporcionado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar el precio final: " + e.getMessage());
        }
    }

    private void insertarIngrediente(int idCombo, int idIngrediente, int cantidad) {
        String consulta = "INSERT INTO Combo_Ing (id_Combo,id_Ingrediente, cantidad) VALUES(?, ?, ?)";
        try {
            con.conectarBDOracle();
            PreparedStatement stmt = con.cn.prepareStatement(consulta);
            stmt.setInt(1, idCombo);
            stmt.setInt(2, idIngrediente);
            stmt.setInt(3, cantidad);
            
            stmt.executeUpdate();
            System.out.println("Ingrediente insertado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar el ingrediente: " + e.getMessage());
        }
    }
    private void insertarPedido(int idCombo) {
    	String consultaSQL = "INSERT INTO PedidosC (id_Pedido, id_Combo, cantidad) " +
                "VALUES (seq_id_PedidoC.NEXTVAL, ?, ?)";
    try {
       con.conectarBDOracle();
       PreparedStatement stmt = con.cn.prepareStatement(consultaSQL);
       stmt.setInt(1, idCombo);
       stmt.setInt(2, 1); //Define la cantidad de veces que se agrega
	   
       stmt.executeUpdate();
       System.out.println("Combo añadido al pedido.");
		
	} catch (Exception e) {
		System.err.println("Error al Agregar pedido a la base de datos"+ e.getMessage());
	}
    	
    }
    
    
}

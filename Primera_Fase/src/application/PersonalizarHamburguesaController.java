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
import conexion.ConectarBD;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class PersonalizarHamburguesaController extends Ingrediente {
	ConectarBD con = new ConectarBD();
	
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
    private int idHamburguesa;
    
    
   
	
	/**
	 * @return the idHamburguesa
	 */
	public int getIdHamburguesa() {
		return idHamburguesa;
	}


	/**
	 * @param idHamburguesa the idHamburguesa to set
	 */
	public void setIdHamburguesa(int idHamburguesa) {
		this.idHamburguesa = idHamburguesa;
	}


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
	        actualizarPrecioFinal(idHamburguesa, precioAcumulado);
	        

    }
    
    
    @FXML
    void btnAgregarOne_Click(ActionEvent event) {
    	String precioFinal = txtPrecioTotal.getText();
    	//int precioF = Integer.parseInt(precioFinal);
        insertarPedido(idHamburguesa);
    	List<Extra> extra = new ArrayList<>();
    	if(checkCarne.isSelected()) {
    		//extra.add(new Extra("Carne Extra",ing.getPrecioCarne()));
    		extra.add(new Extra("Carne Extra", 30.0));
    		insertarExtra(idHamburguesa, 1, 1);
    	}
    	if (checkJitomate.isSelected()) {
    		//extra.add(new Extra("Jitomate Extra",ing.getPrecioJitomate()));
    		extra.add(new Extra("Jitomate Extra",30.0));
    		insertarExtra(idHamburguesa, 2, 1);
    	}
    	if (checkLechuga.isSelected()) {
    		//extra.add(new Extra("Lechuga Extra",ing.getPrecioLechuga()));
    		extra.add(new Extra("Lechuga Extra",30.0));
    		insertarExtra(idHamburguesa, 3, 1);
    	}
    	if (checkPepinillos.isSelected()) {
    		//extra.add(new Extra("Pepinillos Extra",ing.getPrecioPepino()));
    		extra.add(new Extra("Pepinillos Extra",30.0));
    		insertarExtra(idHamburguesa, 4, 1);
    	}
    	if (checkQueso.isSelected()) {
    		//extra.add(new Extra("Queseo Extra", ing.getPrecioQueso()));
    		extra.add(new Extra("Queseo Extra", 30.0));
    		insertarExtra(idHamburguesa, 5, 1);
    	}
    	if (checkSalsa.isSelected()) {
    		//extra.add(new Extra("Salsa Extra",ing.getPrecioSalsa()));
    		extra.add(new Extra("Salsa Extra",30.0));
    		insertarExtra(idHamburguesa, 6, 1);
    	}
    	if (checkPredeterminada.isSelected()) {
    		
    	}

    	String[] partes = getIngredientes().split(","); // Dividir los ingredientes por comas
        List<Ingrediente> ingredientes = new ArrayList<>();
        for (String parte : partes) {
            ingredientes.add(new Ingrediente(parte.trim(),0)); // Añadir cada ingrediente a la lista
            //insertarIngrediente(idHamburguesa, 1, 1);
        }
    	/*List<Ingrediente> ingredientes = new ArrayList<>();
        String[] partes = ingredientes.split(","); // Dividir los ingredientes por comas
        for (String parte : partes) {
            insertarIngrediente(idHamburguesa, 1, 1);
        }*/
    	Hamburguesa hamburguesa = new Hamburguesa(getNombre(),extra,ingredientes,precioHamburguesa,precioHamburguesa);
    	listaHamburguesa.add(hamburguesa);
    	System.out.println(hamburguesa.descripcion());
    	Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Confirmación del Pedido");
        alert.setContentText("El Precio Final De La Hamburguesa  es: " + precioFinal);
        alert.showAndWait();
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("infoHAmburguesa.fxml"));
            Parent loginPane = loader.load();
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(new Scene(loginPane));
            MostarController controller = loader.getController();
            controller.setDes(hamburguesa.descripcion());
            
           
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
 
    private void insertarIngrediente(int idHamburguesa, int idIngrediente, int cantidad) {
        String consulta = "INSERT INTO Ing_Hambu (id_Hamburguesa, id_Ingrediente, cantidad) VALUES(?, ?, ?)";
        try {
            con.conectarBDOracle();
            PreparedStatement stmt = con.cn.prepareStatement(consulta);
            stmt.setInt(1, idHamburguesa);
            stmt.setInt(2, idIngrediente);
            stmt.setInt(3, cantidad);
            
            stmt.executeUpdate();
            System.out.println("Ingrediente insertado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar el ingrediente: " + e.getMessage());
        }
    }
    
    private void insertarExtra(int idHamburguesa, int idExtra, int cantidad) {
        String consulta = "INSERT INTO H_Extra (id_Hamburguesa, id_Extra, cantidad) VALUES (?, ?, ?)";
        try {
        	con.conectarBDOracle();
        	PreparedStatement stmt = con.cn.prepareStatement(consulta);
            stmt.setInt(1, idHamburguesa);
            stmt.setInt(2, idExtra);
            stmt.setInt(3, cantidad);
            
            stmt.executeUpdate();
            System.out.println("Extra insertado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar el extra: " + e.getMessage());
        }
    }
    private void actualizarPrecioFinal(int idHamburguesa, int nuevoPrecioFinal) {
        String consulta = "UPDATE Hamburguesa SET precio_final = ? WHERE id_Hamburguesa = ?";
        try {
            con.conectarBDOracle();
            PreparedStatement stmt = con.cn.prepareStatement(consulta);
            
            stmt.setInt(1, nuevoPrecioFinal); // Nuevo precio con ingredientes extras
            stmt.setInt(2, idHamburguesa);    // ID de la hamburguesa a modificar
            
            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Precio final actualizado correctamente.");
            } else {
                System.out.println("No se encontró la hamburguesa con el ID proporcionado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar el precio final: " + e.getMessage());
        }
    }
    
    private void insertarPedido(int idHamburguesa) {
    	String consultaSQL = "INSERT INTO PedidosH (id_Pedido, id_Hamburguesa, cantidad) " +
                "VALUES (seq_id_PedidoH.NEXTVAL, ?, ?)";
    	try {
    		con.conectarBDOracle();
    		PreparedStatement stmt = con.cn.prepareStatement(consultaSQL);
    		
			stmt.setInt(1, idHamburguesa);
			stmt.setInt(2, 1); //Define la cantidad de veces que se agrega
			
			 // Ejecutar la consulta de inserción
	        stmt.executeUpdate();
	        System.out.println("Hamburguesa añadida al pedido.");
		} catch (Exception e) {
			System.err.println("Error al Agregar pedido a la base de datos"+ e.getMessage());
		}
    }
    
    
}

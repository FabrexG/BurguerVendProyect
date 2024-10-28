module HamburguesasApp {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires java.sql;
	requires ojdbc14;
	requires java.desktop;
	requires iText;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controller to javafx.fxml;
	exports controller;
}

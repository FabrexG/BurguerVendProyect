module Primera_Fase {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	requires com.oracle.database.jdbc;
	requires javafx.swing;
	requires java.xml;
	requires itext5.itextpdf;
	
	opens application to javafx.graphics, javafx.fxml;
	exports application;

}

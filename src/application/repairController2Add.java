package application;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class repairController2Add implements Initializable{

	@FXML
	Button cencelButton;
	@FXML
	Button addButton;
	@FXML
	TextField title; 
	@FXML
	TextArea note;
	@FXML
	ComboBox<String> moto;
	@FXML
	ComboBox<String> importantLvl;
	@FXML
	Button addImageNote;
	
	
	List<File> listFiles;
	int id_user;
	int repairMode;
	DataBase db;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		db = new DataBase();
	}
	public void cencel() {
		try {
			Stage primaryStage = (Stage) cencelButton.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root1 = loader.load(getClass().getResource("repairView2.fxml").openStream());
			repairController2 repairController2C = (repairController2) loader.getController();
			//Ustawianie id użytkownika
			
			repairController2C.setId(1);
			repairController2C.setRepairMode(1);
			Scene scene = new Scene(root1);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void saveNote() {
		if(!(listFiles.isEmpty())) {
			db.saveFiles(listFiles);
		}
		
	//	db.saveNote()
		//db.close();
	}
	
	public void getImage() {
		try {
			Stage primaryStage = (Stage) cencelButton.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root1 = loader.load(getClass().getResource("repairViewAdd2.fxml").openStream());
			repairController2Add2 repairController2Add2 = (repairController2Add2) loader.getController();
			//Ustawianie id użytkownika
			
			repairController2Add2.setId(id_user);
			repairController2Add2.setRepairMode(repairMode);
			Scene scene = new Scene(root1);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setRepairMode(int i) {
		// TODO Auto-generated method stub
		repairMode=i;
	}
	public void setId(int i) {
		// TODO Auto-generated method stub
		id_user=i;
	}
	public void setFiles(List<File> listFiles) {
		// TODO Auto-generated method stub
		this.listFiles = listFiles;
		for(File e: this.listFiles) {
			System.out.println(e.getName());
		}
	}
}
package application;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.LinkedList;
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
	
	
	List<File> listFiles = new LinkedList<>();
	int id_user;
	int repairMode;
	DataBase db;
	
	ObservableList<String> observableList1 = FXCollections.observableArrayList();
	List<Vehicle> list = new LinkedList<>();
	ObservableList<String> observableList2 = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		db = new DataBase();
		observableList1.add("Bardzo wa¿ne");
		observableList1.add("Wa¿ne");
		observableList1.add("Zwyk³e");
		observableList1.add("Ma³o wa¿ne");
		importantLvl.setItems(observableList1);
		list = db.getVehicles(id_user);
		for(Vehicle c: list) {
			observableList2.add(c.getMarka()+" "+c.getModel());
		}
		moto.setItems(observableList2);
	}
	public void cencel() {
		try {
			Stage primaryStage = (Stage) cencelButton.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root1 = loader.load(getClass().getResource("repairView2.fxml").openStream());
			repairController2 repairController2C = (repairController2) loader.getController();
			//Ustawianie id u¿ytkownika
			
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
		System.out.println("Jestem w saveNote()");
	if(moto.getSelectionModel().getSelectedItem() == null) {
		return;
	}
	String repairMode1 = (repairMode==1) ? "Wykonane" : "Do zrobienia";
		db.saveRepairNote(id_user, repairMode1, moto.getSelectionModel().getSelectedItem(), title.getText(), note.getText(), importantLvl.getSelectionModel().getSelectedItem(), listFiles);
		cencel();
	}
	
	public void getImage() {
		try {
			Stage primaryStage = (Stage) cencelButton.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root1 = loader.load(getClass().getResource("repairViewAdd2.fxml").openStream());
			repairController2Add2 repairController2Add2 = (repairController2Add2) loader.getController();
			//Ustawianie id u¿ytkownika
			
			repairController2Add2.setId(id_user);
			repairController2Add2.setList(listFiles);
			repairController2Add2.setRepairMode(repairMode);
			repairController2Add2.setNotes(title.getText(), note.getText(), moto.getSelectionModel().getSelectedItem(), importantLvl.getSelectionModel().getSelectedItem());
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
	public void setNotes(String title2, String note2, String moto2, String importantlvl2) {
		// TODO Auto-generated method stub
		title.setText(title2);
		note.setText(note2);
		moto.getSelectionModel().select(moto2);
		importantLvl.getSelectionModel().select(importantlvl2);
	}
}
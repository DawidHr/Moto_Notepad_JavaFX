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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class repairControllerViewSelectedNote implements Initializable{

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
	
	DataBase db;
	ObservableList<String> observableList1 = FXCollections.observableArrayList();
	List<Vehicle> list = new LinkedList<>();
	ObservableList<String> observableList2 = FXCollections.observableArrayList();
	List<File> listFiles = new LinkedList<>();
	int id_user;
	int repairMode;
	RepairNotes repairNote;
	
	
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
	
	public void setRepairMode(int i) {
		// TODO Auto-generated method stub
		repairMode=i;
	}
	public void setId(int i) {
		// TODO Auto-generated method stub
		id_user=i;
	}
	
	public void setData(int id) {
		repairNote = db.getRepairSelectedNote(id);
		title.setText(repairNote.getTitle());
		note.setText(repairNote.getNote());
		importantLvl.getSelectionModel().select(repairNote.getImportatntLvl());
		moto.getSelectionModel().select(repairNote.getMoto());
	}
	
	public void cencel() {
		try {
			Stage primaryStage = (Stage) cencelButton.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root1 = loader.load(getClass().getResource("repairView2.fxml").openStream());
			repairController2 repairController2C = (repairController2) loader.getController();
			//Ustawianie id u¿ytkownika
			repairController2C.setId(id_user);
			repairController2C.setRepairMode(repairMode);
			repairController2C.main();
			Scene scene = new Scene(root1);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Tu jestem
	
	public void saveNote() {
		System.out.println("Jestem w saveNote()");
	if(moto.getSelectionModel().getSelectedItem() == null) {
		return;
	}
	String repairMode1 = (repairMode==1) ? "Wykonane" : "Do zrobienia";
	System.out.println("===================================="
			+"\n id_user="+repairNote.getId());
		db.udpateRepairNote(repairNote.getId(),id_user, repairMode1, moto.getSelectionModel().getSelectedItem(), title.getText(), note.getText(), importantLvl.getSelectionModel().getSelectedItem(), listFiles);
		cencel();
	}
	
	public void getImage() {
		db.getImages(repairNote.getId_group_files());
	}
}
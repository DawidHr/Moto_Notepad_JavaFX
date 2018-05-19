package application;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class repairController2 implements Initializable{

	@FXML
	Button cencelButton;
	@FXML
	Button addButton;
	@FXML
	Button viewButton;
	@FXML
	Button deleteButton;
	
	int id_user;
	//Czy do naprawy czy naprawione
	int repairMode;
	DataBase db;
	
	@FXML
	TableView<RepairNotes> table;
	@FXML
	TableColumn<RepairNotes, String> title;
	@FXML
	TableColumn<RepairNotes, Date> date2;
	@FXML
	TableColumn<RepairNotes, String> lvl;
	List<RepairNotes> list = new LinkedList<>();
	ObservableList<RepairNotes> observableList1 = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		db = new DataBase();
	}

	public void main() {
		// TODO Auto-generated method stub
		String repairMode1 = (repairMode==1) ? "Wykonane" : "Do zrobienia";
		list = db.getRepairList(id_user, repairMode1);
		if(!list.isEmpty())
		{for(RepairNotes c: list) {
			System.out.println(c.toString());
		}
		observableList1.addAll(list);
		table.setItems(observableList1);
		title.setCellValueFactory(new PropertyValueFactory<RepairNotes, String>("title"));
		date2.setCellValueFactory(new PropertyValueFactory<RepairNotes, Date>("data_note"));
		lvl.setCellValueFactory(new PropertyValueFactory<RepairNotes, String>("importatntLvl"));}
	}
	public void setId(int i) {
		// TODO Auto-generated method stub
		id_user=i;
	}


	public void setRepairMode(int i) {
		// TODO Auto-generated method stub
		repairMode=i;
	}
	
	public void cencel() {
		try {
			Stage primaryStage = (Stage) cencelButton.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root1 = loader.load(getClass().getResource("mainView.fxml").openStream());
			mainController mainControllerc = (mainController) loader.getController();
			mainControllerc.setID(id_user);
			Scene scene = new Scene(root1);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("MOTO NOTATNIK");
			primaryStage.setResizable(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addNewNote() {
	try {
		System.out.println(" repairController2.java id_user="+id_user);
			Stage primaryStage = (Stage) addButton.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root2 = loader.load(getClass().getResource("repairViewAdd.fxml").openStream());
			repairController2Add repairController2Addc = (repairController2Add) loader.getController();
			repairController2Addc.setId(id_user);
			repairController2Addc.setRepairMode(repairMode);
			Scene scene = new Scene(root2);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("MOTO NOTATNIK");
			primaryStage.setResizable(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void viewNote() {
		try {
			Stage primaryStage = (Stage) addButton.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root2 = loader.load(getClass().getResource("repairViewViewSelectedNote.fxml").openStream());
			repairControllerViewSelectedNote repairControllerViewSelectedNotec = (repairControllerViewSelectedNote) loader.getController();
			repairControllerViewSelectedNotec.setId(id_user);
			repairControllerViewSelectedNotec.setRepairMode(repairMode);
			repairControllerViewSelectedNotec.setData(table.getSelectionModel().getSelectedItem().getId());
			Scene scene = new Scene(root2);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("MOTO NOTATNIK");
			primaryStage.setResizable(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete() {
		if(table.getSelectionModel().getSelectedItem() != null) {
		db.deleteRepairNote(table.getSelectionModel().getSelectedItem().getId()); 
		list.clear();
		observableList1.clear();
		main();
		}
	}



}
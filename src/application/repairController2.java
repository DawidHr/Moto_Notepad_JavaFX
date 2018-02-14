package application;

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
	
	int id_user;
	//Czy do naprawy czy naprawione
	int repairMode;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addNewNote() {
	try {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void viewNote() {
		
	}
}
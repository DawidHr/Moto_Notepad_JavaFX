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

public class repairController2Add2 implements Initializable{

	@FXML
	Button buttonAddFile;
	@FXML
	Button buttonDeleteFiles;
	@FXML
	Button buttonCencel;
	@FXML
	Button buttonSave;
	@FXML
	TextArea areaImageText;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	public void cencel() {
		try {
			Stage primaryStage = (Stage) buttonCencel.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root1 = loader.load(getClass().getResource("repairViewAdd.fxml").openStream());
			repairController2Add repairController2AddC = (repairController2Add) loader.getController();
			//Ustawianie id u¿ytkownika
			
			repairController2AddC.setId(1);
			repairController2AddC.setRepairMode(1);
			Scene scene = new Scene(root1);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void save() {
		
	}
	
	public void getImage() {
		
	}
	public void deleteImage() {
		
	}
}
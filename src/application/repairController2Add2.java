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
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
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
	
	int id_user;
	int repairMode;
	List<File> listFiles = new LinkedList<>();
	
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
		if(!(listFiles.isEmpty())) {
			for(File e: listFiles) {
				System.out.println(e.getName());
			}
		}
	}
	
	public void getImage() {
		FileChooser fileCh = new FileChooser();
		File selectedFile = fileCh.showOpenDialog(null); 
		if( selectedFile != null) {
			listFiles.add(selectedFile);
		}
	}
	public void deleteImage() {
		
	}
	public void setId(int i) {
		// TODO Auto-generated method stub
		id_user=i;
	}
	public void setRepairMode(int i) {
		// TODO Auto-generated method stub
		repairMode=i;
	}
}
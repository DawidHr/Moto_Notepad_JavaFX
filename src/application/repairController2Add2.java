package application;

import java.io.File;
import java.net.URL;
import java.security.cert.Extension;
import java.sql.Date;
import java.util.Collection;
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
import javafx.scene.control.ListView;
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
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class repairController2Add2 implements Initializable{

	//
	public String title, note, moto, importantlvl;
	
	@FXML
	Button buttonAddFile;
	@FXML
	Button buttonDeleteFiles;
	@FXML
	Button buttonCencel;
	@FXML
	Button buttonSave;
	@FXML
	ListView filesListView;
	
	int id_user;
	int repairMode;
//	List<File> listFiles = new LinkedList<>();
	List<File> listFiles;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		listFiles = new LinkedList<>();
	}
	public void cencel() {
		try {
			Stage primaryStage = (Stage) buttonCencel.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root1 = loader.load(getClass().getResource("repairViewAdd.fxml").openStream());
			repairController2Add repairController2AddC = (repairController2Add) loader.getController();
			//Ustawianie id u¿ytkownika
			
			repairController2AddC.setId(id_user);
			repairController2AddC.setRepairMode(repairMode);
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
	
	
	public void save() {
		try {
			Stage primaryStage = (Stage) buttonCencel.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root1 = loader.load(getClass().getResource("repairViewAdd.fxml").openStream());
			repairController2Add repairController2AddC = (repairController2Add) loader.getController();
			//Ustawianie id u¿ytkownika
			
			repairController2AddC.setId(id_user);
			repairController2AddC.setRepairMode(repairMode);
			repairController2AddC.setFiles(listFiles);
			repairController2AddC.setNotes(title, note, moto, importantlvl);
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
	
	public void getImage() {
		FileChooser fileCh = new FileChooser();
		ExtensionFilter filter = new ExtensionFilter("Obrazy","*.jpg", "*.jpeg", "*.gif", "*.bmp", "*.pdf");
		fileCh.getExtensionFilters().addAll(filter);
		File selectedFile = fileCh.showOpenDialog(null); 
		if( selectedFile != null) {
			filesListView.getItems().add(selectedFile.getName());
			listFiles.add(selectedFile);
		}
	}
	public void deleteImage() {
		System.out.println("Witaj jestem w kasacji");
		listFiles.clear();
		//filesListView.getItems().removeAll();
		filesListView.getItems().clear();
		System.out.println("listfiles ="+listFiles.size());
	}
	public void setId(int i) {
		// TODO Auto-generated method stub
		id_user=i;
	}
	public void setRepairMode(int i) {
		// TODO Auto-generated method stub
		repairMode=i;
	}
	public void setList(List<File> listFiles2) {
		// TODO Auto-generated method stub
		listFiles.addAll(listFiles2);
		if(!(listFiles2.isEmpty())) {
			for(int i=0; i<listFiles2.size();i++)
			filesListView.getItems().add(listFiles2.get(i).getName());
		}
	}
	public void setNotes(String text, String text2, String selectedItem, String selectedItem2) {
		// TODO Auto-generated method stub
		title=text;
		note=text2;
		moto=selectedItem;
		importantlvl=selectedItem2;
	}
}
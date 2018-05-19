package application;

import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class profileController implements Initializable{

	@FXML
	Label NameProfileLabel;
	@FXML
	Label CountMotoLabel;
	@FXML
	Label CountRepairNotesDoneLabel;
	@FXML
	Label CountRepairNotesNotDoneLabel;
	@FXML
	Label CountNotesLabel;
	@FXML
	Button CencelButton;
	@FXML
	Button ChangeButton;
	
	DataBase db;
	int id;
	String profileNameString; 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		db = new DataBase();
	
	}
	public void setId(int id) {
		this.id=id;
	}
	public void setProfileName() {
		String temp="";
		int tempInt=0;
		
		profileNameString = db.getProfileName(id);
		temp =NameProfileLabel.getText()+" "+profileNameString;
		NameProfileLabel.setText(temp);
		
		tempInt = db.getCountMotoForUser(id);
		temp =CountMotoLabel.getText()+" "+tempInt;
		CountMotoLabel.setText(temp);
		
		tempInt = db.getCountNotesForRepair(id, "Wykonane");
		temp = CountRepairNotesDoneLabel.getText()+ " "+tempInt;
		CountRepairNotesDoneLabel.setText(temp);
		
		tempInt = db.getCountNotesForRepair(id, "Do zrobienia");
		temp = CountRepairNotesNotDoneLabel.getText() + " " + tempInt;
		CountRepairNotesNotDoneLabel.setText(temp);
		
		tempInt = db.getCountNotes(id);
		temp = CountNotesLabel.getText()+" "+tempInt;
		CountNotesLabel.setText(temp);
	}
	
	public void changUserInfo() {
		try {
			db.close();
			Stage primaryStage = (Stage) CencelButton.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root1 = loader.load(getClass().getResource("profileEdit.fxml").openStream());
			profileEditController profileEditControllerc = (profileEditController) loader.getController();
			profileEditControllerc.setId(id);
			profileEditControllerc.setProfileName(profileNameString);
			Scene scene = new Scene(root1);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void cencel() {
		try {
			db.close();
			Stage primaryStage = (Stage) CencelButton.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root1 = loader.load(getClass().getResource("mainView.fxml").openStream());
			mainController mainControllerc = (mainController) loader.getController();
			mainControllerc.setID(id);
			Scene scene = new Scene(root1);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
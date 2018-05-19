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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class profileEditController implements Initializable{

	@FXML
	Button cencelButton;
	@FXML
	Button saveButton;
	@FXML
	TextField profileName;
	@FXML
	PasswordField password1;
	@FXML
	TextField mail;
	
	
	DataBase db;
	int id;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		db = new DataBase();
	
	}
	public void setId(int id) {
		this.id=id;
	}
	public void setProfileName(String name) {
		profileName.setText(name);
		profileName.setEditable(false);
	}
	
	public void changUserInfo() {
		String pass = password1.getText().trim();
		String mail1 = mail.getText().trim();
		db.updateProfile(id, pass, mail1);
		cencel();
	}
	
	
	public void cencel() {
		try {
			db.close();
			Stage primaryStage = (Stage) cencelButton.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root1 = loader.load(getClass().getResource("mainView.fxml").openStream());
			mainController mainControllerc = (mainController) loader.getController();
			mainControllerc.setID(id);
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
}
package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable{

	@FXML
	public ComboBox<String> userName;
	@FXML
	public PasswordField userPassword;
	DataBase db;
	List<String> userNamesList;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		db = new DataBase();
		List<String> userNamesList = db.getUserNames();
		System.out.println("Lista U¿ytkowników");
		for(String element: userNamesList) {
			System.out.println(element);
		}
		ObservableList<String> listUsers = FXCollections.observableArrayList(userNamesList);
		userName.setItems(listUsers);
	}
	
	
	
	public void loginToApp() {
		System.out.println(userName.getSelectionModel().getSelectedItem());
		int id = db.Login(userName.getSelectionModel().getSelectedItem(), userPassword.getText());
		if(id==0) {
			
		}
		else {
			try {
			db.close();
			Stage primaryStage = (Stage) userName.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root1 = loader.load(getClass().getResource("mainView.fxml").openStream());
			mainController main = (mainController) loader.getController();
			main.setID(id);
			Scene scene = new Scene(root1);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void registerNewUser() {
		try {
			db.close();
			Stage primaryStage = (Stage) userName.getScene().getWindow();
			Parent root1 = FXMLLoader.load(getClass().getResource("mainRegisterView.fxml"));
			Scene scene = new Scene(root1);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void sendPasswordToMail() {
		
	}



	
	
	/*public void saveUser() {
		System.out.println("Nazwa uzytkownika: "+userName.getText()
						+"\nHaslo Uzytkownika: "+userPassword.getText()
						+"\nMail Uzytkownika: "+userMail.getText());
		
		if(userName.getText().trim().isEmpty() || userPassword.getText().trim().isEmpty()) {
			
		}
		else {
			DataBase db = new DataBase();
			//Sprawdzanie czy u¿ytkownik o podanej nazwie ju¿ nie istnieje
			boolean findUser = db.searchUserbyName(userName.getText().trim());
			if(findUser) {
				db.close();
			}
			else {
			db.addNewUser(userName.getText().trim(), userPassword.getText().trim(), userMail.getText().trim());
			db.close();
			Stage primaryStage = (Stage) userName.getScene().getWindow();
			try {
			Parent root1 = FXMLLoader.load(getClass().getResource("mainLoginView.fxml"));
			Scene scene = new Scene(root1);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			}
			catch(Exception e) {
				e.printStackTrace();
			}}
		}
	}*/
}

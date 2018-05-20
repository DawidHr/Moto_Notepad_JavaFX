package application;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LoginController implements Initializable{

	@FXML
	public ComboBox<String> userName;
	@FXML
	public PasswordField userPassword;
	DataBase db;
	List<String> userNamesList;
	@FXML
	ImageView LoginImage;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		System.out.println("jestem w init");
		db = new DataBase();
		List<String> userNamesList = db.getUserNames();
		ObservableList<String> listUsers = FXCollections.observableArrayList(userNamesList);
		userName.setItems(listUsers);
	}
	
	
	@FXML
	public void comboBoxFunctionChangeImage() {
	File imageFile = db.getIconByUserName(userName.getSelectionModel().getSelectedItem());
		if(imageFile!=null) {
		String ss = "file:"+imageFile.getAbsolutePath();
		Image img = new Image(ss);
		LoginImage.setImage(img);	
	} 
	else {
		LoginImage.setPreserveRatio(false);
		LoginImage.setImage(new Image("img/indeks.jpg", 200, 200, false, false));
		
	}
	}
	
	public void loginToApp() {
		if(db.isClose()) {
			db = new DataBase();
		}
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
			primaryStage.setTitle("MOTO NOTATNIK");
			primaryStage.setResizable(false);
			
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void registerNewUser() {
		if(db.isClose()) {
			db = new DataBase();
		}
		try {
			db.close();
			Stage primaryStage = (Stage) userName.getScene().getWindow();
			Parent root1 = FXMLLoader.load(getClass().getResource("mainRegisterView.fxml"));
			Scene scene = new Scene(root1);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("MOTO NOTATNIK");
			primaryStage.setResizable(false);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void sendPasswordToMail() {
		if(db.isClose()) {
			db = new DataBase();
		}
		System.out.println("Jestem w przypomnieniu hasla");
		int id = db.Login(userName.getSelectionModel().getSelectedItem());
		if(id==0) {
			System.out.println("id="+id);
		}
		else {
			System.out.println("Jestem w else");
			System.out.println("id="+id);
		try {
			db.close();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Parent root1 = loader.load(getClass().getResource("mainLoginView2.fxml").openStream());
			mainController2 mainController2c = (mainController2) loader.getController();
			mainController2c.setId(id);
			mainController2c.main();
			Scene scene = new Scene(root1);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("MOTO NOTATNIK");
			primaryStage.setResizable(false);
				
		}
			catch(Exception e) {
				e.printStackTrace();
			}}
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

package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {

	@FXML
	public TextField userName;
	@FXML
	public PasswordField userPassword;
	@FXML
	public TextField userMail;
	
	public void saveUser() {
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
				System.out.println("Dodawanie u¿ytkownika");
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
	}
}

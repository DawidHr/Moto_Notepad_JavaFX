package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class fuelController implements Initializable {

	@FXML
	Button buttonCencel;
	@FXML
	Button buttonDelete;
	@FXML 
	Button buttonAdd;
	DataBase db;
	int id;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		db = new DataBase();
	}
	public void setId(int id2) {
		// TODO Auto-generated method stub
		id=id2;
	}

	public void cencel() {
		try {
		db.close();
		Stage primaryStage = (Stage) buttonCencel.getScene().getWindow();
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
		public void delete() {
			
		}
		
		public void addMoto() {
			db.findIfIsMoto(id);
			
			try {
				db.close();
				Stage primaryStage = (Stage) buttonCencel.getScene().getWindow();
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

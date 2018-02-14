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

public class repairController implements Initializable{

	@FXML
	Button buttonRepairDone;
	@FXML
	Button buttonRepairPlane;
	
	int id_user;
	int repairMode;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void setId(int id) {
		id_user=id;
	}
	
	public void moveToRepairDone() {
		repairMode=1;
		moveTo();
	}
	
	public void moveToRepairPlane() {
		repairMode=2;
		moveTo();
	}
	
	public void moveTo() {
		try {
			Stage primaryStage = (Stage) buttonRepairDone.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root1 = loader.load(getClass().getResource("repairView2.fxml").openStream());
			repairController2 repairController2c = (repairController2) loader.getController();
			repairController2c.setId(id_user);
			repairController2c.setRepairMode(repairMode);
			Scene scene = new Scene(root1);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

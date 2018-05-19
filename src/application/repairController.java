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
	int repairMode=0;
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
			System.out.println(" repairController.java id_user="+id_user);
			Stage primaryStage = (Stage) buttonRepairDone.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root1 = loader.load(getClass().getResource("repairView2.fxml").openStream());
			repairController2 repairController2c = (repairController2) loader.getController();
			repairController2c.setId(id_user);
			repairController2c.setRepairMode(repairMode);
			repairController2c.main();
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

package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class mainController implements Initializable {

	@FXML
	Button buttonProfile;
	@FXML
	Button buttonCar;
	@FXML
	Button buttonCombustion;
	@FXML
	Button buttonRepair;
	@FXML
	Button buttonNote;
	@FXML
	Button buttonLogeOff;

	DataBase db;
	int id;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	db = new DataBase();
	}

	public void setID(int id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	// Profil
	public void movetoProfileScreen() {
		System.out.println("Za chwile przejdziemy do Profilu");
		try {
			/*
			 * Zamykanie sceny
			 * 
			 * Stage stage1 = (Stage) buttonRepair.getScene().getWindow(); stage1.close();
			 */
			db.close();
			Stage primaryStage = (Stage) buttonCar.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root1 = loader.load(getClass().getResource("profileView.fxml").openStream());
			profileController profileControllerc = (profileController) loader.getController();
			profileControllerc.setId(id);
			profileControllerc.setProfileName();
			Scene scene = new Scene(root1);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Pojazdy
	public void movetoVehicleScreen() {
		System.out.println("Za chwile sprawdzmy Pojazdy");
		try {
			/*
			 * Zamykanie sceny
			 * 
			 * Stage stage1 = (Stage) buttonRepair.getScene().getWindow(); stage1.close();
			 */
			db.close();
			Stage primaryStage = (Stage) buttonCar.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root1 = loader.load(getClass().getResource("vehiclesView.fxml").openStream());
			vehicleController vehicleControllerc = (vehicleController) loader.getController();
			vehicleControllerc.setId(id);
			Scene scene = new Scene(root1);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Spalanie
	public void movetoFuelScreen() {
		System.out.println("Za chwile sprawdzimy Spalanie");
		try {
			db.close();
			Stage primaryStage = (Stage) buttonNote.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root1 = loader.load(getClass().getResource("fuelView.fxml").openStream());
			fuelController fuelControllerc = (fuelController) loader.getController();
			fuelControllerc.setId(id);
			fuelControllerc.main();
			Scene scene = new Scene(root1);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	// Naprawy
	public void movetoRepairScreen() {
		System.out.println("Zachwile przejdziemy do Napraw");

		try {
			List<Vehicle> list = db.getVehicles(id);
			closeDB();
			if(list.isEmpty()) {
				movetoVehicleScreen();
			}
			/* * Zamykanie sceny
			 * 
			 * Stage stage1 = (Stage) buttonRepair.getScene().getWindow(); stage1.close();
			 */
			else {
			Stage primaryStage = (Stage) buttonRepair.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root1 = loader.load(getClass().getResource("repairView.fxml").openStream());
			repairController repairControllerc = (repairController) loader.getController();
			repairControllerc.setId(id);
			Scene scene = new Scene(root1);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show(); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Notatki
	public void movetoNoteScreen() {
		System.out.println("Za chwile przejdziemy do Notatek");

		try {
			/*
			 * Zamykanie sceny
			 * 
			 * Stage stage1 = (Stage) buttonRepair.getScene().getWindow(); stage1.close();
			 */
			closeDB();
			Stage primaryStage = (Stage) buttonNote.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root1 = loader.load(getClass().getResource("noteView.fxml").openStream());
			noteController noteView = (noteController) loader.getController();
			noteView.setId(id);
			Scene scene = new Scene(root1);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Wyloguj
	public void logOff() {
		System.out.println("W³aœnie siê wylogowa³eœ");
		try {
			closeDB();
			Stage primaryStage = (Stage) buttonNote.getScene().getWindow();
			Parent root1 = FXMLLoader.load(getClass().getResource("mainLoginView.fxml"));
			Scene scene = new Scene(root1);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeDB() {
		db.close();
	}

}

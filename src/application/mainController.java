package application;

import java.net.URL;
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

	int id;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

	public void setID(int id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	// Profil
	public void movetoProfileScreen() {
		System.out.println("Za chwile przejdziemy do Profilu");
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
			Stage primaryStage = (Stage) buttonCar.getScene().getWindow();
			Parent root1 = FXMLLoader.load(getClass().getResource("vehiclesView.fxml"));
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
	}

	// Naprawy
	public void movetoRepairScreen() {
		System.out.println("Zachwile przejdziemy do Napraw");

		try {
			/*
			 * Zamykanie sceny
			 * 
			 * Stage stage1 = (Stage) buttonRepair.getScene().getWindow(); stage1.close();
			 */
			Stage primaryStage = (Stage) buttonRepair.getScene().getWindow();
			Parent root1 = FXMLLoader.load(getClass().getResource("repairView.fxml"));
			Scene scene = new Scene(root1);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
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
		System.out.println("W�a�nie si� wylogowa�e�");
		try {
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

}

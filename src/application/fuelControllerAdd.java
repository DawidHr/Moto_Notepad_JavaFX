package application;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class fuelControllerAdd implements Initializable {

	@FXML
	Button buttonCencel;
	@FXML
	Button buttonAdd;
	
	@FXML
	ComboBox<String> comboBoxMoto;
	@FXML
	ComboBox<String> comboBoxFuel;
	@FXML
	TextField textFieldSize;
	@FXML
	TextField textFieldPrize;
	@FXML
	DatePicker date1;
	
	DataBase db;
	int id;
	List<Vehicle> list = new LinkedList<>();
	ObservableList<String> observableList1 = FXCollections.observableArrayList();
	ObservableList<String> observableList2 = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		db = new DataBase();
		list = db.getVehicles(id);
		for(Vehicle c: list) {
			observableList1.add(c.getMarka()+" "+c.getModel());
		}
		comboBoxMoto.setItems(observableList1);
		
		observableList2.add("Benzyna");
		observableList2.add("LPG");
		observableList2.add("Elektryczny");
		observableList2.add("Diesel");
		comboBoxFuel.setItems(observableList2);
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
	
	public void addFuel() {
		//Sprawdzanie czy zosta³ wybrany pojazd
		if(comboBoxMoto.getSelectionModel().getSelectedItem() == null)
			return;
		//Sprawdzanie czy zosta³ wybrany rodzaj paliwa
		if(comboBoxFuel.getSelectionModel().getSelectedItem() == null)
			return;
		Float fsize = TextToFloat(textFieldSize);
		Float fprize = TextToFloat(textFieldPrize);
		
		LocalDate localdate1 = date1.getValue();
		Date data2 = Date.valueOf(localdate1);
		db.addNewFuel(id, comboBoxMoto.getSelectionModel().getSelectedItem().toString(), comboBoxFuel.getSelectionModel().getSelectedItem().toString(), fsize, fprize, data2);
		cencel();
	}
	
	private float TextToFloat(TextField text) {
		try {
			Float number = Float.valueOf(text.getText());
			return number;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
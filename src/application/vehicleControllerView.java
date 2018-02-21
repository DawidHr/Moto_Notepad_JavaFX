package application;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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

public class vehicleControllerView implements Initializable {

	@FXML
	Button buttonCencel;
	@FXML
	Button buttonUpdate;
	@FXML
	Button buttonDelete;
	@FXML
	TextField textFieldMarka;
	@FXML
	TextField textFieldModel;
	@FXML
	TextField textFieldSize;
	@FXML
	ComboBox<String> comboBoxType;
	@FXML
	DatePicker datd_oc;
	@FXML
	DatePicker data_technical;

	String[] stringListType = {"Coupe", "Hatchback", "Kabriolet", "Kombi", "Van", "Sedan", "SUV", "Terenowy", "Motor"};
	ObservableList list;
	int id;
	DataBase db;
	int id_moto;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		db = new DataBase();
		list = FXCollections.observableArrayList(stringListType);
		comboBoxType.setItems(list);
	}

	public void setId(int id) {
		this.id = id;
	}
	public void vehicleId(int id) {
		id_moto=id;
	}
	
	public void main() {
		Vehicle moto = db.getVehiclebyId(id_moto);
		textFieldMarka.setText(moto.getMarka());
		textFieldModel.setText(moto.getModel());
		textFieldSize.setText(moto.getSize()+"");
		data_technical.setValue(moto.getDataTech().toLocalDate());
		datd_oc.setValue(moto.getDataOc().toLocalDate());
		comboBoxType.getSelectionModel().select(moto.getType());;
	}

	public void cencel() {
		try {
			db.close();
			Stage primaryStage = (Stage) buttonCencel.getScene().getWindow();
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
	
	public void delete() {
		db.deleteVehicle(id_moto);
		cencel();
	}
	
	public void updateVehicle() {
		if( (!(textFieldMarka.getText().trim().isEmpty())) && (!(textFieldModel.getText().trim().isEmpty()))) {
			//Sprawdzenie czy Typ pojazdu zosta³ wybrany
						if(comboBoxType.getSelectionModel().getSelectedItem()==null) {
			return;
						}
		int floatNumber = isNumber(textFieldSize);
		if(datd_oc.getValue() == null) 
			return;
		if(data_technical.getValue() == null)
			return;
		
		LocalDate localDataOc = datd_oc.getValue();
		Date dataOC = Date.valueOf(localDataOc);
		LocalDate localDatetechnical = data_technical.getValue();
		Date dataTechnical = Date.valueOf(localDatetechnical);
		db.updateVehicle(id_moto, textFieldMarka.getText().trim(), textFieldModel.getText().trim(), floatNumber, comboBoxType.getSelectionModel().getSelectedItem(), dataOC, dataTechnical);
	cencel();
		}}
	
	private int isNumber(TextField field) {
		try {
			int floatNumer = Integer.valueOf(field.getText());
			return floatNumer;
		} catch (Exception e) {
			e.printStackTrace();
			textFieldSize.setText("Musi to byæ liczba");
		}
		return 0;

	}
}
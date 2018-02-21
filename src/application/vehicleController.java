package application;

import java.net.URL;
import java.util.Collection;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class vehicleController implements Initializable{

	@FXML
	Button buttonVehicleBack;
	@FXML
	Button buttonVehicleView;
	@FXML
	Button buttonVehicleAdd;
	
	@FXML
	TableView<Vehicle> table;
	@FXML
	TableColumn<Vehicle, String> columnMarka;
	@FXML
	TableColumn<Vehicle, String> columnModel;
	@FXML
	TableColumn<Vehicle, Integer> columnSize;
	@FXML
	TableColumn<Vehicle, String> columnType;
	
	int id;
	DataBase db;
	List<Vehicle> list = new LinkedList<>();
	ObservableList<Vehicle> observableList1 = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		db = new DataBase();
		list = db.getVehicles(id);
		System.out.println("Lista pusta: "+list.isEmpty());
		
		if(!list.isEmpty())
		{for(Vehicle c: list) {
			System.out.println(c.toString());
		}}
		observableList1.addAll(list);
		System.out.println("ObservableList is Empty: "+observableList1.isEmpty());
		table.setItems(observableList1);
		columnMarka.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("marka"));
		columnModel.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("model"));
		columnSize.setCellValueFactory(new PropertyValueFactory<Vehicle, Integer>("size"));
		columnType.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("type"));
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	
	public void addVehicle() {
		try {
			db.close();
			Stage primaryStage = (Stage) buttonVehicleBack.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root1 = loader.load(getClass().getResource("vehicleAdd.fxml").openStream());
			vehicleControllerAdd vehicleControllerAddc = (vehicleControllerAdd) loader.getController();
			vehicleControllerAddc.setId(id);
			Scene scene = new Scene(root1);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void viewVehicle() {
		if(table.getSelectionModel().getSelectedItem() != null) {
			try {
				Stage primaryStage = (Stage) buttonVehicleBack.getScene().getWindow();
				FXMLLoader loader = new FXMLLoader();
				Parent root1 = loader.load(getClass().getResource("vehicleViewView.fxml").openStream());
				vehicleControllerView vehicleControllerViewc = (vehicleControllerView) loader.getController();
				vehicleControllerViewc.setId(id);
				int id_note =table.getSelectionModel().getSelectedItem().getId();
				System.out.println("Notatka id: "+id_note);
				vehicleControllerViewc.vehicleId(id_note);
				vehicleControllerViewc.main();
				Scene scene = new Scene(root1);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void cencel() {
		try {
			db.close();
			Stage primaryStage = (Stage) buttonVehicleBack.getScene().getWindow();
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

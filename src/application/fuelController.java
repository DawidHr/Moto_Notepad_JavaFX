package application;

import java.net.URL;
import java.sql.Date;
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

public class fuelController implements Initializable {

	@FXML
	Button buttonCencel;
	@FXML
	Button buttonDelete;
	@FXML 
	Button buttonAdd;
	DataBase db;
	int id;
	
	@FXML
	TableView<Fuel> table;
	@FXML
	TableColumn<Fuel, String> columnMoto;
	@FXML
	TableColumn<Fuel, String> columnFuel;
	@FXML
	TableColumn<Fuel, Float> columnSize;
	@FXML
	TableColumn<Fuel, Float> columnPrize;
	@FXML
	TableColumn<Fuel, Date> columndata;
	
	List<Fuel> listFuel = new LinkedList<>();
	ObservableList<Fuel> observableList = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		db = new DataBase();
	}
	public void setId(int id2) {
		// TODO Auto-generated method stub
		id=id2;
	}
	
	public void main() {
		listFuel = db.getFule(id);
		observableList.addAll(listFuel);
		table.setItems(observableList);
		for(Fuel c : listFuel) {
			System.out.println("Id paliwa: "+c.getId());
		}
		columnMoto.setCellValueFactory(new PropertyValueFactory<Fuel, String>("moto"));
		columnFuel.setCellValueFactory(new PropertyValueFactory<Fuel, String>("fuel"));
		columnSize.setCellValueFactory(new PropertyValueFactory<Fuel, Float>("howMuch"));
		columnPrize.setCellValueFactory(new PropertyValueFactory<Fuel, Float>("howMuchPrize"));
		columndata.setCellValueFactory(new PropertyValueFactory<Fuel, Date>("date"));
	
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
			if(table.getSelectionModel().getSelectedItem() !=null) {
				System.out.println("Id do kasacji: "+table.getSelectionModel().getSelectedItem().getId());
				db.deleteFuel(table.getSelectionModel().getSelectedItem().getId());
				listFuel.clear();
				observableList.clear();
				
				main();
			}
		}
		
		public void addMoto() {
			boolean isMoto = db.findIfIsMoto(id);
			if(isMoto) {
				try {
					System.out.println("Jestem w dobrem findIfIsMoto");
					db.close();
					Stage primaryStage = (Stage) buttonCencel.getScene().getWindow();
					FXMLLoader loader = new FXMLLoader();
					Parent root1 = loader.load(getClass().getResource("fuelAdd.fxml").openStream());
					fuelControllerAdd fuelControllerAddc = (fuelControllerAdd) loader.getController();
					fuelControllerAddc.setId(id);
					Scene scene = new Scene(root1);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene);
					primaryStage.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else {
				try {
					System.out.println("Jestem w z³ym findIfIsMoto");
					db.close();
					Stage primaryStage = (Stage) buttonCencel.getScene().getWindow();
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
			
		}
	

	
}

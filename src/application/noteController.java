package application;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class noteController implements Initializable{

	@FXML
	Button buttonnoteView;
	@FXML
	Button buttonNoteAdd;
	@FXML 
	Button buttonNoteCencel;
	int id;
	DataBase db;
	@FXML
	TableView<Note> table;
	@FXML
	TableColumn<Note, String> table_title;
	@FXML
	TableColumn<Note, Date> table_date;
	
	public ObservableList<Note> list = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		db = new DataBase();
		List<Note> notesList = db.getNotesList();
		for(Note e: notesList) {
			System.out.println(e.toString());
			
		}
		list.addAll(notesList);
		System.out.println(list.isEmpty());
		for(Note e: list) {
			System.out.println(e.toString());
			
		}
		table.setItems(list);
		table_title.setCellValueFactory(new PropertyValueFactory<Note, String>("title"));
		table_date.setCellValueFactory(new PropertyValueFactory<Note, Date>("date"));
	}
	public void movetoProfileScreen() {
		try {
			Stage primaryStage = (Stage) buttonNoteAdd.getScene().getWindow();
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
	public void movetoViewNote() {
		if(table.getSelectionModel().getSelectedItem() != null) {
			try {
				Stage primaryStage = (Stage) buttonNoteAdd.getScene().getWindow();
				FXMLLoader loader = new FXMLLoader();
				Parent root1 = loader.load(getClass().getResource("noteViewView.fxml").openStream());
				noteViewController noteViewView = (noteViewController) loader.getController();
				noteViewView.setId(id);
				int id_note =table.getSelectionModel().getSelectedItem().getId();
				System.out.println("Notatka id: "+id_note);
				noteViewView.setTile(id_note);
				noteViewView.main();
				Scene scene = new Scene(root1);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void movetoAddNote() {
		try {
			Stage primaryStage = (Stage) buttonNoteAdd.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root1 = loader.load(getClass().getResource("noteViewAdd.fxml").openStream());
			noteAddController noteAddView = (noteAddController) loader.getController();
			noteAddView.setId(id);
			Scene scene = new Scene(root1);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setId(int id) {
		// TODO Auto-generated method stub
		this.id=id;
	}
}
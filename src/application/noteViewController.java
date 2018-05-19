package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class noteViewController implements Initializable{

	int id;
	int note_id;
	@FXML
	TextField title;
	@FXML
	DatePicker data;
	@FXML 
	TextArea note;
	
	DataBase db;
	
	
	
	public void setId(int id) {
		// TODO Auto-generated method stub
		this.id=id;
	}

	public void setTile(int idd) {
		// TODO Auto-generated method stub
		this.note_id=idd;
		System.out.println("tu jestem");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		db = new DataBase();
	}

	public void main() {
		// TODO Auto-generated method stub
		Note note1 = db.getNote(note_id);
		System.out.println("ID notatki w View: "+note_id);
		title.setText(note1.getTitle());
		data.setValue(note1.getDate().toLocalDate());
		note.setText(note1.getNote());
	}
	
	
	public void cencel() {
		moveTo();
	}
	
	public void delete() {
		db.deleteNote(note_id);
		moveTo();
	}
	
	public void editNote() {
		db.updateNote(id, title.getText(), data, note.getText());
		moveTo();
	}
	
	
	public void moveTo() {
		try {
			db.close();
			Stage primaryStage = (Stage) title.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root1 = loader.load(getClass().getResource("noteView.fxml").openStream());
			noteController noteView = (noteController) loader.getController();
			noteView.setId(id);
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

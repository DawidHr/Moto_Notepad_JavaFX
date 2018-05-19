package application;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class noteAddController implements Initializable {

	DataBase db;
	public int id;
	@FXML
	Button saveNote;
	@FXML
	TextField title;
	@FXML
	TextArea note;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		db = new DataBase();
	}
	public void setId(int id) {
		// TODO Auto-generated method stub
		this.id=id;	
	}
	
	public void saveNote() {
		if(title.getText().trim().isEmpty()) {
			System.out.println("tytulu nie ma");
		}
		else {
			try {
				
			LocalDate date =LocalDate.now();
			//dodawanie do bazu danych
			db.addNewNote(id, title.getText().trim(), date, note.getText().trim());
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
			
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void cencel() {
		try {
			Stage primaryStage = (Stage) saveNote.getScene().getWindow();
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

package application;

import java.io.File;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class profileController implements Initializable {

	@FXML
	Label NameProfileLabel;
	@FXML
	Label CountMotoLabel;
	@FXML
	Label CountRepairNotesDoneLabel;
	@FXML
	Label CountRepairNotesNotDoneLabel;
	@FXML
	Label CountNotesLabel;
	@FXML
	Button CencelButton;
	@FXML
	Button ChangeButton;
	@FXML
	Button ImageButton;
	
	DataBase db;
	int id;
	String profileNameString;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		db = new DataBase();
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setProfileName() {
		String temp = "";
		int tempInt = 0;

		profileNameString = db.getProfileName(id);
		temp = NameProfileLabel.getText() + " " + profileNameString;
		NameProfileLabel.setText(temp);

		tempInt = db.getCountMotoForUser(id);
		temp = CountMotoLabel.getText() + " " + tempInt;
		CountMotoLabel.setText(temp);

		tempInt = db.getCountNotesForRepair(id, "Wykonane");
		temp = CountRepairNotesDoneLabel.getText() + " " + tempInt;
		CountRepairNotesDoneLabel.setText(temp);

		tempInt = db.getCountNotesForRepair(id, "Do zrobienia");
		temp = CountRepairNotesNotDoneLabel.getText() + " " + tempInt;
		CountRepairNotesNotDoneLabel.setText(temp);

		tempInt = db.getCountNotes(id);
		temp = CountNotesLabel.getText() + " " + tempInt;
		CountNotesLabel.setText(temp);
		
		File image1 = db.getIcon(id);
		ImageView image11 = new ImageView("img/indeks.jpg");
		if(image1 != null) {
			String ss = "file:"+image1.getAbsolutePath();
			Image img = new Image(ss);
			image11 = new ImageView(img);
		}
		image11.setFitHeight(250.00);
		image11.setFitWidth(250.00);
		ImageButton.setGraphic(image11);
	}
	
	public void setAvatarImage() {
		FileChooser fileCh = new FileChooser();
		ExtensionFilter filter = new ExtensionFilter("Obrazy","*.jpg", "*.jpeg", "*.png", "*.bmp");
		fileCh.getExtensionFilters().addAll(filter);
		File selectedFile = fileCh.showOpenDialog(null);
		if( selectedFile != null) {
			File imageFile;
			imageFile = selectedFile;
			db.saveAvatarImage(id, imageFile);
			String ss = "file:"+imageFile.getAbsolutePath();
			Image img = new Image(ss);
			ImageView image11 = new ImageView(img);
			image11.setFitHeight(250.00);
			image11.setFitWidth(250.00);
			ImageButton.setGraphic(image11);
		}
	}

	public void changUserInfo() {
		try {
			db.close();
			Stage primaryStage = (Stage) CencelButton.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root1 = loader.load(getClass().getResource("profileEdit.fxml").openStream());
			profileEditController profileEditControllerc = (profileEditController) loader.getController();
			profileEditControllerc.setId(id);
			profileEditControllerc.setProfileName(profileNameString);
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

	public void cencel() {
		try {
			db.close();
			Stage primaryStage = (Stage) CencelButton.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Parent root1 = loader.load(getClass().getResource("mainView.fxml").openStream());
			mainController mainControllerc = (mainController) loader.getController();
			mainControllerc.setID(id);
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
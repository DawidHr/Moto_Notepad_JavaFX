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
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class mainController2 implements Initializable {

	@FXML
	Label label1;
	
	DataBase db;
	int id;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		db = new DataBase();
	}

	public void setId(int id2) {
		id=id2;
	}
	public void main() {
		String text = db.getUserMail(id);
		label1.setText(text);
		db.close();
	}
}
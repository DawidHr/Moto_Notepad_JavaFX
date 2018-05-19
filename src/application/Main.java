package application;

import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			DataBase db = new DataBase();
			List<String> userNameList = db.getUserNames();
			db.close();
			System.out.println(userNameList);
			if (userNameList.isEmpty()) {
				try {
					Parent root1 = FXMLLoader.load(getClass().getResource("mainRegisterView.fxml"));
					Scene scene = new Scene(root1);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene);
					primaryStage.show();
					primaryStage.setTitle("MOTO NOTATNIK");
					primaryStage.setResizable(false);
					} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				try {
					Parent root1 = FXMLLoader.load(getClass().getResource("mainLoginView.fxml"));
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

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}

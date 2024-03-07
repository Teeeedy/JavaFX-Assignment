package model;

import controller.StartUpController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		int width = 850;
		int height = 700;
		Parent root;

		// Setting up to the starting view the which is the starting menu
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/StartUpView.fxml"));
			root = loader.load();

			// Getting the controller
			StartUpController controller = loader.getController();
			// Setting the stage size, width for starting gui menu 
			controller.setWindowSize(width, height);

			// Display stage
			primaryStage.setTitle("Data Analytics Hub");
			primaryStage.setScene(new Scene(root, width, height));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
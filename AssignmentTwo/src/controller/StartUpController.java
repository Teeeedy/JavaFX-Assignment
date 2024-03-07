package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartUpController extends Controller {

	@FXML
	private Button loginButton;

	@FXML
	private Button signUpButton;

	// Takes you to the login page when clicked
	public void initialize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void loginButtonClick() {

		stage = (Stage) (loginButton.getScene().getWindow());
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/LoginView.fxml"));
			root = loader.load();

			LoginController controller = loader.getController();
			controller.setWindowSize(width, height);

			Scene scene = new Scene(root, width, height);
			this.stage.setScene(scene);
			this.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Takes you to the sign up page when clicked
	public void signUpButtonClick() {
		stage = (Stage) (signUpButton.getScene().getWindow());
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/SignUpView.fxml"));
			root = loader.load();

			SignUpController controller = loader.getController();
			controller.setWindowSize(width, height);

			Scene scene = new Scene(root, width, height);
			this.stage.setScene(scene);
			this.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AlertBox;

public class LoginController extends Controller {

	@FXML
	private Button loginButton;

	@FXML
	private Button backButton;

	@FXML
	private TextField username;

	@FXML
	private TextField password;

	// Login button clicked and credentials verified
	public void loginButtonClicked() {
		FXMLLoader loader;
		this.stage = (Stage) (this.loginButton.getScene().getWindow());
		AlertBox alertBox = new AlertBox();

//		Getting data from the text fields
		String username = this.username.getText();
		String password = this.password.getText();

		// Validating log in null if user not found
		this.user = this.database.getUser(username, password);

		// Check if user is null
		if (this.user != null) {

			// If user is not VIP
			if (user.getVIP().equals("0")) {
				loader = new FXMLLoader(getClass().getResource("../Views/DashboardView.fxml"));
				try {
					this.root = loader.load();
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				loader = new FXMLLoader(getClass().getResource("../Views/VipDashboardView.fxml"));
				try {
					this.root = loader.load();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			DashboardController controller = loader.getController();
			controller.initialize(this.user, this.width, this.height);

			Scene scene = new Scene(root, this.width, this.height);
			stage.setScene(scene);
			stage.show();

			// Invalid password or username show alert
		} else {
			alertBox.display("Error", "Invalid username and password");
		}
	}

	// Takes you back to the main menu
	public void backButtonClicked() {
		FXMLLoader loader;

		stage = (Stage) (this.backButton.getScene().getWindow());
		try {
			loader = new FXMLLoader(getClass().getResource("../Views/StartUpView.fxml"));
			this.root = loader.load();
			StartUpController controller = loader.getController();
			controller.setWindowSize(this.width, this.height);

			Scene scene = new Scene(root, this.width, this.height);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

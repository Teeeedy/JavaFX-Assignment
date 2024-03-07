package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.AlertBox;
import model.User;

public class GetVipController extends Controller {
	@FXML
	Button yesButton;
	
	@FXML
	Button noButton;
	
	public void initialize(User u, int width, int height) {
		this.user = u;
		this.setWindowSize(width, height);
	}
	
	public void yesClicked() {
		AlertBox alertBox = new AlertBox();
		String query = "UPDATE Users SET VIP = 1 WHERE username = \"" + this.user.getUsername() + "\";";
		boolean approved = this.database.insUpDelQuery(query);
		
		if (approved) {
			alertBox.display("Success", "You have been upgraded to a VIP memeber. Please log out and log in again to access VIP functionalities.");
			Stage stage;
			Parent root;

			stage = (Stage) (this.yesButton.getScene().getWindow());
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/StartUpView.fxml"));
				root = loader.load();
				
				StartUpController controller = loader.getController();
				controller.initialize(this.width, this.height);

				Scene scene = new Scene(root, this.width, this.height);
				stage.setScene(scene);
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void noClicked() {
		Stage stage;
		Parent root;

		stage = (Stage) (this.noButton.getScene().getWindow());
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/DashboardView.fxml"));
			root = loader.load();

			DashboardController controller = loader.getController();
			controller.initialize(this.user, this.width, this.height);

			Scene scene = new Scene(root, this.width, this.height);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

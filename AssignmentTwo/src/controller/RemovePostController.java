package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

public class RemovePostController extends Controller {
	@FXML
	Button backButton;

	@FXML
	Button removePostButton;

	@FXML
	Button removePost;

	@FXML
	TextField postID;

	@FXML
	Label queryResult;

	public void initialize(User u, int width, int height) {
		this.user = u;
		this.setWindowSize(width, height);
	}

	public void removePost() {
		String postID = this.postID.getText();
		boolean removed = this.user.removePost(postID);
		if (removed) {
			this.queryResult.setText("Post with ID: " + postID + " has been removed from the collection.");
		} else {
			this.queryResult.setText("No posts with ID: " + postID + " exist in the collection.");
		}
	}

	public void backButtonClicked() {
		Stage stage;
		Parent root;
		stage = (Stage) (this.backButton.getScene().getWindow());

		if (this.user.getVIP().equals("0")) {
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

		} else {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/VipDashboardView.fxml"));
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

}

package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Post;
import model.User;

public class RetrievePostController extends Controller {

	@FXML
	Button retrievePostButton;

	@FXML
	Button backButton;

	@FXML
	TextField postID;

	@FXML
	Label postDetails;
	
	public void initialize(User u, int width, int height) {
		this.user = u;
		this.setWindowSize(width, height);
	}

	public void retrievePost() {
		String postInput = postID.getText();
		Post post = this.user.retrievePost(postInput);
		
		if (post != null) {
			String output = "Post Details\n";
			output += "Post ID: " + post.getPostID() + "\n";
			output += "Content: " + post.getContent() + "\n";
			output += "Author:  " + post.getAuthor() + "\n";
			output += "Likes:   " + post.getLikes() + "\n";
			output += "Shares:  " + post.getShares() + "\n";
			output += "Date:    " + post.getDateTime() + "\n";
			
			this.postDetails.setText(output);
		} else {
			this.postDetails.setText("The post with ID: " + postInput + " does not exist in the collection.\n Post ID must be an integer value.");
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

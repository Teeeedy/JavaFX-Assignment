package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Post;
import model.User;

public class AddPostController extends Controller {

	@FXML
	Button backButton;

	@FXML
	Button addPostButton;

	@FXML
	TextField postID;

	@FXML
	TextArea content;

	@FXML
	TextField likes;

	@FXML
	TextField shares;

	@FXML
	TextField date;

	@FXML
	Label queryResult;

	// Initializing 
	public void initialize(User u, int width, int height) {
		this.user = u;
		this.setWindowSize(width, height);
	}

	// Function to add Post to database
	public void addPost() {
		
		// Get the input text from the TextFields
		String postID = this.postID.getText();
		String content = this.content.getText();
		String likes = this.likes.getText();
		String shares = this.shares.getText();
		String date = this.date.getText();

		Post post = new Post();

		// Initialize a post
		boolean initialized = post.initializePost(postID, content, this.user.getUsername(), likes, shares, date);

		// If not null
		if (initialized) {
			
			// Add post to database
			boolean added = this.user.addPost(post);
			
			// If succeeded
			if (added) {
				queryResult.setText("Post has been added to collection.");
			} else {
				queryResult.setText("A post with the entered ID already exists in collection.");
			}
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

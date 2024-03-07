package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.User;

public class DashboardController extends Controller {

	@FXML
	private Button addPostButton;

	@FXML
	private Button retrievePostButton;

	@FXML
	private Button removePostButton;

	@FXML
	private Button editProfileButton;

	@FXML
	private Button logoutButton;

	@FXML
	private Button topPostsButton;

	@FXML
	private Button exportPostButton;

	@FXML
	private Button getVipButton;

	@FXML
	private Label welcomeText;

	public void initialize(User u, int width, int height) {
		this.user = u;
		this.setWindowSize(width, height);
		this.welcomeText.setText("Welcome! " + user.getFName() + " " + user.getLName());
	}

	// Each of these functions will take you to their respective views and
	// controllers when the corresponding button is clicked
	public void addPost() {
		Stage stage;
		Parent root;

		stage = (Stage) (this.retrievePostButton.getScene().getWindow());
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/AddPostView.fxml"));
			root = loader.load();

			AddPostController controller = loader.getController();
			controller.initialize(this.user, this.width, this.height);

			Scene scene = new Scene(root, this.width, this.height);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void retrievePost() {
		Stage stage;
		Parent root;

		stage = (Stage) (this.retrievePostButton.getScene().getWindow());
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/RetrievePostView.fxml"));
			root = loader.load();

			RetrievePostController controller = loader.getController();
			controller.initialize(this.user, this.width, this.height);

			Scene scene = new Scene(root, this.width, this.height);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removePost() {
		Stage stage;
		Parent root;

		stage = (Stage) (this.retrievePostButton.getScene().getWindow());
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/RemovePostView.fxml"));
			root = loader.load();

			RemovePostController controller = loader.getController();
			controller.initialize(this.user, this.width, this.height);

			Scene scene = new Scene(root, this.width, this.height);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void editProfile() {
		Stage stage;
		Parent root;

		stage = (Stage) (this.retrievePostButton.getScene().getWindow());
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/EditProfileView.fxml"));
			root = loader.load();

			EditProfileController controller = loader.getController();
			controller.initialize(this.user, this.width, this.height);

			Scene scene = new Scene(root, this.width, this.height);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getTopPost() {
		Stage stage;
		Parent root;

		stage = (Stage) (this.retrievePostButton.getScene().getWindow());
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/TopLikesView.fxml"));
			root = loader.load();

			TopLikesController controller = loader.getController();
			controller.initialize(this.user, this.width, this.height);

			Scene scene = new Scene(root, this.width, this.height);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void exportPost() {
		Stage stage;
		Parent root;

		stage = (Stage) (this.retrievePostButton.getScene().getWindow());
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ExportView.fxml"));
			root = loader.load();

			ExportViewController controller = loader.getController();
			controller.initialize(this.user, this.width, this.height);

			Scene scene = new Scene(root, this.width, this.height);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getVip() {
		Stage stage;
		Parent root;

		stage = (Stage) (this.retrievePostButton.getScene().getWindow());
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/GetVipView.fxml"));
			root = loader.load();

			GetVipController controller = loader.getController();
			controller.initialize(this.user, this.width, this.height);

			Scene scene = new Scene(root, this.width, this.height);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logout() {
		FXMLLoader loader;

		stage = (Stage) (this.logoutButton.getScene().getWindow());
		try {
			loader = new FXMLLoader(getClass().getResource("../Views/StartUpView.fxml"));
			this.root = loader.load();
			StartUpController controller = loader.getController();
			controller.setWindowSize(this.width, this.height);

			Scene scene = new Scene(this.root, this.width, this.height);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package controller;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.AlertBox;
import model.JDBCConnection;
import model.Post;
import model.User;

public class TopLikesController extends Controller {
	@FXML
	private TextField numPosts;

	@FXML
	private TextField selectedUser;

	@FXML
	private Button backButton;

	@FXML
	private Button getTopPostsButton;

	@FXML
	private TableView<Post> postsTable;

	@FXML
	private TableColumn<Post, String> idCol;

	@FXML
	private TableColumn<Post, String> contentCol;

	@FXML
	private TableColumn<String, String> authorCol;

	@FXML
	private TableColumn<Post, String> likesCol;

	@FXML
	private TableColumn<Post, String> sharesCol;

	@FXML
	private TableColumn<Post, String> dateCol;

	@FXML
	private Label topPostsResult;

	public void initialize(User u, int width, int height) {
		this.user = u;
		this.setWindowSize(width, height);
		selectedUser.setPromptText("Leave Empty To See All Users");
	}

	public void getTopPosts() {
		postsTable.getItems().clear();
		topPostsResult.setText("");
		AlertBox alertBox = new AlertBox();
		idCol.setCellValueFactory(new PropertyValueFactory<>("postID"));
		contentCol.setCellValueFactory(new PropertyValueFactory<>("content"));
		authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
		likesCol.setCellValueFactory(new PropertyValueFactory<>("likes"));
		sharesCol.setCellValueFactory(new PropertyValueFactory<>("shares"));
		dateCol.setCellValueFactory(new PropertyValueFactory<>("dateTime"));

		try {
			int topNPosts = Integer.parseInt(numPosts.getText());

			if (topNPosts < 0) {
				throw new IntegerInputLessThanZeroException("Number of posts cannot be less than zero");
			}

			String inputUser = selectedUser.getText();

			if (inputUser.equals("")) {

				ArrayList<Post> postCollection = this.database.getTopNPostsFromAllUsers(topNPosts);

				for (Post post : postCollection) {
					postsTable.getItems().add(post);
				}

			} else {
				ArrayList<Post> postCollection = this.database.getTopNPostsOfUser(topNPosts, inputUser);
				for (Post post : postCollection) {
					postsTable.getItems().add(post);
				}

				if (postCollection.size() == 0) {
					topPostsResult.setText("User does not exist in collection");
				}

			}

		} catch (NumberFormatException e) {

			alertBox.display("Error", "Number of posts must be an integer value");
		} catch (IntegerInputLessThanZeroException e) {
			alertBox.display("Error", e.getMessage());
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

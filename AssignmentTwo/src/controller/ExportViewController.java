package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.GroupLayout.Group;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.JDBCConnection;
import model.Post;
import model.User;
import javafx.stage.Stage;

public class ExportViewController extends Controller {
	FileChooser fileChooser = new FileChooser();

	Post post;

	@FXML
	private TextField postID;

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
	private Button exportButton;

	@FXML
	private Button getPostButton;

	@FXML
	private Button backButton;

	@FXML
	private Label getPostResult;

	public void initialize(User u, int width, int height) {
		this.user = u;
		this.setWindowSize(width, height);
		idCol.setCellValueFactory(new PropertyValueFactory<>("postID"));
		contentCol.setCellValueFactory(new PropertyValueFactory<>("content"));
		authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
		likesCol.setCellValueFactory(new PropertyValueFactory<>("likes"));
		sharesCol.setCellValueFactory(new PropertyValueFactory<>("shares"));
		dateCol.setCellValueFactory(new PropertyValueFactory<>("dateTime"));

		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("CSV Files (*.csv)", "*.csv"));

		ArrayList<Post> allPosts = this.database.getAllPosts();
		for (Post post : allPosts) {
			postsTable.getItems().add(post);
		}
	}

	public void export() {

		if (this.post == null) {
			getPostResult.setText("No post was selected");
			return;
		}

		try {
			File file = fileChooser.showSaveDialog(new Stage());
			PrintWriter printWriter = new PrintWriter(file);
			String postDetails = "ID,content,author,likes,shares,date-time\n";
			postDetails += this.post.getPostID().replace(",", "") + ",";
			postDetails += this.post.getContent() + ",";
			postDetails += this.post.getAuthor() + ",";
			postDetails += this.post.getLikes() + ",";
			postDetails += this.post.getShares() + ",";
			postDetails += this.post.getDateTime();
			printWriter.write(postDetails);
			printWriter.close();
			getPostResult.setText("Your post has been exported");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void getPost() {
		getPostResult.setText("");
		postsTable.getItems().clear();
		String postIDInput = postID.getText();
		this.post = this.user.retrievePost(postIDInput);

		if (this.post == null) {
			ArrayList<Post> allPosts = this.database.getAllPosts();
			for (Post post : allPosts) {
				postsTable.getItems().add(post);
			}
			getPostResult.setText("The post does not exist in the collection");
		} else {
			postsTable.getItems().add(this.post);
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

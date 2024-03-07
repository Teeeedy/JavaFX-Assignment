package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.AlertBox;
import model.Post;
import model.User;

public class BulkImportController extends Controller {
	FileChooser fileChooser = new FileChooser();

	@FXML
	Button importPostsButton;

	@FXML
	Button importFileButton;

	@FXML
	Button backButton;

	@FXML
	Label fileContent;

	@FXML
	Label importResult;

	// Initializing
	public void initialize(User u, int width, int height) {
		this.user = u;
		this.width = width;
		this.height = height;
	}

	// Function to import file
	public void importFile() {
		// Open file window created and opened
		File importedFile = fileChooser.showOpenDialog(new Stage());
		try {

			// Read from the file using scanner
			Scanner sc = new Scanner(importedFile);
			String importedText = "";
			while (sc.hasNextLine()) {
				importedText += sc.nextLine() + "\n";

			}

			// Set file content to a textField
			fileContent.setText(importedText);
			sc.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	// Import posts from TextField
	public void importPosts() {
		AlertBox alertBox = new AlertBox();
		String csvContent = fileContent.getText();
		Scanner sc = new Scanner(csvContent);
		sc.useDelimiter(",");
		sc.nextLine();

		// Use scanner to read from TextField
		// For each post read try adding to database
		while (sc.hasNext()) {
			String postText = sc.nextLine();
			String[] arrPostText = postText.split(",");
			String ID = arrPostText[0];
			String content = arrPostText[1];
			String likes = arrPostText[3];
			String shares = arrPostText[4];
			String date = arrPostText[5];

			Post newPost = new Post();
			boolean valid = newPost.initializePost(ID, content, this.user.getUsername(), likes, shares, date);
			
			// If a post is a fulfills all the constraints add to database
			if (valid) {
				this.user.addPost(newPost);
			} else {
				alertBox.display("Error", "Post ID:" + ID + " already exists in the collection");
			}
		}
		sc.close();

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

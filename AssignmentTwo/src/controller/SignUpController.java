package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.AlertBox;
import model.JDBCConnection;
import javafx.scene.control.TextField;

public class SignUpController extends Controller {

	@FXML
	private Button createAccountButton;

	@FXML
	private Button backButton;

	@FXML
	private TextField firstName;

	@FXML
	private TextField lastName;

	@FXML
	private TextField username;

	@FXML
	private TextField password;

	public void createAccountButtonClicked() {
		Stage stage = (Stage) (createAccountButton.getScene().getWindow());
		Parent root;

		AlertBox alertBox = new AlertBox();

//		Getting data from the textfields
		String firstName = this.firstName.getText();
		String lastName = this.lastName.getText();
		String username = this.username.getText();
		String password = this.password.getText();

		if (!firstName.equals("") && !lastName.equals("") && !username.equals("") && !password.equals("")) {
			String query = "SELECT * FROM USERS WHERE username = \"" + username + "\"";
			boolean found = this.database.searchQuery(query);

			try {
//				The username already exists in the collection make the user try again
				if (found) {
					alertBox.display("Error", "Username already exists in database. Please try again.");

//				If the username does not exist insert user details into database
				} else {
					query = "INSERT INTO Users VALUES (\"" + username + "\", \"" + password + "\", \"" + firstName
							+ "\", \"" + lastName + "\", " + 0 + ")";
					System.out.println(query);
					this.database.insUpDelQuery(query);
					alertBox.display("Account Created!",
							"Your user account has been created and added to the database.");

					this.backButtonClicked();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			alertBox.display("Error", "Fields must not be empty. Please fill out every field.");
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

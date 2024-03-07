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

public class EditProfileController extends Controller {
	@FXML
	Label fName;

	@FXML
	Label lName;

	@FXML
	Label username;

	@FXML
	Label password;

	@FXML
	TextField newFName;

	@FXML
	TextField newLName;

	@FXML
	TextField newUsername;

	@FXML
	TextField newPassword;

	@FXML
	Button editProfileButton;

	@FXML
	Label editProfileResult;

	@FXML
	Button backButton;

	public void initialize(User u, int width, int height) {
		this.user = u;
		this.setWindowSize(width, height);
		this.setFields();
	}

	public void setFields() {
		fName.setText("First Name: " + this.user.getFName());
		lName.setText("Last Name: " + this.user.getLName());
		username.setText("Username: " + this.user.getUsername());
		password.setText("Password: " + this.user.getPassword());
	}

	public void editProfile() {
		String fName = newFName.getText();
		String lName = newLName.getText();
		String username = newUsername.getText();
		String password = newPassword.getText();
		boolean changed = false;

		if (!fName.equals("")) {
			this.user.setFName(fName);
			changed = true;
		} else {
			fName = this.user.getFName();
		}

		if (!lName.equals("")) {
			this.user.setLName(lName);
			changed = true;
		} else {
			lName = this.user.getLName();
		}

		if (!username.equals("")) {
			this.user.setUsername(username);
			changed = true;
		} else {
			username = this.user.getUsername();
		}

		if (!password.equals("")) {
			this.user.setPassword(password);
			changed = true;
		} else {
			password = this.user.getPassword();
		}

		if (changed) {
			String query = "UPDATE Users SET username=\"" + username + "\", password=\"" + password
					+ "\", first_name=\"" + fName + "\", last_name=\"" + lName + "\" WHERE username=\""
					+ this.user.getUsername() + "\";";
			boolean updated = this.database.insUpDelQuery(query);
			if (updated) {
				this.setFields();
				editProfileResult.setText("New changes have been made.");
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

package controller;

import javafx.scene.Parent;
import javafx.stage.Stage;
import model.DatabaseQuery;
import model.User;

// This is an abstract that controllers to inherit
// Declared the attributes that every controller will have
public abstract class Controller {
	User user;
	Stage stage;
	Parent root;
	int width;
	int height;
	DatabaseQuery database = new DatabaseQuery();

	public void setWindowSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

}
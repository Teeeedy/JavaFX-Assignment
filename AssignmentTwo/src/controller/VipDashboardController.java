package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class VipDashboardController extends DashboardController {
	@FXML
	private Button dataVisualsButton;
	
	@FXML
	private Button bulkImportButton;
	
	
	
	
	public void dataVisuals() {
		Stage stage;
		Parent root;

		stage = (Stage) (this.dataVisualsButton.getScene().getWindow());
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/DataVisualsView.fxml"));
			root = loader.load();
			
			DataVisualsController controller = loader.getController();
			controller.initialize(this.user, this.width, this.height);
	
			

			Scene scene = new Scene(root, this.width, this.height);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void bulkImport() {
		Stage stage;
		Parent root;

		stage = (Stage) (this.dataVisualsButton.getScene().getWindow());
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/BulkImportView.fxml"));
			root = loader.load();
			
			BulkImportController controller = loader.getController();
			controller.initialize(this.user, this.width, this.height);
	
			

			Scene scene = new Scene(root, this.width, this.height);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

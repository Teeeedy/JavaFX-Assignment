package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.JDBCConnection;
import model.User;

public class DataVisualsController extends Controller {

	@FXML
	private PieChart pieChart;

	@FXML
	private Button backButton;

	@FXML
	private Label count0To99;

	@FXML
	private Label count100To999;

	@FXML
	private Label count1000Plus;

	@FXML
	private Label totalCount;

	public void initialize(User u, int width, int height) {
		this.user = u;
		this.width = width;
		this.height = height;
		pieChart.setTitle("Posts with shares range Pie Chart");

		PieChart.Data shares0To99 = new PieChart.Data("0-99", this.database.getNumPostsInSharesRange("0", "99"));
		PieChart.Data shares100To999 = new PieChart.Data("100-999", this.database.getNumPostsInSharesRange("100", "999"));
		PieChart.Data shares1000Plus = new PieChart.Data("1000+",
				this.database.getNumPostsInSharesRange("1000", "9223372036854775807"));

		count0To99.setText("0 - 99 shares: " + this.database.getNumPostsInSharesRange("0", "99"));
		count100To999.setText("100 - 999 shares: " + this.database.getNumPostsInSharesRange("100", "999"));
		count1000Plus.setText("1000+ shares: " + this.database.getNumPostsInSharesRange("1000", "9223372036854775807"));
		totalCount.setText("Total Num Posts: "
				+ (this.database.getNumPostsInSharesRange("0", "99") + this.database.getNumPostsInSharesRange("100", "999")
						+ this.database.getNumPostsInSharesRange("1000", "9223372036854775807")));

		pieChart.getData().add(shares0To99);
		pieChart.getData().add(shares100To999);
		pieChart.getData().add(shares1000Plus);
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

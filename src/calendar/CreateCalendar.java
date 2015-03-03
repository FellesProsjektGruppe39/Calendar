package calendar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreateCalendar extends Application {

	private int width = 1000, height = 600;

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Calendar.fxml"));
		Scene scene = new Scene(root, width, height);
        stage.setTitle("Calendar");
        stage.setScene(scene);
        stage.show();
	}
	
	
	public static void main(String[] args) {
		launch(CreateCalendar.class, args);
	}
}
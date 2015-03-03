package calendar;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CreateCalendar extends Application  {

	private int width = 1000, height = 600;

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Calendar.fxml"));
		Scene scene = new Scene(root);
        stage.setTitle("Calendar");
        stage.setScene(scene);
        stage.show();
	} 
	
	 @FXML
	    private Button ExitButton;
	 @FXML
	 private Button ShowID;
	 
	@FXML
    void initialize() {
		assert ExitButton != null : "fx:id=\"ExitButton\" was not injected: check your FXML file 'Calendar.fxml'.";
		assert ShowID != null : "fx:id=\"ExitButton\" was not injected: check your FXML file 'Calendar.fxml'.";

	ExitButton.setOnAction(new EventHandler<ActionEvent>() {
    	public void handle(ActionEvent event) {
    		Platform.exit();
    	}
    });
	ShowID.setOnAction(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent arg0) {
			System.out.println(2);
		}
		
	} );
	}

	
	public static void main(String[] args) {
		launch(CreateCalendar.class, args);
		
	}
}
package calendar;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class CreateCalendar extends Application  {

	private int width = 1000, height = 600, brukerid;
	private String username, password;
	


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
	private Button SelectButton;
	
	@FXML
	private Label ID;
	
	@FXML
    void initialize() {
        assert ExitButton != null : "fx:id=\"ExitButton\" was not injected: check your FXML file 'GameFXML.fxml'.";
        
        SelectButton.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent event) {
        		ID.setText("Brage");
        	}
        });
        ExitButton.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent event) {
        		Platform.exit();
        	}
        });
    }
	public static void main(String[] args) {
		launch(CreateCalendar.class, args);
		
	}
}
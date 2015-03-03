package calendar;

import mysql.sqlRetrieve;
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
import javafx.stage.Stage;

public class CreateCalendar extends Application  {

	private static int BID = 1;
	private int width = 1000, height = 600, brukerid;
	private String username, password;
	
	public void setBrukerid(int id){
		this.brukerid = id;
		BID = this.brukerid;
	}

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
	private Label avtaler;
	
//	public void setName(String name){
//		ID.setText(name);
//	}
	
	@FXML
    void initialize() {
        assert ExitButton != null : "fx:id=\"ExitButton\" was not injected: check your FXML file 'Calendar.fxml'.";
        
        SelectButton.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent event) {
        		sqlRetrieve getName = new sqlRetrieve("SELECT * FROM bruker WHERE brukerid ='" + BID + "';");
        		String fornavn = getName.getQuery()[0][1];
        		String etterNavn = getName.getQuery()[0][2];
        		ID.setText(fornavn + " " + etterNavn);
        		
        		avtaler.setText(fornavn + fornavn + fornavn + fornavn + fornavn + fornavn + fornavn + fornavn + fornavn + fornavn + fornavn + fornavn + "\n" + etterNavn + " ANDRE" + "\n"+ "ANDRE");
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
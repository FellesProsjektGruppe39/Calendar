package calendar;

import java.io.IOException;

import mysql.sqlRetrieve;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateCalendar extends Application  {

	private static int BID = 1;
	private int width = 1000, height = 600, brukerid;
	private String username, password, start;
	
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
	private Button ExitButton, NewMeeting, SelectButton, NewGroup, Save;
	
	@FXML
	private Label ID, avtaler;
	
	@FXML
	private TextField StartTid, SluttTid;
	
	
	@FXML
    void initialize() {
        assert ExitButton != null : "fx:id=\"ExitButton\" was not injected: check your FXML file 'Calendar.fxml'.";
        
        		sqlRetrieve getName = new sqlRetrieve("SELECT * FROM bruker WHERE brukerid ='" + BID + "';");
        		String fornavn = getName.getQuery()[0][1];
        		String etterNavn = getName.getQuery()[0][2];
        		ID.setText(fornavn + " " + etterNavn + "BrukerId: " + BID);
        		avtaler.setText(CheckCalendar.PrintDay(BID));
        		
    	
        		
        ExitButton.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent event) {
        		Platform.exit();
        	}
        });
        
        NewMeeting.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("NewMeeting.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Help menu");
                    stage.setScene(new Scene(root, 500, 450));
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        
        Save.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	start = StartTid.getText();
                (((Node) event.getSource())).getScene().getWindow().hide();
                System.out.println(start);
              }
          });
        
    }
	
	public static void main(String[] args) {
		launch(CreateCalendar.class, args);
		
		
	}
}
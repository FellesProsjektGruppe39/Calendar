package calendar;

import java.awt.SecondaryLoop;
import java.io.IOException;

import Meeting.CreateMeeting;
import Meeting.EditMeeting;

import com.sun.glass.events.MouseEvent;

import mysql.sqlRetrieve;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateCalendar extends Application  {

	private static int BID = 3;
	private int width = 1000, height = 600, brukerid;
	private String username, password, start;
	private String StartT, SlutT, Beskrivelse;
	
	public void setBrukerid(int id){
		this.brukerid = id;
		BID = this.brukerid;
	}

	public void start(final Stage stage) throws Exception {
//		Parent root = FXMLLoader.load(getClass().getResource("Calendar.fxml"));
//		Scene scene = new Scene(root);
//        stage.setTitle("Calendar");
//        stage.setScene(scene);
//        stage.show();
		final GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setHgap(50);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		
		Scene scene = new Scene(grid, 1000, 1000);
		stage.setScene(scene);
		stage.setTitle("Calendar");
		stage.show();
		
		Text name1 = new Text("Name: ");
		name1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(name1, 0, 0, 1, 1);
//		name1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		Text title = new Text("Her er en liste over dine avtaler:");
		grid.add(title, 0,2,1,6);
		Label name2 = new Label(getName(BID));
		name2.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
		grid.add(name2, 0, 0, 1, 5);
		
		Label meeting = new Label(CheckCalendar.PrintDay(BID));
		grid.add(meeting, 0, 6, 1, 10);
		
		Button newMeeting = new Button("New Meeting");
		Button cl = new Button("Close");
		Button update = new Button("Update");
		Button newGroup = new Button("New Group");
		Button changeMeeting = new Button("Change Meeting");
		grid.add(cl, 2, 20,1,1);
		grid.add(newMeeting, 2, 1,1,1);
		grid.add(update, 0,20,1,1);
		grid.add(newGroup,2,2,1,1);
		grid.add(changeMeeting, 2,3,1,1);
		
		
		changeMeeting.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				final Stage stage2 = new Stage();
				GridPane grid = new GridPane();
				grid.setAlignment(Pos.TOP_LEFT);
				grid.setHgap(50);
				grid.setVgap(10);
				grid.setPadding(new Insets(10, 10, 10, 10));
				Scene scene1 = new Scene(grid, 450, 500);
				stage2.setScene(scene1);
				stage2.setTitle("Change Meeting");
				stage2.show();
				
				Button cl = new Button("Save and Exit");
				
				grid.add(cl, 2, 6);
				
				
				
				
				cl.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
						stage2.close();
					}
				});
			}
		});
		
		newGroup.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				final Stage stage2 = new Stage();
				GridPane grid = new GridPane();
				grid.setAlignment(Pos.TOP_LEFT);
				grid.setHgap(50);
				grid.setVgap(10);
				grid.setPadding(new Insets(10, 10, 10, 10));
				Scene scene1 = new Scene(grid, 450, 500);
				stage2.setScene(scene1);
				stage2.setTitle("New Group");
				stage2.show();
				
				Button cl = new Button("Save and Exit");
				
				grid.add(cl, 2, 2);
				
				
				
				
				cl.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
						stage2.close();
					}
				});
			}
		});
		
		newMeeting.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				
				final CreateMeeting mote = new CreateMeeting(BID);
	
				final Stage stage1 = new Stage();
				GridPane grid = new GridPane();
				grid.setAlignment(Pos.TOP_LEFT);
				grid.setHgap(50);
				grid.setVgap(10);
				grid.setPadding(new Insets(10, 10, 10, 10));
				Scene scene1 = new Scene(grid, 450, 500);
				stage1.setScene(scene1);
				stage1.setTitle("New Meeting");
				stage1.show();	
				final Text start = new Text("Start-tidspunkt(hh:mm:ss): ");
				final Text slutt = new Text("Slutt-tidspunkt(hh:mm:ss): ");
				final Text beskrivelse = new Text("Beskrivelse: ");
				final Text dato = new Text("Dato(yyyy-mm-dd): ");
				final Text antall = new Text("Antall: ");
				final TextField start1 = new TextField();
				final TextField slutt1 = new TextField();
				final TextArea beskrivelse1 = new TextArea();
				final TextField dato1 = new TextField();
				final TextField antall1 = new TextField();
				Button cl = new Button("Save and Exit");
				Button cl1 = new Button("Cancel");
				
				grid.add(cl, 2, 20);
				grid.add(cl1, 1, 20);
				grid.add(start, 1, 3);
				grid.add(start1, 2, 3);
				grid.add(slutt, 1, 5);
				grid.add(slutt1, 2, 5);
				grid.add(beskrivelse, 1,7);
				grid.add(beskrivelse1, 2,7);
				grid.add(dato, 1, 13);
				grid.add(dato1, 2, 13);
				grid.add(antall, 1, 15);
				grid.add(antall1, 2, 15);
				
				
				cl.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
						mote.setMeeting(start1.getText(), slutt1.getText(), beskrivelse1.getText(), dato1.getText(), antall1.getText());
						mote.ChooseRoomGUI();
						mote.create();
						sqlRetrieve mid = new sqlRetrieve("SELECT MAX(moteid) FROM mote");
						String MID = mid.getQuery()[0][0];
						int Mid = Integer.parseInt(MID);
						final EditMeeting editmote = new EditMeeting(Mid);
						editmote.leggtilbruker(BID);
						stage1.close();
					}
				});
				cl1.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
	
						stage1.close();
					}
				});
			}

		});
		
		cl.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				stage.close();
			}
		});
		
		update.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				Label name2 = new Label(getName(BID));
				name2.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
				grid.add(name2, 0, 0, 1, 5);
			}
		});
	} 
	
	
	public String getName(int bid){
		sqlRetrieve getName = new sqlRetrieve("SELECT * FROM bruker WHERE brukerid ='" + bid + "';");
		String fornavn = getName.getQuery()[0][1];
		String etterNavn = getName.getQuery()[0][2];
		return fornavn + " " + etterNavn;
		
	}
	
//	@FXML
//	private Button ExitButton, NewMeeting, SelectButton, NewGroup, Save;
//	
//	@FXML
//	private Label ID, avtaler;
//	
//	@FXML
//	private TextField StartTid, SluttTid;
//	
//	
//	@FXML
//    void initialize() {
//        assert ExitButton != null : "fx:id=\"ExitButton\" was not injected: check your FXML file 'Calendar.fxml'.";
//        assert Save != null : "fx:id=\"Save\" was not injected: check your FXML file 'NewMeeting.fxml'.";
//        
//        		sqlRetrieve getName = new sqlRetrieve("SELECT * FROM bruker WHERE brukerid ='" + BID + "';");
//        		String fornavn = getName.getQuery()[0][1];
//        		String etterNavn = getName.getQuery()[0][2];
//        		ID.setText(fornavn + " " + etterNavn + "BrukerId: " + BID);
//        		avtaler.setText(CheckCalendar.PrintDay(BID));
//        		
////		Save.setOnAction(new EventHandler<ActionEvent>() {
////        	public void handle(ActionEvent event) {
////        		Platform.exit();
////        	}
////        });	
//        
//        
//		SelectButton.setOnAction(new EventHandler<ActionEvent>() {
//        	public void handle(ActionEvent event) {
//        		Platform.exit();
//        	}
//        });
//        		
//        ExitButton.setOnAction(new EventHandler<ActionEvent>() {
//        	public void handle(ActionEvent event) {
//        		Platform.exit();
//        	}
//        });
//        
//        NewMeeting.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//            	Parent root;
//                try {
//                    root = FXMLLoader.load(getClass().getResource("NewMeeting.fxml"));
//                    Stage stage = new Stage();
//                    stage.setTitle("New Meeting");
//                    stage.setScene(new Scene(root, 500, 450));
//                    stage.show();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
////                ((Node)(event.getSource())).getScene().getWindow().hide();
//            }
//        });
//    
//    }
	
	
	public static void main(String[] args) {
		launch(CreateCalendar.class, args);
		
		
	}
}
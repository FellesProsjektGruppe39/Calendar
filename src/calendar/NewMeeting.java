
package calendar;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import notification.CreateNotification;
import mysql.sqlExecute;
import mysql.sqlRetrieve;
import Meeting.CreateMeeting;
import Meeting.EditMeeting;
import Room.CheckRoom;

import com.sun.prism.paint.Color;

import sun.launcher.resources.launcher;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Application;

public class NewMeeting extends Application {
	
	private static int BID = 3;
	private int brukerid;
	
	sqlExecute sql = new sqlExecute();
	
	public void setBrukerid(int id){
		this.brukerid = id;
		BID = this.brukerid;
	}
	
	public void start(final Stage stage) throws IOException {
	
		final Stage stage1 = new Stage();
		final GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setHgap(50);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		Scene scene1 = new Scene(grid, 460, 500);
		stage1.setScene(scene1);
		stage1.setTitle("New Meeting");
		stage1.show();	
		
		final Text start = new Text("Start-tidspunkt(hh:mm:ss): ");
		final Text slutt = new Text("Slutt-tidspunkt(hh:mm:ss): ");
		final Text beskrivelse = new Text("Beskrivelse: ");
		final Text dato = new Text("Dato(yyyy-mm-dd): ");
		final TextField start1 = new TextField();
		final TextField slutt1 = new TextField();
		final TextArea beskrivelse1 = new TextArea();
		final TextField dato1 = new TextField();
		final DatePicker datePicker = new DatePicker();
		final Text antall = new Text("Antall brukere i m�tet: ");
		final TextField antall1 = new TextField();
		
		 datePicker.setOnAction(new EventHandler<ActionEvent>() {
		     public void handle(ActionEvent t) {
		         LocalDate date = datePicker.getValue();
		         System.err.println("Selected date: " + date);
		     }
		 });
		 
		final Text text3 = new Text();
		
		Button cl = new Button("Add Users to Meeting");
		Button cl1 = new Button("Cancel");
		
		grid.add(text3, 2, 15);
		grid.add(cl, 2, 20);
		grid.add(cl1, 1, 20);
		grid.add(start, 1, 3);
		grid.add(start1, 2, 3);
		grid.add(slutt, 1, 5);
		grid.add(slutt1, 2, 5);
		grid.add(beskrivelse, 1,7);
		grid.add(beskrivelse1, 2,7);
		grid.add(dato, 1, 13);
		grid.add(datePicker, 2, 13);
		grid.add(antall, 1, 11);
		grid.add(antall1, 2, 11);
		
		cl.setOnAction(new EventHandler<ActionEvent>() {
			@SuppressWarnings("null")
			@Override public void handle(ActionEvent e) {
				CheckCalendar c = new CheckCalendar();
				
				if (!c.checkinput(start1.getText(), slutt1.getText(), datePicker.getValue().toString(), antall1.getText())){
					text3.setText("FEIL INPUT!!");
					text3.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
					text3.setFill(javafx.scene.paint.Color.RED);
				}else{
				final Stage stage3 = new Stage();
				final GridPane grid = new GridPane();
				grid.setAlignment(Pos.TOP_LEFT);
				grid.setHgap(50);
				grid.setVgap(10);
				grid.setPadding(new Insets(10, 10, 10, 10));
				Scene scene1 = new Scene(grid, 450, 500);
				stage3.setScene(scene1);
				stage3.setTitle("Add Users");
				stage3.show();
				Button close = new Button("Save And Exit");
				Button add = new Button("Add All Users");
				Button adda = new Button("Add All Groups");
				Text us = new Text("Her legger man til enkeltbrukere: ");
				Text us1 = new Text("Her legger man til grupper: ");
				grid.add(close, 2, 4);
				grid.add(add, 2, 2);
				grid.add(adda, 2, 3);
				grid.add(us, 0, 0);
				grid.add(us1, 0, 2);
				
				GridPane grid2 = new GridPane();
				grid2.setAlignment(Pos.TOP_LEFT);
				grid2.setHgap(10);
				grid2.setVgap(10);
				grid2.setPadding(new Insets(10, 10, 10, 10));
				ScrollPane sp = new ScrollPane(grid2);
				grid.add(sp, 0, 1);
				
				GridPane grid3 = new GridPane();
				grid3.setAlignment(Pos.TOP_LEFT);
				grid3.setHgap(10);
				grid3.setVgap(10);
				grid3.setPadding(new Insets(10, 10, 10, 10));
				ScrollPane sp1 = new ScrollPane(grid3);
				grid.add(sp1, 0, 3);
				
				String[] names = CreateCalendar.getNames();
				String[] groups = CreateCalendar.getGroups();
				final String[] names1 = names;
				final CheckBox[] cbs = new CheckBox[names.length];
				final CheckBox[] cbs1 = new CheckBox[groups.length];
				
				for (int i = 0; i < names.length; i++) {
					final CheckBox cb = cbs[i] = new CheckBox(names[i]);
					if(!cbs[i].getText().equals(names1[BID-1])){
						grid2.add(cb, 1, i);
					}
					
				}
				for (int i = 0; i < groups.length; i++) {
					final CheckBox cb1 = cbs1[i] = new CheckBox(groups[i]);
					grid3.add(cb1, 1, i);
				}
				
				add.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
						for (int i = 0; i < cbs.length; i++) {
							cbs[i].setSelected(true);		
						}
					}
				});
				adda.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
						for (int i = 0; i < cbs1.length; i++) {
							cbs1[i].setSelected(true);		
						}
					}
				});
				
				
				close.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
						
						
						final CreateMeeting mote = new CreateMeeting(BID);
						if(CheckRoom.check(datePicker.getValue().toString(), start1.getText(), slutt1.getText(), Integer.parseInt(antall1.getText()))){
							stage3.close();
							
						mote.setMeeting(start1.getText(), slutt1.getText(), beskrivelse1.getText(), datePicker.getValue().toString(), antall1.getText());
						mote.ChooseRoomGUI();
						mote.create();
						
						sqlRetrieve mid = new sqlRetrieve("SELECT MAX(moteid) FROM mote");
						final String MID = mid.getQuery()[0][0];
						final int Mid = Integer.parseInt(MID);
						final EditMeeting meeting = new EditMeeting(Mid);
						
						for (int i = 0; i < cbs.length; i++) {
							if(cbs[i].isSelected()){
								if(BID != CreateCalendar.getID(cbs[i].getText())){
									meeting.leggtilbruker(CreateCalendar.getID(cbs[i].getText()));
								}
							}
						}
						for (int i = 0; i < cbs1.length; i++) {
							if(cbs1[i].isSelected()){
								meeting.leggtilgruppe(cbs1[i].getText());
							}
						}
						sqlExecute create = new sqlExecute();
						create.execute("UPDATE mote_has_bruker SET attending ='" + 1 + "' WHERE mote_moteid = '" + Mid + "' AND "+"bruker_brukerid= '"+ BID +"'");
						stage1.close();
						}else{

							Text feil = new Text("Ingen ledige rom, vennligst g� tilbake og velg ett nytt tidspunkt!");

							feil.setFill(javafx.scene.paint.Color.RED);
							feil.setFont(Font.font("Tahoma", FontWeight.NORMAL, 13));
							grid.add(feil, 0, 4);
						}
					}
				});
				
				
				}	
			}
		});
		cl1.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {

				stage1.close();
			}
		});
	}


		
	
	public static void main(String[] args) {
		launch(changeMeeting.class, args);
	}
}
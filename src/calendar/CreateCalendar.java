

package calendar;

import java.awt.SecondaryLoop;
import java.io.IOException;
import java.time.LocalDate;

import notification.GetNotification;
import Meeting.CreateMeeting;
import Meeting.EditMeeting;

import com.sun.glass.events.MouseEvent;

import logIn.LogIn;
import mysql.sqlExecute;
import mysql.sqlRetrieve;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateCalendar extends Application  {

	private static int BID = 3;
	private int width = 1000, height = 600, brukerid;
	private String username, password, start;
	private String StartT, SlutT, Beskrivelse;
	private int antall = 1;
	
	public void setBrukerid(int id){
		this.brukerid = id;
		BID = this.brukerid;
	}

	public void start(final Stage stage) throws Exception {
		final GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setHgap(50);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		
		Scene scene = new Scene(grid, 1000, 1000);
		stage.setScene(scene);
		stage.setTitle("Calendar");
		stage.show();
		
		// Notifications
		GetNotification getNot = new GetNotification(BID);
		String insertionstring = getNot.get();
		//insertionstring += getNot.get();
		//insertionstring += getNot.get();
		//insertionstring += getNot.get();
		//insertionstring += getNot.get();
		//insertionstring += getNot.get();
		//insertionstring += getNot.get();
		//insertionstring += getNot.get();
		//insertionstring += getNot.get();
		//insertionstring += getNot.get();
		
		BorderPane borderPane = new BorderPane();
        VBox myView = new VBox();
        BorderPane borderPane1 = new BorderPane();
        
        
		Text text = new Text(insertionstring);
		text.setFont(Font.font("Consolas", FontWeight.NORMAL, 13));
		text.setWrappingWidth(1000);
        myView.getChildren().addAll(text);
        
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(myView);
        scroll.fitToWidthProperty();
        scroll.setMaxHeight(200);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        borderPane.setCenter(scroll);
        grid.add(scroll,0,30, 10, 10);
        
        ScrollPane scroll1 = new ScrollPane();
        scroll1.fitToWidthProperty();
        scroll1.setMaxHeight(600);
        scroll1.setPrefHeight(200);
        scroll1.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        borderPane1.setCenter(scroll1);
        grid.add(scroll1,0,6,10,10);
        
		
		
		Text name1 = new Text("Name: ");
		name1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(name1, 0, 0, 1, 1);
//		name1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		Text title = new Text("Her er en liste over dine avtaler:");
		grid.add(title, 0,2,1,6);
		Text title1 = new Text("Her er dine notifications:");
		grid.add(title1, 0,29,1,1);
		Label name2 = new Label(getName(BID));
		name2.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
		grid.add(name2, 0, 0, 1, 5);
		
		Label meeting = new Label(CheckCalendar.PrintDay(BID));
//		System.out.println(meeting.getText());
		scroll1.setContent(meeting);
		meeting.setFont(Font.font("Consolas", FontWeight.NORMAL, 13));

		Button newMeeting = new Button("New Meeting");
		Button cl = new Button("Close");
		Button update = new Button("Update");
		Button newGroup = new Button("New Group");
		Button changeMeeting = new Button("Edit Meeting");
		Button showAttendings = new Button("Show unanswered meetings");
		Button showDeclines = new Button("Show declined meetings");
		Button showAccepted = new Button("Show accepted meetings");
		grid.add(cl, 2, 20,1,1);
		grid.add(newMeeting, 2, 1,1,1);
		grid.add(update, 0,20,1,1);
		grid.add(newGroup,2,2,1,1);
		grid.add(changeMeeting, 2,3,1,1);
		grid.add(showAttendings, 0,21,1,1);
		grid.add(showDeclines, 0,22,1,1);
		grid.add(showAccepted, 0,23,1,1);
		

		showAttendings.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {

		        	showAttendings show = new showAttendings();
		        	show.setBrukerid(BID);
	                Stage stage = new Stage();
	                try {
						show.start(stage);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
		showDeclines.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {

		        	showDeclines show = new showDeclines();
		        	show.setBrukerid(BID);
	                Stage stage = new Stage();
	                try {
						show.start(stage);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
		showAccepted.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {

		        	showAccepted show = new showAccepted();
		        	show.setBrukerid(BID);
	                Stage stage = new Stage();
	                try {
						show.start(stage);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
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
				
				sqlRetrieve sql = new sqlRetrieve("SELECT moteid, starttidspunkt, sluttidspunkt, beskrivelse, dato, sted"
						+ " FROM mote "
						+ " WHERE opprettet_av = " + BID 
						+ " AND mr.mote_moteid = moteid");
				
				
				String heading;
				heading = String.format("%-5s    %-10s   %-8s   %-8s   %-30s   %-20s   %-30s","MoteId", "Date", "Start","End","Description","Room","Location");
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
				
				final DatePicker datePicker = new DatePicker();
				 datePicker.setOnAction(new EventHandler<ActionEvent>() {
				     public void handle(ActionEvent e) {
				         LocalDate date = datePicker.getValue();
				         System.err.println("Selected date: " + date);
				     }
				 });
				 
				
				grid.add(cl, 2, 2);
				grid.add(datePicker, 5, 5);
				
				
				
				
				cl.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
						stage2.close();
					}
				});
			}
		});
		
		newMeeting.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				
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
				grid.add(dato1, 2, 13);
				
				cl.setOnAction(new EventHandler<ActionEvent>() {
					@SuppressWarnings("null")
					@Override public void handle(ActionEvent e) {
						CheckCalendar c = new CheckCalendar();
						
						if (!c.checkinput(start1.getText(), slutt1.getText(), dato1.getText())){
							text3.setText("FEIL INPUT!!");
							text3.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
							text3.setFill(Color.RED);
						}else{
						final Stage stage3 = new Stage();
						GridPane grid = new GridPane();
						grid.setAlignment(Pos.TOP_LEFT);
						grid.setHgap(50);
						grid.setVgap(10);
						grid.setPadding(new Insets(10, 10, 10, 10));
						Scene scene1 = new Scene(grid, 450, 500);
						stage3.setScene(scene1);
						stage3.setTitle("Add Users");
						stage3.show();
						Button close = new Button("Save And Exit");
						Button add = new Button("Add All");
						grid.add(close, 2, 4);
						grid.add(add, 2, 2);
						
						
						String[] names = getNames();
						final String[] names1 = names;
						final CheckBox[] cbs = new CheckBox[names.length];
						
						for (int i = 0; i < names.length; i++) {
							final CheckBox cb = cbs[i] = new CheckBox(names[i]);
							if(!cbs[i].getText().equals(names1[BID-1])){
								grid.add(cb, 1, i+2);
							}
							
						}
						
						add.setOnAction(new EventHandler<ActionEvent>() {
							@Override public void handle(ActionEvent e) {
								for (int i = 0; i < cbs.length; i++) {
									cbs[i].setSelected(true);		
								}
							}
						});
						
						
						close.setOnAction(new EventHandler<ActionEvent>() {
							@Override public void handle(ActionEvent e) {
								for (int j = 0; j < cbs.length-1; j++) {
									if (cbs[j].isSelected()){
										antall += 1;
									} else
										cbs[j].setText(null);
								}
//								System.out.println(antall);
								stage3.close();
								
								final CreateMeeting mote = new CreateMeeting(BID);
								
								mote.setMeeting(start1.getText(), slutt1.getText(), beskrivelse1.getText(), dato1.getText(), antall);
								mote.ChooseRoomGUI();
								mote.create();
								
								sqlRetrieve mid = new sqlRetrieve("SELECT MAX(moteid) FROM mote");
								final String MID = mid.getQuery()[0][0];
								final int Mid = Integer.parseInt(MID);
								final EditMeeting meeting = new EditMeeting(Mid);
								System.out.println(Mid);
								
								for (int i = 0; i < cbs.length; i++) {
									if(cbs[i].getText() != null){
										if(BID != getID(cbs[i].getText())){
											meeting.leggtilbruker(getID(cbs[i].getText()));
										}
									}
								}
								sqlExecute create = new sqlExecute();
								create.execute("UPDATE mote_has_bruker SET attending ='" + 1 + "' WHERE mote_moteid = '" + Mid + "' AND "+"bruker_brukerid= '"+ BID +"'");
								stage1.close();
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
	
	public String[] getNames(){
		String str = "";
		sqlRetrieve getName = new sqlRetrieve("SELECT fornavn, etternavn FROM bruker");
		int l = getName.getQuery().length;
		for (int i = 0; i < l; i++) {
		String fornavn = getName.getQuery()[i][0];
		String etterNavn = getName.getQuery()[i][1];
		str +=  " " + fornavn + " " + etterNavn + ",";
		}
		String[] names1 = str.split(",");
//		names1[2].trim();
		
//		System.out.println(names1.length);
		return names1;
	}
	
	public int getID(String navn){
		int ID;
		String[] na = navn.split(" ");
		if(na.length > 3){
		na[2] = na[2] +" "+na[3];
		}
		sqlRetrieve getID = new sqlRetrieve("SELECT brukerid FROM bruker WHERE fornavn = '" + na[1] + "'AND"+ " etternavn = '" + na[2]+"'");
		ID = Integer.parseInt(getID.getQuery()[0][0]);
//		System.out.println(ID);
		return ID;
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
//		CreateCalendar a = new CreateCalendar();
//		a.getID(" Martin Raknes Holth");
		
	}

}
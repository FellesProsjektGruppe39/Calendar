package calendar;

import java.awt.SecondaryLoop;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EventObject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import sun.util.resources.LocaleData;
import notification.CreateNotification;
import notification.GetNotification;
import Meeting.CreateMeeting;
import Meeting.EditMeeting;
import Room.Room;

import com.sun.glass.events.MouseEvent;
import com.sun.xml.internal.ws.Closeable;

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

	private static int BID = 1;
	private int width = 1000, height = 600, brukerid;
	private String username, password, start;
	private String StartT, SlutT, Beskrivelse;
	private int antall = 1, antall2;
	
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
		Button showCalendar = new Button("Show calendar for another user");
		Button createRoom = new Button("Create a new Room");
		grid.add(createRoom,4,22,1,1);
		grid.add(cl, 2, 20,1,1);
		grid.add(newMeeting, 2, 1,1,1);
		grid.add(update, 0,20,1,1);
		grid.add(newGroup,2,2,1,1);
		grid.add(changeMeeting, 2,3,1,1);
		grid.add(showAttendings, 0,21,1,1);
		grid.add(showDeclines, 0,22,1,1);
		grid.add(showAccepted, 0,23,1,1);
		grid.add(showCalendar, 1,20, 1,1);
		
		showCalendar.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				ShowCalendar show = new ShowCalendar();
				Stage stage = new Stage();
                try {
					show.start(stage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

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
		
		createRoom.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				final Stage stage1 = new Stage();
				final GridPane grid = new GridPane();
				grid.setAlignment(Pos.TOP_LEFT);
				grid.setHgap(50);
				grid.setVgap(10);
				grid.setPadding(new Insets(10, 10, 10, 10));
				Scene scene1 = new Scene(grid, 460, 500);
				stage1.setScene(scene1);
				stage1.setTitle("Create a Room");
				stage1.show();	
				
				final Text Romnavn = new Text("Gi rommet et navn: ");
				final Text Sted = new Text("Angi et sted for rommet: ");
				final Text beskrivelse = new Text("Beskrivelse: ");
				final Text Kapasitet = new Text("Romkapasitet: ");
				final TextField Romnavn1 = new TextField();
				final TextField Sted1 = new TextField();
				final TextArea beskrivelse1 = new TextArea();
				final TextField Kapasitet1 = new TextField();
				//final Text text3 = new Text();
				
				Button SandE = new Button("Save and Exit");
				Button cl = new Button("Cancel");

				grid.add(cl, 2, 20);
				grid.add(SandE,1,20);
				grid.add(Romnavn, 1, 3);
				grid.add(Romnavn1, 2, 3);
				grid.add(Sted, 1, 5);
				grid.add(Sted1, 2, 5);
				grid.add(beskrivelse, 1,7);
				grid.add(beskrivelse1, 2,7);
				grid.add(Kapasitet, 1, 13);
				grid.add(Kapasitet1, 2, 13);
				
				cl.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
	
						stage1.close();
					}
				});
				
				SandE.setOnAction(new EventHandler<ActionEvent>(){
					@Override public void handle(ActionEvent e) {
						final Room rom = new Room();
						final String Kappa = Kapasitet1.getText();
						final int kapasitet = Integer.parseInt(Kappa);
						rom.createRoom(Romnavn1.getText(), kapasitet, Sted1.getText(), beskrivelse1.getText());
						stage1.close();
					}
				});
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
				
				
				GridPane grid2 = new GridPane();
				grid2.setAlignment(Pos.CENTER);
				grid2.setHgap(10);
				grid2.setVgap(10);
				grid2.setPadding(new Insets(25, 25, 25, 25));
				
				GridPane grid = new GridPane();
				
				ScrollPane sp = new ScrollPane(grid);
				grid2.add(sp, 0, 1);
				final Stage stage4 = new Stage();
				Scene scene = new Scene(grid2, 1100, 600);
				stage4.setScene(scene);
				stage4.setTitle("Your Meetings");
				stage4.show();
				
				Button cl = new Button("Close");
				grid2.add(cl, 1,10);
				
				final ArrayList<Integer> moteid = new ArrayList<Integer>();
				final ArrayList<Label> print = new ArrayList<Label>();
				
				sqlRetrieve sql = new sqlRetrieve("SELECT moteid, dato, starttidspunkt, sluttidspunkt, beskrivelse, rom_romnavn, sted"
						+ " FROM mote m, mote_has_rom mr "
						+ " WHERE opprettet_av = " + BID 
						+ " AND mr.mote_moteid = moteid"
						+ " AND m.dato >= CURDATE()"
						+ " ORDER BY dato, starttidspunkt ASC");
				
				
				String heading;
				heading = String.format("%-5s    %-10s   %-8s   %-8s   %-30s   %-20s   %-30s","MoteId", "Date", "Start","End","Description","Room","Location");
				print.add(new Label(heading));
				print.get(0).setFont(Font.font("Consolas", FontWeight.NORMAL, 13));
				
				grid.add(print.get(0), 0, 0, 1, 1);
				
				for( int i=0; i < sql.getQuery().length; i++){
					
			
					moteid.add(Integer.parseInt(sql.getQuery()[i][0]));
					String a = String.format("%-5s %-10s - %-8s   %-8s   %-30s   %-20s - %-15s",sql.getQuery()[i][0], sql.getQuery()[i][1], sql.getQuery()[i][2], sql.getQuery()[i][3],sql.getQuery()[i][4], sql.getQuery()[i][5], sql.getQuery()[i][6]);					
					print.add(new Label(a));
					print.get(i+1).setFont(Font.font("Consolas", FontWeight.NORMAL, 13));
					grid.add(print.get(i+1), 0, i+1, 1, 1);
				}
				
				final Text endre = new Text("MeetingID of the meeting you want to edit: ");
				grid2.add(endre, 0,6);
				final TextField endremoteid = new TextField();
				grid2.add(endremoteid, 0,7);
				Button confirm = new Button("  Edit  ");
				grid2.add(confirm, 0, 8);
				
				confirm.setOnAction(new EventHandler<ActionEvent>(){
					

					@Override public void handle(ActionEvent e) {
						final int moteid = Integer.parseInt(endremoteid.getText());
						final sqlRetrieve sql2 = new sqlRetrieve("SELECT * FROM mote WHERE moteid = " + moteid);
						
						
						final Stage stage1 = new Stage();
						final GridPane grid = new GridPane();
						grid.setAlignment(Pos.TOP_LEFT);
						grid.setHgap(50);
						grid.setVgap(10);
						grid.setPadding(new Insets(10, 10, 10, 10));
						Scene scene1 = new Scene(grid, 460, 500);
						stage1.setScene(scene1);
						stage1.setTitle("Edit meeting");
						stage1.show();	
						
						
					
						
						final LocalDate myDate = LocalDate.parse(sql2.getQuery()[0][4]);
						
						
						final Text start = new Text("Start-tidspunkt(hh:mm:ss): ");
						final Text slutt = new Text("Slutt-tidspunkt(hh:mm:ss): ");
						final Text beskrivelse = new Text("Beskrivelse: ");
						final Text dato = new Text("Dato(yyyy-mm-dd): ");
						final TextField start1 = new TextField(sql2.getQuery()[0][1]);
						final TextField slutt1 = new TextField(sql2.getQuery()[0][2]);
						final TextArea beskrivelse1 = new TextArea(sql2.getQuery()[0][3]);
						final DatePicker datePicker = new DatePicker(myDate);
						
						
						
						datePicker.setOnAction(new EventHandler<ActionEvent>() {
						     public void handle(ActionEvent t) {
						        final LocalDate dateX = datePicker.getValue();
						        if(!myDate.equals(dateX)){
						        	EditMeeting em = new EditMeeting(moteid);
						        	em.endreDato(dateX.toString());
						        }
						        
						     }
						 });
						final Text text3 = new Text();
						
						Button cl = new Button("Continue");
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
						
						cl.setOnAction(new EventHandler<ActionEvent>() {
							@SuppressWarnings("null")
							@Override public void handle(ActionEvent e) {
								CheckCalendar c = new CheckCalendar();
								
								if (!c.checkinput(start1.getText(), slutt1.getText(), datePicker.getValue().toString())){
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
								stage3.setTitle("Edit Users");
								stage3.show();
								Button close = new Button("Save And Exit");
								Button add = new Button("Add All");
								grid.add(close, 2, 4);
								grid.add(add, 2, 2);
								
								
								final String[] names = getNames();
								final String[] names1 = names;
								final CheckBox[] cbs = new CheckBox[names.length];
								
								final ArrayList<Integer> attendingusers = new ArrayList<Integer>();
								
								sqlRetrieve sql3 = new sqlRetrieve("SELECT bruker_brukerid FROM mote_has_bruker WHERE mote_moteid = " + moteid);
								sqlRetrieve sql4 = new sqlRetrieve("SELECT count(*) FROM mote_has_bruker WHERE mote_moteid = " + moteid);
								
								int lengde = Integer.parseInt(sql4.getQuery()[0][0]);
								
								for(int i = 0; i < lengde; i++){
									attendingusers.add(Integer.parseInt(sql3.getQuery()[i][0]));
								}
								
								
								for (int i = 0; i < names.length; i++) {
									final CheckBox cb = cbs[i] = new CheckBox(names[i]);
									if (attendingusers.contains(getID(names[i]))){
										cbs[i].setSelected(true);
										
									}
							
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
											} 
												
										}
										
										final EditMeeting emote = new EditMeeting(moteid);
										
//										stage3.close();
										
										if(!sql2.getQuery()[0][1].equalsIgnoreCase(start1.getText())){
											emote.endreStarttid(start1.getText());
										}
										
										if(!sql2.getQuery()[0][2].equalsIgnoreCase(slutt1.getText())){
											emote.endreSluttid(slutt1.getText());
										}
										
										if(!sql2.getQuery()[0][3].equalsIgnoreCase(beskrivelse1.getText())){
											emote.endreBeskrivelse(beskrivelse1.getText());
										}
										
										sqlRetrieve info = new sqlRetrieve("SELECT bruker_brukerid, mote_moteid, beskrivelse, starttidspunkt, sluttidspunkt, dato"
												+ " FROM mote_has_bruker, mote"
												+ " WHERE mote_moteid = moteid"
												+ " AND mote_moteid = " + moteid);
										if(!info.getQuery()[0][5].equalsIgnoreCase(myDate.toString())){
											CreateNotification cn = new CreateNotification();
										
										sqlRetrieve moter = new sqlRetrieve ("SELECT COUNT(*)FROM mote_has_bruker WHERE mote_moteid = " + moteid);
											
											
											for (int i = 0;i < Integer.parseInt(moter.getQuery()[0][0]); i++){
											cn.create(Integer.parseInt(info.getQuery()[i][0]),  info.getQuery()[0][2] + " har endret dato til " + info.getQuery()[0][5] + " fra " + myDate.toString());
											}
										}
										
										for(int i = 0; i < cbs.length;i++){
											if(cbs[i].isSelected()== true){
												if(BID != getID(cbs[i].getText())){
													if (!attendingusers.contains(getID(names[i]))){
														emote.leggtilbruker(getID(cbs[i].getText()));
													}
												}
											}
											if(cbs[i].isSelected() == false){
												if(BID != getID(cbs[i].getText())){
													if (attendingusers.contains(getID(names[i]))){
														emote.fjernbruker(getID(cbs[i].getText()));
												
													}
												}
											}
										}
										
										
									
										
									
									stage3.close();
									stage1.close();
									stage4.close();
									}});
								
								
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
				Scene scene1 = new Scene(grid, 800, 500);
				stage2.setScene(scene1);
				stage2.setTitle("New Group");
				stage2.show();
				
				Text name = new Text("Name of the group:");
				Text name1 = new Text("Participants of the group:");
				final TextField name2 = new TextField();
				Button cl = new Button("Exit");
				Button add = new Button("Add all");
				Button save = new Button("Save");
				
				grid.add(save, 3, 18);
				grid.add(add, 2, 18);
				grid.add(name, 1, 2);
				grid.add(name1, 1, 3);
				grid.add(name2, 2, 2, 3,1);
				grid.add(cl, 3, 19);
				
				String[] names = getNames();
				final String[] names1 = names;
				final CheckBox[] cbs = new CheckBox[names.length];
				
				for (int i = 0; i < names.length; i++) {
					final CheckBox cb = cbs[i] = new CheckBox(names[i]);
						grid.add(cb, 2, i+3);
				}
				
				
				cl.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
						stage2.close();
					}
				});
				add.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
						for (int i = 0; i < cbs.length; i++) {
							cbs[i].setSelected(true);		
						}
					}
				});
				save.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
						sqlExecute create = new sqlExecute();
						create.execute("INSERT INTO gruppe (gruppenavn) VALUES ('"+ name2.getText()+"')");
						sqlRetrieve gid = new sqlRetrieve("SELECT max(gruppeid) FROM gruppe");
						String GID = gid.getQuery()[0][0];
						for (int i = 0; i < cbs.length; i++) {
							if(cbs[i].isSelected()){
								create.execute("INSERT INTO bruker_has_Gruppe (bruker_brukerid, Gruppe_gruppeid) VALUES ('" + getID(names1[i]) +"', "+GID+")");
								CreateNotification not = new CreateNotification();
								not.create(getID(names1[i]), "Du ble nå lagt til i gruppen "+ name2.getText());
							}
							stage2.close();
						}
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
				final DatePicker datePicker = new DatePicker();
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
				
				cl.setOnAction(new EventHandler<ActionEvent>() {
					@SuppressWarnings("null")
					@Override public void handle(ActionEvent e) {
						CheckCalendar c = new CheckCalendar();
						
						if (!c.checkinput(start1.getText(), slutt1.getText(), datePicker.getValue().toString())){
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
						
						String[] names = getNames();
						String[] groups = getGroups();
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
								for (int j = 0; j < cbs.length-1; j++) {
									if (cbs[j].isSelected()){
										antall += 1;
									} 
//										cbs[j].setText(null);
								}
								for (int j = 0; j < cbs1.length-1; j++) {
									if (cbs1[j].isSelected()){
										antall2 += 1;
									} 
//										cbs1[j].setText(null);
								}
//								System.out.println(antall);
								stage3.close();
								
								final CreateMeeting mote = new CreateMeeting(BID);
								
								mote.setMeeting(start1.getText(), slutt1.getText(), beskrivelse1.getText(), datePicker.getValue().toString(), antall);
								mote.ChooseRoomGUI();
								mote.create();
								
								sqlRetrieve mid = new sqlRetrieve("SELECT MAX(moteid) FROM mote");
								final String MID = mid.getQuery()[0][0];
								final int Mid = Integer.parseInt(MID);
								final EditMeeting meeting = new EditMeeting(Mid);
								
								for (int i = 0; i < cbs.length; i++) {
									if(cbs[i].isSelected()){
										if(BID != getID(cbs[i].getText())){
											meeting.leggtilbruker(getID(cbs[i].getText()));
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
				CreateCalendar c = new CreateCalendar();
				try {
					c.start(stage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	} 
	
	
	public static String getName(int bid){
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
	
	public String[] getGroups(){
		sqlRetrieve ret = new sqlRetrieve("SELECT gruppenavn FROM gruppe");
		String str ="";
		for (int i = 0; i < ret.getQuery().length; i++) {
			str += " "+ ret.getQuery()[i][0] + ",";
		}
		String[] res = str.split(",");
		return res;
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
	
	public static void main(String[] args) {
		launch(CreateCalendar.class, args);
//		CreateCalendar a = new CreateCalendar();
//		a.getID(" Martin Raknes Holth");
		
	}
}
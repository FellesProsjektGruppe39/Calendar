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

public class changeMeeting extends Application {
	
	private static int BID = 5;
	private int brukerid;
		
	public void setBrukerid(int id){
		this.brukerid = id;
		BID = this.brukerid;
	}
	
	public void start(final Stage stage) throws IOException {
		
		final GridPane grid2 = new GridPane();
		grid2.setAlignment(Pos.CENTER);
		grid2.setHgap(10);
		grid2.setVgap(10);
		grid2.setPadding( new Insets(25, 25, 25, 25));
		
		final GridPane grid = new GridPane();
		
		final ScrollPane sp = new ScrollPane(grid);
		grid2.add(sp, 0, 1);
		final Stage stage4 = new Stage();
		Scene scene = new Scene(grid2, 1100, 600);
		stage4.setScene(scene);
		stage4.setTitle("Your Meetings");
		stage4.show();
		
		Button cl = new Button("Close");
		grid2.add(cl, 1,10);
		
		final ArrayList<Integer> moteidarr = new ArrayList<Integer>();
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
			
	
			moteidarr.add(Integer.parseInt(sql.getQuery()[i][0]));
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
		Button confirm2 = new Button("Edit attendance");
		Button delete = new Button("Delete meeting");
		grid2.add(delete, 0, 10);
		grid2.add(confirm2, 0, 9);
		
		delete.setOnAction(new EventHandler<ActionEvent>(){
			


			@Override
			public void handle(ActionEvent arg0) {
				
				EditMeeting endremote = new EditMeeting(Integer.parseInt(endremoteid.getText()));
				sqlRetrieve beskrivelse = new sqlRetrieve("SELECT beskrivelse FROM mote WHERE moteid = " + Integer.parseInt(endremoteid.getText()));
				
				endremote.DeleteMeeting();
				Text slettet = new Text(beskrivelse.getQuery()[0][0] + " har nå blitt slettet");
				
				slettet.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
				slettet.setFill(javafx.scene.paint.Color.RED);
				grid2.add(slettet, 2, 8);

				
				
			}
			
		});
		
		confirm.setOnAction(new EventHandler<ActionEvent>(){
			
			
			@Override public void handle(ActionEvent e) {
				final int moteid = Integer.parseInt(endremoteid.getText());
				if(!moteidarr.contains(moteid)){
					    Text text3 = new Text();
						text3.setText("Invalid meetingID");
						text3.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
						text3.setFill(javafx.scene.paint.Color.RED);
						grid2.add(text3, 0, 10);
				}
				else{
				
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
				final Text rom = new Text("Rom:");
				final TextField start1 = new TextField(sql2.getQuery()[0][1]);
				final TextField slutt1 = new TextField(sql2.getQuery()[0][2]);
				final TextArea beskrivelse1 = new TextArea(sql2.getQuery()[0][3]);
				final DatePicker datePicker = new DatePicker(myDate);
				final Text antall = new Text("Antall brukere i møtet: ");
				final TextField antall1 = new TextField();
				
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
				grid.add(antall, 1, 11);
				grid.add(antall1, 2, 11);
				
				cl.setOnAction(new EventHandler<ActionEvent>() {
					@SuppressWarnings("null")
					@Override public void handle(ActionEvent e) {
						CheckCalendar c = new CheckCalendar();

						if (!c.checkinput(start1.getText(), slutt1.getText(), datePicker.getValue().toString(), "1")){
							text3.setText("FEIL INPUT!!");
							text3.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
							text3.setFill(javafx.scene.paint.Color.RED);
						}
						if(antall1.getText().isEmpty()){
							text3.setText("Må ha antall brukere");
							text3.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
							text3.setFill(javafx.scene.paint.Color.RED);
						}
						else{
							
							final ArrayList<Integer> attendingusers = new ArrayList<Integer>();
							
							sqlRetrieve sql3 = new sqlRetrieve("SELECT bruker_brukerid FROM mote_has_bruker WHERE mote_moteid = " + moteid);
							sqlRetrieve sql4 = new sqlRetrieve("SELECT count(*) FROM mote_has_bruker WHERE mote_moteid = " + moteid);
							
							int lengde = Integer.parseInt(sql4.getQuery()[0][0]);
							
							for(int i = 0; i < lengde; i++){
								attendingusers.add(Integer.parseInt(sql3.getQuery()[i][0]));
							}
							
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
							
							final String[] names = CreateCalendar.getNames();
							String[] groups = CreateCalendar.getGroups();
							final String[] names1 = names;
							final CheckBox[] cbs = new CheckBox[names.length];
							final CheckBox[] cbs1 = new CheckBox[groups.length];
							
							for (int i = 0; i < names.length; i++) {
								final CheckBox cb = cbs[i] = new CheckBox(names[i]);
								if(!cbs[i].getText().equals(names1[BID-1])){
									if(attendingusers.contains(CreateCalendar.getID(names[i]))){
										cbs[i].setSelected(true);
									}
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
									
									sqlRetrieve endredato = new sqlRetrieve("SELECT dato FROM mote WHERE moteid = " + moteid); 
	
									final EditMeeting emote = new EditMeeting(moteid);
									
//										stage3.close();
									if(CheckRoom.checkChange(endredato.getQuery()[0][0], start1.getText(), slutt1.getText(), Integer.parseInt(antall1.getText()), moteid)){
										stage3.close();
										sqlRetrieve ledigrom = new sqlRetrieve("SELECT romnavn, beskrivelse, kapasitet, sted"
											+" FROM rom"
											+" WHERE romnavn" 
											+" NOT IN (SELECT r.romnavn"
											+		" FROM mote m, mote_has_rom mr, rom r"
											+		" WHERE mr.mote_moteid = m.moteid" 
											+		" AND m.sluttidspunkt > '" + start1.getText()
											+		"' AND m.starttidspunkt <  '" +  slutt1.getText()
											+		"' AND m.dato = '" + endredato.getQuery()[0][0]
											+		"' AND mr.rom_romnavn = r.romnavn"
											+		" AND m.moteid != " + moteid + ")"
											+		" AND kapasitet >= " + Integer.parseInt(antall1.getText()));
										
										emote.endreRom(ledigrom.getQuery()[0][0]);
										
										
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
											if(BID != CreateCalendar.getID(cbs[i].getText())){
												if (!attendingusers.contains(CreateCalendar.getID(names[i]))){
													emote.leggtilbruker(CreateCalendar.getID(cbs[i].getText()));
												}
											}
										}
										if(cbs[i].isSelected() == false){
											if(BID != CreateCalendar.getID(cbs[i].getText())){
												if (attendingusers.contains(CreateCalendar.getID(names[i]))){
													emote.fjernbruker(CreateCalendar.getID(cbs[i].getText()));
											
												}
											}
										}
								
									}
									for (int i = 0; i < cbs1.length; i++) {
										if(cbs1[i].isSelected()){
											emote.leggtilgruppe(cbs1[i].getText());
										}
									}
									
									
								stage3.close();
								stage1.close();
								stage4.close();
									}
									else{
										Text feil = new Text("Ingen ledige rom, vennligst gå tilbake og velg ett nytt tidspunkt!");

										feil.setFill(javafx.scene.paint.Color.RED);
										feil.setFont(Font.font("Tahoma", FontWeight.NORMAL, 13));
										grid.add(feil, 0, 4);
									}
									
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
		}

			
		});

		

		cl.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				stage4.close();
			}
		});
		
		confirm2.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				
				final int moteid = Integer.parseInt(endremoteid.getText());
				if(!moteidarr.contains(moteid)){
					    Text text3 = new Text();
						text3.setText("Invalid meetingID");
						text3.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
						text3.setFill(javafx.scene.paint.Color.RED);
						grid2.add(text3, 0, 10);
				}
				else{ 
				
				
					final GridPane grid2 = new GridPane();
					grid2.setAlignment(Pos.CENTER);
					grid2.setHgap(10);
					grid2.setVgap(10);
					grid2.setPadding( new Insets(25, 25, 25, 25));
					
					final Stage stage6 = new Stage();
					Scene scene = new Scene(grid2, 500, 500);
					stage6.setScene(scene);
					stage6.setTitle("Attendance list");
					stage6.show();
					
					final sqlRetrieve info = new sqlRetrieve("SELECT bruker_brukerid, attending FROM mote_has_bruker "
							+ "WHERE mote_moteid = '" + moteid + "'");
					
					final ArrayList<ChoiceBox> cb = new ArrayList<ChoiceBox>();	
					final ArrayList<Integer> brukerid = new ArrayList<Integer>();
					final ArrayList<Label> print = new ArrayList<Label>();
					
					for( int i=0; i < info.getQuery().length; i++){
						
						cb.add(new ChoiceBox(FXCollections.observableArrayList(0, 1, 2)));
						cb.get(i).setValue(Integer.parseInt(info.getQuery()[i][1]));
						brukerid.add(Integer.parseInt(info.getQuery()[i][0]));
						String a = CreateCalendar.getName(Integer.parseInt(info.getQuery()[i][0]));
						print.add(new Label(a));
						print.get(i).setFont(Font.font("Consolas", FontWeight.NORMAL, 13));
						grid2.add(print.get(i), 0, i+1, 1, 1);
						grid2.add(cb.get(i), 1, i+1, 1, 1);
						
					}
					
					Text t = new Text();
					t.setText("0 - Not replied yet \n1 - Attending \n2 - Not attending");
					grid2.add(t,0,0);
					
					Button save = new Button("Save");
					grid2.add(save, 1,11);
					
					save.setOnAction(new EventHandler<ActionEvent>() {
						@Override public void handle(ActionEvent e) {
							
							CreateNotification Notif = new CreateNotification();
							sqlExecute sql = new sqlExecute();
							
							for (int i = 0; i < cb.size(); i++) {
									
								sql.execute("UPDATE mote_has_bruker SET attending = " + cb.get(i).getValue() +
										" WHERE mote_moteid = " + moteid + " AND bruker_brukerid = " + brukerid.get(i));
								
								int a = (int) cb.get(i).getValue();
								
								if (Integer.parseInt(info.getQuery()[i][1]) != a) {
									if (a == 0) {
										Notif.create(brukerid.get(i), CreateCalendar.getName(BID) + " har endret din tilstand til ikke svart paa mote id " + moteid);
									}
									if (a == 1) {
										Notif.create(brukerid.get(i), CreateCalendar.getName(BID) + " har endret din tilstand til attending paa mote id " + moteid);
									}
									if (a == 2) {
										Notif.create(brukerid.get(i), CreateCalendar.getName(BID) + " har endret din tilstand til not attending paa mote id " + moteid);
									}
									
								}
								
							}
							stage6.close();
						}
					});
					
					Button cl = new Button("Close");
					grid2.add(cl, 1,10);
					
					cl.setOnAction(new EventHandler<ActionEvent>() {
						@Override public void handle(ActionEvent e) {
							stage6.close();
						}
					});
					
				    }
			}
			
		});
		
	}
	
	
	
	public static void main(String[] args) {
		launch(changeMeeting.class, args);
	}
}
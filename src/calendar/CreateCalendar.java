
package calendar;

import java.awt.SecondaryLoop;
import java.io.IOException;

import notification.GetNotification;
import Meeting.CreateMeeting;
import Meeting.EditMeeting;

import com.sun.glass.events.MouseEvent;

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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
		
		GetNotification getNot = new GetNotification(BID);
		String insertionstring = getNot.get();
		insertionstring += getNot.get();
		insertionstring += getNot.get();
		insertionstring += getNot.get();
		insertionstring += getNot.get();
		insertionstring += getNot.get();
		insertionstring += getNot.get();
		insertionstring += getNot.get();
		insertionstring += getNot.get();
		insertionstring += getNot.get();
		
		BorderPane borderPane = new BorderPane();
        VBox myView = new VBox();
        
		Text text = new Text(insertionstring);
		text.setWrappingWidth(500);
        myView.getChildren().addAll(text);
        
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(myView);
        scroll.fitToWidthProperty();
        scroll.setMaxHeight(200);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        borderPane.setCenter(scroll);
        grid.add(scroll,0,30);
        
		
		
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
		meeting.setFont(Font.font("Consolas"));
		grid.add(meeting, 0, 6, 1, 10);
		
		Button newMeeting = new Button("New Meeting");
		Button cl = new Button("Close");
		Button update = new Button("Update");
		Button newGroup = new Button("New Group");
		Button changeMeeting = new Button("Edit Meeting");
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
//				final Text antall = new Text("Antall: ");
				final TextField start1 = new TextField();
				final TextField slutt1 = new TextField();
				final TextArea beskrivelse1 = new TextArea();
				final TextField dato1 = new TextField();
//				final TextField antall1 = new TextField();
				
				
				Button cl = new Button("Add Users to Meeting");
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
//				grid.add(antall, 1, 15);
//				grid.add(antall1, 2, 15);
				
				cl.setOnAction(new EventHandler<ActionEvent>() {
					@SuppressWarnings("null")
					@Override public void handle(ActionEvent e) {
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
//						ScrollPane sb = new ScrollPane();
////						sb.setOrientation(Orientation.VERTICAL);
//						grid.add(sb, 0, 1, 10 ,1);
						
						Button close = new Button("Save And Exit");
						Button add = new Button("Add All");
						grid.add(close, 4, 2);
						grid.add(add, 4, 7);
						
						String[] names = getNames();
						final String[] names1 = names;
						final CheckBox[] cbs = new CheckBox[names.length];
						
						for (int i = 0; i < names.length -1 ; i++) {
							final CheckBox cb = cbs[i] = new CheckBox(names[i]);
							grid.add(cb, 1, i+2);
							
							
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
										System.out.println(names1[j]);
									}
								}
			
								stage3.close();
							}
						});
						
						
						
						mote.setMeeting(start1.getText(), slutt1.getText(), beskrivelse1.getText(), dato1.getText());
//						mote.ChooseRoomGUI();
						mote.create();
						sqlRetrieve mid = new sqlRetrieve("SELECT MAX(moteid) FROM mote");
						String MID = mid.getQuery()[0][0];
						int Mid = Integer.parseInt(MID);
						final EditMeeting editmote = new EditMeeting(Mid);
						editmote.leggtilbruker(BID);
						sqlExecute create = new sqlExecute();
						create.execute("UPDATE mote_has_bruker SET attending ='" + 1 + "' WHERE mote_moteid = '" + MID + "'");
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
	public String[] getNames(){
		String str = "";
		sqlRetrieve getName = new sqlRetrieve("SELECT fornavn, etternavn FROM bruker");
		int l = getName.getQuery().length;
		for (int i = 0; i < l; i++) {
		String fornavn = getName.getQuery()[i][0];
		String etterNavn = getName.getQuery()[i][1];
		str += fornavn + " " + etterNavn + ", ";
		}
		String[] names1 = str.split(",");
//		names1[2].trim();
		
//		System.out.println(names1.length);
		return names1;
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
//		a.getNames();
		
	}
}

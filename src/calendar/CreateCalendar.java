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
import Room.CheckRoom;
import Room.Room;
import notification.ClearNotification;
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
	private int week;
	private int year;
	
	public void setBrukerid(int id){
		this.brukerid = id;
		BID = this.brukerid;
	}
	
	public void setWeek(int week, int year){
		this.week = week;
		this.year = year;
	}

	public void start(final Stage stage) throws Exception {
		final GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setHgap(50);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		
		Scene scene = new Scene(grid, 1000, 1000);
		scene.getStylesheets().clear();
		scene.getStylesheets().add("http://osakasushi.no/hi.css"); 
		stage.setScene(scene);
		stage.setTitle("Calendar");
		stage.show();
		
		// Notifications
		GetNotification getNot = new GetNotification(BID);
		String insertionstring = getNot.get();
		
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
        
        final ScrollPane scroll1 = new ScrollPane();
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
		
		setWeek(getWeek(), getYear());
		
		Label meeting = new Label(CheckCalendar.PrintWeek(BID, this.week, this.year));
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
		Button createRoom = new Button("New Room");
		Button addUser = new Button("New user");
		Button ClearNotification = new Button("Clear Notifications");
		Button nextweek = new Button("Next Week");
		Button prevweek = new Button("Prev. Week");
		
		grid.add(ClearNotification, 0,21,1,1);
		grid.add(createRoom,5,1,3,1);
		grid.add(cl, 2, 25,1,1);
		grid.add(newMeeting, 2, 1,3,1);
		grid.add(update, 0,20,1,1);
		grid.add(newGroup,3,2,3,1);
		grid.add(changeMeeting, 2,2,3,1);
		grid.add(showAttendings, 1,21,1,1);
		grid.add(showDeclines, 1,22,1,1);
		grid.add(showAccepted, 1,23,1,1);
		grid.add(showCalendar, 1,20, 1,1);
		grid.add(addUser, 3,1, 3,1);
		grid.add(nextweek, 1,1, 3,5);
		grid.add(prevweek, 1,2, 3,5);
		
		nextweek.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				addWeek();
				Label meeting = new Label(CheckCalendar.PrintWeek(BID, week, year));
//				System.out.println(meeting.getText());
				scroll1.setContent(meeting);
				meeting.setFont(Font.font("Consolas", FontWeight.NORMAL, 13));
			}
		});
		
		prevweek.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				removeWeek();
				Label meeting = new Label(CheckCalendar.PrintWeek(BID, week, year));
//				System.out.println(meeting.getText());
				scroll1.setContent(meeting);
				meeting.setFont(Font.font("Consolas", FontWeight.NORMAL, 13));
			}
		});
		
		addUser.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {

		        	addUser add = new addUser();
	                Stage stage = new Stage();
	                try {
						add.start(stage);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
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
				final Text text3 = new Text();
				
				Button SandE = new Button("Save and Exit");
				Button cl = new Button("Cancel");
				
				grid.add(text3, 2, 15);
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
							try {
								final Room rom = new Room();
								final String Kappa = Kapasitet1.getText();
								final int kapasitet = Integer.parseInt(Kappa);
								rom.createRoom(Romnavn1.getText(), kapasitet, Sted1.getText(), beskrivelse1.getText());
								stage1.close();
							}
							catch (Exception ie){
								text3.setText("FEIL INPUT!!");
								text3.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
								text3.setFill(Color.RED);
							}
						
						
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

	        	changeMeeting change = new changeMeeting();
	        	change.setBrukerid(BID);
                Stage stage = new Stage();
                try {
					change.start(stage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
								not.create(getID(names1[i]), "Du ble n� lagt til i gruppen "+ name2.getText());
							}
							stage2.close();
						}
					}
				});
			}
		});
		
		newMeeting.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {

	        	NewMeeting newm = new NewMeeting();
	        	newm.setBrukerid(BID);
                Stage stage = new Stage();
                try {
					newm.start(stage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
		
		ClearNotification.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				final ClearNotification clear = new ClearNotification(BID);
				clear.clearMyAll();
				CreateCalendar c = new CreateCalendar();
				try {
//					c.start(stage);
					
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
	public static String getName1(int gid){
		sqlRetrieve getName = new sqlRetrieve("SELECT gruppenavn FROM gruppe WHERE gruppeid ='" + gid + "';");
		String fornavn = getName.getQuery()[0][0];
		return fornavn;
		
	}
	
	public static String[] getNames(){
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
	
	public static String[] getGroups(){
		sqlRetrieve ret = new sqlRetrieve("SELECT gruppenavn FROM gruppe");
		String str ="";
		for (int i = 0; i < ret.getQuery().length; i++) {
			str += " "+ ret.getQuery()[i][0] + ",";
		}
		String[] res = str.split(",");
		return res;
	}
	
	public static int getID(String navn){
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
	public int getWeek(){
		sqlRetrieve week = new sqlRetrieve("SELECT WEEKOFYEAR(CURDATE())");
		int wee = Integer.parseInt(week.getQuery()[0][0]);
		return wee;
	}
	public int getYear(){
		sqlRetrieve week = new sqlRetrieve("SELECT YEAR(CURDATE())");
		int wee = Integer.parseInt(week.getQuery()[0][0]);
		return wee;
	}
	public void removeWeek(){
		if((week>1)&&(week<53)){
			week = week -1;
		}if(week==1){
			if((year == 2005)||(year == 2010)||(year == 2016)||(year == 2021)||(year == 2027)||(year == 2033)||(year == 2038)){
				week = 53;
				year = year -1;
			}else{
				week = 52;
				year = year-1;
			}
		}
		
	}
	public void addWeek(){
		if((year == 2004)||(year == 2009)||(year == 2015)||(year == 2020)||(year == 2026)||(year == 2032)||(year == 2037)){
			if(week<53){
				week = week + 1;
			}
			else{
				week = 1;
				year = year +1;
			}
		}else{
			if(week < 52){
				week = week +1;
			}else{
				week = 1;
				year = year +1;
			}
		}
	}
	
	public static void main(String[] args) {
		launch(CreateCalendar.class, args);
//		CreateCalendar a = new CreateCalendar();
//		a.getID(" Martin Raknes Holth");
		
	}
}
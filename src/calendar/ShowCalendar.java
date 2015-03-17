package calendar;

import Meeting.EditMeeting;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mysql.sqlExecute;
import mysql.sqlRetrieve;

public class ShowCalendar extends Application{

	private static int BID = 3;
	private int brukerid;
	
	sqlExecute sql = new sqlExecute();
	
	public void setBrukerid(int id){
		this.brukerid = id;
		BID = this.brukerid;
	}

	@Override
	public void start(final Stage stage) throws Exception {
		GridPane grid2 = new GridPane();
		grid2.setAlignment(Pos.TOP_LEFT);
		grid2.setHgap(10);
		grid2.setVgap(10);
		grid2.setPadding(new Insets(25, 25, 25, 25));
		Scene scene = new Scene(grid2, 600, 600);
		stage.setScene(scene);
		stage.setTitle("Show Calendar");
		stage.show();
		Text user = new Text("Show calendar for: ");
		Text user1 = new Text("Users:");
		Text user2 = new Text("Groups:");
		Button cl = new Button("Close");
		grid2.add(cl, 2,15);
		grid2.add(user, 0,0);
		grid2.add(user1, 0,1);
		grid2.add(user2, 1,1);
		
		GridPane grid21 = new GridPane();
		grid21.setAlignment(Pos.TOP_LEFT);
		grid21.setHgap(10);
		grid21.setVgap(10);
		grid21.setPadding(new Insets(10, 10, 10, 10));
		ScrollPane sp = new ScrollPane(grid21);
		grid2.add(sp, 0, 2);
		
		GridPane grid31 = new GridPane();
		grid31.setAlignment(Pos.TOP_LEFT);
		grid31.setHgap(10);
		grid31.setVgap(10);
		grid31.setPadding(new Insets(10, 10, 10, 10));
		ScrollPane sp1 = new ScrollPane(grid31);
		grid2.add(sp1, 1, 2);
		
		final Button[] b = new Button[length()];
		for (int i = 0; i < length(); i++) {
			Button b1 = b[i] = new Button(getNames()[i]);
			grid21.add(b1, 0, i);
		}
		for (int i = 0; i < length(); i++) {
			final int j = i;
		b[j].setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				final Stage stage2 = new Stage();
				GridPane grid = new GridPane();
				grid.setAlignment(Pos.TOP_LEFT);
				grid.setHgap(50);
				grid.setVgap(10);
				grid.setPadding(new Insets(10, 10, 10, 10));
				Scene scene1 = new Scene(grid, 700, 500);
				stage2.setScene(scene1);
				stage2.setTitle("Personal Calendar");
				stage2.show();
				BorderPane borderPane1 = new BorderPane();
				ScrollPane scroll1 = new ScrollPane();
		        scroll1.fitToWidthProperty();
		        scroll1.setMaxHeight(600);
		        scroll1.setPrefHeight(200);
		        scroll1.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		        borderPane1.setCenter(scroll1);
		        grid.add(scroll1,0,2, 10, 10);
		        
				Label name2 = new Label(CreateCalendar.getName(j+1));
				name2.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
				grid.add(name2, 0, 0);
				
				Label meeting = new Label(CheckCalendar.PrintDay(j+1));
//				System.out.println(meeting.getText());
				scroll1.setContent(meeting);
				meeting.setFont(Font.font("Consolas", FontWeight.NORMAL, 13));
				Button c = new Button("Close");
				grid.add(c, 9, 15);
				c.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
						stage2.close();
					}
				});
			}
		});
	}
		final Button[] bn = new Button[length1()];
		for (int i = 0; i < length1(); i++) {
			Button b2 = bn[i] = new Button(getGroups()[i]);
			grid31.add(b2, 0, i);
		}
		for (int i = 0; i < length1(); i++) {
			final int j = i;
		
		bn[j].setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				final Stage stage2 = new Stage();
				GridPane grid = new GridPane();
				grid.setAlignment(Pos.TOP_LEFT);
				grid.setHgap(50);
				grid.setVgap(10);
				grid.setPadding(new Insets(10, 10, 10, 10));
				Scene scene1 = new Scene(grid, 700, 500);
				stage2.setScene(scene1);
				stage2.setTitle("Group Calendar");
				stage2.show();
				BorderPane borderPane1 = new BorderPane();
				ScrollPane scroll1 = new ScrollPane();
				scroll1.fitToWidthProperty();
				scroll1.setMaxHeight(600);
				scroll1.setPrefHeight(200);
				scroll1.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
				borderPane1.setCenter(scroll1);
				grid.add(scroll1,0,2, 10, 10);
				
				Label name2 = new Label(CreateCalendar.getName1(j+1));
				name2.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
				grid.add(name2, 0, 0);
				
				Label meeting = new Label(CheckCalendar.PrintDay1(j+1));
//				System.out.println(meeting.getText());
				scroll1.setContent(meeting);
				meeting.setFont(Font.font("Consolas", FontWeight.NORMAL, 13));
				Button c = new Button("Close");
				grid.add(c, 9, 15);
				c.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
						stage2.close();
					}
				});
			}
		});
		}
		
		
		
		cl.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				stage.close();
			}
		});
	}
	
	public String[] getName(){
		String str = "";
		sqlRetrieve getName = new sqlRetrieve("SELECT fornavn FROM bruker");
		int l = getName.getQuery().length;
		for (int i = 0; i < l; i++) {
		String fornavn = getName.getQuery()[i][0];
		str +=  " " + fornavn + ",";
		}
		String[] names1 = str.split(",");
		return names1;
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
		return names1;
	}
	public String[] getGroups(){
		String str = "";
		sqlRetrieve getName = new sqlRetrieve("SELECT gruppenavn FROM gruppe");
		int l = getName.getQuery().length;
		for (int i = 0; i < l; i++) {
			String fornavn = getName.getQuery()[i][0];
			str +=  " " + fornavn + ",";
		}
		String[] names1 = str.split(",");
		return names1;
	}
	
	public int length(){
		sqlRetrieve getName = new sqlRetrieve("SELECT fornavn, etternavn FROM bruker");
		int l = getName.getQuery().length;
		return l;
	}
	public int length1(){
		sqlRetrieve getName = new sqlRetrieve("SELECT gruppeid FROM gruppe");
		int l = getName.getQuery().length;
		return l;
	}
	public static void main(String[] args) {
		ShowCalendar c = new ShowCalendar();
		c.getNames();
	}
}

package calendar;

import Meeting.EditMeeting;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
		
		GridPane grid = new GridPane();
		
		Scene scene = new Scene(grid2, 600, 600);
		stage.setScene(scene);
		stage.setTitle("Accepted meetings");
		stage.show();
		
		Text user = new Text("Show calendar for: ");
		
		
		Button cl = new Button("Close");
		Button save = new Button("Do it");
		grid2.add(cl, 2,15);
		grid2.add(save, 3,15);
		grid2.add(user, 0,3);
		grid2.add(users, 1,4);
		
		
		cl.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				stage.close();
			}
		});
	}
	
	public String getNames(){
		String str = "";
		sqlRetrieve getName = new sqlRetrieve("SELECT fornavn, etternavn FROM bruker");
		int l = getName.getQuery().length;
		for (int i = 0; i < l; i++) {
		String fornavn = getName.getQuery()[i][0];
		String etterNavn = getName.getQuery()[i][1];
		str +=  " " + fornavn + " " + etterNavn + "\n";
		}
//		System.out.println(str);
		return str;
	}
	
	public static void main(String[] args) {
		ShowCalendar c = new ShowCalendar();
		c.getNames();
	}
}

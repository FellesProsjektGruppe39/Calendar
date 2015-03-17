
package calendar;

import java.io.IOException;
import java.util.ArrayList;

import notification.CreateNotification;
import mysql.sqlExecute;
import mysql.sqlRetrieve;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Application;

public class showAccepted extends Application {
	
	private static int BID = 3;
	private int brukerid;
	
	sqlExecute sql = new sqlExecute();
	
	public void setBrukerid(int id){
		this.brukerid = id;
		BID = this.brukerid;
	}
	
	public void start(final Stage stage) throws IOException {
		
		GridPane grid2 = new GridPane();
		grid2.setAlignment(Pos.CENTER);
		grid2.setHgap(10);
		grid2.setVgap(10);
		grid2.setPadding(new Insets(25, 25, 25, 25));
		
		GridPane grid = new GridPane();
		grid.setMaxWidth(1000);
		grid.setMaxHeight(500);
		
		ScrollPane sp = new ScrollPane(grid);
		grid2.add(sp, 0, 1);
		
		Scene scene = new Scene(grid2, 1200, 600);
		stage.setScene(scene);
		stage.setTitle("Accepted meetings");
		stage.show();
		
		Button cl = new Button("Close");
		grid2.add(cl, 0,10);
		
		Button save = new Button("Save");
		grid2.add(save, 0,11);
		
		Text t = new Text();
		t.setText("0 - Not replied yet \n1 - Attending \n2 - Not attending");
		grid2.add(t,0,0);
		
		final sqlRetrieve info = new sqlRetrieve("(SELECT * FROM(SELECT moteid,dato, starttidspunkt,sluttidspunkt,null as romnavn, beskrivelse, sted, attending, opprettet_av "
		+ "FROM mote m1, mote_has_bruker mb1 "
		+ "WHERE moteid "
		+ "NOT IN "
		+ "(SELECT m2.moteid "
		+ "FROM mote m2, mote_has_bruker mb2,mote_has_rom mr2  "
		+ "WHERE mb2.bruker_brukerid= " + BID
		+ " AND mr2.mote_moteid = m2.moteid "
		+ "AND mb2.mote_moteid = m2.moteid) "
		+ "AND m1.moteid = mb1.mote_moteid "
		+ "AND mb1.bruker_brukerid = " + BID
		+ " AND mb1.attending = 1) "
		+ "AS temp1) "
		+ "UNION "
		+ "(SELECT * FROM(SELECT m3.moteid, m3.dato, m3.starttidspunkt, m3.sluttidspunkt, mr3.rom_romnavn,  m3.beskrivelse, m3.sted, mb3.attending, m3.opprettet_av "
		+ "FROM mote m3, mote_has_bruker mb3,mote_has_rom mr3  "
		+ "WHERE mb3.bruker_brukerid= " + BID
		+ " AND mr3.mote_moteid = m3.moteid "
		+ "AND mb3.mote_moteid = m3.moteid  "
		+ "AND mb3.attending = 1 "
		+ "ORDER BY dato, starttidspunkt ASC) "
		+ "AS temp2) "
		+ "ORDER BY dato, starttidspunkt ASC");
		
		final ArrayList<Integer> moteid = new ArrayList<Integer>();
		final ArrayList<Label> print = new ArrayList<Label>();
		
		String str;
		str = String.format("%-5s    %-10s   %-8s   %-8s   %-30s   %-20s","MoteId", "Date", "Start","End","Description","Room");
		
		print.add(new Label(str));
		print.get(0).setFont(Font.font("Consolas", FontWeight.NORMAL, 13));
		grid.add(print.get(0), 0, 0, 1, 1);
		
		
		final ArrayList<ChoiceBox> cb = new ArrayList<ChoiceBox>();	
		
		for( int i=0; i < info.getQuery().length; i++){
			
			cb.add(new ChoiceBox(FXCollections.observableArrayList(0, 1, 2)));
			cb.get(i).setValue(1);
			moteid.add(Integer.parseInt(info.getQuery()[i][0]));
			String a = String.format("%-5s %-10s - %-8s   %-8s   %-30s   %-20s",info.getQuery()[i][0], info.getQuery()[i][1], info.getQuery()[i][2], info.getQuery()[i][3],info.getQuery()[i][5], info.getQuery()[i][4]);					
			print.add(new Label(a));
			print.get(i+1).setFont(Font.font("Consolas", FontWeight.NORMAL, 13));
			grid.add(print.get(i+1), 0, i+1, 1, 1);
			grid.add(cb.get(i), 1, i+1, 1, 1);
		}
		

		cl.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent e) {
		    stage.close();
		    }
		});
		
		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				
				CreateNotification Notif = new CreateNotification();
				
				for (int i = 0; i < cb.size(); i++) {
					
					sql.execute("UPDATE mote_has_bruker SET attending = " + cb.get(i).getValue() +
							" WHERE mote_moteid = " + moteid.get(i) + " AND bruker_brukerid = " + BID);
					
					int a = (int) cb.get(i).getValue();
					
					if (Integer.parseInt(info.getQuery()[i][7]) != a) {
						if (a == 0) {
							Notif.create(Integer.parseInt(info.getQuery()[i][8]), CreateCalendar.getName(BID) + " har endret til ikke svart paa mote id " + moteid.get(i));
						}
						if (a == 1) {
							Notif.create(Integer.parseInt(info.getQuery()[i][8]), CreateCalendar.getName(BID) + " har endret til attending mote id " + moteid.get(i));
						}
						if (a == 2) {
							Notif.create(Integer.parseInt(info.getQuery()[i][8]), CreateCalendar.getName(BID) + " har endret til not attending mote id " + moteid.get(i));
						}
						
					}
					
				}
				stage.close();
			}
		});
		
	}
	
	
	
	public static void main(String[] args) {
		launch(showAccepted.class, args);
	}
}
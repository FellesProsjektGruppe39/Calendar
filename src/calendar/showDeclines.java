package calendar;

import java.io.IOException;
import java.util.ArrayList;

import mysql.sqlExecute;
import mysql.sqlRetrieve;

import com.sun.prism.paint.Color;

import sun.launcher.resources.launcher;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Application;

public class showDeclines extends Application {
	
	private static int BID = 3;
	private int brukerid;
	
	sqlExecute sql = new sqlExecute();
	
	public void setBrukerid(int id){
		this.brukerid = id;
		BID = this.brukerid;
	}
	
	public void start(final Stage stage) throws IOException {
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		Scene scene = new Scene(grid, 1100, 600);
		stage.setScene(scene);
		stage.setTitle("Declined meetings");
		stage.show();
		
		Button cl = new Button("Close");
		grid.add(cl, 0,10);
		
		Button save = new Button("Save");
		grid.add(save, 0,11);
		
		sqlRetrieve info = new sqlRetrieve("(SELECT * FROM(SELECT moteid,dato, starttidspunkt,sluttidspunkt,null as romnavn, beskrivelse, sted, attending "
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
		+ " AND mb1.attending = 2) "
		+ "AS temp1) "
		+ "UNION "
		+ "(SELECT * FROM(SELECT m3.moteid, m3.dato, m3.starttidspunkt, m3.sluttidspunkt, mr3.rom_romnavn,  m3.beskrivelse, m3.sted, mb3.attending "
		+ "FROM mote m3, mote_has_bruker mb3,mote_has_rom mr3  "
		+ "WHERE mb3.bruker_brukerid= " + BID
		+ " AND mr3.mote_moteid = m3.moteid "
		+ "AND mb3.mote_moteid = m3.moteid  "
		+ "AND mb3.attending = 2 "
		+ "ORDER BY dato, starttidspunkt ASC) "
		+ "AS temp2) "
		+ "ORDER BY dato, starttidspunkt ASC");
		
		final ArrayList<Integer> moteid = new ArrayList<Integer>();
		final ArrayList<Label> print = new ArrayList<Label>();
		
		String str;
		str = String.format("%-5s    %-10s   %-8s   %-8s   %-30s   %-20s   %-30s %-4s","MoteId", "Date", "Start","End","Description","Room","Location","Attending");
		
		print.add(new Label(str));
		print.get(0).setFont(Font.font("Consolas"));
		grid.add(print.get(0), 0, 0, 1, 1);
		
		
		final ArrayList<ChoiceBox> cb = new ArrayList<ChoiceBox>();	
		
		for( int i=0; i < info.getQuery().length; i++){
			
			cb.add(new ChoiceBox(FXCollections.observableArrayList(0, 1, 2)));
			cb.get(i).setValue(2);
			moteid.add(Integer.parseInt(info.getQuery()[i][0]));
			String a = String.format("%-5s %-10s - %-8s   %-8s   %-30s   %-20s - %-30s %-1s",info.getQuery()[i][0], info.getQuery()[i][1], info.getQuery()[i][2], info.getQuery()[i][3],info.getQuery()[i][5], info.getQuery()[i][4], info.getQuery()[i][6], info.getQuery()[i][7]);					
			print.add(new Label(a));
			print.get(i+1).setFont(Font.font("Consolas"));
			grid.add(print.get(i+1), 0, i+1, 1, 1);
			grid.add(cb.get(i), 1, i+1, 1, 1);
		}
		
<<<<<<< Updated upstream
		Label meeting = new Label(str);
		meeting.setFont(Font.font("Consolas", FontWeight.NORMAL, 13));
		grid.add(meeting, 0, 0, 1, 10);
=======
>>>>>>> Stashed changes
		
		cl.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent e) {
		    stage.close();
		    }
		});
		
		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				
				for (int i = 0; i < cb.size(); i++) {
					
					sql.execute("UPDATE mote_has_bruker SET attending = " + cb.get(i).getValue() +
							" WHERE mote_moteid = " + moteid.get(i) + " AND bruker_brukerid = " + BID);
				}
				stage.close();
			}
		});
		
	}
	
	
	
	public static void main(String[] args) {
		launch(showDeclines.class, args);
	}
}

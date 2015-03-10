package calendar;

import java.io.IOException;

import mysql.sqlRetrieve;

import com.sun.prism.paint.Color;

import sun.launcher.resources.launcher;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Application;

public class showAttendings extends Application {
	
	private static int BID = 3;
	private int brukerid;
	
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
		
		Scene scene = new Scene(grid, 700, 400);
		stage.setScene(scene);
		stage.setTitle("Ikke-besvarte møteinnkallelser");
		stage.show();
		
		Button cl = new Button("Close");
		grid.add(cl, 0,0);
		
		sqlRetrieve sql = new sqlRetrieve("(SELECT * FROM(SELECT moteid,dato, starttidspunkt,sluttidspunkt,null as romnavn, beskrivelse, sted, attending "
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
		+ " AND mb1.attending = 0) "
		+ "AS temp1) "
		+ "UNION "
		+ "(SELECT * FROM(SELECT m3.moteid, m3.dato, m3.starttidspunkt, m3.sluttidspunkt, mr3.rom_romnavn,  m3.beskrivelse, m3.sted, mb3.attending "
		+ "FROM mote m3, mote_has_bruker mb3,mote_has_rom mr3  "
		+ "WHERE mb3.bruker_brukerid= " + BID
		+ " AND mr3.mote_moteid = m3.moteid "
		+ "AND mb3.mote_moteid = m3.moteid  "
		+ "AND mb3.attending = 0 "
		+ "ORDER BY dato, starttidspunkt ASC) "
		+ "AS temp2) "
		+ "ORDER BY dato, starttidspunkt ASC");
				
		
		
		cl.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent e) {
		    stage.close();
		    }
		});
		
	}
	
	
	
	public static void main(String[] args) {
		launch(showAttendings.class, args);
	}
}

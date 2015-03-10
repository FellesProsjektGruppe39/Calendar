package calendar;

import java.io.IOException;

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
	
	public void start(final Stage stage) throws IOException {
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		Scene scene = new Scene(grid, 400, 350);
		stage.setScene(scene);
		stage.setTitle("Ikke-besvarte møteinnkallelser");
		stage.show();
		
		Button cl = new Button("Close");
		grid.add(cl, 0,0);

		
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

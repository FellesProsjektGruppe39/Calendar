package calendar;

import java.io.IOException;
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


public class Test extends Application{

	
	private String password,username;
	public void start(final Stage stage) throws IOException {
		
		
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		Scene scene = new Scene(grid, 400, 350);
		stage.setScene(scene);
		stage.setTitle("Calendar");
		stage.show();
		
		Text scenetitle = new Text("Welcome");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);
		Label userName = new Label("User Name:");
		grid.add(userName, 0, 1);
		final TextField name = new TextField();
		grid.add(name, 1, 1);
		Label pw = new Label("Password:");
		grid.add(pw, 0, 2);
		final PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);
		
		Button sb = new Button("Submit");
		Button cl = new Button("Close");
		grid.add(sb, 1,3);
		grid.add(cl, 2,3);
	
		
		cl.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent e) {
		    stage.close();
		    }
		});
		
		sb.setOnAction(new EventHandler<ActionEvent>() {
	    @Override public void handle(ActionEvent e) {
	        username = name.getText();
	        password = pwBox.getText();
	        System.out.println(username + "-----" + password);
	    }
	});
	}
	public static void main(String[] args) {
		launch(Test.class,args);
	}
	
}
	


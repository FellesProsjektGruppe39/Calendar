package calendar;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.event.ChangeListener;

import notification.CreateNotification;
import mysql.sqlExecute;
import mysql.sqlRetrieve;
import User.User;

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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.application.Application;

public class addUser extends Application {
	
	private int brukerid;
	
	sqlExecute sql = new sqlExecute();
	
	public void start(final Stage stage) throws IOException {
		
		final GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		Scene scene = new Scene(grid, 500, 500);
		stage.setScene(scene);
		stage.setTitle("Add user");
		stage.show();
		
		Button cl = new Button("Cancel");
		grid.add(cl, 0,11);
		
		Button save = new Button("Create user");
		grid.add(save, 0,10);
		
		Label fname = new Label("First name:");
		grid.add(fname, 0,0);
		final TextField fnameIn = new TextField();
		grid.add(fnameIn,1,0);
		
		Label lname = new Label("Last name:");
		grid.add(lname, 0,1);
		final TextField lnameIn = new TextField();
		grid.add(lnameIn,1,1);
		
		Label username = new Label("Username:");
		grid.add(username, 0,2);
		final TextField usernameIn = new TextField();
		grid.add(usernameIn,1,2);
		
		Label pw = new Label("Password:");
		grid.add(pw, 0, 3);
		final PasswordField pwIn = new PasswordField();
		grid.add(pwIn, 1, 3);
		
		Label number = new Label("Phone number:");
		grid.add(number, 0,4);
		final TextField numberIn = new TextField();
		grid.add(numberIn,1,4);
		
		Label position = new Label("Position:");
		grid.add(position, 0,5);
		final TextField positionIn = new TextField();
		grid.add(positionIn,1,5);
		
		final CheckBox cb = new CheckBox("Admin?");
		grid.add(cb,0,6);

		cl.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent e) {
		    stage.close();
		    }
		});
	
		
		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				
				int a = 0;
				
				if (cb.isSelected()) {
					a = 1;
				}
				
				if (fnameIn.getText().length() > 2 && lnameIn.getText().length() > 2 && usernameIn.getText().length() > 0 && pwIn.getText().length() > 0 && positionIn.getText().length() > 0 && numberIn.getText().length() > 7) {
					int b = 1;
					try {
						Integer.parseInt(numberIn.getText());
					}
					catch (Exception e1) {
						b = 0;
						grid.add(new Label("Phone number needs to be numeric!"),0,13);
						
					}
					if (b == 1) {
						User user = new User(fnameIn.getText(), lnameIn.getText(), numberIn.getText(), usernameIn.getText(), pwIn.getText(), positionIn.getText(), a);
						user.CreateUser();
						stage.close();
					}
					
				}
				else {
					grid.add(new Label("Remember to fill in all the fields correctly!"),0,14);
				}
			
				
			}
		});
		
	}
	
	
	
	public static void main(String[] args) {
		launch(addUser.class, args);
	}
}

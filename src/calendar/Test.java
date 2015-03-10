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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Application;
import logIn.LogIn;


public class Test extends Application{
	
	private static String password;
	private static String username;
	private static int brukerid;
	
	public String getPassword(){
		return password;
	}
	
	public String getUsername(){
		return username;
	}
	
	public int getBrukerid(){
		return brukerid;
	}
	
	
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
		final Label label = new Label();
		GridPane.setConstraints(label, 1, 4);
		GridPane.setColumnSpan(label, 2);
		grid.getChildren().add(label);

//		//Setting an action for the Submit button
//		submit.setOnAction(new EventHandler<ActionEvent>() {
//
//		@Override
//		    public void handle(ActionEvent e) {
//		        if ((comment.getText() != null && !comment.getText().isEmpty())) {
//		            label.setText(name.getText() + " " + lastName.getText() + ", "
//		                + "thank you for your comment!");
//		        } else {
//		            label.setText("You have not left a comment.");
//		        }
//		     }
//		 });
		
		cl.setOnAction(new EventHandler<ActionEvent>() {
		@Override public void handle(ActionEvent e) {
		    stage.close();
		    }
		});
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent t) {
	            KeyCode key = t.getCode();
	            if (key == KeyCode.ESCAPE){
	                stage.close();
	            }
	        }
	    });
		
		pwBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent t) {
				KeyCode key = t.getCode();
				if (key == KeyCode.ENTER){
					LogIn l = new  LogIn();
			        username = name.getText();
			        password = pwBox.getText();
			        if(l.LogIn(username,password) > 0){
			        	stage.close();
			        	brukerid = l.LogIn(username,password);
			        	System.out.println(brukerid);
			        	CreateCalendar cal = new CreateCalendar();
			        	cal.setBrukerid(brukerid);
		                Stage stage = new Stage();
		                try {
							cal.start(stage);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		                
			        }else 
			        	System.out.println("Du feilet!");
			        	label.setText("Feil brukernavn eller passord!");
			    }
				
			}
		});
		
		sb.setOnAction(new EventHandler<ActionEvent>() {
	    @Override public void handle(ActionEvent e) {
	    	LogIn l = new  LogIn();
	        username = name.getText();
	        password = pwBox.getText();
	        if(l.LogIn(username,password) > 0){
	        	stage.close();
	        	brukerid = l.LogIn(username,password);
	        	System.out.println(brukerid);
	        	CreateCalendar cal = new CreateCalendar();
	        	cal.setBrukerid(brukerid);
                Stage stage = new Stage();
                try {
					cal.start(stage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
	        }else 
	        	System.out.println("Du feilet!");
	        	label.setText("Feil brukernavn eller passord!");
	    }
	});
	}
	public static void main(String[] args) {
		launch(Test.class,args);
//		System.out.println(username);
//		System.out.println(password);
	}
	
}
	


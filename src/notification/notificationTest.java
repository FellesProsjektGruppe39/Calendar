package notification;

import java.awt.TextArea;

import com.sun.scenario.effect.impl.prism.PrImage;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
 // w w  w  . jav a 2  s  . co m

public class notificationTest extends Application {
	
	private String insertiontext;
    
    public void setNotification(int userid){
    	GetNotification getNot = new GetNotification(userid);
    	 this.insertiontext = getNot.get();
    	 this.insertiontext += getNot.get();
    	 this.insertiontext += getNot.get();
    	 this.insertiontext += getNot.get();
    	 this.insertiontext += getNot.get();
    	 this.insertiontext += getNot.get();
    }
    
    @Override
    public void start(Stage primaryStage) {
    	
    	
    	int brukerid = 0;
		this.setNotification(5);
        primaryStage.setTitle("Test");
        BorderPane borderPane = new BorderPane();
        VBox myView = new VBox();
  
		Text text = new Text(insertiontext);
		text.setWrappingWidth(500);
        myView.getChildren().addAll(text);

        ScrollPane scroll = new ScrollPane();
        scroll.setContent(myView);
        scroll.fitToWidthProperty();
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        borderPane.setCenter(scroll);

        Scene scene = new Scene(borderPane, 500,100);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
    public static void main(String[] args) {
    	
    	
        Application.launch(args);
    }
}
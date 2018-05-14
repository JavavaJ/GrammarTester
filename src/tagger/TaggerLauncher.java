package tagger;

import java.io.File;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TaggerLauncher extends Application {
    Stage stage;
    File selectedFile;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        
        Button launchButton = new Button("Launch Tagger");
        launchButton.setOnAction(e -> click_launchButton());
        
        Label label = new Label("Choose db file!");
        
        VBox buttonContainer = new VBox(label, launchButton);
        buttonContainer.setAlignment(Pos.CENTER);
        VBox.setMargin(launchButton, new Insets(10));
        
        HBox hpane = new HBox(buttonContainer);
        hpane.setAlignment(Pos.CENTER);
        
        
        Scene scene = new Scene(hpane, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    
    public void click_launchButton() {
        System.out.println("Launch Button is pressed!");
    }
}
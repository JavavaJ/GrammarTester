package logic.tagger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.testmaker.FileChooserOpenDB;


public class TaggerLauncher extends Application {
    Stage stage;
    String selectedFile;
    
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
        Platform.runLater(new Runnable() {
            public void run() {
                FileChooserOpenDB filechooser = new FileChooserOpenDB();
                filechooser.start(new Stage());

                System.out.println("You have selected (absolute path) : "
                        + filechooser.getSelectedFile().getAbsolutePath());
                selectedFile = filechooser.getSelectedFile().getAbsolutePath();

                // the following snippet is a way to call another Applicaton from the 
                // current one. 
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {                         
                        new TaggerGUI(selectedFile).start(new Stage());
                    }
                });

                stage.close();
            }
        });
    }
}

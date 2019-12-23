package logic.testmaker;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class NewOrOldFileMessageBox  {
    static Button buttonNewFile;
    static Button buttonOldFile;
    static Stage stage;   
    static String selectedFile;
    
    public static void show() {
        String message = "Do you want to save file in existing DataBase or Create a new file?";
        String title = "Old or New File";
        
                            
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);  
        
        stage.setTitle(title);
        stage.setMinWidth(250);
        
        Label label = new Label();
        label.setText(message);        
        
        
        buttonNewFile = new Button();
        buttonNewFile.setText("New File");
        buttonNewFile.setOnAction(e -> click_buttonNewFile());
        
        buttonOldFile = new Button("Existing File");
        buttonOldFile.setOnAction( e -> click_buttonOldFile());
        
        HBox buttonPane = new HBox(buttonNewFile, buttonOldFile);
        buttonPane.setPadding(new Insets(5));
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setSpacing(10);
        
        VBox pane = new VBox(20);
        pane.getChildren().addAll(label, buttonPane);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(5));
        
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.showAndWait();
    }
    
    public static void click_buttonNewFile() {
        Platform.runLater(new Runnable() {
            public void run() {
                FileChooserSaveDB filechooser = new FileChooserSaveDB();
                filechooser.start(new Stage());
                
                System.out.println("You have selected (absolute path) : " + filechooser.getSelectedFile().getAbsolutePath());
                selectedFile = filechooser.getSelectedFile().getAbsolutePath();
                stage.close();
            }
        });                
        
    }
    
    public static void click_buttonOldFile() {
        Platform.runLater(new Runnable() {
            public void run() {
                FileChooserOpenDB filechooser = new FileChooserOpenDB();
                filechooser.start(new Stage());
                
                System.out.println("You have selected (absolute path) : " 
                        + filechooser.getSelectedFile().getAbsolutePath());
                selectedFile = filechooser.getSelectedFile().getAbsolutePath();
                stage.close();
            }
        });        
       
    }
    
        
    public static String getFilePath() {
        return selectedFile;
    }
    
}
package tagger;

import grammartester.SQLReader;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import read.Question;

public class TableAsSceneTest extends Application {
    
    TableAsScene tableScene;
    Button buttonSwitch;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    Stage stage;
    List<Question>allQuestions;
    
    public void start(Stage primaryStage) {
        stage = primaryStage;
        
        buttonSwitch = new Button("Go");
        buttonSwitch.setOnAction(e -> click_buttonSwitch());
        
        VBox pane = new VBox(buttonSwitch);
        pane.setPadding(new Insets(5));
        
        Scene scene1 = new Scene(pane, 200, 100);
        
        
        readDatabase();
        tableScene = new TableAsScene(allQuestions, "Test 7");
        
        primaryStage.setScene(scene1);
        primaryStage.show();
        
    }
    
    public void click_buttonSwitch() {
        stage.setScene(tableScene);
        stage.setX(25);
        stage.setY(25);
    }
    
    public void readDatabase() {
        SQLReader sQLReader = new SQLReader();

        // path of DB with tests
        String filePath = "jdbc:sqlite:C:/sqlite/TEST7.db";
        String tableName = "test7";

        allQuestions = sQLReader.makeQuery(1, sQLReader.getNumberOfRowsInTable(filePath, tableName));
        System.out.println("All questions are read!");
    }

}
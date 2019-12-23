/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.mcq_compiler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.question.Question;
import logic.question.topics.MixType;
import logic.question.topics.A1_LEVEL;
import logic.question.topics.A2_LEVEL;
import logic.question.topics.TagType;
import logic.testmaker.GrammarGUI;

/**
 *
 * @author ALEXXX
 */
public class MCQCompilerGUI extends Application {

    Stage mainStage;    
    List<TagType> allTagTypesList;
    ChoiceBox<TagType> allTagsChoice;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        initAllTagTypes();
        
        primaryStage.setMaximized(true);
        mainStage = primaryStage;
                
        String headerString = "Select a category";
        Text headerText = new Text(headerString);
        headerText.setFont(new Font(22));
        
        HBox textPane = new HBox(headerText);
        textPane.setAlignment(Pos.CENTER);
        
        allTagsChoice = new ChoiceBox<>();
        allTagsChoice.setStyle("-fx-font: 14px \"Segoe UI\";");
        allTagsChoice.getItems().addAll(allTagTypesList);
        allTagsChoice.setPrefSize(150, 40);
        
        HBox choicePane = new HBox(allTagsChoice);
        choicePane.setAlignment(Pos.CENTER);
        
        Button goButton = new Button("Go To Test");
        goButton.setFont(new Font(20));
        goButton.setTooltip(new Tooltip("Compile test and GO"));
        goButton.setOnAction(e -> click_goButton());
        
        HBox buttonPane = new HBox(goButton);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setPadding(new Insets(15));
        
        BorderPane mainPane = new BorderPane();
        mainPane.setTop(textPane);
        mainPane.setCenter(choicePane);
        mainPane.setBottom(buttonPane);
        mainPane.setPadding(new Insets(5));
        
        Scene mainScene = new Scene(mainPane);
        
        
        primaryStage.setScene(mainScene);
        primaryStage.setFullScreen(true);
        
        primaryStage.setTitle("MCQ Compiler");
        primaryStage.show();
        
    }
    
    public void click_goButton() {               
        System.out.println("Go to Test Button has been pressed!");
        
        if (allTagsChoice.getValue() == null) {
            // code which calls alert message to choose value from menu
        } else {
            
            Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        // TODO customize JavaFXGrammarGUI class to make it reusable and
                        // capable of working accepting a reference to List<Question>
                        TagType tagSelectedValue = allTagsChoice.getValue();
                        List<Question> selectedQList = MCQCompilationFactory.getSpecifiedQList(tagSelectedValue);
                        new GrammarGUI(selectedQList).start(mainStage);
                    } 
                 });     
            
        }
    }
    
    public void initAllTagTypes() {
        // todo change the concept tagtype to topic
        allTagTypesList = new ArrayList<>();

        // todo create TopicGather proper functionality
        // todo replace allTagTypesList ChoiceBox<TagType> generic to ChoiceBox<String>
        // todo with streams implement the new logic

        // todo change the logic of initialization to values read from java.database
        A1_LEVEL[] a1LevelsArray = A1_LEVEL.values();
        List<A1_LEVEL> a1LevelList = Arrays.asList(a1LevelsArray);
        allTagTypesList.addAll(a1LevelList);




        allTagTypesList.addAll(Arrays.asList(A2_LEVEL.values()));
        
        //TODO add all other levels
        allTagTypesList.addAll(Arrays.asList(MixType.values()));
    }
    
}
/** The class is a JavaFX GUI implementation of Grammar Tester.
 * 
 */

package grammartester;

import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import read.Question;

public class JavaFXGrammarGUI extends Application {
    RadioButton radioA, radioB, radioC, radioD;
    RadioButton [] radioButtons;
    List<Question> allQuestions;
    int currentQNum = 1; // non-zero based
    String[] chosenAnswers;
    
    Text questionText;
    String initialQuestionText;
    String initialOptionA;
    String initialOptionB;
    String initialOptionC;
    String initialOptionD;
    
    
    public static void main(String[] args) {        
        launch(args);
        
    }
    
    
    
    public void start(Stage primaryStage) {
        readDatabase();
        setInitialTextValues();
        
        questionText = new Text(); 
        questionText.setFont(Font.font(null, FontWeight.BOLD, 20));        
        
        questionText.setText(initialQuestionText);
        
        
        HBox qTextPane = new HBox(questionText);
        qTextPane.setAlignment(Pos.CENTER);
        qTextPane.setPadding(new Insets(5));
        qTextPane.setPrefHeight(200);        
        
        radioA = new RadioButton();
        radioB = new RadioButton();
        radioC = new RadioButton();
        radioD = new RadioButton();
        
        radioA.setText(initialOptionA);
        radioB.setText(initialOptionB);
        radioC.setText(initialOptionC);
        radioD.setText(initialOptionD);
        
        radioButtons = new RadioButton[4];
        
        radioButtons[0] = radioA;
        radioButtons[1] = radioB;
        radioButtons[2] = radioC;
        radioButtons[3] = radioD;
        
        ToggleGroup tGroup = new ToggleGroup();
        
        for (RadioButton r : radioButtons) {
            r.setToggleGroup(tGroup);
            r.setFont(new Font(15));
        }

        
        
        VBox radioPane = new VBox(radioA, radioB, radioC, radioD);
        radioPane.setAlignment(Pos.BASELINE_LEFT);
        radioPane.setSpacing(5);
        radioPane.setPadding(new Insets(5));
       
        
        Button prevButton = new Button("Previous");
        prevButton.setFont(new Font(20));
        prevButton.setTooltip(new Tooltip("Return to previous answer"));
        prevButton.setOnAction(e -> click_prevButton());
        
        Button submitButton = new Button("Submit");        
        submitButton.setFont(new Font(20));
        submitButton.setTooltip(new Tooltip("Submits the Answer"));
        submitButton.setOnAction(e -> click_submitButton());
        
        HBox buttonPane = new HBox(prevButton, submitButton);
        buttonPane.setPadding(new Insets(5));
        buttonPane.setPrefHeight(150);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setSpacing(5);
        
        
        BorderPane mainPane = new BorderPane();
        mainPane.setTop(qTextPane);
        mainPane.setCenter(radioPane);
        mainPane.setBottom(buttonPane);
        
        Scene scene = new Scene(mainPane, 600, 500);
        
        
        String iconPath = "icon_test.jpg";
        
        // setting icon for a window
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(iconPath)));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Grammar Tester");
        primaryStage.show();
        
    }
    
    /** The method reads a database of tests and stores results in 
     *  a list of Question objects.
     * 
     */
    public void readDatabase() {
        SQLReader sQLReader = new SQLReader();
        
        // path of DB with tests
        String filePath = "jdbc:sqlite:C:/sqlite/TEST7.db";
        String tableName = "test7";
        
        allQuestions = sQLReader.makeQuery(1, sQLReader.getNumberOfRowsInTable(filePath, tableName));  
        System.out.println("All questions are read!");        
    }
    
    public void setGUITexts() {
        Question currentQ = allQuestions.get(currentQNum - 1);
        
        String qText = currentQNum + ". " + currentQ.getQuestionPart();
        
        String optionA = currentQ.getOptionA();
        String optionB = currentQ.getOptionB();
        String optionC = currentQ.getOptionC();
        String optionD = currentQ.getOptionD();
        
        questionText.setText(qText);
        radioA.setText("A) " + optionA);
        radioB.setText("B) " + optionB);
        radioC.setText("C) " + optionC);
        radioD.setText("D) " + optionD);
        
    }
    
    public void setInitialTextValues() {
        System.out.println("Starting initializing text values...");
        
        Question currentQ = allQuestions.get(0);
        
        initialQuestionText = "1. " + currentQ.getQuestionPart();
        initialOptionA = "A) " + currentQ.getOptionA();
        initialOptionB = "B) " + currentQ.getOptionB();
        initialOptionC = "C) " + currentQ.getOptionC();
        initialOptionD = "D) " + currentQ.getOptionD();        
        
    }
    
    public void click_submitButton() {
        currentQNum++;
        setGUITexts();
        
        // TODO write code which reads radio button values and 
        // puts them in chosenAnswers array
    }
    
    public void click_prevButton() {
        if (currentQNum == 1) {
            // do nothing
        } else {
            currentQNum--;
            setGUITexts();
        }
        
        // TODO write code which sets as selected radio buttons according to 
        // previously chosen answers
    }
    
    
    
}
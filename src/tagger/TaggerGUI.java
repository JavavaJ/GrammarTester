/** The class is a JavaFX implementation of a Grammar SQL tagger.
 *
 */

package tagger;

import grammartester.SQLReader;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import read.Question;

public class TaggerGUI extends Application {
    Stage mainStage;
    Scene updateScene;

    List<Question> allQuestions;
    private int currentQNum = 1; // non-zero based
    int totalOfQs; // total number of questions in a test
    Button finishButton;

    Text questionText;
    Text optionAText;
    Text optionBText;
    Text optionCText;
    Text optionDText;
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

        mainStage = primaryStage;

        questionText = new Text();
        questionText.setFont(Font.font(null, FontWeight.BOLD, 20));
        questionText.setWrappingWidth(580);
        questionText.setText(initialQuestionText);

        HBox qTextPane = new HBox(questionText);
        qTextPane.setPadding(new Insets(5));
        qTextPane.setPrefHeight(200);
        qTextPane.setAlignment(Pos.CENTER);

        optionAText = new Text(initialOptionA);
        optionBText = new Text(initialOptionB);
        optionCText = new Text(initialOptionC);
        optionDText = new Text(initialOptionD);

        optionAText.setFont(new Font(15));
        optionBText.setFont(new Font(15));
        optionCText.setFont(new Font(15));
        optionDText.setFont(new Font(15));
        
        
        
        VBox optionsPane = new VBox(optionAText, optionBText, optionCText, optionDText);
        optionsPane.setAlignment(Pos.BASELINE_LEFT);
        optionsPane.setSpacing(5);
        optionsPane.setPadding(new Insets(5));

        Button prevButton = new Button("Previous");
        prevButton.setFont(new Font(20));
        prevButton.setTooltip(new Tooltip("Return to previous question"));
        prevButton.setOnAction(e -> click_prevButton());

        Button nextButton = new Button("Next");
        nextButton.setFont(new Font(20));
        nextButton.setTooltip(new Tooltip("Go to next question"));
        nextButton.setOnAction(e -> click_nextButton());

        finishButton = new Button("Finish");
        finishButton.setTooltip(new Tooltip("Finish and Evaluate"));
        finishButton.setFont(new Font(20));
        finishButton.setVisible(false);
        finishButton.setOnAction(e -> click_finishButton());
        
        HBox buttonPane = new HBox(prevButton, nextButton, finishButton);
        buttonPane.setPadding(new Insets(5));
        buttonPane.setPrefHeight(150);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setSpacing(5);
        
        BorderPane mainPane = new BorderPane();
        mainPane.setTop(qTextPane);
        mainPane.setCenter(optionsPane);
        mainPane.setBottom(buttonPane);
        
        Scene taggingScene = new Scene(mainPane, 600, 500);
        
        String iconPath = "tag_icon.png";
        
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(iconPath)));
        primaryStage.setScene(taggingScene);
        primaryStage.setTitle("Grammar Tagger");
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
    
    
    
    /** The method reads values from Question object and sets initial values of 
     * GUI to build it from.
     * 
     */
    public void setInitialTextValues() {
        System.out.println("Starting initializing text values...");

        Question currentQ = allQuestions.get(0);

        initialQuestionText = "1. " + currentQ.getQuestionPart();

        // let's get rid of newline breaks
        initialQuestionText = initialQuestionText.replace("\n", "");


        initialOptionA = "A) " + currentQ.getOptionA();
        initialOptionB = "B) " + currentQ.getOptionB();
        initialOptionC = "C) " + currentQ.getOptionC();
        initialOptionD = "D) " + currentQ.getOptionD();

    }

    public void click_prevButton() {

    }

    public void click_nextButton() {

    }

    public void click_finishButton() {

    }

}
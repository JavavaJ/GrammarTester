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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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
    Button updateTagsButton;
    ChoiceBox<TagType> tagChoice;
    String[] tagsArray; // array holding tags

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
        initTagsArray();
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

        optionAText.setFont(new Font(17));
        optionBText.setFont(new Font(17));
        optionCText.setFont(new Font(17));
        optionDText.setFont(new Font(17));



        VBox optionsPane = new VBox(optionAText, optionBText, optionCText, optionDText);
        optionsPane.setAlignment(Pos.BASELINE_LEFT);
        optionsPane.setSpacing(5);
        optionsPane.setPadding(new Insets(5));

        Button prevButton = new Button("Previous");
        prevButton.setFont(new Font(20));
        prevButton.setTooltip(new Tooltip("Return to previous question"));
        prevButton.setOnAction(e -> click_prevButton());

        Button nextButton = new Button("   Next   ");
        nextButton.setFont(new Font(20));
        nextButton.setTooltip(new Tooltip("Go to next question"));
        nextButton.setOnAction(e -> click_nextButton());

        updateTagsButton = new Button("Update Tags");
        updateTagsButton.setTooltip(new Tooltip("Finish and Update Tags"));
        updateTagsButton.setFont(new Font(20));
        updateTagsButton.setVisible(false);
        updateTagsButton.setOnAction(e -> click_updateTagsButton());
        
        // spacer is added to make a group of buttons appear to be in the middle
        Region spacer = new Region();
        spacer.setPrefWidth(80);

        HBox buttonPane = new HBox(spacer, prevButton, nextButton, updateTagsButton);
        buttonPane.setPadding(new Insets(5));
        buttonPane.setPrefHeight(150);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setSpacing(5);

        tagChoice = new ChoiceBox<>();

        for (TagType tag : TagType.values()) {
            tagChoice.getItems().add(tag);
        }

        tagChoice.setValue(TagType.PASSIVE);
        tagChoice.setPrefSize(150, 40);


        tagChoice.setStyle("-fx-font: 17px \"Segoe UI\";");
        HBox tagPane = new HBox(tagChoice);
        tagPane.setPadding(new Insets(10));

        BorderPane mainPane = new BorderPane();
        mainPane.setTop(qTextPane);
        mainPane.setCenter(optionsPane);
        mainPane.setBottom(buttonPane);
        mainPane.setRight(tagPane);

        Scene taggingScene = new Scene(mainPane, 600, 500);

        String iconPath = "tag_icon.png";

        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(iconPath)));
        primaryStage.setScene(taggingScene);
        primaryStage.setTitle("Grammar Tagger");
        primaryStage.show();


    }

    public int getCurrentQNum() {
        return currentQNum;
    }

    public void incrementCurrentQNum() {
        if (currentQNum < totalOfQs) {
            currentQNum++;
        }
    }

    public void decrementCurrentQNum() {
        if (currentQNum > 1) {
            currentQNum--;
        }
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


        initialOptionA = "A) " + currentQ.getOptionA().replace("\n", "");
        initialOptionB = "B) " + currentQ.getOptionB().replace("\n", "");
        initialOptionC = "C) " + currentQ.getOptionC().replace("\n", "");
        initialOptionD = "D) " + currentQ.getOptionD().replace("\n", "");

    }

    public void click_prevButton() {

    }

    public void click_nextButton() {

    }

    public void click_updateTagsButton() {

    }

    /** The method initializes the integer totalOfQs and an array chosenAnswers.
     * It should be called only after arraylist allQuestions is initialized.
     * That is after calling the method readDatabase().
     */
    public void initTagsArray() {
        totalOfQs = allQuestions.size();
        // initialize an array with a length of number of questions
        tagsArray = new String [totalOfQs];
    }

    /** The method is called every time a Button Next is pressed. It sets
     * GUI elements (Text elements) to values of elements of
     * Question (question Part, optionA, optionB, optionC, optionC).
     */
    public void setGUITexts() {
         // to avoid going out of array's bound
        if (getCurrentQNum() <= totalOfQs) {
            Question currentQ = allQuestions.get(getCurrentQNum() - 1);

            String qText = getCurrentQNum() + ". " + currentQ.getQuestionPart();

            // let's get rid of newline breaks
            qText = qText.replace("\n", "");

            String optionA = currentQ.getOptionA();
            String optionB = currentQ.getOptionB();
            String optionC = currentQ.getOptionC();
            String optionD = currentQ.getOptionD();

            questionText.setText(qText);
            optionAText.setText("A) " + optionA);
            optionBText.setText("B) " + optionB);
            optionCText.setText("C) " + optionC);
            optionDText.setText("D) " + optionD);
        }

    }

}
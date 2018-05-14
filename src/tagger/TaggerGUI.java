/** The class is a JavaFX implementation of a Grammar SQL tagger.
 *
 */

package tagger;

import grammartester.SQLReader;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import read.Question;

public class TaggerGUI extends Application {
    String dataBasePath;
    String tableName;
    
    Stage mainStage;
    Scene updateScene;

    List<Question> allQuestions;
    private int currentQNum = 1; // non-zero based
    int totalOfQs; // total number of questions in a test
    Button updateTagsButton;
    ChoiceBox<String> levelChoice;
    ChoiceBox<TagType> tagChoice;
    TagType[] tagsArray; // array holding tags
    String[] levelsArray; 
    

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
    
    public TaggerGUI() {
        dataBasePath = "C:\\sqlite_grammar_quizzes\\TEST7.db";
    }
    
    public TaggerGUI(String selectedFile) {
        super();
        this.dataBasePath = selectedFile;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        readDatabase(dataBasePath);
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
        
        String[] availableLevelsArray = {"A1", "A2", "B1", "B2", "C1", "Mix"};        
        
        levelChoice = new ChoiceBox<>();
        levelChoice.setStyle("-fx-font: 17px \"Segoe UI\";");
        levelChoice.getItems().addAll(availableLevelsArray);
        levelChoice.setPrefSize(150, 40);
        
        levelChoice.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null && newValue.equals("A1")) {
                    tagChoice.getItems().clear();
                    tagChoice.getItems().addAll(A1_LEVEL.values());
                }
                if (newValue != null && newValue.equals("A2")) {
                    tagChoice.getItems().clear();
                    tagChoice.getItems().addAll(A2_LEVEL.values());
                }
                if (newValue != null && newValue.equals("B1")) {
                    // set tagChoice for B1
                }
                if (newValue != null && newValue.equals("B2")) {
                    // set tagChoice for B2
                }
                if (newValue != null && newValue.equals("C1")) {
                    // set tagChoice for C1
                }
                if (newValue != null && newValue.equals("Mix")) {
                    tagChoice.getItems().clear();
                    tagChoice.getItems().addAll(MixType.values());
                }
            }
        });
        
        
        Region spacerBetwTaggers = new Region();
        spacerBetwTaggers.setPrefHeight(30);

        tagChoice = new ChoiceBox<>();        
        
        tagChoice.getItems().addAll(MixType.values());                  

        tagChoice.setValue(null);
        tagChoice.setPrefSize(150, 40);


        tagChoice.setStyle("-fx-font: 17px \"Segoe UI\";");
        VBox tagPane = new VBox(levelChoice, spacerBetwTaggers , tagChoice);
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
     * @param dataBasePath path to a datebase file which you want to open
     */
    public void readDatabase(String dataBasePath) {        
        
        // reverse the path, and read tableName from the end omittin .db which is 3 symbols to "\\"
        String dataBasePathReversed = new StringBuffer(dataBasePath).reverse().toString();        
        String tableNameRev = dataBasePathReversed.substring(3, dataBasePathReversed.indexOf("\\"));
        tableName = new StringBuffer(tableNameRev).reverse().toString().toLowerCase(); 
        
        SQLReader sQLReader = new SQLReader(dataBasePath, tableName);
        allQuestions = sQLReader.makeQuery(1, sQLReader.getNumberOfRowsInTable());
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

    

    public void click_nextButton() {
        // to avoid going out of array's bound
        if (getCurrentQNum() <= totalOfQs) {
            // read chosen values
            tagsArray[getCurrentQNum() - 1] = (TagType) tagChoice.getValue();
            levelsArray[getCurrentQNum() - 1] = levelChoice.getValue();                       
            
            incrementCurrentQNum();
            setGUITexts();
            
            // to avoid going out of array's bound
            if (getCurrentQNum() <= totalOfQs) {
                
                // unselect ChoiceBox if question is shown for the first time
                if (tagsArray[getCurrentQNum() - 1] == null) {
                    tagChoice.setValue(null);                    
                    levelChoice.getSelectionModel().clearSelection();
                }
                
                /* A user can press previous button, go back to prev question and then
                go back to question (pressing next) which user already tagged before.
                The previously tagged value should be selected in choice box. The
                following lines make sure it happens. */
                if (levelsArray[getCurrentQNum() - 1] != null) {
                    levelChoice.setValue(levelsArray[getCurrentQNum() - 1]);
                }
                
                if (tagsArray[getCurrentQNum() - 1] != null) {
                    // set the needed array of enums to tagChoice, otherwise it will be empty
                    if (levelsArray[getCurrentQNum() - 1].equals("A1")) {
                        tagChoice.getItems().clear();
                        tagChoice.getItems().addAll(A1_LEVEL.values());
                    }
                    if (levelsArray[getCurrentQNum() - 1].equals("A2")) {
                        tagChoice.getItems().clear();
                        tagChoice.getItems().addAll(A2_LEVEL.values());
                    }
                    if (levelsArray[getCurrentQNum() - 1].equals("B1")) {
                        // set tagChoice for B1
                    }
                    if (levelsArray[getCurrentQNum() - 1].equals("B2")) {
                        // set tagChoice for B2
                    }
                    if (levelsArray[getCurrentQNum() - 1].equals("C1")) {
                        // set tagChoice for C1
                    }
                    if (levelsArray[getCurrentQNum() - 1].equals("Mix")) {
                        tagChoice.getItems().clear();
                        tagChoice.getItems().addAll(MixType.values());
                    }

                    tagChoice.setValue(tagsArray[getCurrentQNum() - 1]);
                }


            } // end of inner if
            
        } // end of outer if
        
        if (getCurrentQNum() == totalOfQs) {
            // display UpdateTags button
            updateTagsButton.setVisible(true);
        }

    }
    
    public void click_prevButton() {
        // to avoid going out of array's bound
        if (getCurrentQNum() <= totalOfQs) {
            
            if (getCurrentQNum() == 1) {
                // do nothing
            } else {
                // read the chosen value 
                tagsArray[getCurrentQNum() - 1] = tagChoice.getValue();
                levelsArray[getCurrentQNum() - 1] = levelChoice.getValue();
                decrementCurrentQNum();
                setGUITexts();

                levelChoice.setValue(levelsArray[getCurrentQNum() - 1]);

                // set the needed array of enums to tagChoice, otherwise it will be empty
                if (levelsArray[getCurrentQNum() - 1].equals("A1")) {
                    tagChoice.getItems().clear();
                    tagChoice.getItems().addAll(A1_LEVEL.values());
                }
                if (levelsArray[getCurrentQNum() - 1].equals("A2")) {
                    tagChoice.getItems().clear();
                    tagChoice.getItems().addAll(A2_LEVEL.values());
                }
                if (levelsArray[getCurrentQNum() - 1].equals("B1")) {
                    // set tagChoice for B1
                }
                if (levelsArray[getCurrentQNum() - 1].equals("B2")) {
                    // set tagChoice for B2
                }
                if (levelsArray[getCurrentQNum() - 1].equals("C1")) {
                    // set tagChoice for C1
                }
                if (levelsArray[getCurrentQNum() - 1].equals("Mix")) {
                    tagChoice.getItems().clear();
                    tagChoice.getItems().addAll(MixType.values());
                }

                tagChoice.setValue(tagsArray[getCurrentQNum() - 1]);
                
                
            }
            
        } // end of if

    }

    public void click_updateTagsButton() {
        // TODO instead of just printing write tag values to a database
        click_nextButton();
        
        // and finally if all question are tagged write all the tags to database
        if (areAllQsTagged()) {
            // SQLTagWriter.writeTags(tagsArray);    
            new SQLTagWriter(dataBasePath, tableName).writeTags(tagsArray);
            // TODO here should be the line closing GUI and displaying SUCCESS window scene
        }            
    }
    
    

    /** The method initializes the integer totalOfQs and an array chosenAnswers.
     * It should be called only after arraylist allQuestions is initialized.
     * That is after calling the method readDatabase().
     */
    public void initTagsArray() {
        totalOfQs = allQuestions.size();
        // initialize an array with a length of number of questions
        tagsArray = new TagType[totalOfQs];
        for (TagType tagtype : tagsArray) {
            tagtype = null;
        }
        levelsArray = new String[totalOfQs];
        for (int i = 0; i < levelsArray.length; i++) {
            levelsArray[i] = "";
        }
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
            qText = qText.replace("\n", " ");

            String optionA = currentQ.getOptionA().replace("\n", " ");
            String optionB = currentQ.getOptionB().replace("\n", " ");
            String optionC = currentQ.getOptionC().replace("\n", " ");
            String optionD = currentQ.getOptionD().replace("\n", " ");

            questionText.setText(qText);
            optionAText.setText("A) " + optionA);
            optionBText.setText("B) " + optionB);
            optionCText.setText("C) " + optionC);
            optionDText.setText("D) " + optionD);
        }

    }
    
    
    /** The method checkes if all the questions were tagged. If so, it returns
     * true. Otherwise, it displays a Message Window notifying the user which
     * questions have not been tagged.
     *
     * @return boolean of whether or not all the questions have been tagged.
     */
    public boolean areAllQsTagged() {
        List<Integer> notTagged = new ArrayList<>();
        
        //get the ids of Questions which have not been tagged and put them in arraylist
        int i = 0;
        for (TagType tag : tagsArray) {
            if (tag == null){
                // increment every element of a list by one to switch from zero-based
                notTagged.add(i + 1);
            }
            i++;
        }
        
        // create a message to be shown in MessageBox
        String message = ""; // message to display in MessageBox
        if (!notTagged.isEmpty()) {
            message += "You can't finish now. \n Questions ";
            message += notTagged;
            message += " have not been tagged!";
            MessageBox.show(message, "Complete Tagging");
            return false;
        } else {
            return true;
        }
        
    }

}
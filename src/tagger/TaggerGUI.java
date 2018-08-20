/** The class is a JavaFX implementation of a Grammar SQL tagger.
 *
 */

package tagger;

import grammartester.SQLReader;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import read.Question;

public class TaggerGUI extends Application {
    String dataBasePath;
    String tableName;
    
    Stage mainStage;
    Scene updateScene;
    Scene successScene;

    List<Question> allQuestions;
    private int currentQNum = 1; // non-zero based
    int totalNumOfQs; // total number of questions in a test
    Button updateTagsButton;
    ChoiceBox<String> levelChoice;
    ChoiceBox<TagType> tagChoice;
    TagType[] tagsArray; // array holding tags
    String[] levelsArray; // array of levels mostly needed to choose corrent
    // choice box when moving one question back
    
    // progress of the test property for progress bar
    private DoubleProperty progOfTest;
    HBox progressPane;
    HBox successMainPane;
    Scene taggingScene;
    

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
    
    // default tagger constructor
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
        
        progOfTest = new SimpleDoubleProperty(0.0);
        
        ProgressBar progressBar = new ProgressBar();
        progressBar.setPrefWidth(400);
        
        // bind progressBar property with progOfTest updatable property
        progressBar.progressProperty().bind(progOfTest.divide((double)totalNumOfQs));
        
        progressPane = new HBox(progressBar);
        progressPane.setAlignment(Pos.CENTER);
        progressPane.setPadding(new Insets(5));
        
        Region spacerProg = new Region();
        spacerProg.setPrefHeight(50);
        
        VBox bottomPane = new VBox(buttonPane, progressPane, spacerProg);
        
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
        mainPane.setBottom(bottomPane);
        mainPane.setRight(tagPane);

        taggingScene = new Scene(mainPane, 600, 500);
        
        // ===================== SUCCESS PANE CODE ================
        
        String successStr = "Congratulation! \n You have successfully "
                + "updated your database.";
        Text successText = new Text(successStr);
        successText.setTextAlignment(TextAlignment.CENTER);        
        
        VBox successPaneV = new VBox(successText);
        successPaneV.setAlignment(Pos.CENTER);
        successMainPane = new HBox(successPaneV);
        successMainPane.setAlignment(Pos.CENTER);        
        successScene = new Scene(successMainPane, 600, 500);

        // ^^^^^^^^^^^^^^^^^^^^^ SUCCESS PANE CODE ^^^^^^^^^^^^^^^^^^
        
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
        if (currentQNum < totalNumOfQs) {
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
        // naming convention of data bases and table names is that the name is the same 
        // but data base name is in all capitals and table name is in all lower case letters
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
        if (getCurrentQNum() <= totalNumOfQs) {
            // read chosen values
            tagsArray[getCurrentQNum() - 1] = (TagType) tagChoice.getValue();
            levelsArray[getCurrentQNum() - 1] = levelChoice.getValue();                       
            
            incrementCurrentQNum();
            setGUITexts();
            
            // to avoid going out of array's bound
            if (getCurrentQNum() <= totalNumOfQs) {
                
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
        
        if (getCurrentQNum() == totalNumOfQs) {
            // display UpdateTags button
            updateTagsButton.setVisible(true);
        }
        
        // updates the values of already tagged questions number
        progOfTest.set(getNumberOfTaggedQs());

    }
    
    public void click_prevButton() {
        // to avoid going out of array's bound
        if (getCurrentQNum() <= totalNumOfQs) {
            
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
        // updates the values of already answered questions number
        progOfTest.set(getNumberOfTaggedQs());

    }

    public void click_updateTagsButton() {
        // run ProgressIndicator here to indicate that lengthy process is running
        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressPane.getChildren().add(progressIndicator);
        // TODO instead of just printing write tag values to a database
        click_nextButton();
        
        
        
        // and finally if all question are tagged write all the tags to database
        if (areAllQsTagged()) {
            
            // SQLTagWriter.writeTags(tagsArray);    
            new SQLTagWriter(dataBasePath, tableName).writeTags(tagsArray);
            // TODO here should be the line closing GUI and displaying SUCCESS window scene
            mainStage.setScene(successScene);
        }
        
        
    }
    
    

    /** The method initializes the integer totalNumOfQs and an array chosenAnswers.
     * It should be called only after arraylist allQuestions is initialized.
     * That is after calling the method readDatabase().
     */
    public void initTagsArray() {
        totalNumOfQs = allQuestions.size();
        // initialize an array with a length of number of questions
        tagsArray = new TagType[totalNumOfQs];
        for (TagType tagtype : tagsArray) {
            tagtype = null;
        }
        // initialize levelsArray
        levelsArray = new String[totalNumOfQs];
        for (int i = 0; i < levelsArray.length; i++) {
            levelsArray[i] = "";
        }
    }

    /** The method is called every time a Button Next or Button Previous is 
     * pressed. It sets
     * GUI elements (Text elements) to values of elements of
     * Question (question Part, optionA, optionB, optionC, optionC).
     */
    public void setGUITexts() {
         // to avoid going out of array's bound
        if (getCurrentQNum() <= totalNumOfQs) {
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
    
    /** The method iterates through an array of tagged answers
     * and returns a number of tagged questions.
     * @return a number of tagged questions.
     */
    public int getNumberOfTaggedQs() {
        int numOfTagged = 0;
        for (int i = 0; i < tagsArray.length; i++) {
            if (tagsArray[i] != null) {
                numOfTagged++;
            }
        }
        return numOfTagged;
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
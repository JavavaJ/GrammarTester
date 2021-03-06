/** The class is a JavaFX GUI implementation of Grammar Tester.
 *
 */

package testmaker;

import database.SQLReader;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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
import question.Question;

// TODO separate logic from frontend view to make it reusable
public class GrammarGUI extends Application {
    Stage mainStage;
    Scene resultScene;

    RadioButton radioA, radioB, radioC, radioD;
    RadioButton [] radioButtons;
    List<Question> allQuestions;  // is already in service class
    private int currentQNum = 1; // non-zero based  // is already in service class
    String[] chosenAnswers; // answers a user chooses via radio buttons  // is already in service class
    int totalOfQs; // total number of questions in a test  // is already in service class
    Button finishButton;
    
    // progress of the test property for progress bar
    private DoubleProperty progOfTest; 

    Text questionText;  // is already in service class
    String initialQuestionText;  // is already in service class
    String initialOptionA;  // is already in service class
    String initialOptionB;  // is already in service class
    String initialOptionC;  // is already in service class
    String initialOptionD;  // is already in service class

    Text resultText;
    
    public GrammarGUI() {
        // 
    }
    
    public GrammarGUI(List<Question> allQuestions) {
        super();
        this.allQuestions = allQuestions;
    }

    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) {
        // allQuestions = readDatabase();        
        System.out.println("All questions are read!");
        initChosenAnswers();  // is already in service class
        setInitialTextValues();  // is already in service class

        primaryStage.setMaximized(true);
        mainStage = primaryStage;

        questionText = new Text();
        questionText.setFont(Font.font(null, FontWeight.BOLD, 20));
        questionText.setWrappingWidth(580);
        questionText.setText(initialQuestionText);


        HBox qTextPane = new HBox(questionText);
        qTextPane.setPadding(new Insets(5));
        qTextPane.setPrefHeight(200);
        qTextPane.setAlignment(Pos.CENTER);


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

        // spacer is added to make a group of buttons appear to be in the middle
        Region spacer = new Region();
        spacer.setPrefWidth(60);

        HBox buttonPane = new HBox(spacer, prevButton, nextButton, finishButton);
        buttonPane.setPadding(new Insets(5));
        buttonPane.setPrefHeight(125);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setSpacing(5);
        
        progOfTest = new SimpleDoubleProperty(0.0);
        
        ProgressBar progressBar = new ProgressBar();
        progressBar.setPrefWidth(400);
        
        // bind progressBar property with progOfTest updatable property
        progressBar.progressProperty().bind(progOfTest.divide((double)totalOfQs));
        
        HBox progressPane = new HBox(progressBar);
        progressPane.setAlignment(Pos.CENTER);
        progressPane.setPadding(new Insets(5));
        
        Region spacerProg = new Region();
        spacerProg.setPrefHeight(50);
        
        VBox bottomPane = new VBox(buttonPane, progressPane, spacerProg);


        BorderPane mainPane = new BorderPane();
        mainPane.setTop(qTextPane);
        mainPane.setCenter(radioPane);
        mainPane.setBottom(bottomPane);

        Scene testingScene = new Scene(mainPane, 600, 500);

        // -------------- end of testingScene code -------------------

        resultText = new Text();
        resultText.setFont(Font.font(null, FontWeight.BOLD, 20));
        resultText.setWrappingWidth(580);

        VBox resultPane = new VBox(resultText);
        resultPane.setPadding(new Insets(5));
        resultPane.setAlignment(Pos.CENTER);

        resultScene = new Scene(resultPane, 600, 500);


        String iconPath = "icon_test.jpg";

        // setting icon for a window
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(iconPath)));
        primaryStage.setScene(testingScene);
        primaryStage.setTitle("Grammar Tester");
        primaryStage.setMaximized(true);
        primaryStage.show();

    }
    
    
     /** The method reads a database of tests and returns results in
     *  a list of Question objects.
     * @return list of Question objects List<Question>
     */
    public List<Question> readDatabase() {     

        // path of DB with tests
        String filePath = "jdbc:sqlite:C:/sqlite/TEST7.db";
        String tableName = "test7";
        
        SQLReader sQLReader = new SQLReader(filePath, tableName);

        return sQLReader.makeQuery(1, sQLReader.getNumberOfRowsInTable());
        
    }

    public int getCurrentQNum() {
        return currentQNum;
    }  // is already in service class

    public void incrementCurrentQNum() {  // is already in service class
        if (currentQNum < totalOfQs) {
            currentQNum++;
        }
    }

    public void decrementCurrentQNum() {  // is already in service class
        if (currentQNum > 1) {
            currentQNum--;
        }
    }
    
    /** The methods sets the elements of List<Question> allQuestions to
     * values.
     * @param allQuestions List<Question> allQuestions 
     */
    public void setAllQuestions(List<Question> allQuestions) {
        this.allQuestions = allQuestions;
    }  // is already in service class

    

    /** The method is called every time a Button Next is pressed. It sets
     * GUI elements (Text and Radio Buttons) to values of elements of
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
            radioA.setText("A) " + optionA);
            radioB.setText("B) " + optionB);
            radioC.setText("C) " + optionC);
            radioD.setText("D) " + optionD);
        }

    }

    /** The method reads values from Question object and sets initial values of
     * GUI to build it from.
     *
     */
    public void setInitialTextValues() {  // is already in service class
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


    /** The method initializes the integer totalOfQs and an array chosenAnswers.
     * It should be called only after arraylist allQuestions is initialized.
     * That is after calling the method readDatabase().
     */
    public void initChosenAnswers() {  // is already in service class
        totalOfQs = allQuestions.size();
        // initialize an array with a length of number of questions
        chosenAnswers = new String [totalOfQs];
    }

    /** This is a method which is called when Button Next is pressed. It reads
     * values of radio buttons and stores them in chosenAnswers array. After
     * it sets GUI elements to the values of next Question. Thirdly, the method
     * watches to set all radio buttons unselected if the Question is displayed
     * for the first time. Also, if the Question has already been previously
     * answered, the method sets the radio buttons to the value which was
     * answered before. Finally, it checks if the current question is the last
     * one. If so, it makes the button "Finish" visible.
     *
     */
    public void click_nextButton() {
        // to avoid going out of array's bound
        if (getCurrentQNum() <= totalOfQs) {
            chosenAnswers[getCurrentQNum() - 1] = readRadioButtons();
            incrementCurrentQNum();
            setGUITexts();

            // to avoid going out of array's bound
            if (getCurrentQNum() <= totalOfQs) {
                setRadButtonsToAnswerValues();
            }
        }
        if (getCurrentQNum() == totalOfQs) {
            // display Finish button
            finishButton.setVisible(true);
        }
        // updates the values of already answered questions number
        progOfTest.set(getNumberOfAnsweredQs());
    }


    /** This is a method which is called when Button Previous is pressed. It
     * allows a user to go back and check previously answered questions. While
     * doing that the previously answered values should retain the values which
     * was chosen by a user. Also, the method checks if the question is the first.
     * If so, it does not do anything since one can not go earlier than the first
     * question.
     *
     */
    public void click_prevButton() {
        // to avoid going out of array's bound
        if (getCurrentQNum() <= totalOfQs) {

            if (getCurrentQNum() == 1) {
                // do nothing
            } else {
                chosenAnswers[getCurrentQNum() - 1] = readRadioButtons();
                decrementCurrentQNum();
                setGUITexts();    
                setRadButtonsToAnswerValues();
            }

        }
        // updates the values of already answered questions number
        progOfTest.set(getNumberOfAnsweredQs());

    } // end of method
    
    
    /** The method sets radio buttons selected value to the value of previously
     * chosen answer. A user can press previous button, go back to prev question
     * and then go back to question (pressing next) which user already answered 
     * before. The previously marked answer should be selected in radio buttons.
     */
    public void setRadButtonsToAnswerValues() {
        String chosen = chosenAnswers[getCurrentQNum() - 1];

        // if value is null it throws NullPointerException
        if (chosen != null) {
            if (chosen.equals("a")) {
                radioA.setSelected(true);
            }
            if (chosen.equals("b")) {
                radioB.setSelected(true);
            }
            if (chosen.equals("c")) {
                radioC.setSelected(true);
            }
            if (chosen.equals("d")) {
                radioD.setSelected(true);
            }
        }
        if (chosen == null) {
            for (RadioButton radBut : radioButtons) {
                radBut.setSelected(false);
            }
        }
    }

    /** The method reads the values of radio buttons and return that values as
     * a string value.
     *
     * @return string values which corresponds to a selected radio button.
     */
    public String readRadioButtons() {
        String radValue = null;
        if (radioA.isSelected()) {
            radValue = "a";
        }
        if (radioB.isSelected()) {
            radValue = "b";
        }
        if (radioC.isSelected()) {
            radValue = "c";
        }
        if (radioD.isSelected()) {
            radValue = "d";
        }
        return radValue;
    }


    /** This is a method which is called when Finish Button is pressed. First,
     * it "clickes" Next Button in case it was not pressed before. Secondly,
     * having checked if all the questions have been answered, it calculates
     * the number of right answers.
     *
     */
    public void click_finishButton() {
        click_nextButton();

        if (areAllQsAnswered()) {
        	
        	String feedBackAnalisys = "";
        	
            int answerScore = 0;
            for (int i = 0; i < totalOfQs; i++) {
                String answered = chosenAnswers[i];
                Question question = allQuestions.get(i);
                String correctAn = question.getRightAns();

                if (answered.equals(correctAn)) {
                    answerScore++;
                } else {
                	// write here the code which saves incorrectly answered Qs 
                	// to display them in the result text.
                	feedBackAnalisys += (i + 1) + ". ";
                	String currQuestionPart = question.getQuestionPart();
                	feedBackAnalisys += currQuestionPart + " " + "\n";
                	feedBackAnalisys += "You answered: " + answered + ") ";
                	
                	if (answered.equals("a")) {
                		feedBackAnalisys += question.getOptionA() + "\n";
                	}
					if (answered.equals("b")) {
						feedBackAnalisys += question.getOptionB() + "\n";
					}
					if (answered.equals("c")) {
						feedBackAnalisys += question.getOptionC() + "\n";
					}
					if (answered.equals("d")) {
						feedBackAnalisys += question.getOptionD() + "\n";
					}
					
					feedBackAnalisys += "Correct answer: " + correctAn + ") ";
                	
					if (correctAn.equals("a")) {
                		feedBackAnalisys += question.getOptionA() + "\n";
                	}
					if (correctAn.equals("b")) {
						feedBackAnalisys += question.getOptionB() + "\n";
					}
					if (correctAn.equals("c")) {
						feedBackAnalisys += question.getOptionC() + "\n";
					}
					if (correctAn.equals("d")) {
						feedBackAnalisys += question.getOptionD() + "\n";
					}
                	
                }
            }

            System.out.println("Your Score: " + answerScore);

            String resultString = "You have correctly answered " + answerScore +
                    " out of " + totalOfQs + " questions. It means that your" +
                    " score is " + (answerScore * 100) / totalOfQs + " %.";
            
            resultString += "\n" + "\n" + "Mistakes Analysis: " + "\n" + feedBackAnalisys;

            System.out.println(resultString);

            resultText.setText(resultString);
            mainStage.setScene(resultScene);
            mainStage.setMaximized(true);
        }        


    }
    
    /** The method iterates through an array of chosen answers
     * and returns a number of answered questions.
     * @return a number of answered questions.
     */
    public int getNumberOfAnsweredQs() {  // is already in service class
        int numOfAswered = 0;
        for (String ans : chosenAnswers) {
            if (ans != null) {
                numOfAswered++;
            }
        }
        return numOfAswered;
    }


    /** The method checkes if all the questions were answered. If so, it returns
     * true. Otherwise, it displays a Message Window notifying the user which
     * questions have not been answered.
     *
     * @return boolean of whether or not all the questions have been answered.
     */
    public boolean areAllQsAnswered() {
        List<Integer> notChosen = new ArrayList<>();

        //get the ids of Questions which have not been answered and put them in arraylist
        int i = 0;
        for (String ans : chosenAnswers) {
            if (ans == null) {
                // increment every element of a list by one to switch from zero-based
                notChosen.add(i + 1);
            }
            i++;
        }

        String message = ""; // message to display in MessageBox
        if (!notChosen.isEmpty()) {
            message += "You can't finish now. \n Questions ";
            message += notChosen;
            message += " have not been answered!";
            MessageBox.show(message, "Complete the Test");
            return false;
        } else {
            return true;
        }

    }

}
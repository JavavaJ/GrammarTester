///** The class is a JavaFX GUI implementation of Grammar Tester.
// *
// */
//
//package grammartester;
//
//import java.util.ArrayList;
//import java.util.List;
//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.RadioButton;
//import javafx.scene.control.ToggleGroup;
//import javafx.scene.control.Tooltip;
//import javafx.scene.image.Image;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Region;
//import javafx.scene.layout.VBox;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//import read.Question;
//
//public class JavaFXGrammarGUI extends Application {
//    Stage mainStage;
//    Scene resultScene;
//
//    RadioButton radioA, radioB, radioC, radioD;
//    RadioButton [] radioButtons;
//    List<Question> allQuestions;
//    private int currentQNum = 1; // non-zero based
//    String[] chosenAnswers; // answers a user chooses via radio buttons
//    int totalOfQs; // total number of questions in a test
//    Button finishButton;
//
//    Text questionText;
//    String initialQuestionText;
//    String initialOptionA;
//    String initialOptionB;
//    String initialOptionC;
//    String initialOptionD;
//
//    Text resultText;
//
//    public JavaFXGrammarGUI() {
//        super();
//        this.allQuestions = readDatabase();
//    }
//
//    public JavaFXGrammarGUI(List<Question> allQuestions) {
//        super();
//        this.allQuestions = allQuestions;
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//
//
//    @Override
//    public void start(Stage primaryStage) {
//        allQuestions = readDatabase();
//        System.out.println("All questions are read!");
//        initChosenAnswers();
//        setInitialTextValues();
//
//        mainStage = primaryStage;
//
//        questionText = new Text();
//        questionText.setFont(Font.font(null, FontWeight.BOLD, 20));
//        questionText.setWrappingWidth(580);
//        questionText.setText(initialQuestionText);
//
//
//        HBox qTextPane = new HBox(questionText);
//        qTextPane.setPadding(new Insets(5));
//        qTextPane.setPrefHeight(200);
//        qTextPane.setAlignment(Pos.CENTER);
//
//
//        radioA = new RadioButton();
//        radioB = new RadioButton();
//        radioC = new RadioButton();
//        radioD = new RadioButton();
//
//        radioA.setText(initialOptionA);
//        radioB.setText(initialOptionB);
//        radioC.setText(initialOptionC);
//        radioD.setText(initialOptionD);
//
//        radioButtons = new RadioButton[4];
//
//        radioButtons[0] = radioA;
//        radioButtons[1] = radioB;
//        radioButtons[2] = radioC;
//        radioButtons[3] = radioD;
//
//        ToggleGroup tGroup = new ToggleGroup();
//
//        for (RadioButton r : radioButtons) {
//            r.setToggleGroup(tGroup);
//            r.setFont(new Font(15));
//        }
//
//
//
//        VBox radioPane = new VBox(radioA, radioB, radioC, radioD);
//        radioPane.setAlignment(Pos.BASELINE_LEFT);
//        radioPane.setSpacing(5);
//        radioPane.setPadding(new Insets(5));
//
//
//        Button prevButton = new Button("Previous");
//        prevButton.setFont(new Font(20));
//        prevButton.setTooltip(new Tooltip("Return to previous question"));
//        prevButton.setOnAction(e -> click_prevButton());
//
//        Button nextButton = new Button("Next");
//        nextButton.setFont(new Font(20));
//        nextButton.setTooltip(new Tooltip("Go to next question"));
//        nextButton.setOnAction(e -> click_nextButton());
//
//        finishButton = new Button("Finish");
//        finishButton.setTooltip(new Tooltip("Finish and Evaluate"));
//        finishButton.setFont(new Font(20));
//        finishButton.setVisible(false);
//        finishButton.setOnAction(e -> click_finishButton());
//
//        // spacer is added to make a group of buttons appear to be in the middle
//        Region spacer = new Region();
//        spacer.setPrefWidth(60);
//
//        HBox buttonPane = new HBox(spacer, prevButton, nextButton, finishButton);
//        buttonPane.setPadding(new Insets(5));
//        buttonPane.setPrefHeight(150);
//        buttonPane.setAlignment(Pos.CENTER);
//        buttonPane.setSpacing(5);
//
//
//        BorderPane mainPane = new BorderPane();
//        mainPane.setTop(qTextPane);
//        mainPane.setCenter(radioPane);
//        mainPane.setBottom(buttonPane);
//
//        Scene testingScene = new Scene(mainPane, 600, 500);
//
//        // -------------- end of testingScene code -------------------
//
//        resultText = new Text();
//        resultText.setFont(Font.font(null, FontWeight.BOLD, 20));
//        resultText.setWrappingWidth(580);
//
//        VBox resultPane = new VBox(resultText);
//        resultPane.setPadding(new Insets(5));
//        resultPane.setAlignment(Pos.CENTER);
//
//        resultScene = new Scene(resultPane, 600, 500);
//
//
//        String iconPath = "icon_test.jpg";
//
//        // setting icon for a window
//        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(iconPath)));
//        primaryStage.setScene(testingScene);
//        primaryStage.setTitle("Grammar Tester");
//        primaryStage.show();
//
//    }
//
//    public int getCurrentQNum() {
//        return currentQNum;
//    }
//
//    public void incrementCurrentQNum() {
//        if (currentQNum < totalOfQs) {
//            currentQNum++;
//        }
//    }
//
//    public void decrementCurrentQNum() {
//        if (currentQNum > 1) {
//            currentQNum--;
//        }
//    }
//
//    /** The methods sets the elements of List<Question> allQuestions to
//     * values.
//     * @param allQuestions List<Question> allQuestions
//     */
//    public void setAllQuestions(List<Question> allQuestions) {
//        // TODO create a constructor of a class to which you can pass
//        // a referece to List<Question> allQuestions as a parameter
//        // setAllQuestions(allQuestions);
//    }
//
//    /** The method reads a database of tests and returns results in
//     *  a list of Question objects.
//     * @return list of Question objects List<Question>
//     */
//    public List<Question> readDatabase() {
//        SQLReader sQLReader = new SQLReader();
//
//        // path of DB with tests
//        String filePath = "jdbc:sqlite:C:/sqlite/TEST7.db";
//        String tableName = "test7";
//
//        return sQLReader.makeQuery(1, sQLReader.getNumberOfRowsInTable(filePath, tableName));
//
//    }
//
//
//    /** The method is called every time a Button Next is pressed. It sets
//     * GUI elements (Text and Radio Buttons) to values of elements of
//     * Question (question Part, optionA, optionB, optionC, optionC).
//     */
//    public void setGUITexts() {
//         // to avoid going out of array's bound
//        if (getCurrentQNum() <= totalOfQs) {
//            Question currentQ = allQuestions.get(getCurrentQNum() - 1);
//
//            String qText = getCurrentQNum() + ". " + currentQ.getQuestionPart();
//
//            // let's get rid of newline breaks
//            qText = qText.replace("\n", "");
//
//            String optionA = currentQ.getOptionA();
//            String optionB = currentQ.getOptionB();
//            String optionC = currentQ.getOptionC();
//            String optionD = currentQ.getOptionD();
//
//            questionText.setText(qText);
//            radioA.setText("A) " + optionA);
//            radioB.setText("B) " + optionB);
//            radioC.setText("C) " + optionC);
//            radioD.setText("D) " + optionD);
//        }
//
//    }
//
//    /** The method reads values from Question object and sets initial values of
//     * GUI to build it from.
//     *
//     */
//    public void setInitialTextValues() {
//        System.out.println("Starting initializing text values...");
//
//        Question currentQ = allQuestions.get(0);
//
//        initialQuestionText = "1. " + currentQ.getQuestionPart();
//
//        // let's get rid of newline breaks
//        initialQuestionText = initialQuestionText.replace("\n", "");
//
//
//        initialOptionA = "A) " + currentQ.getOptionA();
//        initialOptionB = "B) " + currentQ.getOptionB();
//        initialOptionC = "C) " + currentQ.getOptionC();
//        initialOptionD = "D) " + currentQ.getOptionD();
//
//    }
//
//
//    /** The method initializes the integer totalOfQs and an array chosenAnswers.
//     * It should be called only after arraylist allQuestions is initialized.
//     * That is after calling the method readDatabase().
//     */
//    public void initChosenAnswers() {
//        totalOfQs = allQuestions.size();
//        // initialize an array with a length of number of questions
//        chosenAnswers = new String [totalOfQs];
//    }
//
//    /** This is a method which is called when Button Next is pressed. It reads
//     * values of radio buttons and stores them in chosenAnswere array. After
//     * it sets GUI elements to the values of next Question. Thirdly, the method
//     * watches to set all radio buttons unselected if the Question is displayed
//     * for the first time. Also, if the Question has already been previously
//     * answered, the method sets the radio buttons to the value which was
//     * answered before. Finally, it checks if the current question is the last
//     * one. If so, it makes the button "Finish" visible.
//     *
//     */
//    public void click_nextButton() {
//        // to avoid going out of array's bound
//        if (getCurrentQNum() <= totalOfQs) {
//            chosenAnswers[getCurrentQNum() - 1] = readRadioButtons();
//            incrementCurrentQNum();
//            setGUITexts();
//
//            // to avoid going out of array's bound
//            if (getCurrentQNum() <= totalOfQs) {
//                setRadButtonsToAnswerValues();
//
//            }
//
//
//
//        }
//        if (getCurrentQNum() == totalOfQs) {
//            // display Finish button
//            finishButton.setVisible(true);
//        }
//    }
//
//
//    /** This is a method which is called when Button Previous is pressed. It
//     * allows a user to go back and check previously answered questions. While
//     * doing that the previously answered values should retain the values which
//     * was chosen by a user. Also, the method checks if the question is the first.
//     * If so, it does not do anything since one can not go earlier than the first
//     * question.
//     *
//     */
//    public void click_prevButton() {
//        // to avoid going out of array's bound
//        if (getCurrentQNum() <= totalOfQs) {
//
//            if (getCurrentQNum() == 1) {
//                // do nothing
//            } else {
//                chosenAnswers[getCurrentQNum() - 1] = readRadioButtons();
//                decrementCurrentQNum();
//                setGUITexts();
//                setRadButtonsToAnswerValues();
//            }
//
//        }
//
//
//
//    } // end of method
//
//
//    /** The method sets radio buttons selected value to the value of previously
//     * chosen answer. A user can press previous button, go back to prev question
//     * and then go back to question (pressing next) which user already answered
//     * before. The previously marked answer should be selected in radio buttons.
//     */
//    public void setRadButtonsToAnswerValues() {
//        String chosen = chosenAnswers[getCurrentQNum() - 1];
//
//        // if value is null it throws NullPointerException
//        if (chosen != null) {
//            if (chosen.equals("a")) {
//                radioA.setSelected(true);
//            }
//            if (chosen.equals("b")) {
//                radioB.setSelected(true);
//            }
//            if (chosen.equals("c")) {
//                radioC.setSelected(true);
//            }
//            if (chosen.equals("d")) {
//                radioD.setSelected(true);
//            }
//
//        }
//        if (chosen == null) {
//            for (RadioButton radBut : radioButtons) {
//                radBut.setSelected(false);
//            }
//        }
//    }
//
//    /** The method reads the values of radio buttons and return that values as
//     * a string value.
//     *
//     * @return string values which corresponds to a selected radio button.
//     */
//    public String readRadioButtons() {
//        String radValue = null;
//        if (radioA.isSelected()) {
//            radValue = "a";
//        }
//        if (radioB.isSelected()) {
//            radValue = "b";
//        }
//        if (radioC.isSelected()) {
//            radValue = "c";
//        }
//        if (radioD.isSelected()) {
//            radValue = "d";
//        }
//        return radValue;
//    }
//
//
//    /** This is a method which is called when Finish Button is pressed. First,
//     * it "clickes" Next Button in case it was not pressed before. Secondly,
//     * having checked if all the questions have been answered, it calculates
//     * the number of right answers.
//     *
//     */
//    public void click_finishButton() {
//        click_nextButton();
//
//        if (areAllQsAnswered()) {
//            int answerScore = 0;
//            for (int i = 0; i < totalOfQs; i++) {
//                String answered = chosenAnswers[i];
//                String correctAns = allQuestions.get(i).getRightAns();
//
//                if (answered.equals(correctAns)) {
//                    answerScore++;
//                }
//            }
//
//
//            System.out.println("Your Score: " + answerScore);
//
//            String resultString = "You have correctly answered " + answerScore +
//                    " out of " + totalOfQs + " questions. It means that your" +
//                    " score is " + (answerScore * 100) / totalOfQs + " %.";
//
//            System.out.println(resultString);
//
//            resultText.setText(resultString);
//            mainStage.setScene(resultScene);
//
//        }
//
//
//    }
//
//
//    /** The method checkes if all the questions were answered. If so, it returns
//     * true. Otherwise, it displays a Message Window notifying the user which
//     * questions have not been answered.
//     *
//     * @return boolean of whether or not all the questions have been answered.
//     */
//    public boolean areAllQsAnswered() {
//        List<Integer> notChosen = new ArrayList<>();
//
//        //get the ids of Questions which have not been answered and put them in arraylist
//        int i = 0;
//        for (String ans : chosenAnswers) {
//            if (ans == null) {
//                // increment every element of a list by one to switch from zero-based
//                notChosen.add(i + 1);
//            }
//            i++;
//        }
//
//        String message = ""; // message to display in MessageBox
//        if (!notChosen.isEmpty()) {
//            message += "You can't finish now. \n Questions ";
//            message += notChosen;
//            message += " have not been answered!";
//            MessageBox.show(message, "Complete the Test");
//            return false;
//        } else {
//            return true;
//        }
//
//    }
//
//
//
//}
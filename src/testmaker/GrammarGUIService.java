package testmaker;

import javafx.beans.property.DoubleProperty;
import read.Question;

import java.util.List;

public class GrammarGUIService {

    List<Question> allQuestions;
    private int currentQNum = 1; // non-zero based
    String[] chosenAnswers; // answers a user chooses via radio buttons
    int totalOfQs; // total number of questions in a test
    // progress of the test property for progress bar
    private DoubleProperty progOfTest;

    String initialQuestionText;
    String initialOptionA;
    String initialOptionB;
    String initialOptionC;
    String initialOptionD;

    public GrammarGUIService(List<Question> allQuestions) {
        this.allQuestions = allQuestions;
        initChosenAnswers();
    }

    /** The method initializes the integer totalOfQs and an array chosenAnswers.
     * It should be called only after arraylist allQuestions is initialized.
     * That is after calling the method readDatabase().
     */
    public void initChosenAnswers() {
        totalOfQs = allQuestions.size();
        // initialize an array with a length of number of questions
        chosenAnswers = new String [totalOfQs];
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



}

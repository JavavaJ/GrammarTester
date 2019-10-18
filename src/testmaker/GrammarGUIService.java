package testmaker;

import read.Question;

import java.util.List;

public class GrammarGUIService {

    List<Question> allQuestions;
    private int currentQNum = 1; // non-zero based
    String[] chosenAnswers; // answers a user chooses via radio buttons
    int totalOfQs; // total number of questions in a test

    public GrammarGUIService(List<Question> allQuestions) {
        this.allQuestions = allQuestions;
    }



}

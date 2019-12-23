package java.testmaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.question.DelimiterCase;
import java.question.Question;
import static java.testmaker.Constants.IS_DEBUGGING_MODE;

public class QuestionUtil {
    
    static DelimiterCase delimiterCase;
    
        /** The method takes a text with numbered multiple choice (4 options) questions,
        * forms Question objects and returns an array of Question objects.
        *
        * @param questionsText  a text with numbered multiple choice (4 options) questions
        *
        * @return Question[] an array of Question objects
        */
    public static Question[] parseTextToQsArray(String questionsText) {
        // sometimes in the text 65533 UTF character appears, we need to get rid of it
        char strangeChar = (char) 65533;
        StringBuffer questionTextBuff = new StringBuffer(questionsText);
        for (int i = 0; i < questionTextBuff.length(); i++) {
            if (questionTextBuff.charAt(i) == strangeChar) {
                questionTextBuff.deleteCharAt(i);
            }            
        }
        questionsText = questionTextBuff.toString();
        
        
        // determine and set text's delimiter case
        setDelimiterCase(questionsText);        
        
        // create a map of questions' ordinal numbers
        Map<String, Integer> mapOfIndices = questionNumIndex(questionsText);

        // create an array of body of questions
        String[] questions = new String[mapOfIndices.size()];
        
        // populate the arrray with questions' texts
        for (int i = 0; i < mapOfIndices.size(); i++) {

            // adding 1 is conversion to a non-zero based integer
            String indexStr = String.valueOf(i+1);

            // adding s is finding upper position to slice a string
            String nextIndexStr = String.valueOf(i+2);

            if (mapOfIndices.get(nextIndexStr) != null) {
                questions[i] = questionsText.substring(mapOfIndices.get(indexStr),
                                                mapOfIndices.get(nextIndexStr));
            } else {
                // the last java.question in the text
                questions[i] = questionsText.substring(mapOfIndices.get(indexStr));
            }

        } // end of loop
        
        // create an array of questions of Question type
        Question[] questionArray = new Question[questions.length];
        
        if (IS_DEBUGGING_MODE) {
            System.out.println("Running in debugging mode!");
            System.out.println("Questions length is: " + questions.length);
        }
        
        for (int i = 0; i < questions.length; i++) {
            // create instances of Question, giving to its
            // constructor texts of questions
            if (IS_DEBUGGING_MODE) {
                System.out.println("Debugging mode!");
                System.out.println("i: " + i);
                System.out.println("java.question[i]: " + questions[i]);
            }
            
            questionArray[i] = new Question(questions[i], delimiterCase);
        } // end of loop

        return questionArray;       
        
        
    }
    
    /** The method takes a string which is a  text with numbered questions.
     * The method returns a map of indices (position in a text) of questions'
     * ordinal numbers. Those indices are used later to parse the questions.
     *
     * @param str text with numbered multiple choice (a, b, c, d) questions
     * @return a map of questions' indices where keys are strings (!) of
     * questions' ordinal numbers and values are there positions in the text.
     */
    public static  Map<String, Integer> questionNumIndex(String str) {
        Map<String, Integer> mapIndex = new HashMap<>();

        int qNum = 1;
        int index = 0;
        while (index != -1) {
            String qNumStr = String.valueOf(qNum);
            String token = qNumStr + ".";
            index = str.indexOf(token, index);

            qNum++;
            // break if we reach the end of questions' text
            if (index == -1) {
                break;
            } else {
                mapIndex.put(qNumStr, index);
                index++;
            }
        }
        
        // debug line
        if (IS_DEBUGGING_MODE) {
            System.out.println("Running is debugging mode!");
            System.out.println("Map of indices: ");
            for (int i = 0; i < mapIndex.size(); i++) {
                System.out.println((i + 1) + " : " + mapIndex.get(String.valueOf(i + 1)));
            }
            System.out.println(mapIndex);
        }
            
        
        return mapIndex;
    }

    /** The method takes a plain multiple choice questions text and 
     * based on some regular expression patterns determines the delimeters 
     * case of the text.
     * @param questionsText string with plain text of multiple choice questions.
     */
    public static void setDelimiterCase(String questionsText) {
        
        String upperRegex = "[A-D]\\)";
        String lowerRegex = "[a-d]\\)";
        
        Pattern upperPattern = Pattern.compile(upperRegex);
        Pattern lowerPattern = Pattern.compile(lowerRegex);
        
        Matcher upperMatcher = upperPattern.matcher(questionsText);
        Matcher lowerMatcher = lowerPattern.matcher(questionsText);
        
        List<Boolean>upperMatchesList = new ArrayList<>();
        List<Boolean>lowerMatchesList = new ArrayList<>();
        
        while (upperMatcher.find()) {
            upperMatchesList.add(new Boolean(true));
        }
        
        while (lowerMatcher.find()) {
            lowerMatchesList.add(new Boolean(true));
        }
        
        if (upperMatchesList.contains(true) && !lowerMatchesList.contains(true)) {
            System.out.println("Delimiter is Upper Case!"); // TODO delete later
            delimiterCase = DelimiterCase.UPPERCASE;
        }
        
        if (lowerMatchesList.contains(true) && !upperMatchesList.contains(true)) {
            System.out.println("Delimiter is Lower Case!"); // TODO delete later
            delimiterCase = DelimiterCase.LOWERCASE;
        }      
        
    }
    
    
    
}
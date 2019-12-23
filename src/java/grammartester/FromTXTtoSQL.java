package java.grammartester;

import java.util.HashMap;
import java.util.Map;

import java.database.SQLWriter;
import java.utils.ReadFileAsString;
import java.question.Question;

class FromTXTtoSQL {


    public static void main(String[] args) {
        FromTXTtoSQL fromTXTtoSQL = new FromTXTtoSQL();
        // the following two methods just read a txt file
        String myFile = "C:\\Users\\ALEXXX\\Documents\\NetBeansProjects\\GrammarTester\\test7.txt";
        String myText = ReadFileAsString.read(myFile);

        fromTXTtoSQL.writeTextToSQL(myText);

    } // end of main method

    /** The method takes a text with numbered multiple choice (4 options) questions,
     * forms Question objects and writes them to SQL java.database.
     * 
     * @param questionsText a text with numbered multiple choice (4 options) questions
     */
    public void writeTextToSQL(String questionsText) {
        writeQsToSQL(textToQsArray(questionsText));
    }

    /** The method takes a text with numbered multiple choice (4 options) questions,
     * forms Question objects and returns an array of Question objects.
     *
     * @param questionsText  a text with numbered multiple choice (4 options) questions
     * @return Question[] an array of Question objects
     */
    public Question[] textToQsArray(String questionsText) {
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
        for (int i = 0; i < questions.length; i++) {
            // create instances of Question, giving to its
            // constructor texts of questions
            questionArray[i] = new Question(questions[i]);
        } // end of loop

        return questionArray;

    } // end of method


    public void writeQsToSQL(Question[] questionArray) {
        // write all the questions to SQL java.database
        for (int i = 0; i < questionArray.length; i++) {
            Question q = questionArray[i];
            int id = q.getId();
            String questionPart = q.getQuestionPart();
            String a = q.getOptionA();
            String b = q.getOptionB();
            String c = q.getOptionC();
            String d = q.getOptionD();

            // and finally write to DB
            SQLWriter writer = new SQLWriter();
            writer.storeValues(id, questionPart, a, b, c, d);
            System.out.println("Successful iteration #:" + i);
        } // end of loop
    }



    /** The method takes a string which is a  text with numbered questions.
     * The method returns a map of indices (position in a text) of questions'
     * ordinal numbers. Those indices are used later to parse the questions.
     *
     * @param str text with numbered multiple choice (a, b, c, d) questions
     * @return a map of questions' indices where keys are strings (!) of
     * questions' ordinal numbers and values are there positions in the text.
     */
    public  Map<String, Integer> questionNumIndex(String str) {
        Map<String, Integer> mapIndex = new HashMap<>();

        int qNum = 1;
        int index = 0;
        while (index != -1) {
            String qNumStr = String.valueOf(qNum);
            index = str.indexOf(qNumStr, index);

            qNum++;
            // break if we reach the end of questions' text
            if (index == -1) {
                break;
            } else {
                mapIndex.put(qNumStr, index);
                index++;
            }
        }
        return mapIndex;
    }
} // end of class
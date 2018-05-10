package read;

import grammartester.SQLReader;
import java.util.ArrayList;
import java.util.List;

class QuestionTest {
    public static void main(String[] args) {
        SQLReader newTest = new SQLReader();
        
        String filePath = "jdbc:sqlite:C:/sqlite/TEST7.db";
        String tableName = "test7";
        
        System.out.println(newTest.getNumberOfRowsInTable(filePath, tableName));
        
        for (Question q : newTest.makeQuery(1, newTest.getNumberOfRowsInTable(filePath, tableName))) {
            q.printQuestion();
            System.out.println("");
        }
        
        /*
        List<Question> allQs = new ArrayList<>();
        allQs = newTest.makeQuery(1, 69);
        
        for (Question q : allQs) {
            q.printQuestion();
            System.out.println("");
        }
        
        */
    }
}
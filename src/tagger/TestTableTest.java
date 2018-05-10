package tagger;

import grammartester.SQLReader;
import java.util.List;
import read.Question;

public class TestTableTest {
    List<Question> allQuestions;

    public static void main(String[] args) {
        TestTableTest testTableTest = new TestTableTest();
    }

    public TestTableTest() {
        readDatabase();        
        TestTable.show(allQuestions);
    }

    public void readDatabase() {
        SQLReader sQLReader = new SQLReader();

        // path of DB with tests
        String filePath = "jdbc:sqlite:C:/sqlite/TEST7.db";
        String tableName = "test7";

        allQuestions = sQLReader.makeQuery(1, sQLReader.getNumberOfRowsInTable(filePath, tableName));
        System.out.println("All questions are read!");
    }

}
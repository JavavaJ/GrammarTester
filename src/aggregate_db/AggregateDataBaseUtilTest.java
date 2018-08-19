/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aggregate_db;

/**
 *
 * @author ALEXXX
 */
public class AggregateDataBaseUtilTest {
    
    public static void main(String[] args) {
        storeFirst7DB();
        
    }
    
    public static void createDB() {
        String aggrerateDBPath = "D:\\Some C files\\sqlite_grammar_quizzes\\ALL_ELEM.db";
        String tableName = "all_elem";
        
        AggregateDataBaseUtil.createAggregateDB(aggrerateDBPath, tableName);
    }
    
    public static void storeFirst7DB() {
        String aggrerateDBPath = "D:\\Some C files\\sqlite_grammar_quizzes\\ALL_ELEM.db";
        String tableName = "all_elem";

        String folderWithTests = "D:\\Some C files\\sqlite_grammar_quizzes";
        AggregateDataBaseUtil.storeQuestionsToAggregateDB
        (AggregateDataBaseUtil.readAllQuestionDBs(folderWithTests, 7),
                aggrerateDBPath, tableName);
    }
    
}

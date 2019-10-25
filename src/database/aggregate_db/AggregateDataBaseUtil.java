/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.aggregate_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import question.Question;
import testmaker.DataBasesUtil;

/**
 *
 * @author ALEXXX
 */
public class AggregateDataBaseUtil {

    private static String urlSQLite;
    private static Connection connectionGlob;
    private static Statement stmt;

    public static void main(String[] args) {

        /*
        // create an aggregate data base
        String aggrerateDBPath = "D:\\Some C files\\sqlite_grammar_quizzes\\ALL_ELEM.db";
        String tableName = "all_elem";
        
        createAggregateDB(aggrerateDBPath, tableName);
        
        */
        
        /*
        String folderWithTests = "D:\\Some C files\\sqlite_grammar_quizzes";
        List<Question> allQuestins = readAllQuestionDBs(folderWithTests, 7);
        
        System.out.println("SIZE: " + allQuestins.size());
        
        for (Question question : allQuestins) {
            question.printQuestion();
        }
        
         */
 
        /*
        // store the values of the first 7 databases in one aggregate db 
        String aggrerateDBPath = "D:\\Some C files\\sqlite_grammar_quizzes\\ALL_ELEM.db";
        String tableName = "all_elem";

        String folderWithTests = "D:\\Some C files\\sqlite_grammar_quizzes";
        storeQuestionsToAggregateDB(readAllQuestionDBs(folderWithTests, 7),
                aggrerateDBPath, tableName);
        */
        
    }

    /**
     * The method creates an aggregate database file and a SQL table in it.
     *
     * @param dataBasePath (full path to .db file where to create a table)
     * @param tableName (is expected to be in lower case)
     */
    public static void createAggregateDB(String dataBasePath, String tableName) {

        urlSQLite = "jdbc:sqlite:" + dataBasePath.replace("\\", "/");

        try {
            // load driver
            Class.forName("org.sqlite.JDBC");

            // establish connection
            connectionGlob = DriverManager.getConnection(urlSQLite);

            stmt = connectionGlob.createStatement();

            String sql2 = "CREATE TABLE " + tableName + " (miniid "
                    + "TEXT, question TEXT, a TEXT, b TEXT, c TEXT, d TEXT, "
                    + "right TEXT, tags TEXT, subtags TEXT);";

            stmt.executeUpdate(sql2);

            stmt.close();
            connectionGlob.close();

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            /*
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
             */
            try {
                if (connectionGlob != null) {
                    connectionGlob.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } // end of final try
        } // end of finally
    } // end of createDB

    public static List<Question> readAllQuestionDBs(String folderWithTests,
            int numOfTestsInFolder) {
        List<Question> allTestsQuestions = new ArrayList<Question>();

        // iterate number of times equals numOfTestsInFolder
        for (int i = 1; i <= numOfTestsInFolder; i++) {
            String pathToDB = folderWithTests + "\\" + "TEST" + i + ".db";

            // append all Questions to allTestsQuestions
            allTestsQuestions.addAll(DataBasesUtil.readDB(pathToDB));
        }

        return allTestsQuestions;
    }

    public static void storeQuestionsToAggregateDB(List<Question> allQuestions,
            String dataBasePath, String tableName) {
        int size = allQuestions.size();

        urlSQLite = "jdbc:sqlite:" + dataBasePath.replace("\\", "/");

        Connection connection = null;
        PreparedStatement prStmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(urlSQLite);

            String prsql = "INSERT INTO " + tableName + " (miniid, question,"
                    + " a, b, c, d, right, tags, subtags) VALUES"
                    + " (?, ?, ?, ?, ?, ?, ?, ?, null)";

            prStmt = connection.prepareStatement(prsql);

            Question question;
            int tableCount = 1;
            int prevId = 0;

            for (int i = 0; i < size; i++) {
                question = allQuestions.get(i);
                tableCount = getTableCount(tableCount, question, prevId);

                // set miniId
                prStmt.setString(1, getMiniId(question, prevId, tableCount));

                prStmt.setString(2, question.getQuestionPart());
                prStmt.setString(3, question.getOptionA());
                prStmt.setString(4, question.getOptionB());
                prStmt.setString(5, question.getOptionC());
                prStmt.setString(6, question.getOptionD());
                prStmt.setString(7, question.getRightAns());
                prStmt.setString(8, question.getTags());

                prStmt.addBatch();

                // set previous Id
                prevId = question.getId();
            }
            prStmt.executeBatch();

            prStmt.close();
            connection.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (ClassNotFoundException notFoundExp) {
            notFoundExp.printStackTrace();
        } finally {

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }

    }

    public static String getMiniId(Question question, int prevId, int tableCount) {
        int id = question.getId();
        String miniId = "tm_elem_t" + tableCount + "_" + id;        
        return miniId;
    }

    // return tableCount which is a number of a dababase file, like 13 in TEST13
    public static int getTableCount(int tableCount, Question question, int prevId) {
        if (prevId > question.getId()) {
            tableCount++;
        }
        return tableCount;
    }

}

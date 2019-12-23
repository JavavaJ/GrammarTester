package java.testmaker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.question.Question;

public class DataBasesUtil {

    private static String urlSQLite;
    private static Connection connectionGlob;
    private static Statement stmt;

    public static void main(String[] args) {
        String testPath = "C:\\sqlite_grammar_quizzes\\TEST7.db";

        List<Question> allQuestions = readDB(testPath);
        for (Question question : allQuestions) {
            question.printQuestion();
        }

    }

    /**
     * The method creates a java.database file and a SQL table in it.
     *
     * @param dataBasePath (full path to .db file where to create a table)
     * @param tableName (is expected to be in lower case)
     */
    public static void createDB(String dataBasePath, String tableName) {

        urlSQLite = "jdbc:sqlite:" + dataBasePath.replace("\\", "/");

        try {
            // load driver
            Class.forName("org.sqlite.JDBC");

            // establish connection
            connectionGlob = DriverManager.getConnection(urlSQLite);

            stmt = connectionGlob.createStatement();

            String sql2 = "CREATE TABLE " + tableName + " (id "
                    + "INTEGER, question TEXT, a TEXT, b TEXT, c TEXT, d TEXT, "
                    + "right TEXT, tags TEXT);";

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

    public static void storeValues(List<Question> allQuestions, String dataBasePath, String tableName) {
        int size = allQuestions.size();

        urlSQLite = "jdbc:sqlite:" + dataBasePath.replace("\\", "/");

        Connection connection = null;
        PreparedStatement prStmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(urlSQLite);

            String prsql = "INSERT INTO " + tableName + " (id, question, a, b, c, d, right, tags) VALUES"
                    + " (?, ?, ?, ?, ?, ?, ?, null)";

            prStmt = connection.prepareStatement(prsql);

            Question question;
            for (int i = 0; i < size; i++) {
                question = allQuestions.get(i);

                prStmt.setInt(1, question.getId());
                prStmt.setString(2, question.getQuestionPart());
                prStmt.setString(3, question.getOptionA());
                prStmt.setString(4, question.getOptionB());
                prStmt.setString(5, question.getOptionC());
                prStmt.setString(6, question.getOptionD());
                prStmt.setString(7, question.getRightAns());

                prStmt.addBatch();
            }
            prStmt.executeBatch();

            prStmt.close();
            connection.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (ClassNotFoundException notFoundExp) {
            notFoundExp.printStackTrace();
        } finally {

            /*
            try {
                if (prStmt != null) {
                    prStmt.close();
                } 
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
             */
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }

    }

    /**
     * The method takes a path name where data base is, and counts a number of
     * rows in the data base.
     *
     * @param dataBasePath path of a data base
     * @return number of rows in a data base
     */
    public static int getNumberOfRowsInTable(String dataBasePath) {

        // let's find the dataBaseName based on knowledge that path has pattern "....\\....db"
        // first let's reverse the string 
        String reversedStr = new StringBuffer(dataBasePath).reverse().toString();

        // and slice fragment at the end of string between "\\" and ".db"
        String dBName = new StringBuffer(reversedStr.substring(3, reversedStr.indexOf("\\"))).reverse().toString();

        // the method is based on convention that the table name is toLowerCase from DataBase name
        String tableName = dBName.toLowerCase();

        String urlSQLite = "jdbc:sqlite:" + dataBasePath.replace("\\", "/");

        int numOfRows = 0;

        Connection connection = null;
        Statement stmt;
        ResultSet rs;

        try {
            // load driver
            Class.forName("org.sqlite.JDBC");

            // establish connection
            connection = DriverManager.getConnection(urlSQLite);

            stmt = connection.createStatement();

            // the sql statement counts a number of rows in a table
            String sql = "SELECT COUNT(*) FROM " + tableName;
            rs = stmt.executeQuery(sql);

            rs.next();
            numOfRows = rs.getInt(1);

            rs.close();
            stmt.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return numOfRows;

    }

    public static List<Question> readDB(String dataBasePath) {
        // let's find the dataBaseName based on knowledge that path has pattern "....\\....db"
        // first let's reverse the string 
        String reversedStr = new StringBuffer(dataBasePath).reverse().toString();

        // and slice fragment at the end of string between "\\" and ".db"
        String dBName = new StringBuffer(reversedStr.substring(3, reversedStr.indexOf("\\"))).reverse().toString();

        // the method is based on convention that the table name is toLowerCase from DataBase name
        String tableName = dBName.toLowerCase();

        List<Question> allQuestions = new ArrayList<>();

        int rowsNum = getNumberOfRowsInTable(dataBasePath);

        String urlSQLite = "jdbc:sqlite:" + dataBasePath.replace("\\", "/");

        Connection connection;
        PreparedStatement prepStmt = null;
        ResultSet queryResult;

        try {
            // load driver
            Class.forName("org.sqlite.JDBC");

            // establish connection
            connection = DriverManager.getConnection(urlSQLite);

            // set up to false Auto Commit to commit a 
            // multiple sql statement manually
            connection.setAutoCommit(false);

            prepStmt = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?");

            for (int i = 1; i <= rowsNum; i++) {
                prepStmt.setInt(1, i);
                queryResult = prepStmt.executeQuery();

                while (queryResult.next()) {
                    Question question = new Question();

                    int id = queryResult.getInt("id");
                    question.setId(id);

                    String questionText = queryResult.getString("java/question");
                    question.setQuestionPart(questionText);

                    String aOption = queryResult.getString("a");
                    question.setOptionA(aOption);

                    String bOption = queryResult.getString("b");
                    question.setOptionB(bOption);

                    String cOption = queryResult.getString("c");
                    question.setOptionC(cOption);

                    String dOption = queryResult.getString("d");
                    question.setOptionD(dOption);

                    String right = queryResult.getString("right");
                    question.setRightAns(right);

                    String tags = queryResult.getString("tags");
                    question.setTags(tags);

                    allQuestions.add(question);

                }

                connection.commit();

            } // end of loop

        } catch (Exception e) {
            // Handle errors for class.forName
            e.printStackTrace();
        } finally {
            try {
                if (prepStmt != null) {
                    prepStmt.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return allQuestions;

    }

}

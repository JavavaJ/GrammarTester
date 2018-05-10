package testmaker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import read.Question;

public class DataBasesUtil {
    private static String urlSQLite;
    private static Connection connectionGlob;
    private static Statement stmt;
    
    public static void main(String[] args) {
        String path = "C:\\sqlite\\founders.db";
        String pathFormatted = path.replace("\\", "/");
        System.out.println(pathFormatted);
    }
    
    
    /** The method creates a database file and a SQL table in it. 
     *
     * @param dataBasePath (path to .db file where to create a table)
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
           
            
            String sql2 = "CREATE TABLE " + tableName + " (id " +
            "INTEGER, question TEXT, a TEXT, b TEXT, c TEXT, d TEXT, "
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
    
    
}
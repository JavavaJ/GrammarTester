package java.database;

import java.config.PropertiesLoader;
import java.question.Question;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLReader {
    private String urlSQLite;
    private Driver driverSQLite;
    private Connection connection;
    private Statement stmt;
    private PreparedStatement prepStmt;
    
    private String dataBasePath;
    private String tableName;
    
    
    public SQLReader() {
        urlSQLite = "jdbc:sqlite:C:/sqlite/TEST7.db";
    }
    
    public SQLReader(String dataBasePath, String tableName) {
        super();
        this.dataBasePath = dataBasePath;
        this.tableName = tableName;
        
        urlSQLite = "jdbc:sqlite:" + dataBasePath.replace("\\", "/");
        
    }
    
    
    /** The method makes a single query from java.database based on idRow
     * and returns a HashMap of values (id, java.question, a, b, c, d, right).
     * 
     * @param int idRow
     * @return HashMap<String, String>
     */
    public Map<String, String> makeQuery(int idRow) {
        Map<String, String> queryValues = new HashMap<>();     
        
        try {
            // load driver
            Class.forName("org.sqlite.JDBC");

            // establish connection
            connection = DriverManager.getConnection(urlSQLite);

            // set up to false Auto Commit to commit a 
            // multiple sql statement manually
            connection.setAutoCommit(false);
            

            prepStmt = null;
            prepStmt = connection.prepareStatement("SELECT * FROM test7 WHERE id = ?");
            prepStmt.setInt(1, idRow);

            ResultSet queryResult = prepStmt.executeQuery();

            while(queryResult.next()) {
                // get the resuts of a query and put them into hashmap
                int id = queryResult.getInt("id");

                // integer id should be converted to String type              ;
                queryValues.put("id", Integer.toString(id));

                String question = queryResult.getString("java/question");
                queryValues.put("java/question", question);
                String a = queryResult.getString("a");
                queryValues.put("a", a);
                String b = queryResult.getString("b");
                queryValues.put("b", b);
                String c = queryResult.getString("c");
                queryValues.put("c", c);
                String d = queryResult.getString("d");
                queryValues.put("d", d);        
                String right = queryResult.getString("right");
                queryValues.put("right", right);
                          
            }

            connection.commit();
            prepStmt.close();

            System.out.println("Query executed successfully");

            } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (prepStmt != null) {
                    prepStmt.close();
                } 
            } catch (SQLException se2) {
                    // nothing we can do
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                   se.printStackTrace();
            } // end of final try
            
        } // end of finally        
    return queryValues;  
    } // end of makeQuery method
    
    
    /** The method makes multiple queries from java.database using
     * single connection and thus is resource-efficient. It reads row in a 
     * java.database starting from row fromId and to row toId. The method returns an
     * arraylist of Question objects.
     * 
     * @param fromId
     * @param toId
     * @return 
     */
    public List<Question> makeQuery(int fromId, int toId) {
        List<Question> allQuestions = new ArrayList<>();        
        
        Connection connection;
        PreparedStatement prepStmt = null;
        ResultSet queryResult;
        
        try {
            // load driver
            Class.forName("org.sqlite.JDBC");

            // establish connection
            connection = DriverManager.getConnection(urlSQLite);

            // set up to false Auto Commit to commit a multiple sql statement manually
            connection.setAutoCommit(false);

            prepStmt = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?");
            
            for (int i = fromId; i <= toId; i++) {
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
    
    
    /** The method takes filePath and tableName and return 
     * a number of rows in a table.
     * 
     * @param filePathURLFormat path to directory of java.database
     * @param tableName name of a table in a java.database
     * @return 
     */
    public int getNumberOfRowsInTable(String filePathURLFormat, String tableName) {
        int numOfRows = 0;
        
        String urlSQLite = filePathURLFormat;
        Connection connection = null;
        Statement stmt;
        ResultSet rs;
        
        try {
            // load driver
            Class.forName(PropertiesLoader.getProperties().getProperty("dBDriver"));

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
    
    
    
    /** The method returns 
     * a number of rows in a table based on filePath and tableName given to 
     * SQLReader constructor
     * 
     * @return int number of rows in a table 
     */
    public int getNumberOfRowsInTable() {
        int numOfRows = 0;
        
        Connection connection = null;
        Statement stmt;
        ResultSet rs;
        
        try {
            // load driver
            Class.forName(PropertiesLoader.getProperties().getProperty("dBDriver"));

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
    
       

} // end of class
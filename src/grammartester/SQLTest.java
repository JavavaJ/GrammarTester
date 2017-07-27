package grammartester;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

class SQLTest {
    private String urlSQLite;
    private Driver driverSQLite;
    private Connection connection;
    private Statement stmt;
    
    public HashMap<String, String> makeQuery(int idRow) {
        HashMap<String, String> queryValues = new HashMap<>();
        
        urlSQLite = "jdbc:sqlite:C:/sqlite/GrammarTestSample.db";
        
        try {
            // load driver
            Class.forName("org.sqlite.JDBC");

            // establish connection
            connection = DriverManager.getConnection(urlSQLite);

            // set up to false Auto Commit to commit a 
            // multiple sql statement manually
            connection.setAutoCommit(false);

            // create sql statement
            // String sql = "SELECT * FROM GrammarTestSample WHERE id = " + idRow;

            PreparedStatement prepStmt = null;
            prepStmt = connection.prepareStatement("SELECT * FROM GrammarTestSample WHERE id = ?");
            prepStmt.setInt(1, idRow);

            ResultSet queryResult = prepStmt.executeQuery();

            while(queryResult.next()) {
                // get the resuts of a query and put them into hashmap
                int id = queryResult.getInt("id");

                // integer id should be converted to String type              ;
                queryValues.put("id", Integer.toString(id));

                String question = queryResult.getString("question");
                queryValues.put("question", question);
                String a = queryResult.getString("a");
                queryValues.put("a", a);
                String b = queryResult.getString("b");
                queryValues.put("b", b);
                String c = queryResult.getString("c");
                queryValues.put("c", c);
                String d = queryResult.getString("d");
                queryValues.put("d", d);
                String key = queryResult.getString("key");
                queryValues.put("key", key);
                          
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
                if (stmt != null) {
                    stmt.close();
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
    
    public static void main(String[] args) {
        SQLTest test = new SQLTest();
        test.makeQuery(1);
    }

} // end of class
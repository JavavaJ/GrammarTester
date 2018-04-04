package grammartester;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

class SQLWriter {
    private String dBName;
    private String tableName;
    private String urlSQLite;
    private Connection connection;
    private Statement stmt;

    public SQLWriter() {
        // TODO delete this constructor with the app is made fully OOP
        dBName = "TEST7";
        tableName = "test7";
    }
    
    public SQLWriter(String dBName, String tableName) {
        this.dBName = dBName;
        this.tableName = tableName;
    }
    
    
    
    public void storeValues(int id, 
                            String questionPart, 
                            String optionA, 
                            String optionB, 
                            String optionC, 
                            String optionD) {
        urlSQLite = "jdbc:sqlite:C:/sqlite/TEST7.db";
        
        try {
            
            // load driver
            Class.forName("org.sqlite.JDBC");

            // establish connection
            connection = DriverManager.getConnection(urlSQLite);

            stmt = connection.createStatement();

            String sql1 = "INSERT INTO test7 (id, question, a, b, c, d) VALUES"
                    + " (" + id + ", '" + questionPart + "', '" + optionA 
                    + "', '" + optionB + "', '" + optionC + "', '" + optionD + "')";
            
            stmt.executeUpdate(sql1);
            
            stmt.close();
            connection.close();

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
                
    } // end of storeValues                      
} // end of class
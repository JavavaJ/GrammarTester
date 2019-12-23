/* The code updates the newly added column "right" answers 
*/

package java.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLUpdateColumn {
    private String urlSQLite;
    private Connection connection;
    private Statement stmt;

    public void updateColumn(int id, String right) {

        urlSQLite = "jdbc:sqlite:C:/sqlite/TEST7.db";

        try {
            // load driver
            Class.forName("org.sqlite.JDBC");

            // establish connection
            connection = DriverManager.getConnection(urlSQLite);

            stmt = connection.createStatement();

            // create SQL statement
            String sql = "UPDATE test7 SET right = '" 
                        + right + "' WHERE id = " + id;

            stmt.executeUpdate(sql);

            System.out.println("UPDATE executed successfully!");
            
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
    } // end of updateColumn
} // end of class
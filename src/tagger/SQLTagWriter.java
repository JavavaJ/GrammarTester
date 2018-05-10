/** The class writes tags to SQL database. 
 * 
 */

package tagger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class SQLTagWriter {
    private static String databasePath;
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement prepStmt;
    
    public static void writeTags(TagType[] tagsArray) {
        databasePath = "jdbc:sqlite:C:/sqlite/TEST7.db";
        
        try {
            // load driver
            Class.forName("org.sqlite.JDBC");
            
            // establish connection
            connection = DriverManager.getConnection(databasePath);
            
            String sqlCommand = "UPDATE test7 SET tags = ? WHERE id = ?";
            
            prepStmt = connection.prepareStatement(sqlCommand);            
            
            for (int i = 0; i < tagsArray.length; i++) {
                prepStmt.setString(1, tagsArray[i].getTag());
                prepStmt.setInt(2, i + 1);
                prepStmt.addBatch();
                System.out.println("Tag #: " + (i+1) + " is added to the Batch.");                
            }
            
            prepStmt.executeBatch();
            System.out.println("The Batch is executed!");
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (prepStmt != null) {
                    prepStmt.close();
                }
                
            }  catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
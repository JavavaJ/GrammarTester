package tagger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BatchTest {
    private String DB_file_path;
    private Connection connection;
    private PreparedStatement prepState;
    
    public static void main(String[] args) {
        BatchTest batchTest = new BatchTest();
        batchTest.writeColumn();
    }
    
    public void writeColumn() {
        DB_file_path = "jdbc:sqlite:C:/sqlite/TEST7.db";
        
        try {
            // load driver
            Class.forName("org.sqlite.JDBC");
            
            connection = DriverManager.getConnection(DB_file_path);
            
            String sqlCommand = "UPDATE test7 SET tags = ? WHERE id = ?";
            
            prepState = connection.prepareStatement(sqlCommand);
            
            prepState.setString(1, "five");
            prepState.setInt(2, 5);
            prepState.addBatch();
            
            prepState.setString(1, "six");
            prepState.setInt(2, 6);
            prepState.addBatch();
            
            prepState.setString(1, "seven");
            prepState.setInt(2, 7);
            prepState.addBatch();
            
            prepState.setString(1, "eight");
            prepState.setInt(2, 8);
            prepState.addBatch();
            
            prepState.executeBatch();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (prepState != null) {
                    prepState.close();
                }
            } catch (Exception e) {
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
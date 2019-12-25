/** The class has a method createDB which takes two parameters dataBaseName and
 * tableName and creates a new java.database.
 */

package logic.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseMaker {
    private static String urlSQLite;
    private static Connection connection;
    private static Statement stmt;


    /** The method creates a database file and a SQL table in it.
     *
     * @param dataBaseName (is expected to be capitalized)
     * @param tableName (is expected to be in lower case)
     */
    public static void createDB(String dataBaseName, String tableName) {

        urlSQLite = "jdbc:sqlite:C:/sqlite/" + dataBaseName + ".db";

        try {
            // load driver
            Class.forName("org.sqlite.JDBC");

            // establish connection
            connection = DriverManager.getConnection(urlSQLite);

            stmt = connection.createStatement();
           
            
            String sql2 = "CREATE TABLE " + tableName + " (id " +
            "INTEGER, question TEXT, a TEXT, b TEXT, c TEXT, d TEXT, "
            + "right TEXT, tags TEXT);";

            stmt.executeUpdate(sql2);

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
    } // end of createDB

} // end of class
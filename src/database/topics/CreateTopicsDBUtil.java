package database.topics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTopicsDBUtil {

    public static void main(String[] args) {
        String dBPath = "resources/ALL_ELEM.db";
        createTopicTable(dBPath);
    }

    public static void createTopicTable(String dataBasePath) {
        String urlSQLite = "jdbc:sqlite:" + dataBasePath.replace("\\", "/");

        try {
            // load driver
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(urlSQLite);
            Statement statement = connection.createStatement();

            String sql = "CREATE TABLE topics (level TEXT, topic_full TEXT, topic_tag TEXT);";
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}

package database.topics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TopicsDBUtil {

    public static void main(String[] args) {
        String dBPath = "resources/ALL_ELEM.db";
        System.out.println("Preparing to create topics table ...");
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
            System.out.println("Topics table is created ... ");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void writeEnumsToTopicsDb() {
        TopicsFactory topicsFactory = new TopicsFactory();
        List<TopicEntity> topics = topicsFactory.getTopicsFromEnums();

        String dBPath = "resources/ALL_ELEM.db";
        String urlSQLite = "jdbc:sqlite:" + dBPath.replace("\\", "/");

        // load driver
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(urlSQLite);

            for (TopicEntity topic : topics) {
                String level = topic.getLevel();
                String topicFull = topic.getTopicFull();
                String topicTag = topic.getTopicTag();
            }

            String sql = "INSERT INTO topics (level, topic_full, topic_tag) VALUES ('one', 'two', 'three')";
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

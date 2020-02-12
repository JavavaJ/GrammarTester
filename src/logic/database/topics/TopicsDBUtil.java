package logic.database.topics;

import logic.config.PropertiesLoader;
import logic.question.Question;

import java.sql.*;
import java.util.List;
import java.util.Properties;

public class TopicsDBUtil {

    public static void main(String[] args) {
        String dBPath = "resources/ALL_ELEM.db";
        System.out.println("Preparing to create topics table ...");
        createTopicTable(dBPath);
        writeEnumsToTopicsDb();
    }

    public static void createTopicTable(String dataBasePath) {
        String urlSQLite = "jdbc:sqlite:" + dataBasePath.replace("\\", "/");

        try {
            // load driver
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(urlSQLite);
            Statement statement = connection.createStatement();

            String sql = "CREATE TABLE topics (id INTEGER, level TEXT, topic_full TEXT, topic_tag TEXT);";
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
            String prepSql = "INSERT INTO topics (id, level, topic_full, topic_tag) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(prepSql);

            int id = 1;
            for (TopicEntity topic : topics) {
                String level = topic.getLevel();
                String topicFull = topic.getTopicFull();
                String topicTag = topic.getTopicTag();

                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, level);
                preparedStatement.setString(3, topicFull);
                preparedStatement.setString(4, topicTag);

                preparedStatement.addBatch();
                id++;
            }

            preparedStatement.executeBatch();
            preparedStatement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void writeSingleTopic(TopicEntity topic) {
        Properties properties = PropertiesLoader.getProperties();
        String dbPath = properties.getProperty("allElemDbPath");
        String urlSQLite = "jdbc:sqlite:" + dbPath.replace("\\", "/");
        String topicsTableName = properties.getProperty("topicsTableName");

        Connection connection = null;
        PreparedStatement prStmt;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(urlSQLite);

            String sql = "insert into " + topicsTableName + " (id, level, topic_full, topic_tag) values ((select MAX(id) from topics) + 1, ?, ?, ?)";
            prStmt = connection.prepareStatement(sql);

            prStmt.setString(1, topic.getLevel());
            prStmt.setString(2, topic.getTopicFull());
            prStmt.setString(3, topic.getTopicTag());

            prStmt.addBatch();
            prStmt.executeBatch();
            prStmt.close();
            connection.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (ClassNotFoundException notFoundExp) {
            notFoundExp.printStackTrace();
        } finally {

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

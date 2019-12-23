package logic.database.topics;

import logic.database.SQLReader;
import logic.config.PropertiesLoader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TopicGather {

    private static final List<TopicEntity> topics = initTopics();

    public static List<TopicEntity> getTopics() {
        return topics;
    }

    private static List<TopicEntity> initTopics() {

        Properties properties = PropertiesLoader.getProperties();
        String dataBasePath = properties.getProperty("allElemDbPath");
        String tableName = properties.getProperty("topicsTableName");
        String urlSQLite =  properties.getProperty("dDPrefix") + dataBasePath;

        List<TopicEntity> topics = new ArrayList<>();

        String dBDriver = properties.getProperty("dBDriver");

        int numOfRows = new SQLReader().getNumberOfRowsInTable(urlSQLite, tableName);

        // load driver
        try {
            Class.forName(dBDriver);
            Connection connection = DriverManager.getConnection(urlSQLite);

            // set up to false Auto Commit to commit a multiple sql statement manually
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
            // todo introduce id into table
            // todo write topics to db
            // todo run db test
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            for (int i = 1; i <= numOfRows; i++) {
                preparedStatement.setInt(1, i);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    TopicEntity topicEntity = new TopicEntity();

                    String level = resultSet.getString("level");
                    String topicFull = resultSet.getString("topic_full");
                    String topicTag = resultSet.getString("topic_tag");

                    topicEntity.setLevel(level);
                    topicEntity.setTopicFull(topicFull);
                    topicEntity.setTopicTag(topicTag);

                    topics.add(topicEntity);
                }
                connection.commit();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topics;
    }

}

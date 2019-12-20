package database.topics;

import config.PropertiesLoader;
import database.SQLReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TopicGather {

    private static final List<TopicEntity> topics = initTopics();

    private static List<TopicEntity> initTopics() {

        Properties properties = PropertiesLoader.getProperties();
        String dataBasePath = properties.getProperty("allElemDbPath");
        String tableName = properties.getProperty("topicsTableName");
        String urlSQLite =  properties.getProperty("dDPrefix") + dataBasePath;

        List<TopicEntity> topics = new ArrayList<>();

        String dBDriver = properties.getProperty("dBDriver");

        SQLReader sqlReader = new SQLReader();
        int numOfRows = sqlReader.getNumberOfRowsInTable(urlSQLite, tableName);

        // load driver
        try {
            Class.forName(dBDriver);
            Connection connection = DriverManager.getConnection(urlSQLite);

            // set up to false Auto Commit to commit a multiple sql statement manually
            connection.setAutoCommit(false);

            String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            for (int i = 1; i < numOfRows+1; i++) {
                preparedStatement.setInt(1, i);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    TopicEntity topicEntity = new TopicEntity();
                    // todo continue form here
                }

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topics;
    }

}

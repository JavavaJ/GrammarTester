package config;

import java.util.Properties;

public class PropertiesTest {

    public static void main(String[] args) {
        Properties properties = PropertiesLoader.getProperties();

        String allElemDbPath = properties.getProperty("allElemDbPath");
        System.out.println("allElemDbPath: " + allElemDbPath);

        String dBDrivers = properties.getProperty("dBDriver");
        System.out.println("dBDriver: " + dBDrivers);

        String allElemTableName = properties.getProperty("allElemTableName");
        System.out.println("allElemTableName: " + allElemTableName);

        String topicsTableName = properties.getProperty("topicsTableName");
        System.out.println("topicsTableName: " + topicsTableName);
    }

}

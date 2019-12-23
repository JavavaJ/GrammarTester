package java.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

    private static Properties properties;

    static {
        String rootPath = Thread.currentThread()
                .getContextClassLoader()
                .getResource("")
                .getPath();
        String appConfigPath = rootPath + "app.properties";
        properties = new Properties();
        try {
            properties.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getProperties() {
        return properties;
    }

}

package org.example.props;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    public static String getConnectionUrl() {
        try (InputStream input = PropertyReader.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return null;
            }
            prop.load(input);
            return new StringBuilder("jdbc:")
                    .append(prop.getProperty("h2.db.host"))
                    .append(":./")
                    .append(prop.getProperty("h2.db.database"))
                    .toString();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

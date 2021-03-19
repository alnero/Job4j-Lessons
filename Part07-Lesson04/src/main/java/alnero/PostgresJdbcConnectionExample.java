package alnero;

import alnero.readFile.Config;
import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresJdbcConnectionExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        File f = new File(PostgresJdbcConnectionExample.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        Config propertiesFile = new Config(f.getPath() + "/app.properties");
        propertiesFile.load();
        try (Connection connection = DriverManager.getConnection(
                propertiesFile.value("url"),
                propertiesFile.value("username"),
                propertiesFile.value("password"))) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}

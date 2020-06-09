package alnero.readFile;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNull;

/**
 * Test reading of config file.
 */
public class ConfigTest {
    @Test
    public void whenLoadPropertiesThenKeyValuesAreCorrect() {
        String path = "src/test/resources/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
        assertThat(config.value("hibernate.connection.url"), is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
        assertThat(config.value("hibernate.connection.driver_class"), is("org.postgresql.Driver"));
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
        assertThat(config.value("hibernate.connection.password"), is("password"));
    }

    @Test
    public void whenLoadPropertiesThenNoCommentsAndEmptyStrings() {
        String path = "src/test/resources/app.properties";
        Config config = new Config(path);
        config.load();
        assertNull(config.value("## PostgreSQL"));
        assertNull(config.value(""));
    }
}

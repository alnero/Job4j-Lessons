package alnero.readFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.HashMap;
import java.util.StringJoiner;

/**
 * Configuration file reader.
 */
public class Config {
    /** Path to properties file. **/
    private final String path;
    /** Properties map. **/
    private final Map<String, String> values = new HashMap<String, String>();

    /**
     * Initialize path to the config file.
     * @param path path to config file
     */
    public Config(final String path) {
        this.path = path;
    }

    /**
     * Read config file and populate key-value map with config properties.
     */
    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(str -> !"".equals(str) && !str.startsWith("##"))
                    .map(str -> str.split("="))
                    .forEach(prop -> values.put(prop[0], prop[1])
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get config values according to supplied key.
     * @param key config property key
     * @return config property value
     */
    public String value(String key) {
        return this.values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}

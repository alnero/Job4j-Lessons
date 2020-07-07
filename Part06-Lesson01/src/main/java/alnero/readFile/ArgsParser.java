package alnero.readFile;

import java.util.HashMap;
import java.util.Map;

public class ArgsParser {
    /** Arguments key-value map. */
    private final Map<String, String> values = new HashMap<>();

    /**
     * Get value of argument by key. If key not exists in map then throw exception.
     * @param key key
     * @return value
     */
    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("No argument found.");
        }
        return values.get(key);
    }

    /**
     * Convert array of arguments to map of key-values.
     * @param args array of arguments
     */
    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments array is empty.");
        }
        for (String pair : args) {
            String[] splitArg = pair.substring(1).split("=");
            values.put(splitArg[0], splitArg[1]);
        }
    }

    /**
     * Get parser object based on array of arguments.
     * @param args array of arguments
     * @return parser object
     */
    public static ArgsParser of(String[] args) {
        ArgsParser names = new ArgsParser();
        names.parse(args);
        return names;
    }
}

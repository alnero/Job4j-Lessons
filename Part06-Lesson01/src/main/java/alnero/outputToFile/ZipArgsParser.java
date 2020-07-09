package alnero.outputToFile;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Parser for zip archiver arguments.
 */
public class ZipArgsParser {
    /** Arguments key-value map. */
    private final Map<String, String> values = new HashMap<>();
    /** Flag for valid arguments. */
    private final boolean isValid;

    /**
     * Constructor checks supplied arguments.
     *  - number of arguments is three
     *  - format of arguments key=value is preserved
     *  - arguments keys are only d, e, o
     *  - no null or empty values in arguments
     *  - path to source folder is valid
     *  - name of destination file ends in .zip
     * @param args archiver string arguments
     */
    public ZipArgsParser(String[] args) {
        if (args.length != 3) {
            this.throwExceptionAboutIllegalArguments();
        }
        for (String pair : args) {
            String[] splitArg = pair.substring(1).split("=");
            if (splitArg.length == 2) {
                values.put(splitArg[0], splitArg[1]);
            }
        }
        if (values.keySet().size() != 3
                || !values.keySet().containsAll(Arrays.asList("d", "e", "o"))
                || values.containsValue(null)
                || values.containsValue("")) {
            this.throwExceptionAboutIllegalArguments();
        }
        try {
            Paths.get(this.directory());
        } catch (InvalidPathException e) {
            this.throwExceptionAboutIllegalArguments();
        }
        if (!this.output().endsWith(".zip")) {
            this.throwExceptionAboutIllegalArguments();
        }
        this.isValid = true;
    }

    public boolean valid() {
        return this.isValid;
    }

    public String directory() {
        return values.get("d");
    }

    public String exclude() {
        return values.get("e");
    }

    public String output() {
        return values.get("o");
    }

    private void throwExceptionAboutIllegalArguments() {
        throw new IllegalArgumentException("Arguments required, usage:java -cp Part06-Lesson01-1.0.0.jar alnero.outputToFile.ZipArchiver -d=PATH_TO_FOLDER -e=FILE_EXTENSION -o=OUTPUT_FILE");
    }
}

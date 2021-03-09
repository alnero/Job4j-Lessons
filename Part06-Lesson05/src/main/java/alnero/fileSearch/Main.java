package alnero.fileSearch;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;
import java.io.File;
import java.io.IOException;

public class Main {
    /** Search directory key. */
    private static final String SEARCH_DIRECTORY_KEY = "-d";
    /** Search type key. */
    private static final String SEARCH_TYPE_KEY = "-t";
    /** Search phrase key. */
    private static final String SEARCH_PHRASE_KEY = "-n";
    /** Output file name key. */
    private static final String OUTPUT_FILE_NAME_KEY = "-o";
    /** Search type names. */
    private static final String[] SEARCH_TYPE_NAMES = new String[]{"name", "mask", "regex"};

    /**
     * Search files by name, mask or regex in specified directory and sub-directories.
     * Mandatory arguments are supplied via CLI. Result is available as separate file.
     * @param args -d=search directory, -t=search type (name, mask, regex), -n=search pattern, -o=result file name
     */
    public static void main(String[] args) {
        try {
            ArgsParser argsParser = new ArgsParser();
            Map<String, String> searchArguments = argsParser.getSearchArgs(
                    args,
                    new String[]{SEARCH_DIRECTORY_KEY, SEARCH_TYPE_KEY, SEARCH_PHRASE_KEY, OUTPUT_FILE_NAME_KEY},
                    SEARCH_TYPE_NAMES,
                    SEARCH_TYPE_KEY);
            Finder finder = new Finder();
            List<File> result = finder.searchFiles(
                    searchArguments.get(SEARCH_DIRECTORY_KEY),
                    searchArguments.get(SEARCH_TYPE_KEY),
                    searchArguments.get(SEARCH_PHRASE_KEY));
            Saver saver = new Saver();
            saver.saveStringListToFile(
                    result.stream().map(File::toString).collect(Collectors.toList()),
                    new File(searchArguments.get(OUTPUT_FILE_NAME_KEY)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

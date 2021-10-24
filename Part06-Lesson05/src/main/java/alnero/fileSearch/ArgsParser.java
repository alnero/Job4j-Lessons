package alnero.fileSearch;

import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class ArgsParser {
    /**
     * Parsing of required arguments.
     * 1. Args to have only one separator symbol "=".
     * 2. Only predefined keys are allowed.
     * 3. Only predefined search types are allowed.
     * @param args CLI supplied arguments in key=value format
     * @param keys required keys
     * @param searchTypeNames predefined search type names
     * @param searchTypeKey predefined search type key
     * @return map of key=values derived from supplied arguments
     */
    public Map<String, String> getSearchArgs(String[] args, String[] keys, String[] searchTypeNames, String searchTypeKey) {
        if (args.length != 4) {
            printHelpThrowIllegalArgumentException();
        }
        Map<String, String> searchArguments = new HashMap<>();
        for (String argument : args) {
            if (!argument.contains("=") || argument.indexOf("=", argument.indexOf("=") + 1) > -1) {
                printHelpThrowIllegalArgumentException();
            }
            String[] keyValue = argument.split("=");
            String key = keyValue[0];
            if (Arrays.stream(keys).noneMatch(key::matches)) {
                printHelpThrowIllegalArgumentException();
            }
            String value = keyValue[1];
            if (searchTypeKey.equals(key) && Arrays.stream(searchTypeNames).noneMatch(value::matches)) {
                printHelpThrowIllegalArgumentException();
            }
            searchArguments.put(key, value);
        }
        return searchArguments;
    }

    /**
     * Help is printed and exception is thrown if there are mistakes during parsing of supplied arguments.
     */
    public static void printHelpThrowIllegalArgumentException() {
        System.out.println(
                "Usage: java -jar fileSearch.jar [OPTION]...\n"
                        + "Search files by name, mask or regex in specified directory and sub-directories.\n"
                        + "Search results are available as separate file.\n\n"
                        + "Example: java -jar fileSearch.jar -d=c:/ -t=mask -n=*.txt -o=result.txt\n\n"
                        + "All arguments are mandatory:\n"
                        + "-d\t\tsearch directory\n"
                        + "-t\t\tsearch type: name, mask, regex\n"
                        + "-n\t\tfile name, mask or regular expression\n"
                        + "-o\t\tresult file name\n");
        throw new IllegalArgumentException();
    }
}

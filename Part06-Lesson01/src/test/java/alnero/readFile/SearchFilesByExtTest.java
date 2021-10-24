package alnero.readFile;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test search files.
 */
public class SearchFilesByExtTest {
    @Test
    public void whenSearchForTxtFilesThenCorrectFilesReturned() throws IOException {
        SearchFilesByExt searchFiles = new SearchFilesByExt();
        Path path = Paths.get("src/test/resources");
        List<Path> pathsToTxtFiles = searchFiles.search(path, "txt");
        List<String> result = pathsToTxtFiles.stream().map(Path::getFileName).map(Path::toString).collect(Collectors.toList());
        List<String> expected = Arrays.asList("numbers.txt", "line_separator.txt", "lorem_ipsum.txt", "one_sentense.txt", "log.txt", "empty_file.txt");
        MatcherAssert.assertThat(result, Matchers.containsInAnyOrder(expected.toArray()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoArgumentsThenThrowIllegalArgumentException() throws IOException {
        SearchFilesByExt.main(new String[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenOnlyOneArgumentThenThrowIllegalArgumentException() throws IOException {
        SearchFilesByExt.main(new String[]{"src/test/resources"});
    }

    @Test
    public void whenNoArgumentsThenThrowAndMessageHowToUseProgramFromCommandLine() {
        String result = "";
        try {
            SearchFilesByExt.main(new String[]{});
        } catch (Exception e) {
            result = e.getMessage();
        }
        assertThat(result, is("Arguments required, usage: java -cp Part06-Lesson01-1.0.0.jar alnero.readFile.SearchFilesByExt PATH_TO_SEARCH_FOLDER FILE_EXTENSION"));
    }

    @Test
    public void whenOnlyOneArgumentThenThrowAndMessageHowToUseProgramFromCommandLine() {
        String result = "";
        try {
            SearchFilesByExt.main(new String[]{"src/test/resources"});
        } catch (Exception e) {
            result = e.getMessage();
        }
        assertThat(result, is("Arguments required, usage: java -cp Part06-Lesson01-1.0.0.jar alnero.readFile.SearchFilesByExt PATH_TO_SEARCH_FOLDER FILE_EXTENSION"));
    }

    /**
     * Test redirects console input and then restores console input.
     */
    @Test
    public void whenTwoCorrectArgumentThenProperResultsReturned() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream mockOut = new PrintStream(byteArrayOutputStream);
        PrintStream systemOut = System.out;
        System.setOut(mockOut);

        SearchFilesByExt.main(new String[]{"src/test/resources", "txt"});
        List<String> result = Arrays.asList(byteArrayOutputStream.toString().split(System.lineSeparator()));
        List<String> expected = Arrays.asList(
                "src/test/resources/numbers.txt",
                "src/test/resources/line_separator.txt",
                "src/test/resources/lorem_ipsum.txt",
                "src/test/resources/one_sentense.txt",
                "src/test/resources/log.txt",
                "src/test/resources/empty_file.txt");
        MatcherAssert.assertThat(result, Matchers.containsInAnyOrder(expected.toArray()));

        System.out.flush();
        System.setOut(systemOut);
    }
}

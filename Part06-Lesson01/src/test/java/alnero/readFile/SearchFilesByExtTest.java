package alnero.readFile;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

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
        List<String> expected = Arrays.asList("numbers.txt", "log.txt");
        assertThat(result, is(expected));
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

    @Test
    public void whenTwoCorrectArgumentThenProperResultsReturned() throws IOException {
        // redirect console output for test
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream mockOut = new PrintStream(byteArrayOutputStream);
        PrintStream systemOut = System.out;
        System.setOut(mockOut);
        // search txt files
        SearchFilesByExt.main(new String[]{"src/test/resources", "txt"});
        assertThat(byteArrayOutputStream.toString(), is(
                "src/test/resources/numbers.txt"  + System.lineSeparator()
                    + "src/test/resources/log.txt" + System.lineSeparator()
        ));
        // restore console output
        System.out.flush();
        System.setOut(systemOut);
    }
}

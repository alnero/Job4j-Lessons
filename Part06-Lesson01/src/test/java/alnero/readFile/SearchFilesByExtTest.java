package alnero.readFile;

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
}

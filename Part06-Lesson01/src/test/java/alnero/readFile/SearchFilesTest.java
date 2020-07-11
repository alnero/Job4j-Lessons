package alnero.readFile;

import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

/**
 * Test searching of files.
 */
public class SearchFilesTest {
    /** Common searcher for tests. */
    private SearchFiles searcher;
    /** Search directory. */
    private File searchDirectory;

    @Before
    public void beforeTest() {
        this.searcher = new SearchFiles();
        this.searchDirectory = new File("src/test/resources");
    }

    @Test
    public void whenSearchOneFileThenProperFileReturned() throws IOException {
        List<File> fileList = searcher.searchFiles(searchDirectory, file -> "log.txt".equals(file.getName()));
        List<String> result = fileList.stream().map(File::getName).collect(Collectors.toList());
        List<String> expected = Arrays.asList("log.txt");
        assertEquals(expected, result);
    }

    @Test
    public void whenSearchFilesByExtensionThenProperFilesReturned() throws IOException {
        String regExp = "*.txt".replace("*", ".*?");
        List<File> fileList = searcher.searchFiles(searchDirectory, file -> file.getName().matches(regExp));
        List<String> result = fileList.stream().map(File::getName).collect(Collectors.toList());
        List<String> expected = Arrays.asList("numbers.txt", "log.txt");
        assertEquals(expected, result);
    }

    @Test
    public void whenSearchFilesByNamePartThenProperFilesReturned() throws IOException {
        List<File> fileList = searcher.searchFiles(searchDirectory, file -> file.getName().contains("test"));
        List<String> result = fileList.stream().map(File::getName).collect(Collectors.toList());
        List<String> expected = Arrays.asList("test_file_2.test", "test_file_1.test");
        assertEquals(expected, result);
    }

    @Test
    public void whenSearchFilesByExtensionPartThenProperFilesReturned() throws IOException {
        String regExp = "app.prop*".replace("*", ".*?");
        List<File> fileList = searcher.searchFiles(searchDirectory, file -> file.getName().matches(regExp));
        List<String> result = fileList.stream().map(File::getName).collect(Collectors.toList());
        List<String> expected = Arrays.asList("app.properties");
        assertEquals(expected, result);
    }

    @Test
    public void whenSearchFilesByWildcardThenProperFilesReturned() throws IOException {
        String regExp = "test_file_?.test".replace("?", ".?");
        List<File> fileList = searcher.searchFiles(searchDirectory, file -> file.getName().matches(regExp));
        List<String> result = fileList.stream().map(File::getName).collect(Collectors.toList());
        List<String> expected = Arrays.asList("test_file_2.test", "test_file_1.test");
        assertEquals(expected, result);
    }

    @Test
    public void whenSearchAllFilesThenAllFilesReturned() throws IOException {
        String regExp = "*".replace("*", ".*?");
        List<File> fileList = searcher.searchFiles(searchDirectory, file -> file.getName().matches(regExp));
        List<String> result = fileList.stream().map(File::getName).collect(Collectors.toList());
        List<String> expected = Arrays.asList("numbers.txt", "test_file_2.test", "test_file_1.test", "log.txt", "server.log", "app.properties");
        assertEquals(expected, result);
    }
}

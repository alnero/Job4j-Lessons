package alnero.readFile;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Test listing of files from directory.
 */
public class ListDirTest {
    @Test
    public void whenListDirThenProperFileNamesAndSizesReturned() {
        ListDir listDir = new ListDir();
        List<String> result = listDir.listFiles("src/test/resources");
        List<String> expected = Arrays.asList("numbers.txt 7", "line_separator.txt 1", "Test_folder_1 4096", "lorem_ipsum.txt 3861", "one_sentense.txt 56", "chat.log 80", "log.txt 852", "server.log 180", "empty_file.txt 0", "app.properties 296");
        MatcherAssert.assertThat(result, Matchers.containsInAnyOrder(expected.toArray()));
    }
}

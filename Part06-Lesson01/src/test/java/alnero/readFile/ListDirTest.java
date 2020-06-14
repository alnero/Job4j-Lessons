package alnero.readFile;

import org.junit.Test;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test listing of files from directory.
 */
public class ListDirTest {
    @Test
    public void whenListDirThenProperFileNamesAndSizesReturned() {
        ListDir listDir = new ListDir();
        List<String> result = listDir.listFiles("src/test/resources");
        assertThat(result.toString(), is("[numbers.txt 7, log.txt 852, server.log 180, app.properties 296]"));
    }
}

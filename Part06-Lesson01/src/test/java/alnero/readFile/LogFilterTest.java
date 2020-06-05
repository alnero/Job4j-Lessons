package alnero.readFile;

import java.util.List;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test reading of strings from log file.
 */
public class LogFilterTest {
    @Test
    public void whenReadStringsFromLogFileThenOnlyStringsWith404Returned() {
        // redirect console output for test
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream mockOut = new PrintStream(byteArrayOutputStream);
        PrintStream systemOut = System.out;
        System.setOut(mockOut);
        // read file and test
        LogFilter logReader = new LogFilter();
        List<String> log = logReader.filter("src/test/resources/log.txt");
        for (String str : log) {
            System.out.println(str);
        }
        assertThat(byteArrayOutputStream.toString(), is(
        "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:18 +0300] \"GET /items/ajax.html HTTP/1.1\" 404 1113" + System.lineSeparator()
            + "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:23 +0300] \"GET /TrackStudio/ HTTP/1.1\" 404 1110"  + System.lineSeparator()
            + "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:34 +0300] \"GET /TrackStudioNew/ HTTP/1.1\" 404 -"  + System.lineSeparator()));
        // restore console output
        System.out.flush();
        System.setOut(systemOut);
    }
}

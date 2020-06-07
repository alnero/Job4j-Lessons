package alnero.readFile;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test reading and writing strings from log file.
 */
public class LogFilterTest {
    /**
     * Temp folder for test files.
     */
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

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
            + "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:34 +0300] \"GET /TrackStudioNew/ HTTP/1.1\" 404 -"  + System.lineSeparator()
        ));
        // restore console output
        System.out.flush();
        System.setOut(systemOut);
    }

    @Test
    public void whenWrite404StringsToFileThenProperFileWritten() throws IOException {
        LogFilter logReader = new LogFilter();
        List<String> log = logReader.filter("src/test/resources/log.txt");
        File file = folder.newFile("404.txt");
        logReader.save(log, file.getAbsolutePath());

        StringBuilder result = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String line = in.readLine();
            while (line != null) {
                result.append(line).append(System.lineSeparator());
                line = in.readLine();
            }
        }
        assertThat(result.toString(), is(
        "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:18 +0300] \"GET /items/ajax.html HTTP/1.1\" 404 1113" + System.lineSeparator()
            + "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:23 +0300] \"GET /TrackStudio/ HTTP/1.1\" 404 1110"  + System.lineSeparator()
            + "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:34 +0300] \"GET /TrackStudioNew/ HTTP/1.1\" 404 -"  + System.lineSeparator()
        ));
    }
}

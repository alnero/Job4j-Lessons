package alnero.readFile;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test analysis of server log file.
 */
public class ServerLogAnalyseTest {
    /**
     * Temp folder for test files.
     */
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenReadServerLog() throws IOException {
        String source = "src/test/resources/server.log";
        String target = folder.newFile("unavailable.csv").getAbsolutePath();
        ServerLogAnalyse logAnalyse = new ServerLogAnalyse();
        logAnalyse.unavailable(source, target);
        StringBuilder result = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            String line = in.readLine();
            while (line != null) {
                result.append(line).append(System.lineSeparator());
                line = in.readLine();
            }
        }
        assertThat(result.toString(), is(
            "10:57:01;10:59:01;" + System.lineSeparator()
                + "11:01:02;11:02:02;"  + System.lineSeparator()
                + "11:58:02;12:02:03;"  + System.lineSeparator()
        ));
    }
}

package alnero.outputToFile;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test writing of multiplication table to a file.
 */
public class MultiplicationTableToFileTest {
    /**
     * Temp folder for test files.
     */
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenOutputMultiplicationTableToFileThenProperFileWritten() throws IOException {
        File file = folder.newFile("multiplicationTable.txt");
        MultiplicationTableToFile table = new MultiplicationTableToFile();
        table.write(5, file);
        StringBuilder result = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String line = in.readLine();
            while (line != null) {
                result.append(line).append(System.lineSeparator());
                line = in.readLine();
            }
        }
        assertThat(result.toString(), is(
                "0  0  0  0  0  0  " + System.lineSeparator()
                    + "0  1  2  3  4  5  " + System.lineSeparator()
                    + "0  2  4  6  8  10 " + System.lineSeparator()
                    + "0  3  6  9  12 15 " + System.lineSeparator()
                    + "0  4  8  12 16 20 " + System.lineSeparator()
                    + "0  5  10 15 20 25 " + System.lineSeparator()));
    }
}

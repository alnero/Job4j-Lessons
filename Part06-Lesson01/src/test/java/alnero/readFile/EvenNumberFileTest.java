package alnero.readFile;

import org.junit.Test;
import java.io.File;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test reading of numbers from a file with redirection and restoring of console.
 */
public class EvenNumberFileTest {
    @Test
    public void whenReadNumbersFromFileThenCorrectInfoAboutEvenOrNot() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream mockOut = new PrintStream(byteArrayOutputStream);
        PrintStream systemOut = System.out;
        System.setOut(mockOut);

        String path = "src/test/resources/numbers.txt";
        File file = new File(path);
        EvenNumberFile numberFile = new EvenNumberFile();
        numberFile.read(file);
        assertThat(byteArrayOutputStream.toString(), is(
                "1 is NOT even" + System.lineSeparator()
                    + "2 is even"  + System.lineSeparator()
                    + "5 is NOT even"  + System.lineSeparator()
                    + "6 is even"  + System.lineSeparator()));

        System.out.flush();
        System.setOut(systemOut);
    }
}

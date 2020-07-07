package alnero.readFile;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test arguments parser.
 */
public class ArgsParserTest {
    @Test
    public void whenGetFirstArgumentThenProperValueReturned() {
        ArgsParser jvm = ArgsParser.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        assertThat(jvm.get("Xmx"), is("512"));
    }

    @Test
    public void whenGetFirstReorderedArgumentThenProperValueReturned() {
        ArgsParser jvm = ArgsParser.of(new String[] {"-encoding=UTF-8", "-Xmx=512"});
        assertThat(jvm.get("Xmx"), is("512"));
    }

    @Test
    public void whenGetAllArgumentsThenProperValuesReturned() {
        ArgsParser jvm = ArgsParser.of(new String[] {"-Xmx=512", "-encoding=UTF-8", "-out=project.zip"});
        assertThat(jvm.get("out"), is("project.zip"));
        assertThat(jvm.get("encoding"), is("UTF-8"));
        assertThat(jvm.get("Xmx"), is("512"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoSuchArgumentThenThrow() {
        ArgsParser jvm = ArgsParser.of(new String[] {"-out=project.zip"});
        jvm.get("Xmx");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenArgsArrayIsEmptyThenThrow() {
        ArgsParser jvm = ArgsParser.of(new String[] {});
    }
}

package alnero.outputToFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Formatter;

/**
 * Class to write/print multiplication table of required size to the file.
 */
public class MultiplicationTableToFile {
    /**
     * Write multiplication table to file.
     * @param tableSize size of multiplication table
     * @param file destination file
     */
    public void write(int tableSize, File file) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        // number of places for tabulation, log10 is used to find number of digits in integer
        int tabulationLength = (int) (Math.log10(tableSize * tableSize) + 2);
        String tabulationFormat = "%-" + tabulationLength + "d";
        for (int i = 0; i <= tableSize; i++) {
            for (int j = 0; j <= tableSize; j++) {
                formatter.format(tabulationFormat, i * j);
            }
            sb.append("\n");
        }
        this.writeDataToFile(sb.toString().getBytes(), file);
    }

    /**
     * Write byte data to file.
     * @param data array with byte data
     * @param file destination file
     */
    private void writeDataToFile(byte[] data, File file) {
        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

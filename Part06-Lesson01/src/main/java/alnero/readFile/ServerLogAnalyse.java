package alnero.readFile;

import java.util.Optional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

/**
 * Analyse server log for downtime.
 */
public class ServerLogAnalyse {
    /**
     * Check time periods when server was down according to the log.
     * @param source server log file
     * @param target downtime periods file
     */
    public void unavailable(String source, String target) {
        Optional<String> downTime = Optional.ofNullable(null);
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            String str = in.readLine();
            while (str != null) {
                if (downTime.isEmpty() && ((str.startsWith("400") || str.startsWith("500")))) {
                    downTime = Optional.of(str.split(" ")[1]);
                }
                if (downTime.isPresent() && ((str.startsWith("200") || str.startsWith("300")))) {
                    String upTime = str.split(" ")[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append(downTime.get()).append(";");
                    sb.append(upTime).append(";");
                    sb.append(System.lineSeparator());
                    out.write(sb.toString());
                    downTime = Optional.ofNullable(null);
                }
                str = in.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

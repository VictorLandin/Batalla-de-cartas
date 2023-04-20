package Model;

import java.io.*;
import java.text.*;
import java.util.*;

/**
 *
 * @author a22victorlr
 */
public class ExceptionLogger {

    private static final String txtLog = "exception_log.txt";
    private static final SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
/**
 * 
 * @param e 
 */
    public static void logException(Exception e) {
        try {
            File logFile = new File(txtLog);
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            try (FileWriter fw = new FileWriter(logFile, true); PrintWriter pw = new PrintWriter(fw)) {
                pw.println(formatoFecha.format(new Date()) + " " + e.toString());
                e.printStackTrace(pw);
                pw.println();
            }
        } catch (IOException ex) {
        }
    }
}

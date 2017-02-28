package uk.gov.dvsa.moti.helper;

import org.testng.Reporter;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Utilities {

    public static class Logger {

        /**
         * Logs a message in TestNG and Standard log<p>
         * <b>RETURNS:</b><p>
         * N/A
         */
        public static void LogInfo(String logText) {
            String logInfo = ("INFO: [" + logText + "]");
            //Output to TestNG log and Standard log
            Reporter.log(logInfo, true);
        }

        /**
         * Logs an error to TestNG and Standard log<p>
         * <b>RETURNS:</b><p>
         * N/A
         */
        public static void LogError(String logText) {
            String logErr = ("ERROR: [" + logText + "]");
            //Output to TestNG log and Standard log
            Reporter.log(logErr, true);
        }

        /**
         * Logs an error and prints the exception details to TestNG and Standard log<p>
         * <b>RETURNS:</b><p>
         * N/A
         */
        public static void LogError(String logText, Exception e) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);

            String logErr = ("ERROR: [" + logText + "]\n" +
                    stringWriter.toString());

            //Output to TestNG log and Standard log
            Reporter.log(logErr, true);
        }
    }
}

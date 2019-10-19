package main.java;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Logger{

    private static final Logger log = new Logger();

    public String logname = "vacuumLogger";
    protected String env = System.getProperty("user.dir");
    private static File logFile;


    public static Logger getInstance() {
        return log;
    }

    public void createLogFile() {
        File logsFolder = new File(env + '/' + "logs");
        if(!logsFolder.exists()) {
            System.err.println("INFO: Creating new logs directory in " + env);
            logsFolder.mkdir();
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

        logname = logname + '-' + dateFormat.format(cal.getTime()) + ".txt";
        Logger.logFile = new File(logsFolder.getName(), logname);
        try {
            if(logFile.createNewFile()) {
                System.err.println("INFO: Creating new log file");
            }
        } catch(IOException e) {
            System.err.println("ERROR: Cannot create log file");
            System.exit(1);
        }

    }

    private Logger() {
        if(log != null) {
            throw new IllegalStateException("Cannot instantiate a new singleton instance of log");
        }
        this.createLogFile();
    }

    public static void logInfo(String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            FileWriter out = new FileWriter(Logger.logFile, true);
            out.write('\n');
            out.write(now.format(formatTime) + " INFO: " + message);
            out.close();
            System.out.println(message);
        } catch(IOException e) {
            System.err.println("ERROR: Could not write to log file");
        }
    }

    public static void logWarning(String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            FileWriter out = new FileWriter(Logger.logFile, true);
            out.write('\n');
            out.write(now.format(formatTime) + " WARNING: " + message.toCharArray());
            out.close();
            System.out.println(message);
        } catch(IOException e) {
            System.err.println("ERROR: Could not write to log file");
        }
    }

    public static void logError(String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            FileWriter out = new FileWriter(Logger.logFile, true);
            out.write(now.format(formatTime) + " ERROR: " + message.toCharArray());
            out.close();
            System.out.println(message);
        } catch(IOException e) {
            System.err.println("ERROR: Could not write to log file");
        }
    }
}

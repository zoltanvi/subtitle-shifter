package com.subtitleshifter.Shifter;

import com.subtitleshifter.Util.MyFormatter;
import com.subtitleshifter.Util.IntChecker;

import java.io.*;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubtitleShifter {
    static Logger logger = Logger.getLogger(SubtitleShifter.class.getName());
    /**
     * Opens a file with .srt extension
     * @param path is the file's path
     * @return the file
     * @throws Exception not a valid input file
     */
    public File openFile(String path) throws Exception {

        String file = path.substring(path.lastIndexOf(File.separator));
        String extension = file.substring(file.indexOf("."));
        if(extension.equals(".srt")) {
            try {
                return new File(path);
            } catch (Exception x) {
                logMe(x.toString());
            }
        }
        throw new Exception("Not a valid input file!");
    }

    /**
     * Iterates through and change all timestamps in the file
     * @param inputFile is the input file (.srt)
     * @param outputPath is the folder path to save the file
     * @param offsetHour is the offset in hours
     * @param offsetMinute is the offset in minutes
     * @param offsetSecond is the offset in seconds
     * @param offsetMilliSec is the offset in milliseconds
     */
    public void processFile(File inputFile,
                            String outputPath,
                            int offsetHour,
                            int offsetMinute,
                            int offsetSecond,
                            int offsetMilliSec) {
        if (inputFile != null) {

            try (BufferedReader br = new BufferedReader(
                     new InputStreamReader(new FileInputStream(inputFile), "Cp1252"));
                 BufferedWriter bw = new BufferedWriter(
                     new OutputStreamWriter(new FileOutputStream(outputPath), "ISO8859_1"))
            ){
                String line, line2;
                String regex = "(\\d{2}):(\\d{2}):(\\d{2}),(\\d{3}) --> (\\d{2}):(\\d{2}):(\\d{2}),(\\d{3})";
                Pattern p = Pattern.compile(regex);

                while ((line = br.readLine()) != null) {
                    if (IntChecker.isInteger(line)) {
                        bw.write(line);
                        bw.write(System.lineSeparator());
                        if ((line2 = br.readLine()) != null) {

                            Matcher m = p.matcher(line2);
                            TimestampShifter aTime = null;
                            while (m.find()) {
                                aTime = new TimestampShifter(
                                        Integer.parseInt(m.group(1)),
                                        Integer.parseInt(m.group(2)),
                                        Integer.parseInt(m.group(3)),
                                        Integer.parseInt(m.group(4)),
                                        Integer.parseInt(m.group(5)),
                                        Integer.parseInt(m.group(6)),
                                        Integer.parseInt(m.group(7)),
                                        Integer.parseInt(m.group(8))
                                );
                            }
                            if (aTime != null) {
                                aTime.shiftTimes(offsetHour, offsetMinute, offsetSecond, offsetMilliSec);
                            }

                            if (aTime != null) {
                                bw.write(aTime.toString());
                            }
                            bw.write(System.lineSeparator());
                        }

                    } else {
                        bw.write(line);
                        bw.write(System.lineSeparator());
                    }

                }
            } catch (Exception x) {
                logMe(x.toString());
            }
        }
    }
    private void logMe(String s){
        logger.setLevel(Level.FINE);
        try {
            Handler fileHandler = new FileHandler(System.getProperty("user.dir") +
                    File.separator + "error.log", 2000, 1, true);
            fileHandler.setFormatter(new MyFormatter());
            logger.addHandler(fileHandler);
            logger.info(s);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }
}

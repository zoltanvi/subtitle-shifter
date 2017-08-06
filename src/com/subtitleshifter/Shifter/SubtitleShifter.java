package com.subtitleshifter.Shifter;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubtitleShifter {

    /**
     * Checks a string to see if it is an integer
     * @param s is the string
     * @return true if it is an integer, false if not
     */
    private static boolean isInteger(String s) {
        return isInteger(s, 10);
    }

    /**
     * Checks a string to see if it is an integer
     * @param s is the string
     * @param radix is the radix of the number
     * @return true if it is an integer, false if not
     */
    private static boolean isInteger(String s, int radix) {
        if (s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) return false;
                else continue;
            }
            if (Character.digit(s.charAt(i), radix) < 0) return false;
        }
        return true;
    }

    /**
     * Opens a file
     * @param path is the file's path
     * @return the file
     */
    public File openFile(String path) {
        try {
            return new File(path);
        } catch (NullPointerException x) {
            System.out.println("Can't open that!");
            return null;
        }
    }

    /**
     * Iterates through and change all timestamps in the file
     * @param input_file is the input file (.srt)
     * @param output_path is the folder path to save the file
     * @param a is the offset in hours
     * @param b is the offset in minutes
     * @param c is the offset in seconds
     * @param d is the offset in milliseconds
     */
    public void processFile(File input_file, String output_path, int a, int b, int c, int d) {
        if (input_file != null) {

            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(input_file), "Cp1252"));
                 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output_path), "ISO8859_1")))
            {
                String line, line2;
                String regex = "(\\d{2}):(\\d{2}):(\\d{2}),(\\d{3}) --> (\\d{2}):(\\d{2}):(\\d{2}),(\\d{3})";
                Pattern p = Pattern.compile(regex);

                while ((line = br.readLine()) != null) {
                    if (isInteger(line)) {
                        bw.write(line);
                        bw.write(System.lineSeparator());
                        if ((line2 = br.readLine()) != null) {

                            Matcher m = p.matcher(line2);
                            TheTime aTime = null;
                            while (m.find()) {
                                aTime = new TheTime(
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
                                aTime.changeTime(a, b, c, d);
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
                x.printStackTrace();
            }
        }
    }
}

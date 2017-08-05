package com.subtitleshifter;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubtitleShifter {

    public File openFile(String path){
        try {
            File file = new File(path);

            return file;
        } catch (NullPointerException x){
            System.out.println("Nem nyithato meg!");
            return null;
        }
    }



    public void processFile(File input_file, String output_path, int a, int b, int c, int d) {
        if (input_file != null) {

            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(input_file), "Cp1252"));
                 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output_path), "ISO8859_1"))) {
                String line, line2;
                String regex = "(\\d{2}):(\\d{2}):(\\d{2}),(\\d{3}) --> (\\d{2}):(\\d{2}):(\\d{2}),(\\d{3})";
                Pattern p = Pattern.compile(regex);

                while ((line = br.readLine()) != null) {
                    if(isInteger(line)){
                        bw.write(line);
                        bw.write(System.lineSeparator());
                        if((line2 = br.readLine()) != null){

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
                            aTime.changeTime(a, b, c, d);

                            bw.write(aTime.toString());
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

    public static boolean isInteger(String s) {
        return isInteger(s,10);
    }

    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }




}

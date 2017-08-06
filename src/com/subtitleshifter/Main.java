package com.subtitleshifter;

import com.subtitleshifter.GUI.GUI;
import com.subtitleshifter.Util.MyFormatter;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    static Logger logger = Logger.getLogger(Main.class.getName());


    /**
     * Starts the application
     * @param args doesn't do anything yet
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() ->
        {
            try {
               GUI window = new GUI();
               window.initialize();
            } catch (Exception e) {
                logMe(e.toString());

                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                        "Failed to start the application!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    private static void logMe(String s){
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

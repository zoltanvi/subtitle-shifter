package com.subtitleshifter;

import com.subtitleshifter.GUI.GUI;

import javax.swing.*;
import java.awt.*;

public class Main {
    /**
     * Starts the application
     * @param args doesn't do anything yet
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() ->
        {
            try {
                GUI window = new GUI();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                        "Failed to start the application!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}

package com.subtitleshifter.GUI;

import com.subtitleshifter.Shifter.SubtitleShifter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.io.File;
import java.text.ParseException;

public class GUI {

    private JButton okButton;
    private JButton cancelButton;
    private JSpinner hourSpinner;
    private JSpinner minuteSpinner;
    private JSpinner secondSpinner;
    private JSpinner millisecondSpinner;
    private JButton openButton;
    private JButton openButton2;
    private JFrame frmSubtitleShifter;
    private JTextField txtPath;
    private JTextField txtPath2;


    /**
     * Create the application.
     */
    public GUI() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmSubtitleShifter = new JFrame();
        frmSubtitleShifter.setResizable(false);
        frmSubtitleShifter.setTitle("Subtitle Shifter");
        frmSubtitleShifter.setBounds(100, 100, 450, 550);
        frmSubtitleShifter.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frmSubtitleShifter.setVisible(true);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException |
                UnsupportedLookAndFeelException |
                IllegalAccessException |
                InstantiationException e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        frmSubtitleShifter.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBorder(new TitledBorder(null, "Step 4: Click Ok", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.add(bottomPanel, BorderLayout.SOUTH);

        okButton = new JButton("Ok");
        bottomPanel.add(okButton);

        cancelButton = new JButton("Cancel");
        bottomPanel.add(cancelButton);
        cancelButton.addActionListener(e -> System.exit(0));

        JPanel middlePanel = new JPanel();
        middlePanel.setBorder(new TitledBorder(null, "Step 3: Set the values to shift", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.add(middlePanel, BorderLayout.CENTER);
        middlePanel.setLayout(new GridLayout(4, 1, 0, 0));

        JPanel panelHours = new JPanel();
        middlePanel.add(panelHours);
        panelHours.setLayout(null);
        panelHours.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Hours", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));

        hourSpinner = new JSpinner();
        hourSpinner.setBounds(12, 24, 100, 30);
        panelHours.add(hourSpinner);

        JPanel panelMinutes = new JPanel();
        panelMinutes.setLayout(null);
        panelMinutes.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Minutes", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
        middlePanel.add(panelMinutes);

        minuteSpinner = new JSpinner();
        minuteSpinner.setBounds(12, 24, 100, 30);
        panelMinutes.add(minuteSpinner);

        JPanel panelSeconds = new JPanel();
        panelSeconds.setLayout(null);
        panelSeconds.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Seconds", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
        middlePanel.add(panelSeconds);

        secondSpinner = new JSpinner();
        secondSpinner.setBounds(12, 24, 100, 30);
        panelSeconds.add(secondSpinner);

        JPanel panelMillis = new JPanel();
        panelMillis.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Milliseconds", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
        middlePanel.add(panelMillis);
        panelMillis.setLayout(null);

        millisecondSpinner = new JSpinner();
        millisecondSpinner.setBounds(12, 24, 100, 30);
        panelMillis.add(millisecondSpinner);

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new EmptyBorder(10, 5, 5, 5));
        panel.add(panel_2, BorderLayout.NORTH);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel_2.setLayout(new GridLayout(2, 1, 0, 0));
        panel_2.add(topPanel);
        topPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Step 1: Open a subtitle to work with", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

        openButton = new JButton("Open");
        openButton.addActionListener(e ->
        {
            try {
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new File(System.getProperty("user.home")));
                fc.setDialogTitle("Select the subtitle file");
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fc.setFileFilter(new FileNameExtensionFilter("Subtitles", "srt"));
                fc.showOpenDialog(frmSubtitleShifter);
                txtPath.setText(fc.getSelectedFile().getAbsolutePath());
            } catch (Exception y) {
                JOptionPane.showMessageDialog(frmSubtitleShifter,
                        "Please select a subtitle!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        openButton.setHorizontalAlignment(SwingConstants.LEFT);
        topPanel.add(openButton);

        txtPath = new JTextField();
        topPanel.add(txtPath);
        txtPath.setColumns(30);

        JPanel bopa = new JPanel();
        bopa.setBorder(new EmptyBorder(0, 5, 5, 5));
        panel_2.add(bopa);

        JPanel toptitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bopa.add(toptitle);
        toptitle.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Step 2: Select a folder for the output file", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

        openButton2 = new JButton("Open");
        openButton2.addActionListener(e ->
        {
            try {
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new File(System.getProperty("user.home")));
                fc.setDialogTitle("Select a folder for the output");
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fc.showOpenDialog(frmSubtitleShifter);
                txtPath2.setText(fc.getSelectedFile().getAbsolutePath());
            } catch (Exception y) {
                JOptionPane.showMessageDialog(frmSubtitleShifter,
                        "Please select a folder!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        openButton2.setHorizontalAlignment(SwingConstants.LEFT);
        toptitle.add(openButton2);

        txtPath2 = new JTextField();
        toptitle.add(txtPath2);
        txtPath2.setColumns(30);

        okButton.addActionListener(e ->
        {
            try {
                if (!txtPath.getText().isEmpty() && !txtPath2.getText().isEmpty()) {
                    try {
                        hourSpinner.commitEdit();
                        minuteSpinner.commitEdit();
                        secondSpinner.commitEdit();
                        millisecondSpinner.commitEdit();
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    int temphour = (Integer) hourSpinner.getValue();
                    int tempminute = (Integer) minuteSpinner.getValue();
                    int tempsecond = (Integer) secondSpinner.getValue();
                    int tempmilli = (Integer) millisecondSpinner.getValue();

                    try {
                        SubtitleShifter shifter = new SubtitleShifter();
                        shifter.processFile(shifter.openFile(txtPath.getText()),
                                (txtPath2.getText() + File.separator + "SubShifter.srt"),
                                temphour,
                                tempminute,
                                tempsecond,
                                tempmilli);

                        JOptionPane.showMessageDialog(frmSubtitleShifter,
                                "Time shifted successfully!",
                                "Success", JOptionPane.PLAIN_MESSAGE);

                    } catch (Exception xx) {
                        xx.printStackTrace();
                        JOptionPane.showMessageDialog(frmSubtitleShifter,
                                "Something went wrong!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(frmSubtitleShifter,
                            "Please enter correct paths for the files!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception x) {
                x.printStackTrace();
                JOptionPane.showMessageDialog(frmSubtitleShifter,
                        "Something wrong with the subtite file." +
                                "Please check it!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}

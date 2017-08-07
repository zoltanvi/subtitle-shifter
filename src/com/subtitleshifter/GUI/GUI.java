package com.subtitleshifter.GUI;

import com.subtitleshifter.Util.MyFormatter;
import com.subtitleshifter.Shifter.SubtitleShifter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GUI{

    private JButton okButton;
    private JButton cancelButton;
    private JSpinner hourSpinner;
    private JSpinner minuteSpinner;
    private JSpinner secondSpinner;
    private JSpinner millisecondSpinner;
    private JButton btnOpenInput;
    private JButton btnOpenOutput;
    private JFrame frame;
    private JTextField txtInputPath;
    private JTextField txtOutputPath;
    private JPanel topPanel;
    private JPanel middlePanel;
    private JPanel bottomPanel;
    private JPanel firstTopPanel;
    private JPanel secondTopPanel;
    private JPanel mainPanel;
    private Handler fileHandler;
    private JTextField txtFileName;
    static Logger logger = Logger.getLogger(GUI.class.getName());
    private static final int MAX_HOURS = 580;
    private static final int MAX_MINUTES = 34800;
    private static final int MAX_SECONDS = 2088000;
    private static final int MAX_MILLISECONDS = 2088000000;

    /**
     * Initialize the contents of the frame.
     */
    public void initialize() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setTitle("Subtitle Shifter");
        frame.setBounds(100, 100, 500, 550);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException |
                UnsupportedLookAndFeelException |
                IllegalAccessException |
                InstantiationException e) {
            logMe(e.toString());
        }

        initMainPanel();
    }

    private void initMainPanel(){
        mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new BorderLayout(0, 0));

        initTopPanel();
        initMiddlePanel();
        initBottomPanel();
    }

    private void initTopPanel(){
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1, 0, 0));
        topPanel.setBorder(new EmptyBorder(10, 5, 0, 5));
        mainPanel.add(topPanel, BorderLayout.NORTH);

        initFirstTopPanel();
        initSecondTopPanel();
    }

    private void initMiddlePanel(){
        middlePanel = new JPanel();
        middlePanel.setBorder(new TitledBorder(null, "Step 3: Set the values to shift",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        middlePanel.setLayout(new GridLayout(4, 1, 0, 0));

        initHoursPanel();
        initMinutesPanel();
        initSecondsPanel();
        initMillisPanel();
    }

    private void initBottomPanel(){
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.setBorder(new TitledBorder(null, "Step 4: Click Ok",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));

        okButton = new JButton("Ok");
        bottomPanel.add(okButton);

        cancelButton = new JButton("Cancel");
        bottomPanel.add(cancelButton);
        cancelButton.addActionListener(e -> System.exit(0));

        okButton.addActionListener(e ->
        {
            try {
                if((int)hourSpinner.getValue() <= MAX_HOURS &&
                        (int)hourSpinner.getValue() >= (-MAX_HOURS) &&
                        (int)minuteSpinner.getValue() <= MAX_MINUTES &&
                        (int)minuteSpinner.getValue() >= (-MAX_MINUTES) &&
                        (int)secondSpinner.getValue() <= MAX_SECONDS &&
                        (int)secondSpinner.getValue() >= (-MAX_SECONDS) &&
                        (int)millisecondSpinner.getValue() <= MAX_MILLISECONDS &&
                        (int)millisecondSpinner.getValue() >= (-MAX_MILLISECONDS)) {


                    if ((!txtInputPath.getText().isEmpty() &&
                            !txtOutputPath.getText().isEmpty() &&
                            !txtFileName.getText().isEmpty()) &&
                            !(txtInputPath.getText().substring(txtInputPath.getText().lastIndexOf(File.separator)))
                                    .equals(File.separator + txtFileName.getText() + ".srt")){

                        try {
                            hourSpinner.commitEdit();
                            minuteSpinner.commitEdit();
                            secondSpinner.commitEdit();
                            millisecondSpinner.commitEdit();
                        } catch (ParseException ex) {
                            logMe(ex.toString());
                            throw new Exception("Something went wrong!");
                        }
                        int temphour = (Integer) hourSpinner.getValue();
                        int tempminute = (Integer) minuteSpinner.getValue();
                        int tempsecond = (Integer) secondSpinner.getValue();
                        int tempmilli = (Integer) millisecondSpinner.getValue();

                        try {
                            SubtitleShifter shifter = new SubtitleShifter();
                            shifter.processFile(shifter.openFile(txtInputPath.getText()),
                                    (txtOutputPath.getText() + File.separator + txtFileName.getText() + ".srt"),
                                    temphour,
                                    tempminute,
                                    tempsecond,
                                    tempmilli);

                            JOptionPane.showMessageDialog(frame,
                                    "Time shifted successfully!",
                                    "Success", JOptionPane.PLAIN_MESSAGE);

                        } catch (Exception xx) {
                            logMe(xx.toString());
                            JOptionPane.showMessageDialog(frame,
                                    "Something went wrong!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    } else {
                            JOptionPane.showMessageDialog(frame,
                                    "Please enter correct paths for the files!\n" +
                                            "Note: The opened and the new subtitle file names must not match!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }

                } else{
                    JOptionPane.showMessageDialog(frame,
                            "Please enter closer values to zero!.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception x) {
                logMe(x.toString());
                JOptionPane.showMessageDialog(frame,
                        "Something is wrong with the subtite file." +
                                "Please check it!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void initFirstTopPanel(){
        firstTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(firstTopPanel);
        firstTopPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
                "Step 1: Open a subtitle to work with",
                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        btnOpenInput = new JButton("Open");
        btnOpenInput.addActionListener(e ->
        {
            try {
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new File(System.getProperty("user.home")));
                fc.setDialogTitle("Select the subtitle file");
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fc.setFileFilter(new FileNameExtensionFilter("Subtitles", "srt"));
                int result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File f = fc.getSelectedFile();
                    txtInputPath.setText(f.getAbsolutePath());
                }
            } catch (Exception y) {
                logMe(y.getMessage());
            }
        });
        btnOpenInput.setHorizontalAlignment(SwingConstants.LEFT);
        firstTopPanel.add(btnOpenInput);

        txtInputPath = new JTextField();
        txtInputPath.setEditable(false);
        firstTopPanel.add(txtInputPath);
        txtInputPath.setColumns(34);
    }

    private void initSecondTopPanel(){
        secondTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(secondTopPanel);
        secondTopPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
                "Step 2: Select a folder and enter a name for the new subtitle",
                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        btnOpenOutput = new JButton("Open");
        btnOpenOutput.addActionListener(e ->
        {
            try {
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new File(System.getProperty("user.home")));
                fc.setDialogTitle("Select a folder for the output");
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File f = fc.getSelectedFile();
                    txtOutputPath.setText(f.getAbsolutePath());
                }
            } catch (Exception y) {
                logMe(y.getMessage());
            }
        });
        btnOpenOutput.setHorizontalAlignment(SwingConstants.LEFT);
        secondTopPanel.add(btnOpenOutput);

        txtOutputPath = new JTextField();
        txtOutputPath.setEditable(false);
        secondTopPanel.add(txtOutputPath);
        txtOutputPath.setColumns(18);

        JLabel lblSlash = new JLabel(File.separator);
        lblSlash.setVerticalAlignment(SwingConstants.BOTTOM);
        secondTopPanel.add(lblSlash);

        txtFileName = new JTextField();
        secondTopPanel.add(txtFileName);
        txtFileName.setColumns(12);

        JLabel lblExtension = new JLabel(".srt");
        secondTopPanel.add(lblExtension);

    }

    private void initHoursPanel(){
        JPanel panelHours = new JPanel();
        middlePanel.add(panelHours);
        panelHours.setLayout(null);
        panelHours.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Hours", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));

        hourSpinner = new JSpinner();
        hourSpinner.setBounds(12, 24, 100, 30);
        panelHours.add(hourSpinner);
    }

    private void initMinutesPanel(){
        JPanel panelMinutes = new JPanel();
        middlePanel.add(panelMinutes);
        panelMinutes.setLayout(null);
        panelMinutes.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Minutes", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));

        minuteSpinner = new JSpinner();
        minuteSpinner.setBounds(12, 24, 100, 30);
        panelMinutes.add(minuteSpinner);
    }

    private void initSecondsPanel(){
        JPanel panelSeconds = new JPanel();
        middlePanel.add(panelSeconds);
        panelSeconds.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Seconds", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panelSeconds.setLayout(null);
        secondSpinner = new JSpinner();
        secondSpinner.setBounds(12, 24, 100, 30);
        panelSeconds.add(secondSpinner);
    }

    private void initMillisPanel(){
        JPanel panelMillis = new JPanel();
        middlePanel.add(panelMillis);
        panelMillis.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Milliseconds", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panelMillis.setLayout(null);
        millisecondSpinner = new JSpinner();
        millisecondSpinner.setBounds(12, 24, 100, 30);
        panelMillis.add(millisecondSpinner);
    }

    private void logMe(String s){
        logger.setLevel(Level.FINE);
        try {
            fileHandler = new FileHandler(System.getProperty("user.dir") +
                        File.separator + "error.log", 20000, 1, true);
            fileHandler.setFormatter(new MyFormatter());
            logger.addHandler(fileHandler);
            logger.info(s);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }
}

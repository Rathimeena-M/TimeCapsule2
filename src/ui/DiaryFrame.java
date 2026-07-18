package ui;


import javax.swing.JFileChooser;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import dao.DiaryDAO;
import model.Diary;
import java.sql.Date;
import model.Session;

public class DiaryFrame extends JFrame {

    JComboBox<String> moodBox;
    JTextArea diaryArea;
    JButton btnSave;
    JButton btnBack;
    JButton btnPhoto;

    String photoPath = "";

    public DiaryFrame() {

        setTitle("Write Diary");
        setSize(600,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Mood Selection
        String[] moods = {"😊 Happy", "😔 Sad", "😎 Excited", "😌 Calm"};
        moodBox = new JComboBox<>(moods);

        // Diary Text Area
        diaryArea = new JTextArea();
        diaryArea.setLineWrap(true);
        diaryArea.setWrapStyleWord(true);

        // Buttons
        btnSave = new JButton("Save");
        btnPhoto = new JButton("Choose Photo");
        btnBack = new JButton("Back");


        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnPhoto);
        bottomPanel.add(btnSave);
        bottomPanel.add(btnBack);

        panel.add(moodBox, BorderLayout.NORTH);
        panel.add(new JScrollPane(diaryArea), BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        add(panel);
        btnPhoto.addActionListener(e -> {

            JFileChooser chooser = new JFileChooser();

            int result = chooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {

                File file = chooser.getSelectedFile();

                photoPath = file.getAbsolutePath();

                JOptionPane.showMessageDialog(this,
                        "Photo Selected:\n" + file.getName());
            }

        });

        btnSave.addActionListener(e -> {

            String diaryText = diaryArea.getText().trim();

            if (diaryText.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please write your diary.");
                return;
            }

            int lines = diaryText.split("\\R").length;

            if (lines < 5) {
                JOptionPane.showMessageDialog(this,
                        "Please write at least 5 lines.");
                return;
            }

            Diary diary = new Diary(
                    Session.loggedInEmail,
                    new Date(System.currentTimeMillis()),
                    moodBox.getSelectedItem().toString(),
                    diaryText,
                    photoPath
            );
            DiaryDAO dao = new DiaryDAO();

            if (Session.loggedInEmail.isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Please login first.");

                dispose();
                new LoginFrame();
                return;
            }

            if (dao.saveDiary(diary)) {
                JOptionPane.showMessageDialog(this,
                        "Diary Saved Successfully!");
                diaryArea.setText("");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Failed to Save Diary!");
            }

        });

        btnBack.addActionListener(e -> {
            dispose();
            new DashboardFrame();
        });

        setVisible(true);
    }
}
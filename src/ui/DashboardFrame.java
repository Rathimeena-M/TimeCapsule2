package ui;

import model.Session;

import javax.swing.*;
import java.awt.*;


public class DashboardFrame extends JFrame {

    JButton btnWriteDiary;
    JButton btnViewDiary;
    JButton btnLogout;

    public DashboardFrame() {

        setTitle("TimeCapsule Dashboard");
        setSize(550,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1,10,10));
        panel.setBackground(new Color(245,247,250));
        new Font("Segoe UI",Font.BOLD,14);

        JLabel label = new JLabel(
                "Welcome, " + Session.loggedInEmail,
                SwingConstants.CENTER
        );
        label.setFont(new Font("Arial", Font.BOLD, 20));

        btnWriteDiary = new JButton("Write Diary");
        btnViewDiary = new JButton("View My Diaries");
        btnLogout = new JButton("Logout");

        panel.add(label);
        panel.add(btnWriteDiary);
        panel.add(btnViewDiary);
        panel.add(btnLogout);

        add(panel);

        // Write Diary
        btnWriteDiary.addActionListener(e -> {
            dispose();
            new DiaryFrame();
        });

        // View Diary
        btnViewDiary.addActionListener(e -> {
            dispose();
            new ViewDiaryFrame();
        });

        // Logout
        btnLogout.addActionListener(e -> {

            Session.loggedInEmail = "";

            JOptionPane.showMessageDialog(this,
                    "Logged out successfully!");

            dispose();
            new LoginFrame();
        });

        setVisible(true);
    }
}
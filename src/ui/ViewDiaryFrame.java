package ui;

import dao.DiaryDAO;
import model.Session;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import db.DBConnection;
import java.io.File;

public class ViewDiaryFrame extends JFrame {

    public ViewDiaryFrame() {

        setTitle("My Diaries");
        setSize(700,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM diary WHERE user_email=? ORDER BY diary_date DESC";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, Session.loggedInEmail);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                JPanel diaryPanel = new JPanel();
                diaryPanel.setLayout(new BorderLayout());
                diaryPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                JTextArea area = new JTextArea();

                area.setEditable(false);
                area.setLineWrap(true);
                area.setWrapStyleWord(true);

                area.setText(
                        "📅 Date : " + rs.getDate("diary_date") +
                                "\n😊 Mood : " + rs.getString("mood") +
                                "\n\n📝 Diary :\n" +
                                rs.getString("diary_text")
                );

                diaryPanel.add(area, BorderLayout.CENTER);

                String path = rs.getString("photo_path");

                if(path != null && !path.isEmpty()){

                    File file = new File(path);

                    if(file.exists()){

                        ImageIcon icon = new ImageIcon(path);

                        Image img = icon.getImage().getScaledInstance(150,150,Image.SCALE_SMOOTH);

                        JLabel imageLabel = new JLabel(new ImageIcon(img));

                        diaryPanel.add(imageLabel, BorderLayout.EAST);

                    }
                }

                mainPanel.add(diaryPanel);
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        JScrollPane scroll = new JScrollPane(mainPanel);

        add(scroll);

        setVisible(true);
    }
}
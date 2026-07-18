package dao;

import db.DBConnection;
import model.Diary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DiaryDAO {

    // Save Diary
    public boolean saveDiary(Diary diary) {

        String sql = "INSERT INTO diary(user_email, diary_date, mood, diary_text, photo_path) VALUES (?, ?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, diary.getUserEmail());
            ps.setDate(2, diary.getDiaryDate());
            ps.setString(3, diary.getMood());
            ps.setString(4, diary.getDiaryText());
            ps.setString(5, diary.getPhotoPath());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // View Diaries
    public String getAllDiaries(String email) {

        StringBuilder data = new StringBuilder();

        String sql = "SELECT * FROM diary WHERE user_email=? ORDER BY diary_date DESC";

        try {

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);

            System.out.println("Searching for: " + email);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println("Diary Found!");

                data.append("📅 Date : ")
                        .append(rs.getDate("diary_date"))
                        .append("\n");

                data.append("😊 Mood : ")
                        .append(rs.getString("mood"))
                        .append("\n\n");

                data.append("📝 Diary :\n")
                        .append(rs.getString("diary_text"))
                        .append("\n\n");

                data.append("---------------------------------\n\n");
                String photo = rs.getString("photo_path");

                if (photo != null && !photo.isEmpty()) {
                    data.append("📷 Photo : ")
                            .append(photo)
                            .append("\n\n");
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return data.toString();
    }
}
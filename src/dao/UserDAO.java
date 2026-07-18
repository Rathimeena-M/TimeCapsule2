package dao;

import db.DBConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    public boolean emailExists(String email) {

        String sql = "SELECT * FROM users WHERE email=?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Email Found!");
                return true;
            } else {
                System.out.println("Email Not Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Register User
    public boolean registerUser(User user) {

        String sql = "INSERT INTO users(name,email,password) VALUES(?,?,?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Login User
    public boolean loginUser(String email, String password) {

        String sql = "SELECT * FROM users WHERE email=? AND password=?";

        try {

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email.trim());
            ps.setString(2, password.trim());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("LOGIN SUCCESS");
                return true;
            } else {
                System.out.println("LOGIN FAILED");
                System.out.println("Email = " + email);
                System.out.println("Password = " + password);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
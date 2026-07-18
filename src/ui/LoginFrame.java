package ui;

import dao.UserDAO;
import model.Session;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginFrame extends JFrame {

    JTextField txtEmail;
    JPasswordField txtPassword;
    JButton btnLogin, btnRegister;

    public LoginFrame() {

        setTitle("TimeCapsule - Login");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(245,247,250));
        panel.setBorder(new EmptyBorder(20,20,20,20));
        panel.setLayout(new GridLayout(4, 2, 15, 15));

        // Email
        JLabel lblEmail = new JLabel("📧 Email");
        lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(lblEmail);

        txtEmail = new JTextField();
        panel.add(txtEmail);

// Password
        JLabel lblPassword = new JLabel("🔒 Password");
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(lblPassword);

        txtPassword = new JPasswordField();
        panel.add(txtPassword);
        //buttons
        btnLogin = new JButton("Login");
        btnRegister = new JButton("Register");
        btnLogin.setBackground(new Color(46,125,50));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));

        btnRegister.setBackground(new Color(33,150,243));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFocusPainted(false);
        btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 14));

        panel.add(btnLogin);
        panel.add(btnRegister);

        JLabel title = new JLabel("🌸 TimeCapsule", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));

        add(title, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        // Login Button
        btnLogin.addActionListener(e -> {

            System.out.println("Typed Email: " + txtEmail.getText());
            System.out.println("Typed Password: " + new String(txtPassword.getPassword()));

            UserDAO dao = new UserDAO();

            boolean success = dao.loginUser(
                    txtEmail.getText(),
                    new String(txtPassword.getPassword())
            );

            if (success) {

                Session.loggedInEmail = txtEmail.getText();

                JOptionPane.showMessageDialog(this,
                        "Login Successful!");

                System.out.println("Opening Dashboard...");

                dispose();
                new DashboardFrame();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Invalid Email or Password");

                System.out.println("Opening Login...");


            }

        });

        // Register Button
        btnRegister.addActionListener(e -> {

            dispose();
            new RegisterFrame();

        });

        setVisible(true);
    }
}
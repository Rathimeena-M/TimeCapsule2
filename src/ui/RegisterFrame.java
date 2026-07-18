package ui;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {

    JTextField txtName, txtEmail;
    JPasswordField txtPassword;
    JButton btnRegister, btnLogin;

    public RegisterFrame() {

        setTitle("TimeCapsule - Register");
        setSize(550, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBackground(new Color(245,247,250));
        new Font("Segoe UI",Font.BOLD,14);
        panel.add(new JLabel("Name"));
        txtName = new JTextField();
        panel.add(txtName);

        panel.add(new JLabel("Email"));
        txtEmail = new JTextField();
        panel.add(txtEmail);

        panel.add(new JLabel("Password"));
        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        btnRegister = new JButton("Register");
        btnLogin = new JButton("Go to Login");

        panel.add(btnRegister);
        panel.add(btnLogin);

        add(panel);

        // Register Button
        btnRegister.addActionListener(e -> {
            btnRegister.setBackground(new Color(33,150,243));
            btnRegister.setForeground(Color.WHITE);
            UserDAO dao = new UserDAO();

            if (txtName.getText().trim().isEmpty()
                    || txtEmail.getText().trim().isEmpty()
                    || new String(txtPassword.getPassword()).trim().isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Please fill all fields!");
                return;
            }

            if (dao.emailExists(txtEmail.getText().trim())) {

                JOptionPane.showMessageDialog(this,
                        "Email already registered!");
                return;
            }

            User user = new User(
                    txtName.getText().trim(),
                    txtEmail.getText().trim(),
                    new String(txtPassword.getPassword())
            );

            boolean success = dao.registerUser(user);
            System.out.println("Name: " + txtName.getText());
            System.out.println("Email: " + txtEmail.getText());
            System.out.println("Password: " + new String(txtPassword.getPassword()));

            System.out.println("Register Result: " + success);
            if (success) {

                JOptionPane.showMessageDialog(this,
                        "Registration Successful!");

                dispose();
                new LoginFrame();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Registration Failed!");

            }

        });

        // Go To Login Button
        btnLogin.addActionListener(e -> {
            btnLogin.setBackground(new Color(46,125,50));
            btnLogin.setForeground(Color.WHITE);

            dispose();
            new LoginFrame();

        });

        setVisible(true);
    }
}
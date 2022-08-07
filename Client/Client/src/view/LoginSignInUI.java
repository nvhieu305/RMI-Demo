package view;

import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginSignInUI extends JFrame{
    private JTextField edtUsername;
    private JRadioButton btnIsAdmin;
    private JButton btnLogin;
    private JButton btnSignIn;
    private JPanel mainDemo;
    private JLabel tvLogEvent;
    private JPasswordField edtPassword;

    LoginSignInCallback loginSignInCallback;
    public LoginSignInUI(LoginSignInCallback loginSignInCallback, DetailUserCallback detailUserCallback){
        setTitle("Login");
        setContentPane(mainDemo);
        setSize(700, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                user.setUsername(edtUsername.getText());
                user.setPassword(edtPassword.getText());

                User userResult = loginSignInCallback.login(user);

                if(userResult == null) {
                    showLog("Login Error!");

                } else {
                    showLog("Login Success!");
                    DetailUserUI detailUserUI = new DetailUserUI(userResult, detailUserCallback);
                    detailUserUI.setVisible(true);
                }
            }
        });

        btnSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                user.setUsername(edtUsername.getText());
                user.setPassword(edtPassword.getText());
                if(btnIsAdmin.isSelected()){
                    user.setRole(1);
                } else {
                    user.setRole(0);
                }

                User userResult = loginSignInCallback.addUser(user);
                if(userResult == null) {
                    showLog("SignIn Error!");
                } else {
                    showLog("SignIn Success!");
                    DetailUserUI detailUserUI = new DetailUserUI(userResult, detailUserCallback);
                    detailUserUI.setVisible(true);
                }
            }
        });
    }

    public void showLog(String log) {
        tvLogEvent.setText(log);
    }
}

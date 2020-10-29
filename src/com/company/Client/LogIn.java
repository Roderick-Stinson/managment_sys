package com.company.Client;

import javax.swing.*;

public class LogIn extends JPanel {
    private JLabel userTag, pwdTag;
    public JTextField userInputBox;
    public JPasswordField pwdInputBox;
    public JButton btn_login;

    public LogIn() {
        super(null);
        init();
        this.setVisible(true);
    }
    public void init() {
        this.userTag = new JLabel("UserName: ");
        this.userTag.setBounds(10,20,80,25);
        this.add(this.userTag);

        this.userInputBox = new JTextField(20);
        this.userInputBox.setBounds(100,20,165,25);
        this.add(this.userInputBox);

        this.pwdTag = new JLabel("Password: ");
        this.pwdTag.setBounds(10,50,80,25);
        this.add(this.pwdTag);

        this.pwdInputBox = new JPasswordField(20);
        this.pwdInputBox.setBounds(100,50,165,25);
        this.add(this.pwdInputBox);

        this.btn_login = new JButton("Log in");
        this.btn_login.setBounds(10, 80, 80, 25);
        this.add(btn_login);

    }
    public void hide_panel() {
        this.setVisible(false);
    }

}

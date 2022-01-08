package client.graphic.welcome;

import client.controller.LoginController;
import client.graphic.interfaces.CloseButton;
import client.listener.LoginListener;
import client.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.Properties;

public class LoginView implements CloseButton {

    public JLabel warnLabel = new JLabel();
    public LoginListener loginListener = new LoginListener();
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    LoginController loginController;

    public void loginPage(){
        loginController = new LoginController(this);
        MainController.panel.removeAll();
        JLabel userLabel = new JLabel();
        JLabel passwordLabel = new JLabel();

        JButton loginButton = new JButton();
        JButton registerButton = new JButton();
        JCheckBox showPassword = new JCheckBox();

        try {
            FileReader reader = new FileReader("resources/configuration/login.properties");
            Properties properties = new Properties();
            properties.load(reader);
            userLabel.setText(properties.getProperty("userLabel"));
            userLabel.setBounds(Integer.parseInt(properties.getProperty("userLabelX")),
                    Integer.parseInt(properties.getProperty("userLabelY")),
                    Integer.parseInt(properties.getProperty("userLabelWidth")),
                    Integer.parseInt(properties.getProperty("userLabelHeight")));
            passwordLabel.setText(properties.getProperty("passLabel"));
            passwordLabel.setBounds(Integer.parseInt(properties.getProperty("passLabelX")),
                    Integer.parseInt(properties.getProperty("passLabelY")),
                    Integer.parseInt(properties.getProperty("passLabelWidth")),
                    Integer.parseInt(properties.getProperty("passLabelHeight")));
            userTextField.setBackground(new Color(Integer.parseInt(properties.getProperty("red")),
                    Integer.parseInt(properties.getProperty("green")),
                    Integer.parseInt(properties.getProperty("blue"))));
            userTextField.setBounds(Integer.parseInt(properties.getProperty("userFieldX")),
                    Integer.parseInt(properties.getProperty("userFieldY")),
                    Integer.parseInt(properties.getProperty("userFieldWidth")),
                    Integer.parseInt(properties.getProperty("userFieldHeight")));
            passwordField.setBackground(new Color(Integer.parseInt(properties.getProperty("red")),
                    Integer.parseInt(properties.getProperty("green")),
                    Integer.parseInt(properties.getProperty("blue"))));
            passwordField.setBounds(Integer.parseInt(properties.getProperty("passFieldX")),
                    Integer.parseInt(properties.getProperty("passFieldY")),
                    Integer.parseInt(properties.getProperty("passFieldWidth")),
                    Integer.parseInt(properties.getProperty("passFieldHeight")));
            loginButton.setText(properties.getProperty("login"));
            loginButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            loginButton.setBounds(Integer.parseInt(properties.getProperty("loginX")),
                    Integer.parseInt(properties.getProperty("loginY")),
                    Integer.parseInt(properties.getProperty("loginWidth")),
                    Integer.parseInt(properties.getProperty("loginHeight")));
            registerButton.setText(properties.getProperty("register"));
            registerButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            registerButton.setBounds(Integer.parseInt(properties.getProperty("registerX")),
                    Integer.parseInt(properties.getProperty("registerY")),
                    Integer.parseInt(properties.getProperty("registerWidth")),
                    Integer.parseInt(properties.getProperty("registerHeight")));
            showPassword.setText(properties.getProperty("showPass"));
            showPassword.setBounds(Integer.parseInt(properties.getProperty("showPassX")),
                    Integer.parseInt(properties.getProperty("showPassY")),
                    Integer.parseInt(properties.getProperty("showPassWidth")),
                    Integer.parseInt(properties.getProperty("showPassHeight")));
            warnLabel.setBounds(Integer.parseInt(properties.getProperty("warnX")),
                    Integer.parseInt(properties.getProperty("warnY")),
                    Integer.parseInt(properties.getProperty("warnWidth")),
                    Integer.parseInt(properties.getProperty("warnHeight")));
        }catch (Exception e){
            e.printStackTrace();
        }
        closeButton();
        MainController.panel.setBackground(Color.lightGray);
        MainController.panel.add(userLabel);
        MainController.panel.add(passwordLabel);
        MainController.panel.add(userTextField);
        MainController.panel.add(passwordField);
        MainController.panel.add(loginButton);
        MainController.panel.add(registerButton);
        MainController.panel.add(showPassword);
        MainController.panel.add(warnLabel);
        MainController.panel.revalidate();
        MainController.panel.repaint();
        loginButton.addActionListener(this::loginPressed);
        registerButton.addActionListener(this::registerPressed);

        showPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPassword.isSelected()) {
                   passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('*');
               }

            }
        });

//        loginButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//               // SendLoginEvent sendLoginEvent  = new SendLoginEvent(userTextField.getText(),passwordField.getText(),warnLabel.getText());
//               /// warnLabel.setText(MainController.loginAgent.login(sendLoginEvent));
//               // loginListener.login(sendLoginEvent);
//
//            }
//
//        });

//        registerButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                RegisterView registerView = new RegisterView();
//                LoginPanelRegisterButton loginPanelRegisterButton = new LoginPanelRegisterButton(registerView);
//                loginListener.pressRegister(loginPanelRegisterButton);
//
//                //registerView.registerPage();
//
//            }
//        });

    }

    private void registerPressed(ActionEvent actionEvent) {
        new RegisterView().registerPage();
    }

    private void loginPressed(ActionEvent actionEvent) {
        loginController.loginPressed(userTextField.getText(),passwordField.getText());
    }


}


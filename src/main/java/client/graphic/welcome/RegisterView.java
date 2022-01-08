package client.graphic.welcome;

import client.controller.RegisterController;
import client.graphic.interfaces.CloseButton;
import client.listener.RegisterListener;
import client.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.Properties;

public class RegisterView implements CloseButton {
    RegisterController registerController;
    public JLabel warnLabel = new JLabel();
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JTextField nameField = new JTextField();
    JTextField emailField = new JTextField();
    JTextField phoneField = new JTextField();
    public RegisterListener registerListener = new RegisterListener();


    public void registerPage(){
        registerController = new RegisterController(this);

        MainController.panel.removeAll();
        JLabel userLabel = new JLabel();
        JLabel passwordLabel = new JLabel();
        JLabel nameLabel = new JLabel();
        JLabel emailLabel = new JLabel();
        JLabel phoneLabel = new JLabel();
        ///text fields
        JButton registerButton = new JButton();
        JCheckBox showPassword = new JCheckBox();

        try {
            FileReader reader = new FileReader("resources/configuration/register.properties");
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
            nameLabel.setText(properties.getProperty("nameLabel"));
            nameLabel.setBounds(Integer.parseInt(properties.getProperty("nameLabelX")),
                    Integer.parseInt(properties.getProperty("nameLabelY")),
                    Integer.parseInt(properties.getProperty("nameLabelWidth")),
                    Integer.parseInt(properties.getProperty("nameLabelHeight")));
            emailLabel.setText(properties.getProperty("emailLabel"));
            emailLabel.setBounds(Integer.parseInt(properties.getProperty("emailLabelX")),
                    Integer.parseInt(properties.getProperty("emailLabelY")),
                    Integer.parseInt(properties.getProperty("emailLabelWidth")),
                    Integer.parseInt(properties.getProperty("emailLabelHeight")));
            phoneLabel.setText(properties.getProperty("phoneLabel"));
            phoneLabel.setBounds(Integer.parseInt(properties.getProperty("phoneLabelX")),
                    Integer.parseInt(properties.getProperty("phoneLabelY")),
                    Integer.parseInt(properties.getProperty("phoneLabelWidth")),
                    Integer.parseInt(properties.getProperty("phoneLabelHeight")));
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
            nameField.setBackground(new Color(Integer.parseInt(properties.getProperty("red")),
                    Integer.parseInt(properties.getProperty("green")),
                    Integer.parseInt(properties.getProperty("blue"))));
            nameField.setBounds(Integer.parseInt(properties.getProperty("nameFieldX")),
                    Integer.parseInt(properties.getProperty("nameFieldY")),
                    Integer.parseInt(properties.getProperty("nameFieldWidth")),
                    Integer.parseInt(properties.getProperty("nameFieldHeight")));
            emailField.setBackground(new Color(Integer.parseInt(properties.getProperty("red")),
                    Integer.parseInt(properties.getProperty("green")),
                    Integer.parseInt(properties.getProperty("blue"))));
            emailField.setBounds(Integer.parseInt(properties.getProperty("emailFieldX")),
                    Integer.parseInt(properties.getProperty("emailFieldY")),
                    Integer.parseInt(properties.getProperty("emailFieldWidth")),
                    Integer.parseInt(properties.getProperty("emailFieldHeight")));
            phoneField.setBackground(new Color(Integer.parseInt(properties.getProperty("red")),
                    Integer.parseInt(properties.getProperty("green")),
                    Integer.parseInt(properties.getProperty("blue"))));
            phoneField.setBounds(Integer.parseInt(properties.getProperty("phoneFieldX")),
                    Integer.parseInt(properties.getProperty("phoneFieldY")),
                    Integer.parseInt(properties.getProperty("phoneFieldWidth")),
                    Integer.parseInt(properties.getProperty("phoneFieldHeight")));
            registerButton.setText(properties.getProperty("register"));
            registerButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            registerButton.setBounds(Integer.parseInt(properties.getProperty("registerX")),
                    Integer.parseInt(properties.getProperty("registerY")),
                    Integer.parseInt(properties.getProperty("registerWidth")),
                    Integer.parseInt(properties.getProperty("registerHeight")));
            showPassword.setText(properties.getProperty("showPassword"));
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
        MainController.panel.add(nameLabel);
        MainController.panel.add(nameField);
        MainController.panel.add(emailLabel);
        MainController.panel.add(emailField);
        MainController.panel.add(phoneLabel);
        MainController.panel.add(phoneField);
        MainController.panel.add(registerButton);
        MainController.panel.add(showPassword);
        MainController.panel.add(warnLabel);
        MainController.panel.revalidate();
        MainController.panel.repaint();
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

//        registerButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                SendRegisterEvent registerEvent = new SendRegisterEvent(userTextField.getText(),passwordField.getText(),
//                        nameField.getText(),emailField.getText(),warnLabel.getText());
//                warnLabel.setText(MainController.registerAgent.register(registerEvent));
//                registerListener.register(registerEvent);
//
//            }
//        });



    }
    public void registerPressed(ActionEvent actionEvent){
        registerController.registerPressed(userTextField.getText(),nameField.getText(),
                passwordField.getText(),emailField.getText(),phoneField.getText());
    }


}

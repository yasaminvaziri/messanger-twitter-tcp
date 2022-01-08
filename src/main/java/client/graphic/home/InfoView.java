package client.graphic.home;



import client.graphic.photo.Photo;
import client.graphic.interfaces.BackToHomeButton;
import client.graphic.interfaces.CloseButton;
import client.graphic.interfaces.MenuButton;
import client.controller.MainController;
import shared.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.Properties;


public class InfoView implements MenuButton, CloseButton, BackToHomeButton {


    public void infoPage(UserInfo userInfo){

        MainController.panel.removeAll();
        menuButton();
        closeButton();
        backToHome();
        JLabel warn = new JLabel();
        JLabel userLabel = new JLabel();
        JLabel passwordLabel = new JLabel();
        JLabel nameLabel = new JLabel();
        JLabel emailLabel = new JLabel();
        JLabel phoneLabel = new JLabel();
        JTextField userTextField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JCheckBox showPassword = new JCheckBox();
        JButton changePassButton = new JButton();
        JTextField nameField = new JTextField();
        JButton changeNameButton = new JButton();
        JTextField emailField = new JTextField();
        JButton changeEmailButton = new JButton();
        JTextField phoneField = new JTextField();
        JButton changePhoneButton = new JButton();
        JLabel birthdateLabel = new JLabel();
        JTextField birthField = new JTextField();
        JButton birthButton = new JButton();
        JLabel bioLabel = new JLabel();
        JTextField bioField = new JTextField();
        JButton changeBioButton = new JButton();
        try {
            FileReader reader = new FileReader("resources/configuration/info.properties");
            Properties properties = new Properties();
            properties.load(reader);
            warn.setBounds(Integer.parseInt(properties.getProperty("warnX")),
                    Integer.parseInt(properties.getProperty("warnY")),
                    Integer.parseInt(properties.getProperty("warnWidth")),
                    Integer.parseInt(properties.getProperty("warnHeight")));
            userLabel.setText(properties.getProperty("userLabel"));
            userLabel.setBounds(Integer.parseInt(properties.getProperty("userLabelX")),
                    Integer.parseInt(properties.getProperty("userLabelY")),
                    Integer.parseInt(properties.getProperty("userLabelWidth")),
                    Integer.parseInt(properties.getProperty("userLabelHeight")));
            passwordLabel.setText(properties.getProperty("passwordLabel"));
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
            userTextField.setBounds(Integer.parseInt(properties.getProperty("userTextFieldX")),
                    Integer.parseInt(properties.getProperty("userTextFieldY")),
                    Integer.parseInt(properties.getProperty("userTextFieldWidth")),
                    Integer.parseInt(properties.getProperty("userTextFieldHeight")));
            userTextField.setText(MainController.infoAgent.showUsername(MainController.username));
            showPassword.setText(properties.getProperty("showPassword"));
            showPassword.setBounds(Integer.parseInt(properties.getProperty("showPasswordX")),
                    Integer.parseInt(properties.getProperty("showPasswordY")),
                    Integer.parseInt(properties.getProperty("showPasswordWidth")),
                    Integer.parseInt(properties.getProperty("showPasswordHeight")));
            passwordField.setBackground(new Color(Integer.parseInt(properties.getProperty("red")),
                    Integer.parseInt(properties.getProperty("green")),
                    Integer.parseInt(properties.getProperty("blue"))));
            passwordField.setBounds(Integer.parseInt(properties.getProperty("passwordFieldX")),
                    Integer.parseInt(properties.getProperty("passwordFieldY")),
                    Integer.parseInt(properties.getProperty("passwordFieldWidth")),
                    Integer.parseInt(properties.getProperty("passwordFieldHeight")));
            passwordField.setText(MainController.infoAgent.showPass(MainController.username));
            changePassButton.setText(properties.getProperty("changePass"));
            changePassButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            changePassButton.setBounds(Integer.parseInt(properties.getProperty("changePassX")),
                    Integer.parseInt(properties.getProperty("changePassY")),
                    Integer.parseInt(properties.getProperty("changePassWidth")),
                    Integer.parseInt(properties.getProperty("changePassHeight")));
            nameField.setBackground(new Color(Integer.parseInt(properties.getProperty("red")),
                    Integer.parseInt(properties.getProperty("green")),
                    Integer.parseInt(properties.getProperty("blue"))));
            nameField.setBounds(Integer.parseInt(properties.getProperty("nameFieldX")),
                    Integer.parseInt(properties.getProperty("nameFieldY")),
                    Integer.parseInt(properties.getProperty("nameFieldWidth")),
                    Integer.parseInt(properties.getProperty("nameFieldHeight")));
            nameField.setText(MainController.infoAgent.showName(MainController.username));
            changeNameButton.setText(properties.getProperty("changeName"));
            changeNameButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            changeNameButton.setBounds(Integer.parseInt(properties.getProperty("changeNameX")),
                    Integer.parseInt(properties.getProperty("changeNameY")),
                    Integer.parseInt(properties.getProperty("changeNameWidth")),
                    Integer.parseInt(properties.getProperty("changeNameHeight")));
            emailField.setBackground(new Color(Integer.parseInt(properties.getProperty("red")),
                    Integer.parseInt(properties.getProperty("green")),
                    Integer.parseInt(properties.getProperty("blue"))));
            emailField.setBounds(Integer.parseInt(properties.getProperty("emailFieldX")),
                    Integer.parseInt(properties.getProperty("emailFieldY")),
                    Integer.parseInt(properties.getProperty("emailFieldWidth")),
                    Integer.parseInt(properties.getProperty("emailFieldHeight")));
            emailField.setText(MainController.infoAgent.showEmail(MainController.username));
            changeEmailButton.setText(properties.getProperty("changeEmail"));
            changeEmailButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            changeEmailButton.setBounds(Integer.parseInt(properties.getProperty("changeEmailX")),
                    Integer.parseInt(properties.getProperty("changeEmailY")),
                    Integer.parseInt(properties.getProperty("changeEmailWidth")),
                    Integer.parseInt(properties.getProperty("changeEmailHeight")));
            phoneField.setBackground(new Color(Integer.parseInt(properties.getProperty("red")),
                    Integer.parseInt(properties.getProperty("green")),
                    Integer.parseInt(properties.getProperty("blue"))));
            phoneField.setBounds(Integer.parseInt(properties.getProperty("phoneFieldX")),
                    Integer.parseInt(properties.getProperty("phoneFieldY")),
                    Integer.parseInt(properties.getProperty("phoneFieldWidth")),
                    Integer.parseInt(properties.getProperty("phoneFieldHeight")));
            phoneField.setText(MainController.infoAgent.showPhone(MainController.username));
            changePhoneButton.setText(properties.getProperty("changePhone"));
            changePhoneButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            changePhoneButton.setBounds(Integer.parseInt(properties.getProperty("changePhoneX")),
                    Integer.parseInt(properties.getProperty("changePhoneY")),
                    Integer.parseInt(properties.getProperty("changePhoneWidth")),
                    Integer.parseInt(properties.getProperty("changePhoneHeight")));
            birthdateLabel.setText(properties.getProperty("birthLabel"));
            birthdateLabel.setBounds(Integer.parseInt(properties.getProperty("birthLabelX")),
                    Integer.parseInt(properties.getProperty("birthLabelY")),
                    Integer.parseInt(properties.getProperty("birthLabelWidth")),
                    Integer.parseInt(properties.getProperty("birthLabelHeight")));
            birthField.setBackground(new Color(Integer.parseInt(properties.getProperty("red")),
                    Integer.parseInt(properties.getProperty("green")),
                    Integer.parseInt(properties.getProperty("blue"))));
            birthField.setBounds(Integer.parseInt(properties.getProperty("birthFieldX")),
                    Integer.parseInt(properties.getProperty("birthFieldY")),
                    Integer.parseInt(properties.getProperty("birthFieldWidth")),
                    Integer.parseInt(properties.getProperty("birthFieldHeight")));
            birthField.setText(MainController.infoAgent.showBirth(MainController.username));
            birthButton.setText(properties.getProperty("birth"));
            birthButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            birthButton.setBounds(Integer.parseInt(properties.getProperty("birthX")),
                    Integer.parseInt(properties.getProperty("birthY")),
                    Integer.parseInt(properties.getProperty("birthWidth")),
                    Integer.parseInt(properties.getProperty("birthHeight")));
            bioLabel.setText(properties.getProperty("bioLabel"));
            bioLabel.setBounds(Integer.parseInt(properties.getProperty("bioLabelX")),
                    Integer.parseInt(properties.getProperty("bioLabelY")),
                    Integer.parseInt(properties.getProperty("bioLabelWidth")),
                    Integer.parseInt(properties.getProperty("bioLabelHeight")));
            bioField.setBackground(new Color(Integer.parseInt(properties.getProperty("red")),
                    Integer.parseInt(properties.getProperty("green")),
                    Integer.parseInt(properties.getProperty("blue"))));
            bioField.setBounds(Integer.parseInt(properties.getProperty("bioFieldX")),
                    Integer.parseInt(properties.getProperty("bioFieldY")),
                    Integer.parseInt(properties.getProperty("bioFieldWidth")),
                    Integer.parseInt(properties.getProperty("bioFieldHeight")));
            bioField.setText(MainController.infoAgent.showBio(MainController.username));
            changeBioButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            changeBioButton.setBounds(Integer.parseInt(properties.getProperty("bioX")),
                    Integer.parseInt(properties.getProperty("bioY")),
                    Integer.parseInt(properties.getProperty("bioWidth")),
                    Integer.parseInt(properties.getProperty("bioHeight")));
        }catch (Exception e){
            e.printStackTrace();
        }
        Photo.addPhoto();

        MainController.panel.setBackground(Color.lightGray);
        MainController.panel.add(userLabel);
        MainController.panel.add(passwordLabel);
        MainController.panel.add(showPassword);
        MainController.panel.add(userTextField);
        MainController.panel.add(passwordField);
        MainController.panel.add(nameLabel);
        MainController.panel.add(nameField);
        MainController.panel.add(emailLabel);
        MainController.panel.add(emailField);
        MainController.panel.add(phoneLabel);
        MainController.panel.add(phoneField);
        MainController.panel.add(birthdateLabel);
        MainController.panel.add(birthField);
        MainController.panel.add(bioLabel);
        MainController.panel.add(bioField);
        MainController.panel.add(birthButton);
        MainController.panel.add(changePassButton);
        MainController.panel.add(changeNameButton);
        MainController.panel.add(changeEmailButton);
        MainController.panel.add(changePhoneButton);
        MainController.panel.add(changeBioButton);
        MainController.panel.add(warn);
        MainController.panel.revalidate();
        MainController.panel.repaint();

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

        birthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                birthField.setText(MainController.infoAgent.addBirthdate(MainController.username,birthField.getText()));
            }
        });

        changeBioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bioField.setText(MainController.infoAgent.addBio(MainController.username,bioField.getText()));
            }
        });

        changeNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                warn.setText(MainController.infoAgent.changeFullName(MainController.username,nameField.getText(),warn.getText()));

            }
        });

        changePassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                warn.setText(MainController.infoAgent.changePassword(MainController.username,passwordField.getText(),warn.getText()));
            }
        });

        changeEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                warn.setText(MainController.infoAgent.changeEmail(MainController.username,emailField.getText(),warn.getText()));
            }
        });

        changePhoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                warn.setText(MainController.infoAgent.changePhoneNumber(MainController.username,phoneField.getText(),warn.getText()));
            }
        });







    }

}

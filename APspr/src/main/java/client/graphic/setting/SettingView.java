package client.graphic.setting;

import shared.events.settingsEvent.*;
import client.graphic.welcome.LoginView;
import client.graphic.interfaces.BackToMenuButton;
import client.graphic.interfaces.CloseButton;
import client.graphic.interfaces.MenuButton;
import client.listener.SettingListener;
import client.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.Properties;

public class SettingView implements MenuButton, CloseButton, BackToMenuButton {
    public void settingPage(){
        SettingListener settingListener = new SettingListener();
        MainController.panel.removeAll();
        menuButton();
        closeButton();
        backButton();
        JButton logoutButton = new JButton();
        JButton deleteButton = new JButton();
        JButton privacyButton = new JButton();
        JButton inactivateButton = new JButton();
        JButton lastSeenButton = new JButton();
        try {
            FileReader reader = new FileReader("resources/configuration/settingsView.properties");
            Properties properties = new Properties();
            properties.load(reader);
            logoutButton.setText(properties.getProperty("logout"));
            logoutButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            logoutButton.setBounds(Integer.parseInt(properties.getProperty("logoutX")),
                    Integer.parseInt(properties.getProperty("logoutY")),
                    Integer.parseInt(properties.getProperty("logoutWidth")),
                    Integer.parseInt(properties.getProperty("logoutHeight")));
            deleteButton.setText(properties.getProperty("delete"));
            deleteButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            deleteButton.setBounds(Integer.parseInt(properties.getProperty("deleteX")),
                    Integer.parseInt(properties.getProperty("deleteY")),
                    Integer.parseInt(properties.getProperty("deleteWidth")),
                    Integer.parseInt(properties.getProperty("deleteHeight")));
            privacyButton.setText(properties.getProperty("privacy"));
            privacyButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            privacyButton.setBounds(Integer.parseInt(properties.getProperty("privacyX")),
                    Integer.parseInt(properties.getProperty("privacyY")),
                    Integer.parseInt(properties.getProperty("privacyWidth")),
                    Integer.parseInt(properties.getProperty("privacyHeight")));
            inactivateButton.setText(properties.getProperty("inactivate"));
            inactivateButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            inactivateButton.setBounds(Integer.parseInt(properties.getProperty("inactivateX")),
                    Integer.parseInt(properties.getProperty("inactivateY")),
                    Integer.parseInt(properties.getProperty("inactivateWidth")),
                    Integer.parseInt(properties.getProperty("inactivateHeight")));
            lastSeenButton.setText(properties.getProperty("seen"));
            lastSeenButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            lastSeenButton.setBounds(Integer.parseInt(properties.getProperty("seenX")),
                    Integer.parseInt(properties.getProperty("seenY")),
                    Integer.parseInt(properties.getProperty("seenWidth")),
                    Integer.parseInt(properties.getProperty("seenHeight")));
        }catch (Exception e){
            e.printStackTrace();
        }
        MainController.panel.add(logoutButton);
        MainController.panel.add(deleteButton);
        MainController.panel.add(privacyButton);
        MainController.panel.add(inactivateButton);
        MainController.panel.add(lastSeenButton);
        MainController.panel.revalidate();
        MainController.panel.repaint();

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(logoutButton,"Are You Sure?");
                if (a == JOptionPane.YES_OPTION){
                    LoginView loginView = new LoginView();
                    SendLogOutEvent sendLogOutEvent = new SendLogOutEvent(loginView);
                    settingListener.logOut(sendLogOutEvent);
                    //loginView.loginPage();
                }

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileReader reader = new FileReader("resources/configuration/settingsView.properties");
                    Properties properties = new Properties();
                    properties.load(reader);
                    int a = JOptionPane.showConfirmDialog(deleteButton,properties.getProperty("message"));
                    if (a == JOptionPane.YES_OPTION){
                        SendDeleteAccEvent sendDeleteAccEvent = new SendDeleteAccEvent(MainController.username);
                        settingListener.deleteAccount(sendDeleteAccEvent);
                        //MainController.infoAgent.deleteYourAccount(MainController.username);
                        //safe logino nmiare
                        LoginView loginView = new LoginView();
                        loginView.loginPage();
                    }
                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });

        inactivateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    FileReader reader = new FileReader("resources/configuration/settingsView.properties");
                    Properties properties = new Properties();
                    properties.load(reader);
                    int a = JOptionPane.showConfirmDialog(inactivateButton,properties.getProperty("message"));
                    if (a == JOptionPane.YES_OPTION){
                        SendInActivateEvent sendInActivateEvent = new SendInActivateEvent(MainController.username);
                        settingListener.inactivate(sendInActivateEvent);
                        //MainController.infoAgent.inactivate(MainController.username);
                        LoginView loginView = new LoginView();
                        loginView.loginPage();
                    }
                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });

        privacyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrivacyView privacyButton = new PrivacyView();
                SendPrivacyEvent sendPrivacyEvent = new SendPrivacyEvent(privacyButton);
                settingListener.pressPrivacy(sendPrivacyEvent);
                //privacyButton.privacyPage();
            }
        });
        lastSeenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LastSeenView lastSeenButton1 = new LastSeenView();
                SendLastSeenEvent sendLastSeenEvent = new SendLastSeenEvent(lastSeenButton1);
                settingListener.pressLastSeen(sendLastSeenEvent);
                //lastSeenButton1.lastSeenPage();
            }
        });


    }
}

package client.graphic.setting;

import client.graphic.interfaces.BackToSettingButton;
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

public class PrivacyView implements MenuButton, BackToSettingButton, CloseButton {
    public void privacyPage(){
        SettingListener settingListener = new SettingListener();
        MainController.panel.removeAll();
        menuButton();
        closeButton();
        backToSetting();
        JCheckBox changeButton = new JCheckBox();
        JLabel warn = new JLabel();
        JCheckBox permissionButton = new JCheckBox();
        JLabel label = new JLabel();
        JLabel warnLabel = new JLabel();
        try {
            FileReader reader = new FileReader("resources/configuration/privacy.properties");
            Properties properties = new Properties();
            properties.load(reader);
            changeButton.setText(properties.getProperty("change"));
            changeButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            changeButton.setBounds(Integer.parseInt(properties.getProperty("changeX")),
                    Integer.parseInt(properties.getProperty("changeY")),
                    Integer.parseInt(properties.getProperty("changeWidth")),
                    Integer.parseInt(properties.getProperty("changeHeight")));
            warn.setBounds(Integer.parseInt(properties.getProperty("wX")),
                    Integer.parseInt(properties.getProperty("wY")),
                    Integer.parseInt(properties.getProperty("wWidth")),
                    Integer.parseInt(properties.getProperty("wHeight")));
            permissionButton.setText(properties.getProperty("permit"));
            permissionButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            permissionButton.setBounds(Integer.parseInt(properties.getProperty("permitX")),
                    Integer.parseInt(properties.getProperty("permitY")),
                    Integer.parseInt(properties.getProperty("permitWidth")),
                    Integer.parseInt(properties.getProperty("permitHeight")));
            label.setText(properties.getProperty("label"));
            label.setBounds(Integer.parseInt(properties.getProperty("lX")),
                    Integer.parseInt(properties.getProperty("lY")),
                    Integer.parseInt(properties.getProperty("lWidth")),
                    Integer.parseInt(properties.getProperty("lHeight")));
            warnLabel.setBounds(Integer.parseInt(properties.getProperty("w2X")),
                    Integer.parseInt(properties.getProperty("w2Y")),
                    Integer.parseInt(properties.getProperty("w2Width")),
                    Integer.parseInt(properties.getProperty("w2Height")));
        }catch (Exception e){
            e.printStackTrace();
        }

        MainController.panel.setBackground(Color.lightGray);
        MainController.panel.add(warnLabel);
        MainController.panel.add(label);
        MainController.panel.add(warn);
        MainController.panel.add(permissionButton);
        MainController.panel.add(changeButton);
        MainController.panel.revalidate();
        MainController.panel.repaint();
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //SendSwitchEvent sendSwitchEvent = new SendSwitchEvent(MainController.username,warn.getText());
                //settingListener.switchPrivacy(sendSwitchEvent);
                warn.setText(MainController.infoAgent.switchPrivacy(MainController.username,warn.getText()));
            }
        });
        permissionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                warnLabel.setText(MainController.infoAgent.changePermission(MainController.username,warnLabel.getText()));
            }
        });
    }
}

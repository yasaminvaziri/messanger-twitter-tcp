package client.graphic.setting;

import client.graphic.interfaces.BackToSettingButton;
import client.graphic.interfaces.CloseButton;
import client.graphic.interfaces.MenuButton;
import client.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.Properties;

public class LastSeenView implements MenuButton, CloseButton, BackToSettingButton {
    public void lastSeenPage(){
        MainController.panel.removeAll();
        menuButton();
        closeButton();
        backToSetting();
        JButton everyone = new JButton();
        JButton followingsOnly = new JButton();
        JButton nobody = new JButton();
        JLabel warn = new JLabel();
        try {
            FileReader reader = new FileReader("resources/configuration/lastSeen.properties");
            Properties properties = new Properties();
            properties.load(reader);
            everyone.setText(properties.getProperty("everyone"));
            everyone.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            everyone.setBounds(Integer.parseInt(properties.getProperty("eX")),
                    Integer.parseInt(properties.getProperty("eY")),
                    Integer.parseInt(properties.getProperty("eWidth")),
                    Integer.parseInt(properties.getProperty("eHeight")));
            followingsOnly.setText(properties.getProperty("fO"));
            followingsOnly.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            followingsOnly.setBounds(Integer.parseInt(properties.getProperty("fX")),
                    Integer.parseInt(properties.getProperty("fY")),
                    Integer.parseInt(properties.getProperty("fWidth")),
                    Integer.parseInt(properties.getProperty("fHeight")));
            nobody.setText(properties.getProperty("no"));
            nobody.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            nobody.setBounds(Integer.parseInt(properties.getProperty("nX")),
                    Integer.parseInt(properties.getProperty("nY")),
                    Integer.parseInt(properties.getProperty("nWidth")),
                    Integer.parseInt(properties.getProperty("nHeight")));
            warn.setBounds(Integer.parseInt(properties.getProperty("wX")),
                    Integer.parseInt(properties.getProperty("wY")),
                    Integer.parseInt(properties.getProperty("wWidth")),
                    Integer.parseInt(properties.getProperty("wHeight")));

        }catch (Exception e){
            e.printStackTrace();
        }
        MainController.panel.add(warn);
        MainController.panel.add(nobody);
        MainController.panel.add(followingsOnly);
        MainController.panel.add(everyone);
        MainController.panel.revalidate();
        MainController.panel.repaint();
        everyone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                warn.setText(MainController.infoAgent.lastSeenEveryone(MainController.username,warn.getText()));
            }
        });
        followingsOnly.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                warn.setText(MainController.infoAgent.lastSeenFollowings(MainController.username,warn.getText()));
            }
        });
        nobody.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                warn.setText(MainController.infoAgent.lastSeenNobody(MainController.username,warn.getText()));
            }
        });
    }

}

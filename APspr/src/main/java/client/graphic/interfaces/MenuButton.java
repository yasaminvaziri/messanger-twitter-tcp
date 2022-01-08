package client.graphic.interfaces;

import shared.events.backsEvent.SendBackToMenuEvent;
import client.graphic.welcome.MenuView;
import client.listener.BacksListener;
import client.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.Properties;

public interface MenuButton {
    default void menuButton(){
        BacksListener backsListener = new BacksListener();
        JButton menuButton = new JButton();
        try {
            FileReader reader = new FileReader("resources/configuration/menu.properties");
            Properties properties = new Properties();
            properties.load(reader);
            menuButton.setText(properties.getProperty("menuButton"));
            menuButton.setBounds(Integer.parseInt(properties.getProperty("menuX")),
                    Integer.parseInt(properties.getProperty("menuY")),
                    Integer.parseInt(properties.getProperty("menuWidth")),
                    Integer.parseInt(properties.getProperty("menuHeight")));
            menuButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));

        }catch (Exception e){
            e.printStackTrace();
        }
        MainController.panel.add(menuButton);
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuView menuView = new MenuView();
                SendBackToMenuEvent sendBackToMenuEvent = new SendBackToMenuEvent(menuView);
                backsListener.backToMenu(sendBackToMenuEvent);
                //menuView.menuPage();
            }
        });
    }
}

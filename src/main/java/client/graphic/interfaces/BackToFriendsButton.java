package client.graphic.interfaces;


import shared.events.backsEvent.SendBackToFriendsEvent;
import client.graphic.friendLists.Friends;
import client.listener.BacksListener;
import client.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.Properties;

public interface BackToFriendsButton {
    default void backToFriends(){
        BacksListener backsListener = new BacksListener();
        JButton backButton = new JButton();
        try {
            FileReader reader = new FileReader("resources/configuration/backs.properties");
            Properties properties = new Properties();
            properties.load(reader);
            backButton.setText(properties.getProperty("back"));
            backButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            backButton.setBounds(Integer.parseInt(properties.getProperty("backX")),
                    Integer.parseInt(properties.getProperty("backY")),
                    Integer.parseInt(properties.getProperty("backWidth")),
                    Integer.parseInt(properties.getProperty("backHeight")));
        }catch (Exception e){
            e.printStackTrace();
        }
        MainController.panel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Friends friends = new Friends();
                SendBackToFriendsEvent sendBackToFriendsEvent = new SendBackToFriendsEvent(friends,MainController.username);
                backsListener.backToFriends(sendBackToFriendsEvent);
                //friends.friendsPage(MainController.logicAgent.showFollowers(MainController.username));

            }
        });
    }
}

package client.graphic.home;

import shared.events.homeEvents.SendActivityEvent;
import shared.events.homeEvents.SendFriendsEvent;
import shared.events.homeEvents.SendInfoEvent;
import client.graphic.activity.ActivityView;
import client.graphic.friendLists.Friends;
import client.graphic.interfaces.BackToMenuButton;
import client.graphic.interfaces.CloseButton;
import client.graphic.interfaces.MenuButton;
import client.listener.HomeListener;
import client.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.Properties;

public class HomePageView implements MenuButton, CloseButton, BackToMenuButton {
    public void homePage(){
        HomeListener homeListener = new HomeListener();
        MainController.panel.removeAll();
        menuButton();
        closeButton();
        backButton();
        JButton activityButton = new JButton();
        JButton infoButton = new JButton();
        JButton friendButton = new JButton();
        try {
            FileReader reader = new FileReader("resources/configuration/homeView.properties");
            Properties properties = new Properties();
            properties.load(reader);
            activityButton.setText(properties.getProperty("activityButton"));
            activityButton.setBounds(Integer.parseInt(properties.getProperty("activityButtonX")),
                    Integer.parseInt(properties.getProperty("activityButtonY")),
                    Integer.parseInt(properties.getProperty("activityButtonWidth")),
                    Integer.parseInt(properties.getProperty("activityButtonHeight")));
            activityButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            infoButton.setText(properties.getProperty("infoButton"));
            infoButton.setBounds(Integer.parseInt(properties.getProperty("infoX")),
                    Integer.parseInt(properties.getProperty("infoY")),
                    Integer.parseInt(properties.getProperty("infoWidth")),
                    Integer.parseInt(properties.getProperty("infoHeight")));
            infoButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            friendButton.setText(properties.getProperty("friendsButton"));
            friendButton.setBounds(Integer.parseInt(properties.getProperty("friendsX")),
                    Integer.parseInt(properties.getProperty("friendsY")),
                    Integer.parseInt(properties.getProperty("friendsWidth")),
                    Integer.parseInt(properties.getProperty("friendsHeight")));
            friendButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
        }catch (Exception e){
            e.printStackTrace();
        }

        MainController.panel.setBackground(Color.lightGray);
        MainController.panel.add(activityButton);
        MainController.panel.add(infoButton);
        MainController.panel.add(friendButton);
        MainController.panel.revalidate();
        MainController.panel.repaint();



        activityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ActivityView activityView = new ActivityView();
                SendActivityEvent sendActivityEvent = new SendActivityEvent(activityView,MainController.username);
                homeListener.goToActivity(sendActivityEvent);
               //activityView.activity(MainController.logicAgent.showFollowers(MainController.username));
            }
        });

        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfoView infoView = new InfoView();
                SendInfoEvent infoEvent = new SendInfoEvent(infoView);
                homeListener.goToInfo(infoEvent);
                //infoView.infoPage();
            }
        });


        friendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Friends friends = new Friends();
                SendFriendsEvent sendFriendsEvent = new SendFriendsEvent(friends);
                homeListener.goToFriends(sendFriendsEvent);
                //friends.friendsPage(MainController.logicAgent.showFollowers(MainController.username));
            }
        });



    }
}

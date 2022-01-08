package client.graphic.welcome;

import shared.events.welcomeEvents.SendMenuEvent;
import client.graphic.explorer.ExplorerView;
import client.graphic.home.HomePageView;
import client.graphic.interfaces.CloseButton;
import client.graphic.messaging.MessagingView;
import client.graphic.setting.SettingView;
import client.graphic.timeLine.TimeLineView;
import client.listener.MenuListener;
import client.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.util.Properties;

public class  MenuView implements CloseButton {
    MenuListener menuListener = new MenuListener();

    public void menuPage(){

        MainController.panel.removeAll();
        JButton homeButton = new JButton();
        JButton timeLineButton = new JButton();
        JButton explorerButton = new JButton();
        JButton settingButton = new JButton();
        JButton messageButton = new JButton();
        try {
            FileReader reader = new FileReader("resources/configuration/menuView.properties");
            Properties properties = new Properties();
            properties.load(reader);
            homeButton.setText(properties.getProperty("home"));
            homeButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            homeButton.setBounds(Integer.parseInt(properties.getProperty("homeX")),
                    Integer.parseInt(properties.getProperty("homeY")),
                    Integer.parseInt(properties.getProperty("homeWidth")),
                    Integer.parseInt(properties.getProperty("homeHeight")));
            timeLineButton.setText(properties.getProperty("timeLine"));
            timeLineButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            timeLineButton.setBounds(Integer.parseInt(properties.getProperty("timeLineX")),
                    Integer.parseInt(properties.getProperty("timeLineY")),
                    Integer.parseInt(properties.getProperty("timeLineWidth")),
                    Integer.parseInt(properties.getProperty("timeLineHeight")));
            explorerButton.setText(properties.getProperty("explorer"));
            explorerButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            explorerButton.setBounds(Integer.parseInt(properties.getProperty("explorerX")),
                    Integer.parseInt(properties.getProperty("explorerY")),
                    Integer.parseInt(properties.getProperty("explorerWidth")),
                    Integer.parseInt(properties.getProperty("explorerHeight")));
            settingButton.setText(properties.getProperty("setting"));
            settingButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            settingButton.setBounds(Integer.parseInt(properties.getProperty("settingX")),
                    Integer.parseInt(properties.getProperty("settingY")),
                    Integer.parseInt(properties.getProperty("settingWidth")),
                    Integer.parseInt(properties.getProperty("settingHeight")));
            messageButton.setText(properties.getProperty("message"));
            messageButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            messageButton.setBounds(Integer.parseInt(properties.getProperty("messageX")),
                    Integer.parseInt(properties.getProperty("messageY")),
                    Integer.parseInt(properties.getProperty("messageWidth")),
                    Integer.parseInt(properties.getProperty("messageHeight")));
        }catch (Exception e){
            e.printStackTrace();
        }

        closeButton();
        MainController.panel.setBackground(Color.lightGray);
        MainController.panel.add(homeButton);
        MainController.panel.add(timeLineButton);
        MainController.panel.add(explorerButton);
        MainController.panel.add(settingButton);
        MainController.panel.add(messageButton);
        MainController.panel.revalidate();
        MainController.panel.repaint();
        homeButton.addActionListener(this::pressHome);
        timeLineButton.addActionListener(this::pressTimeLine);
        explorerButton.addActionListener(this::pressExplorer);
        settingButton.addActionListener(this::pressSetting);
        messageButton.addActionListener(this::pressMessage);

//        homeButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                HomePageView homePageView = new HomePageView();
//                SendMenuEvent sendMenuEvent = new SendMenuEvent(homePageView);
//                menuListener.goToHome(sendMenuEvent);
//            }
//        });
//
//
//        timeLineButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                TimeLineView timeLineView = new TimeLineView();
//                SendMenuEvent sendMenuEvent = new SendMenuEvent(timeLineView);
//                menuListener.goToTimeLine(sendMenuEvent);
//                //timeLineView.timeLinePage(MainController.tweetAgent.showTweet(MainController.username));
//            }
//        });
//
//
//        explorerButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//               ExplorerView explorerView = new ExplorerView();
//               SendMenuEvent sendMenuEvent = new SendMenuEvent(explorerView);
//               menuListener.goToExplorer(sendMenuEvent);
//               //explorerView.explorerPage(MainController.tweetAgent.discover(MainController.username));
//            }
//        });
//
//
//        settingButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                SettingView settingView = new SettingView();
//                SendMenuEvent sendMenuEvent = new SendMenuEvent(settingView);
//                menuListener.goToSetting(sendMenuEvent);
//                //settingView.settingPage();
//            }
//        });
//
//
//        messageButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                MessagingView messagingView = new MessagingView();
//                SendMenuEvent sendMenuEvent = new SendMenuEvent(messagingView);
//                menuListener.goToMessaging(sendMenuEvent);
//                //messagingView.messagingView();
//            }
//        });
    }

    private void pressMessage(ActionEvent actionEvent) {
        MessagingView messagingView = new MessagingView();
        SendMenuEvent sendMenuEvent = new SendMenuEvent(messagingView);
        menuListener.goToMessaging(sendMenuEvent);
    }

    private void pressHome(ActionEvent actionEvent) {
        HomePageView homePageView = new HomePageView();
        SendMenuEvent sendMenuEvent = new SendMenuEvent(homePageView);
        menuListener.goToHome(sendMenuEvent);

    }

    private void pressTimeLine(ActionEvent actionEvent){
        TimeLineView timeLineView = new TimeLineView();
        SendMenuEvent sendMenuEvent = new SendMenuEvent(timeLineView);
        menuListener.goToTimeLine(sendMenuEvent);


    }
    private void pressExplorer(ActionEvent actionEvent){
        ExplorerView explorerView = new ExplorerView();
        SendMenuEvent sendMenuEvent = new SendMenuEvent(explorerView);
        menuListener.goToExplorer(sendMenuEvent);

    }
    private void pressSetting(ActionEvent actionEvent){
        SettingView settingView = new SettingView();
        SendMenuEvent sendMenuEvent = new SendMenuEvent(settingView);
        menuListener.goToSetting(sendMenuEvent);

    }

}

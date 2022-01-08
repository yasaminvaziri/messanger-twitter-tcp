package client.graphic.timeLine;

import client.controller.TimeLineController;
import client.graphic.interfaces.BackToMenuButton;
import client.graphic.interfaces.CloseButton;
import client.graphic.interfaces.MenuButton;
import client.listener.TimeLineListener;
import server.model.Tweet;
import client.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Properties;

public class TimeLineView implements MenuButton, CloseButton, BackToMenuButton {
    public JTextField forwardTo = new JTextField();
    public TimeLineListener timeLineListener = new TimeLineListener();
    TimeLineController timeLineController;
    JTextField tweetText = new JTextField();
    final File f[] = {null};
    public void timeLinePage(LinkedList<Tweet> tweets){
        timeLineController = new TimeLineController(this);
        MainController.panel.removeAll();
        menuButton();
        backButton();
        closeButton();

        JButton sendTweetButton= new JButton();
        JButton sendPhotoButton = new JButton();
        JLabel ft = new JLabel();

        try {
            FileReader reader = new FileReader("resources/configuration/timeLine.properties");
            Properties properties = new Properties();
            properties.load(reader);
            tweetText.setBackground(new Color(Integer.parseInt(properties.getProperty("red")),
                    Integer.parseInt(properties.getProperty("green")),Integer.parseInt(properties.getProperty("blue"))));
            tweetText.setBounds(Integer.parseInt(properties.getProperty("tweetTextX")),
                    Integer.parseInt(properties.getProperty("tweetTextY")),
                    Integer.parseInt(properties.getProperty("tweetTextWidth")),
                    Integer.parseInt(properties.getProperty("tweetTextHeight")));
            sendTweetButton.setText(properties.getProperty("sendButton"));
            sendTweetButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            sendTweetButton.setBounds(Integer.parseInt(properties.getProperty("sendButtonX")),
                    Integer.parseInt(properties.getProperty("sendButtonY")),
                    Integer.parseInt(properties.getProperty("sendButtonWidth")),
                    Integer.parseInt(properties.getProperty("sendButtonHeight")));
            sendPhotoButton.setText(properties.getProperty("sendPhoto"));
            sendPhotoButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            sendPhotoButton.setBounds(Integer.parseInt(properties.getProperty("sendPhotoX")),
                    Integer.parseInt(properties.getProperty("sendPhotoY")),
                    Integer.parseInt(properties.getProperty("sendPhotoWidth")),
                    Integer.parseInt(properties.getProperty("sendPhotoHeight")));
            forwardTo.setBounds(Integer.parseInt(properties.getProperty("forwardToX")),
                    Integer.parseInt(properties.getProperty("forwardToY")),
                    Integer.parseInt(properties.getProperty("forwardToWidth")),
                    Integer.parseInt(properties.getProperty("forwardToHeight")));
            ft.setText(properties.getProperty("forward"));
            ft.setBounds(Integer.parseInt(properties.getProperty("ftX")),
                    Integer.parseInt(properties.getProperty("ftY")),
                    Integer.parseInt(properties.getProperty("ftWidth")),
                    Integer.parseInt(properties.getProperty("ftHeight")));

        }catch (Exception e){
            e.printStackTrace();
        }

        MainController.panel.add(tweetText);
        MainController.panel.add(sendTweetButton);
        MainController.panel.add(sendPhotoButton);
        MainController.panel.add(forwardTo);
        MainController.panel.add(ft);
        MainController.panel.setBackground(Color.lightGray);
        TweetPanelToTimeLine tweetPanelToTimeLine = new TweetPanelToTimeLine();
        tweetPanelToTimeLine.tweetPanel(tweets);
        MainController.panel.revalidate();
        MainController.panel.repaint();
        sendTweetButton.addActionListener(this::tweetPressed);
        // final File f[] = {null};
        sendPhotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                f[0] = chooser.getSelectedFile();
            }

        });

//        sendTweetButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                SendTweetEvent sendTweetEvent = new SendTweetEvent(MainController.username,tweetText.getText(),f[0]);
//                timeLineListener.sendTweet(sendTweetEvent);
//                System.out.println(f[0] != null);
//                timeLinePage(MainController.tweetAgent.showTweet(MainController.username));
//            }
//        });


    }

    private void tweetPressed(ActionEvent actionEvent) {
        timeLineController.sendTweetPressed(MainController.username,tweetText.getText(),f[0]);
        timeLinePage(MainController.tweetAgent.showTweet(MainController.username));

    }
}

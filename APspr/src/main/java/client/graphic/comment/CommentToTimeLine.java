package client.graphic.comment;

import shared.events.timeLineEvents.*;
import client.graphic.profile.ShowProfileView;
import client.graphic.interfaces.ScrollPaneFromBottom;
import client.listener.TimeLineListener;
import server.controller.FileImage;
import server.model.Tweet;
import client.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Properties;

import static client.graphic.photo.Photo.scaleFileImage;

public class CommentToTimeLine extends CommentView implements ScrollPaneFromBottom {

    public void commentPanel(LinkedList<Tweet> comments, LinkedList<Tweet> backs){
        TimeLineListener timeLineListener = new TimeLineListener();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollToBottom(scrollPane);
        try {
            FileReader reader = new FileReader("resources/configuration/tweets.properties");
            Properties properties = new Properties();
            properties.load(reader);
            scrollPane.setBounds(Integer.parseInt(properties.getProperty("scrollPaneX")),
                    Integer.parseInt(properties.getProperty("scrollPaneY")),
                    Integer.parseInt(properties.getProperty("scrollPaneWidth")),
                    Integer.parseInt(properties.getProperty("scrollPaneHeight")));

        }catch (Exception e){
            e.printStackTrace();
        }
        for (Tweet tweet: comments) {
            JLabel photo = new JLabel();
            photo.setIcon(scaleFileImage(FileImage.loadImage(tweet.getUser().getUsername())));
            JLabel label = new JLabel();
            label.setText(tweet.getT());
            JButton like = new JButton();
            JButton save = new JButton();
            JButton disLike = new JButton();
            JButton report = new JButton();
            JButton forward = new JButton();
            JButton seeP = new JButton();
            JButton comment = new JButton();
            JButton retweet = new JButton();
            try {
                FileReader reader = new FileReader("resources/configuration/tweets.properties");
                Properties properties = new Properties();
                properties.load(reader);
                like.setText(properties.getProperty("like"));
                like.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));
                save.setText(properties.getProperty("save"));
                save.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));
                disLike.setText(properties.getProperty("dislike"));
                disLike.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));
                report.setText(properties.getProperty("report"));
                report.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));
                forward.setText(properties.getProperty("forward"));
                forward.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));
                seeP.setText(properties.getProperty("profile"));
                seeP.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));
                comment.setText(properties.getProperty("comment"));
                comment.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));
                retweet.setText(properties.getProperty("retweet"));
                retweet.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));

            }catch (Exception e){
                e.printStackTrace();
            }
            panel.add(photo);
            panel.add(label);
            panel.add(like);
            panel.add(save);
            panel.add(report);
            panel.add(disLike);
            panel.add(forward);
            panel.add(retweet);
            panel.add(comment);
            panel.add(seeP);

            comment.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CommentView commentView = new CommentView();
                    backs.add(tweet);
                    commentView.commentPage(MainController.tweetAgent.idToTweet( tweet.getCommentList()),tweet, backs);
                }
            });

            seeP.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ShowProfileView showProfileView = new ShowProfileView();
                    SendShowProfileEvent sendShowProfileEvent = new SendShowProfileEvent(showProfileView,tweet);
                    timeLineListener.showProfile(sendShowProfileEvent);
                    //showProfileView.showProfile(tweet.getUser().getUsername());
                }
            });

            like.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SendLikeEvent sendLikeEvent = new SendLikeEvent(MainController.username,tweet);
                    timeLineListener.like(sendLikeEvent);
                    //MainController.tweetAgent.likeTweet(tweet,MainController.username);
                }
            });

            disLike.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SendDislikeEvent sendDislikeEvent = new SendDislikeEvent(MainController.username,tweet);
                    timeLineListener.disLike(sendDislikeEvent);
                    //MainController.tweetAgent.dislikeTweet(tweet,MainController.username);
                }
            });

            report.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SendReportEvent sendReportEvent = new SendReportEvent(MainController.username,tweet);
                    timeLineListener.reportTweet(sendReportEvent);
                    //MainController.tweetAgent.reportTweet(tweet,MainController.username);
                }
            });

            retweet.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SendRetweetEvent sendRetweetEvent = new SendRetweetEvent(tweet,MainController.username);
                    timeLineListener.retweet(sendRetweetEvent);
                    //MainController.tweetAgent.retweet(tweet,MainController.username);
                    commentPage(comments,tweet,backs);
                }
            });

            forward.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SendForwardEvent sendForwardEvent = new SendForwardEvent(MainController.username,forwardTo.getText(),tweet);
                    timeLineListener.forwardTweet(sendForwardEvent);
                    //MainController.messageAgent.forwardTweet(MainController.username,forwardTo.getText(),tweet);
                }
            });

            save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SavedMessageEvent savedMessageEvent = new SavedMessageEvent(tweet.getUser().getUsername(),tweet);
                    timeLineListener.addTweetToSavedMessages(savedMessageEvent);
                    //MainController.messageAgent.addTweetToSaved(tweet.getUser().getUsername(),tweet);
                }
            });

        }
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        MainController.panel.add(scrollPane);
        MainController.panel.revalidate();
        MainController.panel.repaint();




    }
}

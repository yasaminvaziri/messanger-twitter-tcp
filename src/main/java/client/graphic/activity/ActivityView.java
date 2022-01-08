package client.graphic.activity;

import shared.events.activityEvents.SendRejectedFromEvent;
import shared.events.activityEvents.SendRequestViewEvent;
import shared.events.activityEvents.SendRequestedToEvent;
import shared.events.activityEvents.SendUnfollowedFromEvent;
import client.graphic.interfaces.*;
import client.listener.ActivityListener;
import client.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Properties;

public class ActivityView implements MenuButton, CloseButton, BackToHomeButton, ScrollPaneFromBottom {
    public void activity(LinkedList<String> followed_you){
        ActivityListener activityListener = new ActivityListener();
        MainController.panel.removeAll();
        menuButton();
        closeButton();
        backToHome();
        JButton requests = new JButton();
        JButton rejected_from = new JButton();
        JButton your_requests = new JButton();
        JButton unfollowed_you = new JButton();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollToBottom(scrollPane);
        try(FileReader reader = new FileReader("resources/configuration/activityView.properties")){
            Properties properties = new Properties();
            properties.load(reader);
            requests.setText(properties.getProperty("requests"));
            requests.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            requests.setBounds(Integer.parseInt(properties.getProperty("requestsX")),
                    Integer.parseInt(properties.getProperty("requestsY")),
                    Integer.parseInt(properties.getProperty("requestsWidth")),
                    Integer.parseInt(properties.getProperty("requestsHeight")));
            rejected_from.setText(properties.getProperty("rejected_from"));
            rejected_from.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            rejected_from.setBounds(Integer.parseInt(properties.getProperty("rejected_fromX")),
                    Integer.parseInt(properties.getProperty("rejected_fromY")),
                    Integer.parseInt(properties.getProperty("rejected_fromWidth")),
                    Integer.parseInt(properties.getProperty("rejected_fromHeight")));
            your_requests.setText(properties.getProperty("your_requests"));
            your_requests.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            your_requests.setBounds(Integer.parseInt(properties.getProperty("your_requestsX")),
                    Integer.parseInt(properties.getProperty("your_requestsY")),
                    Integer.parseInt(properties.getProperty("your_requestsWidth")),
                    Integer.parseInt(properties.getProperty("your_requestsHeight")));
            unfollowed_you.setText(properties.getProperty("unFollowed_you"));
            unfollowed_you.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            unfollowed_you.setBounds(Integer.parseInt(properties.getProperty("unFollowed_youX")),
                    Integer.parseInt(properties.getProperty("unFollowed_youY")),
                    Integer.parseInt(properties.getProperty("unFollowed_youWidth")),
                    Integer.parseInt(properties.getProperty("unFollowed_youHeight")));
            scrollPane.setBounds(Integer.parseInt(properties.getProperty("scrollPaneX")),
                    Integer.parseInt(properties.getProperty("scrollPaneY")),
                    Integer.parseInt(properties.getProperty("scrollPaneWidth")),
                    Integer.parseInt(properties.getProperty("scrollPaneHeight")));
        }catch (Exception e){
            e.printStackTrace();
        }
        for (String names : followed_you){
            JLabel nameLabel = new JLabel();
            try {
                FileReader reader = new FileReader("resources/configuration/activityView.properties");
                Properties properties = new Properties();
                properties.load(reader);
                nameLabel.setFont(new Font(properties.getProperty("fontName"),
                        Font.PLAIN,Integer.parseInt(properties.getProperty("fontSize"))));
                nameLabel.setText(names + " " + properties.getProperty("nameLabelText") );
            }catch (Exception e){
                e.printStackTrace();
            }
            panel.add(nameLabel);
        }
        unfollowed_you.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UnfollowedFrom unfollowedFrom = new UnfollowedFrom();
                SendUnfollowedFromEvent sendUnfollowedFromEvent = new SendUnfollowedFromEvent(unfollowedFrom);
                activityListener.unfollowedFrom(sendUnfollowedFromEvent);
                //unfollowedFrom.unfollowedFrom(MainController.logicAgent.showUnfollowedFrom(MainController.username));
            }
        });
        requests.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RequestsView requestsView = new RequestsView();
                SendRequestViewEvent sendRequestViewEvent = new SendRequestViewEvent(requestsView);
                activityListener.requests(sendRequestViewEvent);
                //requestsView.request(MainController.logicAgent.showRequests(MainController.username));
            }
        });
        your_requests.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RequestedToView requestedToView = new RequestedToView();
                SendRequestedToEvent sendRequestedToEvent = new SendRequestedToEvent(requestedToView);
                activityListener.requestedTo(sendRequestedToEvent);
                //requestedToView.requestTo(MainController.logicAgent.showSentRequest(MainController.username));
            }
        });
        rejected_from.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RejectedFromView rejectedFromView = new RejectedFromView();
                SendRejectedFromEvent sendRejectedFromEvent = new SendRejectedFromEvent(rejectedFromView);
                activityListener.rejectedFrom(sendRejectedFromEvent);
                //rejectedFromView.rejectedFrom(MainController.logicAgent.showRejectedFrom(MainController.username));
            }
        });

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        MainController.panel.add(scrollPane);
        MainController.panel.add(unfollowed_you);
        MainController.panel.add(your_requests);
        MainController.panel.add(rejected_from);
        MainController.panel.add(requests);
        MainController.panel.revalidate();
        MainController.panel.repaint();

    }
}

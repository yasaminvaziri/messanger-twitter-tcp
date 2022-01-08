package client.graphic.friendLists;

import shared.events.friendsEvent.*;
import client.graphic.interfaces.BackToHomeButton;
import client.graphic.interfaces.CloseButton;
import client.graphic.interfaces.MenuButton;
import client.listener.HomeListener;
import client.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Friends implements MenuButton, CloseButton, BackToHomeButton {
    public void friendsPage(LinkedList<String> names){
        HomeListener homeListener = new HomeListener();
        MainController.panel.removeAll();
        menuButton();
        closeButton();
        backToHome();
        JButton followersButton = new JButton("FOLLOWERS");
        followersButton.setBackground(new Color(51,153,200));
        followersButton.setBounds(225,100,200,30);
        JButton followingsButton = new JButton("FOLLOWINGS");
        followingsButton.setBackground(new Color(51,153,200));
        followingsButton.setBounds(225,200,200,30);
        JButton blockListButton = new JButton("BLOCK LIST");
        blockListButton.setBackground(new Color(51,153,200));
        blockListButton.setBounds(225,300,200,30);
        JButton muteListButton = new JButton("MUTE LIST");
        muteListButton.setBackground(new Color(51,153,200));
        muteListButton.setBounds(225,400,200,30);
        JButton reportListButton = new JButton("REPORT LIST");
        reportListButton.setBackground(new Color(51,153,200));
        reportListButton.setBounds(225,500,200,30);
        MainController.panel.setBackground(Color.lightGray);
        MainController.panel.add(reportListButton);
        MainController.panel.add(muteListButton);
        MainController.panel.add(blockListButton);
        MainController.panel.add(followingsButton);
        MainController.panel.add(followersButton);
        MainController.panel.revalidate();
        MainController.panel.repaint();

        followersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Followers followers = new Followers();
                SendFollowersEvent sendFollowersEvent = new SendFollowersEvent(followers);
                homeListener.followers(sendFollowersEvent);
                //followers.followers(MainController.logicAgent.showFollowers(MainController.username));
            }
        });
        blockListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BlockList blockList = new BlockList();
                SendBlockListEvent sendBlockListEvent = new SendBlockListEvent(blockList);
                homeListener.blockList(sendBlockListEvent);
               // blockList.blockList(MainController.logicAgent.showBlockList(MainController.username));
            }
        });
        muteListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MuteList muteList = new MuteList();
                SendMuteListEvent sendMuteListEvent = new SendMuteListEvent(muteList);
                homeListener.muteList(sendMuteListEvent);
                //muteList.muteList(MainController.logicAgent.showMuteList(MainController.username));
            }
        });
        reportListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReportList reportList = new ReportList();
                SendReportListEvent sendReportListEvent = new SendReportListEvent(reportList);
                homeListener.reportList(sendReportListEvent);
                //reportList.reportList(MainController.logicAgent.showReportList(MainController.username));
            }
        });
        followingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Followings followings = new Followings();
                SendFollowingsEvent sendFollowingsEvent = new SendFollowingsEvent(followings);
                homeListener.followings(sendFollowingsEvent);
                //followings.followings(MainController.logicAgent.showFollowings(MainController.username));
            }
        });

    }
}

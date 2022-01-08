package client.graphic.messaging;

import shared.events.messageEvents.SendChatViewEvent;
import shared.events.messageEvents.SendGroupsViewEvent;
import shared.events.messageEvents.SendSavedMessageViewEvent;
import client.graphic.interfaces.BackToMenuButton;
import client.graphic.interfaces.CloseButton;
import client.graphic.interfaces.MenuButton;
import client.listener.MessageListener;
import client.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.Properties;

public class MessagingView implements CloseButton, MenuButton, BackToMenuButton {
    public void messagingView(){
        MessageListener messageListener = new MessageListener();
        MainController.panel.removeAll();
        closeButton();
        menuButton();
        backButton();
        JButton savedMessages = new JButton();
        JButton chats = new JButton();
        JButton groups = new JButton();
        try {
            FileReader reader = new FileReader("resources/configuration/messagingView.properties");
            Properties properties = new Properties();
            properties.load(reader);
            savedMessages.setText(properties.getProperty("sm"));
            savedMessages.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            savedMessages.setBounds(Integer.parseInt(properties.getProperty("smX")),
                    Integer.parseInt(properties.getProperty("smY")),
                    Integer.parseInt(properties.getProperty("smWidth")),
                    Integer.parseInt(properties.getProperty("smHeight")));
            chats.setText(properties.getProperty("c"));
            chats.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            chats.setBounds(Integer.parseInt(properties.getProperty("cX")),
                    Integer.parseInt(properties.getProperty("cY")),
                    Integer.parseInt(properties.getProperty("cWidth")),
                    Integer.parseInt(properties.getProperty("cHeight")));
            groups.setText(properties.getProperty("gr"));
            groups.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            groups.setBounds(Integer.parseInt(properties.getProperty("grX")),
                    Integer.parseInt(properties.getProperty("grY")),
                    Integer.parseInt(properties.getProperty("grWidth")),
                    Integer.parseInt(properties.getProperty("grHeight")));
        }catch (Exception e){
            e.printStackTrace();
        }

        MainController.panel.add(groups);
        MainController.panel.add(chats);
        MainController.panel.add(savedMessages);
        MainController.panel.revalidate();
        MainController.panel.repaint();

        savedMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SavedMessagesView savedMessagesView = new SavedMessagesView();
                SendSavedMessageViewEvent sendSavedMessageViewEvent = new SendSavedMessageViewEvent(savedMessagesView);
                messageListener.savedMessage(sendSavedMessageViewEvent);
                savedMessagesView.savedMessages(MainController.messageAgent.showSavedMessages(MainController.username));
            }
        });
        chats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChatsView chatsView = new ChatsView();
                SendChatViewEvent sendChatViewEvent = new SendChatViewEvent(chatsView);
                messageListener.goToChats(sendChatViewEvent);
                chatsView.chats(MainController.messageAgent.showNames(MainController.username));
            }
        });
        groups.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GroupsView groupsView = new GroupsView();
                SendGroupsViewEvent sendGroupsViewEvent = new SendGroupsViewEvent(groupsView);
                messageListener.goToGroups(sendGroupsViewEvent);
                groupsView.groupView(MainController.messageAgent.showGroupsName(MainController.username));
            }
        });

    }
}

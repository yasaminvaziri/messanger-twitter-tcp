package client.graphic.messaging;

import shared.events.messageEvents.AddGroupNameEvent;
import shared.events.messageEvents.LeftGroupEvent;
import shared.events.messageEvents.SendGroupChatViewEvent;
import client.graphic.interfaces.BackToMessagingButton;
import client.graphic.interfaces.CloseButton;
import client.graphic.interfaces.MenuButton;
import client.graphic.interfaces.ScrollPaneFromBottom;
import client.listener.MessageListener;
import server.model.Groups;
import client.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Properties;

public class GroupsView implements CloseButton, MenuButton, BackToMessagingButton, ScrollPaneFromBottom {
    public void groupView(LinkedList<Groups> groups){
        MessageListener messageListener = new MessageListener();
        MainController.panel.removeAll();
        closeButton();
        menuButton();
        backToMessaging();
        JTextField groupNameText = new JTextField();
        JLabel label = new JLabel();
        JButton addButton = new JButton();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollToBottom(scrollPane);
        try {
            FileReader reader = new FileReader("resources/configuration/chats.properties");
            Properties properties = new Properties();
            properties.load(reader);
            groupNameText.setBackground(new Color(Integer.parseInt(properties.getProperty("red")),
                    Integer.parseInt(properties.getProperty("green")),
                    Integer.parseInt(properties.getProperty("blue"))));
            groupNameText.setBounds(Integer.parseInt(properties.getProperty("fX")),
                    Integer.parseInt(properties.getProperty("fY")),
                    Integer.parseInt(properties.getProperty("fWidth")),
                    Integer.parseInt(properties.getProperty("fHeight")));
            label.setText(properties.getProperty("label2"));
            label.setBounds(Integer.parseInt(properties.getProperty("lX")),
                    Integer.parseInt(properties.getProperty("lY")),
                    Integer.parseInt(properties.getProperty("lWidth")),
                    Integer.parseInt(properties.getProperty("lHeight")));
            addButton.setText(properties.getProperty("add"));
            addButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            addButton.setBounds(Integer.parseInt(properties.getProperty("aX")),
                    Integer.parseInt(properties.getProperty("aY")),
                    Integer.parseInt(properties.getProperty("aWidth")),
                    Integer.parseInt(properties.getProperty("aHeight")));
            scrollPane.setBounds(Integer.parseInt(properties.getProperty("scX")),
                    Integer.parseInt(properties.getProperty("scY")),
                    Integer.parseInt(properties.getProperty("scWidth")),
                    Integer.parseInt(properties.getProperty("scHeight")));
        }catch (Exception e){
            e.printStackTrace();
        }
        for (Groups group : groups){
            JButton name = new JButton();
            JButton leftGroup = new JButton();
            try {
                FileReader reader = new FileReader("resources/configuration/chats.properties");
                Properties properties = new Properties();
                properties.load(reader);
                name.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));
                name.setText(group.getName());
                leftGroup.setText(properties.getProperty("left"));
                leftGroup.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));

            }catch (Exception e){
                e.printStackTrace();
            }
            panel.add(name);
            panel.add(leftGroup);
            name.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GroupConversationView groupConversationView = new GroupConversationView();
                    SendGroupChatViewEvent sendGroupChatView = new SendGroupChatViewEvent(groupConversationView,group,group.getUsernames());
                    messageListener.goToGroupChat(sendGroupChatView);
                    //groupConversationView.groupChat(MainController.messageAgent.showMessageInGroup(MainController.username,group.getUsernames()),group.getUsernames(),group);
                }
            });
            leftGroup.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    LeftGroupEvent leftGroupEvent = new LeftGroupEvent(group,MainController.username);
                    messageListener.leftFromGroup(leftGroupEvent);
                    //MainController.messageAgent.leftFromGroup(MainController.username,group);
                    groupView(MainController.messageAgent.showGroupsName(MainController.username));
                }
            });

        }
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddGroupNameEvent addGroupNameEvent = new AddGroupNameEvent(groupNameText.getText(),MainController.username);
                messageListener.addGroupName(addGroupNameEvent);
                //MainController.messageAgent.addGroupName(MainController.username,groupNameText.getText());
                groupView(MainController.messageAgent.showGroupsName(MainController.username));

            }
        });

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        MainController.panel.add(groupNameText);
        MainController.panel.add(addButton);
        MainController.panel.add(label);
        MainController.panel.add(scrollPane);
        MainController.panel.revalidate();
        MainController.panel.repaint();

    }
}

package client.graphic.messaging;

import shared.events.messageEvents.AddNameToChatsEvent;
import shared.events.profileEvent.SeeProfileEvent;
import shared.events.messageEvents.SendConversationViewEvent;
import client.graphic.profile.ShowProfileView;
import client.graphic.interfaces.BackToMessagingButton;
import client.graphic.interfaces.CloseButton;
import client.graphic.interfaces.MenuButton;
import client.graphic.interfaces.ScrollPaneFromBottom;
import client.listener.MessageListener;
import server.controller.FileImage;
import server.model.messaging.Chat;
import client.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Properties;

import static client.graphic.photo.Photo.scaleFileImage;

public class ChatsView implements CloseButton, MenuButton, BackToMessagingButton, ScrollPaneFromBottom {
    public void chats(LinkedList<Chat> chats){
        MessageListener messageListener = new MessageListener();
        MainController.panel.removeAll();
        menuButton();
        closeButton();
        backToMessaging();
        JTextField followingsNameText = new JTextField();
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
            followingsNameText.setBackground(new Color(Integer.parseInt(properties.getProperty("red")),
                    Integer.parseInt(properties.getProperty("green")),
                    Integer.parseInt(properties.getProperty("blue"))));
            followingsNameText.setBounds(Integer.parseInt(properties.getProperty("fX")),
                    Integer.parseInt(properties.getProperty("fY")),
                    Integer.parseInt(properties.getProperty("fWidth")),
                    Integer.parseInt(properties.getProperty("fHeight")));
            label.setText(properties.getProperty("label"));
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
        for (Chat chat : chats){
            JLabel photo = new JLabel();
            JButton nameButton = new JButton();
            JLabel unread = new JLabel();
            JButton seeProfile = new JButton();
            try {
                FileReader reader = new FileReader("resources/configuration/chats.properties");
                Properties properties = new Properties();
                properties.load(reader);
                nameButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));

                if (chat.user2.equals(MainController.username)) {
                    nameButton.setText(chat.user1);
                    photo.setIcon(scaleFileImage(FileImage.loadImage(chat.user1)));
                    unread.setText(properties.getProperty("unread") + String.valueOf(chat.getUnread(chat.user1)));
                }
                else {
                    nameButton.setText(chat.user2);
                    photo.setIcon(scaleFileImage(FileImage.loadImage(chat.user2)));
                    unread.setText(properties.getProperty("unread") + String.valueOf(chat.getUnread(chat.user2)));
                }
                seeProfile.setText(properties.getProperty("profile"));
                seeProfile.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));
            }catch (Exception e){
                e.printStackTrace();
            }
            panel.add(nameButton);
            panel.add(photo);
            panel.add(unread);
            panel.add(seeProfile);
            nameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ConversationView conversationView = new ConversationView();
                    SendConversationViewEvent event = new SendConversationViewEvent(conversationView,MainController.username,nameButton.getText());
                    messageListener.goToConversation(event);
                    //conversationView.messages(MainController.messageAgent.showMessagesInChat(MainController.username, nameButton.getText()),nameButton.getText());
                }
            });

            seeProfile.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ShowProfileView profileView = new ShowProfileView();
                    SeeProfileEvent seeProfileEvent = new SeeProfileEvent(chat.user2,profileView);
                    messageListener.showProfile(seeProfileEvent);
                    //profileView.showProfile(chat.user2);
                }
            });

        }
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNameToChatsEvent addNameToChatsEvent = new AddNameToChatsEvent(MainController.username,followingsNameText.getText());
                messageListener.addNameToChat(addNameToChatsEvent);
                //MainController.messageAgent.addNameToChats(MainController.username,followingsNameText.getText());
                chats(MainController.messageAgent.showNames(MainController.username));
            }
        });


        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        MainController.panel.add(followingsNameText);
        MainController.panel.add(addButton);
        MainController.panel.add(label);
        MainController.panel.add(scrollPane);
        MainController.panel.revalidate();
        MainController.panel.repaint();
    }
}

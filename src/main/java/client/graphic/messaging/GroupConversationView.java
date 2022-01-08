package client.graphic.messaging;


import shared.events.messageEvents.EditGroupMessageEvent;
import shared.events.messageEvents.SendAddGroupMemberEvent;
import shared.events.messageEvents.SendDeleteInGroupEvent;
import shared.events.messageEvents.SendMessageGroupEvent;
import client.graphic.interfaces.BackToGroups;
import client.graphic.interfaces.CloseButton;
import client.graphic.interfaces.MenuButton;
import client.graphic.interfaces.ScrollPaneFromBottom;
import client.listener.MessageListener;
import server.controller.FileImage;
import server.model.Groups;
import server.model.messaging.Message;
import client.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Properties;

import static client.graphic.photo.Photo.scaleFileImage;

public class GroupConversationView implements CloseButton, MenuButton, ScrollPaneFromBottom, BackToGroups {
    public void groupChat(LinkedList<Message> messages, LinkedList<String> members, Groups groups){
        MessageListener messageListener = new MessageListener();
        MainController.panel.removeAll();
        closeButton();
        menuButton();
        backToGroups();
        JLabel label = new JLabel();
        JTextField memberName   = new JTextField();
        JButton addMember = new JButton();
        JTextField messageText = new JTextField();
        JButton sendButton = new JButton();
        JButton sendPhotoButton = new JButton();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollToBottom(scrollPane);
        try {
            FileReader reader = new FileReader("resources/configuration/conversation.properties");
            Properties properties = new Properties();
            properties.load(reader);
            label.setBounds(Integer.parseInt(properties.getProperty("lX")),
                    Integer.parseInt(properties.getProperty("lY")),
                    Integer.parseInt(properties.getProperty("lWidth")),
                    Integer.parseInt(properties.getProperty("lHeight")));
            memberName.setBackground(new Color(Integer.parseInt(properties.getProperty("red")),
                    Integer.parseInt(properties.getProperty("green")),
                    Integer.parseInt(properties.getProperty("blue"))));
            memberName.setBounds(Integer.parseInt(properties.getProperty("fX")),
                    Integer.parseInt(properties.getProperty("fY")),
                    Integer.parseInt(properties.getProperty("fWidth")),
                    Integer.parseInt(properties.getProperty("fHeight")));
            addMember.setText(properties.getProperty("add"));
            addMember.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            addMember.setBounds(Integer.parseInt(properties.getProperty("aX")),
                    Integer.parseInt(properties.getProperty("aY")),
                    Integer.parseInt(properties.getProperty("aWidth")),
                    Integer.parseInt(properties.getProperty("aHeight")));
            messageText.setBackground(new Color(Integer.parseInt(properties.getProperty("red")),
                    Integer.parseInt(properties.getProperty("green")),
                    Integer.parseInt(properties.getProperty("blue"))));
            messageText.setBounds(Integer.parseInt(properties.getProperty("mtX")),
                    Integer.parseInt(properties.getProperty("mtY")),
                    Integer.parseInt(properties.getProperty("mtWidth")),
                    Integer.parseInt(properties.getProperty("mtHeight")));
            sendButton.setText(properties.getProperty("send"));
            sendButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            sendButton.setBounds(Integer.parseInt(properties.getProperty("sX")),
                    Integer.parseInt(properties.getProperty("sY")),
                    Integer.parseInt(properties.getProperty("sWidth")),
                    Integer.parseInt(properties.getProperty("sHeight")));
            sendPhotoButton.setText(properties.getProperty("photo"));
            sendPhotoButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            sendPhotoButton.setBounds(Integer.parseInt(properties.getProperty("pX")),
                    Integer.parseInt(properties.getProperty("pY")),
                    Integer.parseInt(properties.getProperty("pWidth")),
                    Integer.parseInt(properties.getProperty("pHeight")));
            scrollPane.setBounds(Integer.parseInt(properties.getProperty("scX")),
                    Integer.parseInt(properties.getProperty("scY")),
                    Integer.parseInt(properties.getProperty("scWidth")),
                    Integer.parseInt(properties.getProperty("scHeight")));
        }catch (Exception e){
            e.printStackTrace();
        }
        for (Message message : messages){
            JLabel photo = new JLabel();
            photo.setIcon(scaleFileImage(FileImage.loadImage(message.getSender())));
            JLabel messageTextLabel = new JLabel(), labelPhoto = new JLabel();
            File file = FileImage.loadMessagePhoto(message.getSender(),message.getId());
            if (file != null){
                labelPhoto.setIcon(scaleFileImage(file));
            }
            JButton edit = new JButton();
            JButton delete = new JButton();
            try {
                FileReader reader = new FileReader("resources/configuration/conversation.properties");
                Properties properties = new Properties();
                properties.load(reader);
                messageTextLabel.setFont(new Font(properties.getProperty("fontName"),Font.PLAIN,
                        Integer.parseInt(properties.getProperty("fontSize"))));
                messageTextLabel.setText(message.getSender() + ":" + message.showM(MainController.username));
                edit.setText(properties.getProperty("edit"));
                edit.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));
                delete.setText(properties.getProperty("delete"));
                delete.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));
            }catch (Exception e){
                e.printStackTrace();
            }
            panel.add(photo);
            panel.add(labelPhoto);
            panel.add(messageTextLabel);
            panel.add(edit);
            panel.add(delete);

            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SendDeleteInGroupEvent deleteInGroupEvent = new SendDeleteInGroupEvent(MainController.username,message,members);
                    messageListener.deleteInGroup(deleteInGroupEvent);
                    //MainController.messageAgent.deleteInGroup(MainController.username,members,message);
                    groupChat(MainController.messageAgent.showMessageInGroup(MainController.username,members),members,groups);

                }
            });
            edit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    EditGroupMessageEvent editGroupMessageEvent = new EditGroupMessageEvent(MainController.username,messageText.getText(),message,members);
                    messageListener.editInGroup(editGroupMessageEvent);
                    //MainController.messageAgent.editInGroup(MainController.username,members,message,messageText.getText());
                    groupChat(MainController.messageAgent.showMessageInGroup(MainController.username,members),members,groups);

                }
            });
        }
        final File f[] = {null};
        sendPhotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                f[0] = chooser.getSelectedFile();
            }

        });
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendMessageGroupEvent sendMessageGroupEvent = new SendMessageGroupEvent(MainController.username,messageText.getText(),members,f[0]);
                messageListener.addMessageToGroup(sendMessageGroupEvent);
                //MainController.messageAgent.addMessageToGroup(MainController.username,messageText.getText(),members,f[0]);
                groupChat(MainController.messageAgent.showMessageInGroup(MainController.username,members),members,groups);
            }
        });
        addMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendAddGroupMemberEvent sendAddGroupMemberEvent = new SendAddGroupMemberEvent(MainController.username,memberName.getText(),groups);
                messageListener.addMemberToGroup(sendAddGroupMemberEvent);
                //MainController.messageAgent.addMemberToGroup(MainController.username,memberName.getText(),groups);
                groupChat(MainController.messageAgent.showMessageInGroup(MainController.username,members),members,groups);

            }
        });



        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        MainController.panel.add(scrollPane);
        MainController.panel.add(messageText);
        MainController.panel.add(sendButton);
        MainController.panel.add(sendPhotoButton);
        MainController.panel.add(label);
        MainController.panel.add(memberName);
        MainController.panel.add(addMember);
        MainController.panel.setBackground(Color.lightGray);
        MainController.panel.revalidate();
        MainController.panel.repaint();

    }

}

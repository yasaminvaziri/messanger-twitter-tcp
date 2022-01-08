package client.graphic.messaging;

import shared.events.messageEvents.DeleteChatMessageEvent;
import shared.events.messageEvents.EditInChatEvent;
import shared.events.messageEvents.SendChatMessageEvent;
import client.graphic.interfaces.*;
import client.listener.MessageListener;
import server.controller.FileImage;
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

public class ConversationView implements CloseButton, MenuButton, ScrollPaneFromBottom, BackToChats {
    public void messages(LinkedList<Message> messages, String target){
        MessageListener messageListener = new MessageListener();
        MainController.panel.removeAll();
        closeButton();
        menuButton();
        backToChats();
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
                    DeleteChatMessageEvent deleteChatMessageEvent = new DeleteChatMessageEvent(target,MainController.username,message);
                    messageListener.deleteInChat(deleteChatMessageEvent);
                    //MainController.messageAgent.deleteMessage(MainController.username,target,message);
                    messages(MainController.messageAgent.showMessagesInChat(MainController.username, target),target);
                }
            });
            edit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    EditInChatEvent editInChatEvent = new EditInChatEvent(MainController.username,target,messageText.getText(),message);
                    messageListener.editInChat(editInChatEvent);
                    //MainController.messageAgent.editMessage(MainController.username,target,message,messageText.getText());
                    messages(MainController.messageAgent.showMessagesInChat(MainController.username, target),target);
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
                SendChatMessageEvent event = new SendChatMessageEvent(MainController.username,target,messageText.getText(),f[0]);
                messageListener.addMessageInChat(event);
                //MainController.messageAgent.addMessage(MainController.username,messageText.getText(),target,f[0]);
                messages(MainController.messageAgent.showMessagesInChat(MainController.username, target),target);
            }
        });




        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        MainController.panel.add(scrollPane);
        MainController.panel.add(messageText);
        MainController.panel.add(sendButton);
        MainController.panel.add(sendPhotoButton);
        MainController.panel.setBackground(Color.lightGray);
        MainController.panel.revalidate();
        MainController.panel.repaint();


    }

}

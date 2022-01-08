package client.graphic.messaging;

import shared.events.messageEvents.AddToSavedEvent;
import shared.events.messageEvents.DeleteSavedMessageEvent;
import shared.events.messageEvents.EditSavedMessageEvent;
import client.graphic.interfaces.BackToMessagingButton;
import client.graphic.interfaces.CloseButton;
import client.graphic.interfaces.MenuButton;
import client.graphic.interfaces.ScrollPaneFromBottom;
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

public class SavedMessagesView implements BackToMessagingButton, MenuButton, CloseButton, ScrollPaneFromBottom {
    public void savedMessages(LinkedList<Message> messages){
        MessageListener messageListener = new MessageListener();
        MainController.panel.removeAll();
        menuButton();
        backToMessaging();
        closeButton();
        JTextField messageText = new JTextField();
        JButton sendPhoto = new JButton();
        JButton sendButton = new JButton();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollToBottom(scrollPane);


        try {
            FileReader reader = new FileReader("resources/configuration/savedMessages.properties");
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
            sendButton.setBounds(Integer.parseInt(properties.getProperty("sendX")),
                    Integer.parseInt(properties.getProperty("sendY")),
                    Integer.parseInt(properties.getProperty("sendWidth")),
                    Integer.parseInt(properties.getProperty("sendHeight")));
            scrollPane.setBounds(Integer.parseInt(properties.getProperty("scX")),
                    Integer.parseInt(properties.getProperty("scY")),
                    Integer.parseInt(properties.getProperty("scWidth")),
                    Integer.parseInt(properties.getProperty("scHeight")));
            sendPhoto.setText(properties.getProperty("photo"));
            sendPhoto.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            sendPhoto.setBounds(Integer.parseInt(properties.getProperty("pX")),
                    Integer.parseInt(properties.getProperty("pY")),
                    Integer.parseInt(properties.getProperty("pWidth")),
                    Integer.parseInt(properties.getProperty("pHeight")));
        }catch (Exception e){
            e.printStackTrace();
        }

        for (Message message : messages){
            JLabel photo = new JLabel();
            photo.setIcon(scaleFileImage(FileImage.loadImage(message.getSender())));
            JLabel labelPhoto = new JLabel();
            JLabel label = new JLabel();
            File file = FileImage.loadMessagePhoto(message.getSender(),message.getId());
            if (file != null){
                labelPhoto.setIcon(scaleFileImage(file));
            }
            JButton edit = new JButton();
            JButton delete = new JButton();
            try {
                FileReader reader = new FileReader("resources/configuration/savedMessages.properties");
                Properties properties = new Properties();
                properties.load(reader);
                label.setFont(new Font(properties.getProperty("fontName"),Font.PLAIN
                        ,Integer.parseInt(properties.getProperty("fontSize"))));
                label.setText(MainController.username+ ":" + message.showM(MainController.username));
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
            panel.add(label);
            panel.add(edit);
            panel.add(delete);

            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DeleteSavedMessageEvent deleteSavedMessageEvent = new DeleteSavedMessageEvent(message,MainController.username);
                    messageListener.deleteSavedMessage(deleteSavedMessageEvent);
                  //MainController.messageAgent.deleteSavedMessage(MainController.username,message);
                  savedMessages(MainController.messageAgent.showSavedMessages(MainController.username));
                }
            });
            edit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    EditSavedMessageEvent editSavedMessageEvent = new EditSavedMessageEvent(messageText.getText(),message);
                    messageListener.editSavedMessage(editSavedMessageEvent);
                    //MainController.messageAgent.editSavedMessage(message,messageText.getText());
                    label.setText(MainController.username + ":" + messageText.getText());
                }
            });


        }
        final File f[] = {null};
        sendPhoto.addActionListener(new ActionListener() {
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
                AddToSavedEvent addToSavedEvent = new AddToSavedEvent(messageText.getText(),MainController.username,f[0]);
                messageListener.addMessageToSaved(addToSavedEvent);
                //MainController.messageAgent.addMessageToSaved(MainController.username,messageText.getText());
                savedMessages(MainController.messageAgent.showSavedMessages(MainController.username));
            }
        });



        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        MainController.panel.add(scrollPane);
        MainController.panel.add(messageText);
        MainController.panel.add(sendButton);
        MainController.panel.add(sendPhoto);
        MainController.panel.setBackground(Color.lightGray);
        MainController.panel.revalidate();
        MainController.panel.repaint();

    }

}

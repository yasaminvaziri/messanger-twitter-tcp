package client.graphic.friendLists;

import client.graphic.interfaces.BackToFriendsButton;
import client.graphic.interfaces.CloseButton;
import client.graphic.interfaces.MenuButton;
import client.graphic.interfaces.ScrollPaneFromBottom;
import client.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Properties;

public class BlockList implements BackToFriendsButton, MenuButton, CloseButton, ScrollPaneFromBottom {
    public void blockList(LinkedList<String> blockList){
        MainController.panel.removeAll();
        backToFriends();
        menuButton();
        closeButton();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollToBottom(scrollPane);
        try {
            FileReader reader = new FileReader("resources/configuration/friends.properties");
            Properties properties = new Properties();
            properties.load(reader);
            scrollPane.setBounds(Integer.parseInt(properties.getProperty("scrollPaneX")),
                    Integer.parseInt(properties.getProperty("scrollPaneY")),
                    Integer.parseInt(properties.getProperty("scrollPaneWidth")),
                    Integer.parseInt(properties.getProperty("scrollPaneHeight")));
        }catch (Exception e){
            e.printStackTrace();
        }
        for (String names : blockList){
            JLabel nameLabel = new JLabel();
            try {
                FileReader reader = new FileReader("resources/configuration/friends.properties");
                Properties properties = new Properties();
                properties.load(reader);
                nameLabel.setFont(new Font(properties.getProperty("fontName"),Font.PLAIN,
                        Integer.parseInt(properties.getProperty("fontSize"))));
                nameLabel.setText(names);
            }catch (Exception e){
                e.printStackTrace();
            }
            panel.add(nameLabel);
        }



        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        MainController.panel.add(scrollPane);
        MainController.panel.revalidate();
        MainController.panel.repaint();

    }

}

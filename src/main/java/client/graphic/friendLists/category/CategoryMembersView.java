package client.graphic.friendLists.category;

import shared.events.backsEvent.SendBackToCategoryEvent;
import shared.events.categoryEvents.SendAddNameEvent;
import shared.events.categoryEvents.SendRemoveNameEvent;
import client.graphic.interfaces.CloseButton;
import client.graphic.interfaces.MenuButton;
import client.graphic.interfaces.ScrollPaneFromBottom;
import client.listener.CategoryListener;
import server.model.Group;
import client.controller.MainController;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Properties;

public class CategoryMembersView implements MenuButton, CloseButton, ScrollPaneFromBottom {
    public void categoryMembers(LinkedList<String> names, Group group){
        CategoryListener categoryListener = new CategoryListener();
        MainController.panel.removeAll();
        menuButton();
        closeButton();
        JPanel panel = new JPanel();
        JButton back = new JButton();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollToBottom(scrollPane);
        JLabel label = new JLabel();
        JTextField catName = new JTextField();
        JButton addButton = new JButton();


        try {
            FileReader reader = new FileReader("resources/configuration/categoryView.properties");
            Properties properties = new Properties();
            properties.load(reader);
            back.setText(properties.getProperty("back"));
            back.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            back.setBounds(Integer.parseInt(properties.getProperty("backX")),
                    Integer.parseInt(properties.getProperty("backY")),
                    Integer.parseInt(properties.getProperty("backWidth")),
                    Integer.parseInt(properties.getProperty("backHeight")));
            scrollPane.setBounds(Integer.parseInt(properties.getProperty("scrollPaneX")),
                    Integer.parseInt(properties.getProperty("scrollPaneY")),
                    Integer.parseInt(properties.getProperty("scrollPaneWidth")),
                    Integer.parseInt(properties.getProperty("scrollPaneHeight")));
            label.setText(properties.getProperty("label2"));
            label.setBounds(Integer.parseInt(properties.getProperty("label2X")),
                    Integer.parseInt(properties.getProperty("label2Y")),
                    Integer.parseInt(properties.getProperty("label2Width")),
                    Integer.parseInt(properties.getProperty("label2Height")));
            catName.setBounds(Integer.parseInt(properties.getProperty("catNameX")),
                    Integer.parseInt(properties.getProperty("catNameY")),
                    Integer.parseInt(properties.getProperty("catNameWidth")),
                    Integer.parseInt(properties.getProperty("catNameHeight")));
            catName.setBackground(new Color(Integer.parseInt(properties.getProperty("red")),
                    Integer.parseInt(properties.getProperty("green")),
                    Integer.parseInt(properties.getProperty("blue"))));
            addButton.setText(properties.getProperty("addButton"));
            addButton.setBounds(Integer.parseInt(properties.getProperty("addButtonX")),
                    Integer.parseInt(properties.getProperty("addButtonY")),
                    Integer.parseInt(properties.getProperty("addButtonWidth")),
                    Integer.parseInt(properties.getProperty("addButtonHeight")));
            addButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
        }catch (Exception e){
            e.printStackTrace();
        }
        for (String name : names ){
            JLabel usernameLabel = new JLabel();
            JButton removeUsername = new JButton();
            try {
                FileReader reader = new FileReader("resources/configuration/categoryView.properties");
                Properties properties = new Properties();
                properties.load(reader);
                usernameLabel.setFont(new Font(properties.getProperty("fontName"),Font.PLAIN,
                        Integer.parseInt(properties.getProperty("fontSize"))));
                usernameLabel.setText(name);
                removeUsername.setText(properties.getProperty("removeUsername"));
                removeUsername.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));
            }catch (Exception e){
                e.printStackTrace();
            }
            panel.add(usernameLabel);
            panel.add(removeUsername);

            removeUsername.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                  SendRemoveNameEvent sendRemoveNameEvent = new SendRemoveNameEvent(MainController.username,name,group);
                  categoryListener.removeUsername(sendRemoveNameEvent);
                 //MainController.categoryAgent.deleteName(MainController.username,name,group);
                  categoryMembers(MainController.categoryAgent.showMembers(MainController.username,group),group);
                }
            });
        }
            addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendAddNameEvent sendAddNameEvent = new SendAddNameEvent(MainController.username,catName.getText(),group);
                categoryListener.addMember(sendAddNameEvent);
              //MainController.categoryAgent.addToGroup(MainController.username,catName.getText(),group);
              categoryMembers(MainController.categoryAgent.showMembers(MainController.username,group),group);
            }
            });

           back.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   CategoryView categoryView = new CategoryView();
                   SendBackToCategoryEvent sendBackToCategoryEvent = new SendBackToCategoryEvent(categoryView,MainController.username);
                   categoryListener.backToCategory(sendBackToCategoryEvent);
                   //categoryView.categoryPage(MainController.categoryAgent.showGroups(MainController.username));
               }
           });


        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        MainController.panel.add(back);
        MainController.panel.add(addButton);
        MainController.panel.add(catName);
        MainController.panel.add(label);
        MainController.panel.add(scrollPane);
        MainController.panel.revalidate();
        MainController.panel.repaint();




    }
}

package client.graphic.friendLists.category;

import shared.events.backsEvent.SendBackToFollowingsEvent;
import shared.events.categoryEvents.SendAddCategoryEvent;
import shared.events.categoryEvents.SendCategoryMembersEvent;
import shared.events.categoryEvents.SendCategoryMessageEvent;
import shared.events.categoryEvents.SendDeleteCategoryEvent;
import client.graphic.friendLists.Followings;
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

public class CategoryView implements MenuButton, CloseButton, ScrollPaneFromBottom {
    public void categoryPage(LinkedList<Group> categories){
        CategoryListener categoryListener = new CategoryListener();
        MainController.panel.removeAll();
        menuButton();
        closeButton();
        JPanel panel = new JPanel();
        JButton back = new JButton();
        JLabel label = new JLabel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollToBottom(scrollPane);
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
            label.setText(properties.getProperty("label"));
            label.setBounds(Integer.parseInt(properties.getProperty("labelX")),
                    Integer.parseInt(properties.getProperty("labelY")),
                    Integer.parseInt(properties.getProperty("labelWidth")),
                    Integer.parseInt(properties.getProperty("labelHeight")));
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
        for (Group name : categories ){
            JButton categoryName = new JButton(name.getName());
            categoryName.setBackground(Color.cyan);
            JButton deleteCategory = new JButton();
            JButton sendMessage = new JButton();
            try {
                FileReader reader = new FileReader("resources/configuration/categoryView.properties");
                Properties properties = new Properties();
                properties.load(reader);
                deleteCategory.setText(properties.getProperty("deleteCategory"));
                deleteCategory.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));
                sendMessage.setText(properties.getProperty("send"));
                sendMessage.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));

            }catch (Exception e){
                e.printStackTrace();
            }
            panel.add(categoryName);
            panel.add(deleteCategory);
            panel.add(sendMessage);

            deleteCategory.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   SendDeleteCategoryEvent sendDeleteCategoryEvent = new SendDeleteCategoryEvent(MainController.username,name);
                   categoryListener.deleteCategory(sendDeleteCategoryEvent);
                   //MainController.categoryAgent.deleteGroup(MainController.username,name);
                   categoryPage(MainController.categoryAgent.showGroups(MainController.username));
               }
           });
            categoryName.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CategoryMembersView categoryMembers = new CategoryMembersView();
                    SendCategoryMembersEvent sendCategoryMembersEvent = new SendCategoryMembersEvent(categoryMembers,name.getUsernames(),name);
                    categoryListener.showMembers(sendCategoryMembersEvent);
                    //categoryMembers.categoryMembers(name.getUsernames(),name);
                }
            });
            sendMessage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MessageToCategoryView messageToCategoryView = new MessageToCategoryView();
                    SendCategoryMessageEvent sendCategoryMessageEvent = new SendCategoryMessageEvent(messageToCategoryView,name.getUsernames(),name);
                    categoryListener.showMessageInCategory(sendCategoryMessageEvent);
                   // messageToCategoryView.categoryChat(MainController.categoryAgent.showMessageInCategory(MainController.username,name.getUsernames()),name.getUsernames(),name);
                }
            });


        }
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendAddCategoryEvent sendAddCategoryEvent = new SendAddCategoryEvent(MainController.username,catName.getText());
                categoryListener.addCategory(sendAddCategoryEvent);
                //MainController.categoryAgent.addCategory(MainController.username,catName.getText());
                categoryPage(MainController.categoryAgent.showGroups(MainController.username));
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Followings followings = new Followings();
                SendBackToFollowingsEvent sendBackToFollowingsEvent = new SendBackToFollowingsEvent(followings);
                categoryListener.backToFollowings(sendBackToFollowingsEvent);
                //followings.followings(MainController.logicAgent.showFollowings(MainController.username));
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

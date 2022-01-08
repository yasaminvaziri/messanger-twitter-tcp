package client.graphic.interfaces;

import shared.events.backsEvent.SendBackToCategoryEvent;
import client.graphic.friendLists.category.CategoryView;
import client.listener.BacksListener;
import client.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.Properties;

public interface BackToCategory {
    default void backToCategory(){
        BacksListener backsListener = new BacksListener();
        JButton backButton = new JButton();
        try {
            FileReader reader = new FileReader("resources/configuration/backs.properties");
            Properties properties = new Properties();
            properties.load(reader);
            backButton.setText(properties.getProperty("back"));
            backButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            backButton.setBounds(Integer.parseInt(properties.getProperty("backX")),
                    Integer.parseInt(properties.getProperty("backY")),
                    Integer.parseInt(properties.getProperty("backWidth")),
                    Integer.parseInt(properties.getProperty("backHeight")));
        }catch (Exception e){
            e.printStackTrace();
        }
        MainController.panel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CategoryView categoryView = new CategoryView();
                SendBackToCategoryEvent sendBackToCategoryEvent = new SendBackToCategoryEvent(categoryView,MainController.username);
                backsListener.backToCategory(sendBackToCategoryEvent);
               // categoryView.categoryPage(MainController.categoryAgent.showGroups(MainController.username));
            }
        });
    }
}

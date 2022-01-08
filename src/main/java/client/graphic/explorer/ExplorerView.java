package client.graphic.explorer;

import shared.events.profileEvent.SeeProfileEvent;
import client.graphic.interfaces.BackToMenuButton;
import client.graphic.interfaces.CloseButton;
import client.graphic.interfaces.MenuButton;
import client.graphic.profile.ShowProfileView;
import client.listener.ExplorerListener;
import server.model.Tweet;
import client.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Properties;

public class ExplorerView implements MenuButton, CloseButton, BackToMenuButton {
    JTextField forwardTo = new JTextField();
    public void explorerPage(LinkedList<Tweet> tweets){
        ExplorerListener explorerListener = new ExplorerListener();
        MainController.panel.removeAll();
        menuButton();
        closeButton();
        backButton();
        JTextField searchField = new JTextField();
        JLabel warnLabel = new JLabel();
        JLabel sLabel = new JLabel();
        JButton searchButton = new JButton();
        JLabel ft = new JLabel();
        try {
            FileReader reader = new FileReader("resources/configuration/discovery.properties");
            Properties properties = new Properties();
            properties.load(reader);
            searchField.setBackground(new Color(Integer.parseInt(properties.getProperty("red")),
                    Integer.parseInt(properties.getProperty("green")),
                    Integer.parseInt(properties.getProperty("blue"))));
            searchField.setBounds(Integer.parseInt(properties.getProperty("searchFieldX")),
                    Integer.parseInt(properties.getProperty("searchFieldY")),
                            Integer.parseInt(properties.getProperty("searchFieldWidth")),
                                    Integer.parseInt(properties.getProperty("searchFieldHeight")));
            warnLabel.setBounds(Integer.parseInt(properties.getProperty("warnX")),
                    Integer.parseInt(properties.getProperty("warnY")),
                    Integer.parseInt(properties.getProperty("warnWidth")),
                    Integer.parseInt(properties.getProperty("warnHeight")));
            sLabel.setText(properties.getProperty("sLabel"));
            sLabel.setBounds(Integer.parseInt(properties.getProperty("sLabelX")),
                    Integer.parseInt(properties.getProperty("sLabelY")),
                    Integer.parseInt(properties.getProperty("sLabelWidth")),
                    Integer.parseInt(properties.getProperty("sLabelHeight")));
            searchButton.setText(properties.getProperty("searchButton"));
            searchButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            searchButton.setBounds(Integer.parseInt(properties.getProperty("searchButtonX")),
                    Integer.parseInt(properties.getProperty("searchButtonY")),
                    Integer.parseInt(properties.getProperty("searchButtonWidth")),
                    Integer.parseInt(properties.getProperty("searchButtonHeight")));
            forwardTo.setBounds(Integer.parseInt(properties.getProperty("forwardToX")),
                    Integer.parseInt(properties.getProperty("forwardToY")),
                    Integer.parseInt(properties.getProperty("forwardToWidth")),
                    Integer.parseInt(properties.getProperty("forwardToHeight")));
            ft.setText(properties.getProperty("forward"));
            ft.setBounds(Integer.parseInt(properties.getProperty("ftX")),
                    Integer.parseInt(properties.getProperty("ftY")),
                    Integer.parseInt(properties.getProperty("ftWidth")),
                    Integer.parseInt(properties.getProperty("ftHeight")));
        }catch (Exception e){
            e.printStackTrace();
        }
        DiscoveryView discoveryView = new DiscoveryView();
        discoveryView.discoveryPage(tweets);
        MainController.panel.add(forwardTo);
        MainController.panel.add(ft);
        MainController.panel.setBackground(Color.lightGray);
        MainController.panel.add(warnLabel);
        MainController.panel.add(searchButton);
        MainController.panel.add(sLabel);
        MainController.panel.add(searchField);
        MainController.panel.revalidate();
        MainController.panel.repaint();

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!MainController.tweetAgent.findUser(MainController.username,searchField.getText())){
                  warnLabel.setText("user does not exist");
                }
                else {
                  ShowProfileView showProfileView = new ShowProfileView();
                    SeeProfileEvent seeProfileEvent = new SeeProfileEvent(searchField.getText(),showProfileView);
                    explorerListener.showProfile(seeProfileEvent);
                  //showProfileView.showProfile(searchField.getText());
                }
            }
        });



    }
}

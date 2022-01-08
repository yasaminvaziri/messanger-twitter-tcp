package client.graphic.activity;

import shared.events.activityEvents.SendAcceptEvent;
import shared.events.activityEvents.SendRejectNotifyEvent;
import shared.events.activityEvents.SendRejectSilentEvent;
import client.graphic.interfaces.BackToActivityButton;
import client.graphic.interfaces.CloseButton;
import client.graphic.interfaces.MenuButton;
import client.graphic.interfaces.ScrollPaneFromBottom;
import client.listener.ActivityListener;
import client.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Properties;

public class RequestsView implements MenuButton, CloseButton, BackToActivityButton, ScrollPaneFromBottom {
    public void request(LinkedList<String> requests){
        ActivityListener activityListener = new ActivityListener();
        MainController.panel.removeAll();
        menuButton();
        closeButton();
        backToActivity();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollToBottom(scrollPane);
        JLabel warn = new JLabel();
        try {
            FileReader reader = new FileReader("resources/configuration/requestsView.properties");
            Properties properties = new Properties();
            properties.load(reader);
            scrollPane.setBounds(Integer.parseInt(properties.getProperty("scrollPaneX")),
                    Integer.parseInt(properties.getProperty("scrollPaneY")),
                    Integer.parseInt(properties.getProperty("scrollPaneWidth")),
                    Integer.parseInt(properties.getProperty("scrollPaneHeight")));
            warn.setBounds(Integer.parseInt(properties.getProperty("warnX")),
                    Integer.parseInt(properties.getProperty("warnY")),
                    Integer.parseInt(properties.getProperty("warnWidth")),
                    Integer.parseInt(properties.getProperty("warnHeight")));
        }catch (Exception e){
            e.printStackTrace();
        }
        for (String name : requests){
            JButton accept = new JButton();
            JButton reject_notify = new JButton();
            JButton reject = new JButton();
            JLabel nameLabel = new JLabel();
            try {
                FileReader reader = new FileReader("resources/configuration/requestsView.properties");
                Properties properties = new Properties();
                properties.load(reader);
                accept.setText(properties.getProperty("accept"));
                accept.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),Integer.parseInt(properties.getProperty("b"))));
                reject_notify.setText(properties.getProperty("reject_notify"));
                reject_notify.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),Integer.parseInt(properties.getProperty("b"))));
                reject.setText(properties.getProperty("reject"));
                reject.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),Integer.parseInt(properties.getProperty("b"))));
                nameLabel.setFont(new Font(properties.getProperty("fontName"),Font.PLAIN,
                        Integer.parseInt(properties.getProperty("fontSize"))));
                nameLabel.setText(name + " " + properties.getProperty("nameLabelText"));

            } catch (Exception e) {
                e.printStackTrace();
            }
            panel.add(nameLabel);
            panel.add(accept);
            panel.add(reject_notify);
            panel.add(reject);
            accept.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SendAcceptEvent sendAcceptEvent = new SendAcceptEvent(MainController.username,name,warn.getText());
                    activityListener.accept(sendAcceptEvent);
                    warn.setText(MainController.logicAgent.accept(sendAcceptEvent));
                    request(MainController.logicAgent.showRequests(MainController.username));
                }
            });
            reject.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SendRejectSilentEvent sendRejectSilentEvent = new SendRejectSilentEvent(MainController.username,name,warn.getText());
                    activityListener.rejectSilently(sendRejectSilentEvent);
                    warn.setText(MainController.logicAgent.rejectSilently(sendRejectSilentEvent));
                    request(MainController.logicAgent.showRequests(MainController.username));
                }
            });
            reject_notify.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SendRejectNotifyEvent sendRejectNotifyEvent = new SendRejectNotifyEvent(MainController.username,name,warn.getText());
                    activityListener.reject_notify(sendRejectNotifyEvent);
                    warn.setText(MainController.logicAgent.reject_notify(sendRejectNotifyEvent));
                    //warn.setText(MainController.logicAgent.reject_notify(MainController.username,name,warn.getText()));
                    request(MainController.logicAgent.showRequests(MainController.username));
                }
            });


        }
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        MainController.panel.add(scrollPane);
        MainController.panel.add(warn);
        MainController.panel.revalidate();
        MainController.panel.repaint();
    }
}

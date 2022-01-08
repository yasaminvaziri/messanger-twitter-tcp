package client.graphic.profile;

import client.graphic.interfaces.BackToExplorerButton;
import client.graphic.interfaces.CloseButton;
import client.graphic.interfaces.MenuButton;
import client.graphic.messaging.ChatsView;
import server.controller.FileImage;
import client.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.Properties;

import static client.graphic.photo.Photo.scaleFileImage;

public class ShowProfileView implements MenuButton, CloseButton, BackToExplorerButton {

    public void showProfile(String username){
        MainController.panel.removeAll();
        menuButton();
        closeButton();
        backToExplorer();
        JLabel photoL = new JLabel();
        JLabel userLabel = new JLabel();
        JLabel userL = new JLabel();
        JLabel fullNameLabel = new JLabel();
        JLabel nameL = new JLabel();
        JLabel emailLabel = new JLabel();
        JLabel emailL = new JLabel();
        JLabel phoneLabel = new JLabel();
        JLabel phoneL = new JLabel();
        JLabel birthLabel = new JLabel();
        JLabel birthL = new JLabel();
        JLabel bioLabel = new JLabel();
        JLabel bioL = new JLabel();
        JLabel lastSeenLabel = new JLabel();
        JLabel seenL = new JLabel();
        JButton followButton = new JButton();
        JButton unFollowButton = new JButton();
        JButton blockButton = new JButton();
        JButton unBlockButton = new JButton();
        JButton reportButton = new JButton();
        JButton messageButton = new JButton();
        JButton muteButton = new JButton();
        JButton unMuteButton = new JButton();
        JLabel warn = new JLabel();
        try {
            FileReader reader = new FileReader("resources/configuration/showProfile.properties");
            Properties properties = new Properties();
            properties.load(reader);
            photoL.setText(properties.getProperty("p"));
            photoL.setIcon(scaleFileImage(FileImage.loadImage(username)));
            photoL.setBounds(Integer.parseInt(properties.getProperty("pX")),
                    Integer.parseInt(properties.getProperty("pY")),
                    Integer.parseInt(properties.getProperty("pWidth")),
                    Integer.parseInt(properties.getProperty("pHeight")));
            userLabel.setBounds(Integer.parseInt(properties.getProperty("u1X")),
                    Integer.parseInt(properties.getProperty("u1Y")),
                    Integer.parseInt(properties.getProperty("u1Width")),
                    Integer.parseInt(properties.getProperty("u1Height")));
            userLabel.setText(MainController.infoAgent.showUsername(username));
            userL.setText(properties.getProperty("u2"));
            userL.setBounds(Integer.parseInt(properties.getProperty("u2X")),
                    Integer.parseInt(properties.getProperty("u2Y")),
                    Integer.parseInt(properties.getProperty("u2Width")),
                    Integer.parseInt(properties.getProperty("u2Height")));
            fullNameLabel.setBounds(Integer.parseInt(properties.getProperty("f1X")),
                    Integer.parseInt(properties.getProperty("f1Y")),
                    Integer.parseInt(properties.getProperty("f1Width")),
                    Integer.parseInt(properties.getProperty("f1Height")));
            fullNameLabel.setText(MainController.infoAgent.showName(username));
            nameL.setText(properties.getProperty("f2"));
            nameL.setBounds(Integer.parseInt(properties.getProperty("f2X")),
                    Integer.parseInt(properties.getProperty("f2Y")),
                    Integer.parseInt(properties.getProperty("f2Width")),
                    Integer.parseInt(properties.getProperty("f2Height")));
            emailLabel.setBounds(Integer.parseInt(properties.getProperty("e1X")),
                    Integer.parseInt(properties.getProperty("e1Y")),
                    Integer.parseInt(properties.getProperty("e1Width")),
                    Integer.parseInt(properties.getProperty("e1Height")));
            emailLabel.setText(MainController.infoAgent.showEmail(username));
            emailL.setText(properties.getProperty("e2"));
            emailL.setBounds(Integer.parseInt(properties.getProperty("e2X")),
                    Integer.parseInt(properties.getProperty("e2Y")),
                    Integer.parseInt(properties.getProperty("e2Width")),
                    Integer.parseInt(properties.getProperty("e2Height")));
            phoneLabel.setBounds(Integer.parseInt(properties.getProperty("p1X")),
                    Integer.parseInt(properties.getProperty("p1Y")),
                    Integer.parseInt(properties.getProperty("p1Width")),
                    Integer.parseInt(properties.getProperty("p1Height")));
            phoneLabel.setText(MainController.infoAgent.showPhone(username));
            phoneL.setText(properties.getProperty("p2"));
            phoneL.setBounds(Integer.parseInt(properties.getProperty("p2X")),
                    Integer.parseInt(properties.getProperty("p2Y")),
                    Integer.parseInt(properties.getProperty("p2Width")),
                    Integer.parseInt(properties.getProperty("p2Height")));
            birthLabel.setBounds(Integer.parseInt(properties.getProperty("b1X")),
                    Integer.parseInt(properties.getProperty("b1Y")),
                    Integer.parseInt(properties.getProperty("b1Width")),
                    Integer.parseInt(properties.getProperty("b1Height")));
            birthLabel.setText(MainController.infoAgent.showBirth(username));
            birthL.setText(properties.getProperty("b2"));
            birthL.setBounds(Integer.parseInt(properties.getProperty("b2X")),
                    Integer.parseInt(properties.getProperty("b2Y")),
                    Integer.parseInt(properties.getProperty("b2Width")),
                    Integer.parseInt(properties.getProperty("b2Height")));
            bioLabel.setBounds(Integer.parseInt(properties.getProperty("bi1X")),
                    Integer.parseInt(properties.getProperty("bi1Y")),
                    Integer.parseInt(properties.getProperty("bi1Width")),
                    Integer.parseInt(properties.getProperty("bi1Height")));
            bioLabel.setText(MainController.infoAgent.showBio(username));
            bioL.setText(properties.getProperty("bi2"));
            bioL.setBounds(Integer.parseInt(properties.getProperty("bi2X")),
                    Integer.parseInt(properties.getProperty("bi2Y")),
                    Integer.parseInt(properties.getProperty("bi2Width")),
                    Integer.parseInt(properties.getProperty("bi2Height")));
            lastSeenLabel.setBounds(Integer.parseInt(properties.getProperty("s1X")),
                    Integer.parseInt(properties.getProperty("s1Y")),
                    Integer.parseInt(properties.getProperty("s1Width")),
                    Integer.parseInt(properties.getProperty("s1Height")));
            lastSeenLabel.setText(MainController.infoAgent.showLastSeen(MainController.username,username,lastSeenLabel.getText()));
            seenL.setText(properties.getProperty("s2"));
            seenL.setBounds(Integer.parseInt(properties.getProperty("s2X")),
                    Integer.parseInt(properties.getProperty("s2Y")),
                    Integer.parseInt(properties.getProperty("s2Width")),
                    Integer.parseInt(properties.getProperty("s2Height")));
            followButton.setText(properties.getProperty("f"));
            followButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            followButton.setBounds(Integer.parseInt(properties.getProperty("fX")),
                    Integer.parseInt(properties.getProperty("fY")),
                    Integer.parseInt(properties.getProperty("fWidth")),
                    Integer.parseInt(properties.getProperty("fHeight")));
            unFollowButton.setText(properties.getProperty("uf"));
            unFollowButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            unFollowButton.setBounds(Integer.parseInt(properties.getProperty("ufX")),
                    Integer.parseInt(properties.getProperty("ufY")),
                    Integer.parseInt(properties.getProperty("ufWidth")),
                    Integer.parseInt(properties.getProperty("ufHeight")));
            blockButton.setText(properties.getProperty("bl"));
            blockButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            blockButton.setBounds(Integer.parseInt(properties.getProperty("bX")),
                    Integer.parseInt(properties.getProperty("bY")),
                    Integer.parseInt(properties.getProperty("bWidth")),
                    Integer.parseInt(properties.getProperty("bHeight")));
            unBlockButton.setText(properties.getProperty("ub"));
            unBlockButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            unBlockButton.setBounds(Integer.parseInt(properties.getProperty("ubX")),
                    Integer.parseInt(properties.getProperty("ubY")),
                    Integer.parseInt(properties.getProperty("ubWidth")),
                    Integer.parseInt(properties.getProperty("ubHeight")));
            reportButton.setText(properties.getProperty("re"));
            reportButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            reportButton.setBounds(Integer.parseInt(properties.getProperty("reX")),
                    Integer.parseInt(properties.getProperty("reY")),
                    Integer.parseInt(properties.getProperty("reWidth")),
                    Integer.parseInt(properties.getProperty("reHeight")));
            messageButton.setText(properties.getProperty("m"));
            messageButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            messageButton.setBounds(Integer.parseInt(properties.getProperty("mX")),
                    Integer.parseInt(properties.getProperty("mY")),
                    Integer.parseInt(properties.getProperty("mWidth")),
                    Integer.parseInt(properties.getProperty("mHeight")));
            muteButton.setText(properties.getProperty("mu"));
            muteButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            muteButton.setBounds(Integer.parseInt(properties.getProperty("muX")),
                    Integer.parseInt(properties.getProperty("muY")),
                    Integer.parseInt(properties.getProperty("muWidth")),
                    Integer.parseInt(properties.getProperty("muHeight")));
            unMuteButton.setText(properties.getProperty("umu"));
            unMuteButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            unMuteButton.setBounds(Integer.parseInt(properties.getProperty("umuX")),
                    Integer.parseInt(properties.getProperty("umuY")),
                    Integer.parseInt(properties.getProperty("umuWidth")),
                    Integer.parseInt(properties.getProperty("umuHeight")));
            warn.setBounds(Integer.parseInt(properties.getProperty("wX")),
                    Integer.parseInt(properties.getProperty("wY")),
                    Integer.parseInt(properties.getProperty("wWidth")),
                    Integer.parseInt(properties.getProperty("wHeight")));
        }catch (Exception e){
            e.printStackTrace();
        }

        MainController.panel.add(warn);
        MainController.panel.add(unMuteButton);
        MainController.panel.add(muteButton);
        MainController.panel.add(photoL);
        MainController.panel.add(userL);
        MainController.panel.add(nameL);
        MainController.panel.add(emailL);
        MainController.panel.add(phoneL);
        MainController.panel.add(birthL);
        MainController.panel.add(bioL);
        MainController.panel.add(seenL);
        MainController.panel.add(messageButton);
        MainController.panel.add(reportButton);
        MainController.panel.add(unBlockButton);
        MainController.panel.add(blockButton);
        MainController.panel.add(unFollowButton);
        MainController.panel.add(followButton);
        MainController.panel.add(lastSeenLabel);
        MainController.panel.add(bioLabel);
        MainController.panel.add(birthLabel);
        MainController.panel.add(phoneLabel);
        MainController.panel.add(emailLabel);
        MainController.panel.add(fullNameLabel);
        MainController.panel.add(userLabel);
        MainController.panel.revalidate();
        MainController.panel.repaint();

        followButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                  warn.setText(MainController.logicAgent.follow(MainController.username,username,warn.getText()));
            }
        });

        unFollowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                warn.setText(MainController.logicAgent.unFollow(MainController.username,username,warn.getText()));
            }
        });

        blockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                warn.setText(MainController.logicAgent.block(MainController.username,username,warn.getText()));
            }
        });

        unBlockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                warn.setText(MainController.logicAgent.unBlock(MainController.username,username,warn.getText()));
            }
        });
        muteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                warn.setText(MainController.logicAgent.muteUser(MainController.username,username,warn.getText()));
            }
        });
        unMuteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                warn.setText(MainController.logicAgent.unMuteUser(MainController.username,username,warn.getText()));
            }
        });
        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                warn.setText(MainController.logicAgent.reportUser(MainController.username,username,warn.getText()));
            }
        });
        messageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChatsView chatsView = new ChatsView();
                chatsView.chats(MainController.messageAgent.showNames(MainController.username));
            }
        });

    }
}

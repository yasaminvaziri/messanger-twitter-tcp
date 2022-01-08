package client.controller;


import client.graphic.welcome.LoginView;

import server.controller.*;
import server.app.controller.LoginAgent;
import server.app.controller.MenuAgent;
import server.app.controller.RegisterAgent;
import server.app.controller.TweetAgent;
import server.dataBase.Load;


import javax.swing.*;
import java.io.FileReader;
import java.util.Properties;


public class MainController  {
    public static LogicAgent logicAgent = new LogicAgent();
    public static InfoAgent infoAgent = new InfoAgent();
    public static TweetAgent tweetAgent = new TweetAgent();
    public static MessageAgent messageAgent = new MessageAgent();
    public static CategoryAgent categoryAgent = new CategoryAgent();
    public static LoginAgent loginAgent = new LoginAgent();
    public static RegisterAgent registerAgent  = new RegisterAgent();
    public static MenuAgent menuAgent = new MenuAgent();
    public static BacksAgent backsAgent = new BacksAgent();
    public static JFrame frame = new JFrame();
    public static JPanel panel = new JPanel();
    public static String username;
    public void mainController() {
        Load.loadUser();
        Load.loadTweet();
        Load.loadMessage();
        frame.add(panel);
        try(FileReader reader = new FileReader("resources/configuration/mainFrame.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            frame.setSize(Integer.parseInt(properties.getProperty("width")),Integer.parseInt(properties.getProperty("height")));
            LoginView loginView = new LoginView();
            loginView.loginPage();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            panel.setLayout(null);
            frame.setTitle(properties.getProperty("title"));
            frame.setVisible(true);
        }catch (Exception e){
            e.printStackTrace();
        }




    }




}

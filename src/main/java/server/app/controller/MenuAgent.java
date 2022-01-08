package server.app.controller;

import client.controller.MainController;
import shared.events.welcomeEvents.SendMenuEvent;
import client.graphic.explorer.ExplorerView;
import client.graphic.home.HomePageView;
import client.graphic.messaging.MessagingView;
import client.graphic.setting.SettingView;
import client.graphic.timeLine.TimeLineView;

public class MenuAgent {
    public void pressHomeButton(SendMenuEvent e){
        HomePageView homePageView = e.getHomePageView();
        homePageView.homePage();
    }
    public void pressTimeLineButton(SendMenuEvent e){
        TimeLineView timeLineView = e.getTimeLineView();
        timeLineView.timeLinePage(MainController.tweetAgent.showTweet(MainController.username));
    }
    public void pressExplorerButton(SendMenuEvent e){
        ExplorerView explorerView = e.getExplorerView();
        explorerView.explorerPage(MainController.tweetAgent.discover(MainController.username));
    }
    public void pressSettingButton(SendMenuEvent e){
        SettingView settingView = e.getSettingView();
        settingView.settingPage();
    }
    public void pressMessagingButton(SendMenuEvent e){
        MessagingView messagingView = e.getMessagingView();
        messagingView.messagingView();

    }
}

package client.listener;

import shared.events.backsEvent.*;
import client.controller.MainController;

public class BacksListener {
    public void backToActivity(SendBackToActivityEvent e){
        MainController.backsAgent.backToActivity(e);
    }
    public void backToCategory(SendBackToCategoryEvent e){
        MainController.backsAgent.backToCategory(e);
    }
    public void backToChats(SendBackToChatsEvent e){
        MainController.backsAgent.backToChats(e);
    }
    public void backToExplorer(SendBackToExplorerEvent e){
        MainController.backsAgent.backToExplorer(e);
    }
    public void backToFriends(SendBackToFriendsEvent e){
        MainController.backsAgent.backToFriends(e);
    }
    public void backToGroups(SendBackToGroupsEvent e){
        MainController.backsAgent.backToGroupsView(e);
    }
    public void backToHome(SendBackToHomeEvent e){
        MainController.backsAgent.backToHome(e);
    }
    public void backToMenu(SendBackToMenuEvent e){
        MainController.backsAgent.backToMenu(e);
    }
    public void backToMessaging(SendBackToMessagingEvent e){
        MainController.backsAgent.backToMessaging(e);
    }
    public void backToSettings(SendBackToSettingsEvent e){
        MainController.backsAgent.backToSetting(e);
    }
}

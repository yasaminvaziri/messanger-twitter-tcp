package server.controller;

import client.controller.MainController;
import shared.events.backsEvent.*;
import client.graphic.activity.ActivityView;
import client.graphic.explorer.ExplorerView;
import client.graphic.friendLists.Friends;
import client.graphic.friendLists.category.CategoryView;
import client.graphic.home.HomePageView;
import client.graphic.messaging.ChatsView;
import client.graphic.messaging.GroupsView;
import client.graphic.messaging.MessagingView;
import client.graphic.setting.SettingView;
import client.graphic.welcome.MenuView;

public class BacksAgent {
    public void backToActivity(SendBackToActivityEvent e){
        ActivityView activityView = e.getActivityView();
        activityView.activity(MainController.logicAgent.showFollowers(MainController.username));
    }
    public void backToCategory(SendBackToCategoryEvent e){
        CategoryView categoryView = e.getCategoryView();
        categoryView.categoryPage(MainController.categoryAgent.showGroups(MainController.username));
    }
    public void backToChats(SendBackToChatsEvent e){
        ChatsView chatsView = e.getChatsView();
        chatsView.chats(MainController.messageAgent.showNames(MainController.username));
    }
    public void backToExplorer(SendBackToExplorerEvent e){
        ExplorerView explorerView = e.getExplorerView();
        explorerView.explorerPage(MainController.tweetAgent.discover(MainController.username));
    }
    public void backToFriends(SendBackToFriendsEvent e){
        Friends friends = e.getFriends();
        friends.friendsPage(MainController.logicAgent.showFollowers(MainController.username));
    }
    public void backToGroupsView(SendBackToGroupsEvent e){
        GroupsView groupsView = e.getGroupsView();
        groupsView.groupView(MainController.messageAgent.showGroupsName(MainController.username));
    }
    public void backToHome(SendBackToHomeEvent e){
        HomePageView homePageView = e.getHomePageView();
        homePageView.homePage();
    }
    public void backToMenu(SendBackToMenuEvent e){
        MenuView menuView = e.getMenuView();
        menuView.menuPage();
    }
    public void backToMessaging(SendBackToMessagingEvent e){
        MessagingView messagingView = e.getMessagingView();
        messagingView.messagingView();
    }
    public void backToSetting(SendBackToSettingsEvent e){
        SettingView settingView = e.getSettingView();
        settingView.settingPage();
    }
}

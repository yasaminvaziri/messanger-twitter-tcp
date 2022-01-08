package client.listener;

import shared.events.friendsEvent.*;
import shared.events.homeEvents.SendActivityEvent;
import shared.events.homeEvents.SendFriendsEvent;
import shared.events.homeEvents.SendInfoEvent;
import client.controller.MainController;

public class HomeListener {
    public void goToActivity(SendActivityEvent e){
        MainController.logicAgent.goToActivityView(e);
    }
    public void goToInfo(SendInfoEvent e){
        MainController.logicAgent.goToInfo(e);
    }
    public void goToFriends(SendFriendsEvent e){
        MainController.logicAgent.goToFriends(e);
    }
    public void followers(SendFollowersEvent e){
        MainController.logicAgent.goToFollowersView(e);
    }
    public void blockList(SendBlockListEvent e){
        MainController.logicAgent.goToBlockListView(e);
    }
    public void reportList(SendReportListEvent e){
        MainController.logicAgent.showReportListView(e);
    }
    public void muteList(SendMuteListEvent e){
        MainController.logicAgent.showMuteListView(e);
    }
    public void followings(SendFollowingsEvent e){
        MainController.logicAgent.showFollowingsView(e);
    }
    public void showCategories(SendCategoryEvent e){
        MainController.categoryAgent.goToCategories(e);
    }

}

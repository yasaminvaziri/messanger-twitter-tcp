package client.listener;

import shared.events.settingsEvent.*;
import client.controller.MainController;

public class SettingListener {
    public void logOut(SendLogOutEvent e){
        MainController.logicAgent.logOut(e);
    }
    public void deleteAccount(SendDeleteAccEvent e){
        MainController.infoAgent.deleteYourAccount(e);
    }
    public void inactivate(SendInActivateEvent e){
        MainController.infoAgent.inactivate(e);
    }
    public void pressPrivacy(SendPrivacyEvent e){
        MainController.infoAgent.goToPrivacy(e);
    }
    public void pressLastSeen(SendLastSeenEvent e){
        MainController.infoAgent.goToLastSeen(e);
    }
}

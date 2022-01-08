package client.listener;

import shared.events.welcomeEvents.SendMenuEvent;
import client.controller.MainController;

public class MenuListener {
    public void goToHome(SendMenuEvent e){
        MainController.menuAgent.pressHomeButton(e);
    }
    public void goToTimeLine(SendMenuEvent e){
        MainController.menuAgent.pressTimeLineButton(e);
    }
    public void goToExplorer(SendMenuEvent e){
        MainController.menuAgent.pressExplorerButton(e);
    }
    public void goToSetting(SendMenuEvent e){
        MainController.menuAgent.pressSettingButton(e);
    }
    public void goToMessaging(SendMenuEvent e){
        MainController.menuAgent.pressMessagingButton(e);
    }
}

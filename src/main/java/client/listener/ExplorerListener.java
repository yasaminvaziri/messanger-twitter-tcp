package client.listener;

import shared.events.profileEvent.SeeProfileEvent;
import client.controller.MainController;

public class ExplorerListener {
    public void showProfile(SeeProfileEvent e){
        MainController.tweetAgent.showProfile(e);

    }
}

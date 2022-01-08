package client.listener;

import shared.events.welcomeEvents.SendRegisterEvent;
import client.controller.MainController;

public class RegisterListener {
    public void register(SendRegisterEvent event){
        MainController.registerAgent.register(event);
    }

}

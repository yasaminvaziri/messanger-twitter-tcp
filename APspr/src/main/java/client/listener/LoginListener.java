package client.listener;

import shared.events.loginEvents.LoginPanelRegisterButton;
import shared.events.loginEvents.SendLoginEvent;
import client.controller.MainController;


public class LoginListener {

   public void login(SendLoginEvent e){
       MainController.loginAgent.login(e);
   }

   public void pressRegister(LoginPanelRegisterButton e){
       MainController.loginAgent.pressRegisterButton(e);
   }


}

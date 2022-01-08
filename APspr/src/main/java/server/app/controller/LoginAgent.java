package server.app.controller;

import client.controller.MainController;
import shared.events.loginEvents.LoginPanelRegisterButton;
import shared.events.loginEvents.SendLoginEvent;
import client.graphic.welcome.MenuView;
import client.graphic.welcome.RegisterView;
import server.dataBase.Load;
import server.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.util.Properties;

public class LoginAgent {
    private static final Logger logger = LogManager.getLogger(LoginAgent.class);
    public boolean validateLogin(String username, String password) {
        if (RegisterAgent.validUsername(username) && RegisterAgent.isValidPassword(password)) {

            User user1 = Load.loadUser(username);
            if (user1 != null) {

                if (user1.getUsername().equals(username) &&
                        user1.getPassword().equals(password)) {
                    user1.setActive(true);
                    user1.setOnline(true);
                    logger.info(user1.getUsername() + " " + "logged in");
                    user1.updateLastSeen();
                    return true;
                }
            }else
                return false;

        }
        return false;
    }
    public String login(SendLoginEvent event){
        String username  = event.getUsername();
        String password  =event.getPassword();
        String warn = event.getWarnLabel();
        if (validateLogin(username, password)) {
            try {
                FileReader reader = new FileReader("resources/configuration/login.properties");
                Properties properties = new Properties();
                properties.load(reader);
                warn = properties.getProperty("warn1");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            MainController.username = username;
                   MenuView menuView = new MenuView();
                   menuView.menuPage();
        }
        else
            try {
                FileReader reader = new FileReader("resources/configuration/login.properties");
                Properties properties = new Properties();
                properties.load(reader);
                warn = properties.getProperty("warn2");
            }catch (Exception exception){
                exception.printStackTrace();
            }
            return warn;
    }
    public void pressRegisterButton(LoginPanelRegisterButton l){
        RegisterView registerView = l.getRegisterView();
        registerView.registerPage();

    }


}

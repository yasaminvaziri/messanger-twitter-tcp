package client.controller;

import shared.events.loginEvents.SendLoginEvent;
import client.graphic.welcome.LoginView;
import client.holders.TokenHolder;
import client.stream.StreamUtil;
import shared.GsonUtil;
import shared.networking.AuthRequest;
import shared.networking.Request;
import shared.networking.Response;

import java.io.FileReader;
import java.util.Properties;

import static java.lang.Thread.sleep;

public class LoginController {
    LoginView loginView;
    public LoginController(LoginView loginView){
        this.loginView = loginView;
    }
    public void loginPressed(String username, String password){
        AuthRequest authRequest = new AuthRequest(username,password,null,null,null,null,null);
        try(FileReader reader = new FileReader("src/main/java/client/config/controller.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            StreamUtil.sendRequest(new Request(properties.getProperty("login"), GsonUtil.getGson().toJson(authRequest)));
            Response response = StreamUtil.getResponse();
            if (response.status.equals("accepted")) {
                TokenHolder.token = response.data;
                sendOnlineRequest();
                SendLoginEvent loginEvent  = new SendLoginEvent(username,password);
                loginView.loginListener.login(loginEvent);
                //new MenuView();
            }
            else {
                loginView.warnLabel.setText(response.data);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void sendOnlineRequest() {
        Thread thread = new Thread(()-> {
            try(FileReader reader = new FileReader("src/main/java/client/config/controller.properties")) {
                Properties properties = new Properties();
                properties.load(reader);
                StreamUtil.sendRequest(new Request(properties.getProperty("online"), "", TokenHolder.token));
            }catch (Exception e){
                e.printStackTrace();
            }
            try {
                sleep(5000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            sendOnlineRequest();
        });
        thread.start();

    }

}
